package br.mack.pagamentos.domain.payment;

import br.mack.pagamentos.custumer.Cliente;

import java.math.BigDecimal;

public class PagamentoBoleto extends PagamentoBase {
    private final String linhaDigitavel;

    public PagamentoBoleto(Cliente cliente, BigDecimal valor, String linhaDigitavel) {
        super(cliente, valor);
        this.linhaDigitavel = linhaDigitavel;
    }

    @Override
    public boolean processar() {
        boolean aprovado = getValor().doubleValue() <= 1000.0; // regra didática
        status = aprovado ? StatusPagamento.APROVADO : StatusPagamento.FALHOU;
        return aprovado;
    }

    public String getLinhaDigitavel() { return linhaDigitavel; }
    @Override public String toString() { return "Boleto(" + linhaDigitavel + ")"; }
}