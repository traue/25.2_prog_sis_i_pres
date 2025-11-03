package br.mack.pagamentos.domain.notification;

import br.mack.pagamentos.custumer.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);                 // base
    void notificar(Cliente cliente, String mensagem, boolean urgente); // OVERLOAD

    default void notificar(String email, String mensagem) {            // overload + default
        System.out.println("[EMAIL] para " + email + " :: " + mensagem);
    }
}
