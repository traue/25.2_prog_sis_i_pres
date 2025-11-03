package br.mack.pagamentos.domain.common;

import java.math.BigDecimal;

public interface Pagavel {
    BigDecimal getValor();
}