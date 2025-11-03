package br.mack.pagamentos.service;

import br.mack.pagamentos.infra.log.Logger;
import br.mack.pagamentos.domain.notification.Notificador;
import br.mack.pagamentos.domain.payment.PagamentoBase;
import br.mack.pagamentos.domain.payment.PagamentoCartaoCredito;

import java.util.List;

public class ProcessadorPagamentos {
    private final Logger logger;
    private final Notificador notificador;

    public ProcessadorPagamentos(Logger logger, Notificador notificador) {
        this.logger = logger;
        this.notificador = notificador;
    }

    // OVERLOAD 1: genérico
    public boolean pagar(PagamentoBase p) {
        logger.log("Processando pagamento genérico: %s, valor=%s",
                p.getClass().getSimpleName(), p.getValor());
        boolean ok = p.processar();                 // OVERRIDE em runtime
        p.enviarRecibo(notificador);
        return ok;
    }

    // OVERLOAD 2: especializado para cartão
    public boolean pagar(PagamentoCartaoCredito p) {
        logger.log("Processando **CARTAO**: %s, valor=%s", p.getNumeroMascarado(), p.getValor());
        boolean ok = p.processar();
        if (!ok) {
            logger.log("Tentando anti-fraude/3DS para %s...", p.getNumeroMascarado());
        }
        p.enviarRecibo(notificador);
        return ok;
    }

    // OVERLOAD 3: lote
    public int pagar(List<? extends PagamentoBase> pagamentos) {
        int aprovados = 0;
        for (PagamentoBase p : pagamentos) {
            if (pagar(p)) aprovados++;
        }
        logger.log("Lote concluído: %d/%d aprovados", aprovados, pagamentos.size());
        return aprovados;
    }

    // OVERLOAD 4: com tentativas
    public boolean pagar(PagamentoBase p, int tentativas) {
        for (int i = 1; i <= tentativas; i++) {
            logger.log("Tentativa %d para %s", i, p);
            if (pagar(p)) return true;
        }
        return false;
    }
}
