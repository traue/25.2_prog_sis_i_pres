package br.mack.pagamentos.app;

import br.mack.pagamentos.custumer.Cliente;
import br.mack.pagamentos.infra.log.Logger;
import br.mack.pagamentos.infra.notification.NotificadorEmail;
import br.mack.pagamentos.infra.persistence.Repositorio;
import br.mack.pagamentos.domain.payment.PagamentoBase;
import br.mack.pagamentos.domain.payment.PagamentoBoleto;
import br.mack.pagamentos.domain.payment.PagamentoCartaoCredito;
import br.mack.pagamentos.domain.payment.PagamentoPix;
import br.mack.pagamentos.service.ProcessadorPagamentos;
import br.mack.pagamentos.service.Relatorios;

import java.math.BigDecimal;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Logger logger = new Logger();
        NotificadorEmail notificador = new NotificadorEmail();
        ProcessadorPagamentos proc = new ProcessadorPagamentos(logger, notificador);

        Cliente ana = new Cliente("Ana", "ana@example.com");
        Cliente bob = new Cliente("Bob", "bob@example.com");

        PagamentoCartaoCredito cartaoAna = new PagamentoCartaoCredito(
                ana, new BigDecimal("150.00"), "**** **** **** 4242");
        PagamentoPix pixBob = new PagamentoPix(
                bob, new BigDecimal("59.90"), "bob@pix");
        PagamentoBoleto boletoAna = new PagamentoBoleto(
                ana, new BigDecimal("1200.00"), "23790.12345 12345.678901 12345.678901 1 23456789012345");

        // SUBTIPO: lista de supertipo com objetos heterogêneos
        List<PagamentoBase> carrinho = new ArrayList<>();
        carrinho.add(cartaoAna);
        carrinho.add(pixBob);
        carrinho.add(boletoAna);

        // GENERICS (wildcard ? extends)
        System.out.println("Total do carrinho: " + Relatorios.somarValores(carrinho));

        // OVERLOAD: escolha pelo TIPO ESTÁTICO
        proc.pagar(cartaoAna);     // chama overload especializado (Cartão)
        PagamentoBase refBase = cartaoAna; // upcast
        proc.pagar(refBase);       // chama overload genérico (mesmo objeto, outra assinatura)

        // OVERLOAD: lote
        proc.pagar(carrinho);

        // Repositório genérico
        Repositorio<PagamentoBase> repoPag = new Repositorio<>();
        for (PagamentoBase p : carrinho) repoPag.salvar(p);
        System.out.println("Pagamentos no repositório: " + repoPag.listar().size());

        // PECS: copiar de <? extends PagamentoBase> para <? super PagamentoBase>
        List<Object> destino = new ArrayList<>();
        Repositorio.copiar(destino, carrinho);
        System.out.println("Destino após cópia (PECS): " + destino.size() + " elementos");

        // OVERLOAD do Notificador
        notificador.notificar(ana, "Obrigado pela compra!", true);
        notificador.notificar("suporte@example.com", "Ticket #123 aberto para " + bob.getNome());
    }
}
