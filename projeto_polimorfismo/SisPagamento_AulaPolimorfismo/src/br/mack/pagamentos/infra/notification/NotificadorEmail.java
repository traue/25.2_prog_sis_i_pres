package br.mack.pagamentos.infra.notification;

import br.mack.pagamentos.domain.notification.Notificador;
import br.mack.pagamentos.custumer.Cliente;

public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("[EMAIL] para " + cliente.getEmail() + " :: " + mensagem);
    }

    @Override
    public void notificar(Cliente cliente, String mensagem, boolean urgente) {
        String prefixo = urgente ? "[URGENTE] " : "";
        notificar(cliente, prefixo + mensagem);
    }
}