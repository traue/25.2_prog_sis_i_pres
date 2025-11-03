package br.mack.pagamentos.domain.payment;

import br.mack.pagamentos.domain.notification.Notificador;
import br.mack.pagamentos.custumer.Cliente;
import br.mack.pagamentos.domain.common.Entidade;
import br.mack.pagamentos.domain.common.Pagavel;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class PagamentoBase implements Pagavel, Entidade {
    private final UUID id = UUID.randomUUID();
    protected final Cliente cliente;
    protected final BigDecimal valor;
    protected StatusPagamento status = StatusPagamento.PENDENTE;

    protected PagamentoBase(Cliente cliente, BigDecimal valor) {
        if (cliente == null) throw new IllegalArgumentException("cliente null");
        if (valor == null || valor.signum() <= 0) throw new IllegalArgumentException("valor > 0");
        this.cliente = cliente;
        this.valor = valor;
    }

    public abstract boolean processar(); // SUBTIPO/OVERRIDE nas subclasses

    public void enviarRecibo(Notificador notificador) {
        notificador.notificar(cliente, "Pagamento " + status + " no valor de " + valor);
    }

    @Override public UUID getId() { return id; }
    @Override public BigDecimal getValor() { return valor; }
    public StatusPagamento getStatus() { return status; }
    public Cliente getCliente() { return cliente; }
}