package br.mack.pagamentos.service;

import br.mack.pagamentos.domain.common.Pagavel;

import java.math.BigDecimal;
import java.util.Collection;

public final class Relatorios {
    private Relatorios() {}

    public static BigDecimal somarValores(Collection<? extends Pagavel> itens) {
        BigDecimal total = BigDecimal.ZERO;
        for (Pagavel p : itens) {
            total = total.add(p.getValor());
        }
        return total;
    }
}