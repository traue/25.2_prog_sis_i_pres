package br.mack.pagamentos.domain.payment;

import br.mack.pagamentos.custumer.Cliente;

import java.math.BigDecimal;

public class PagamentoPix extends PagamentoBase {
    private final String chavePix;

    public PagamentoPix(Cliente cliente, BigDecimal valor, String chavePix) {
        super(cliente, valor);
        this.chavePix = chavePix;
    }

    @Override
    public boolean processar() {
        status = StatusPagamento.APROVADO; // simplificação: PIX liquida
        return true;
    }

    public String getChavePix() { return chavePix; }
    @Override public String toString() { return "Pix(" + chavePix + ")"; }
}