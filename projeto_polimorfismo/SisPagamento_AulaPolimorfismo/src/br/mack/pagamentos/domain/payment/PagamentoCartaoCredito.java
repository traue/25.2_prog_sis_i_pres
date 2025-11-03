package br.mack.pagamentos.domain.payment;

import br.mack.pagamentos.custumer.Cliente;

import java.math.BigDecimal;
import java.util.Random;

public class PagamentoCartaoCredito extends PagamentoBase {
    private final String numeroMascarado;
    private static final Random RNG = new Random();

    public PagamentoCartaoCredito(Cliente cliente, BigDecimal valor, String numeroMascarado) {
        super(cliente, valor);
        this.numeroMascarado = numeroMascarado;
    }

    @Override
    public boolean processar() {
        boolean aprovado = RNG.nextDouble() < 0.7; // 70% de chance, simulando gateway
        status = aprovado ? StatusPagamento.APROVADO : StatusPagamento.FALHOU;
        return aprovado;
    }

    public String getNumeroMascarado() { return numeroMascarado; }
    @Override public String toString() { return "CartaoCredito(" + numeroMascarado + ")"; }
}