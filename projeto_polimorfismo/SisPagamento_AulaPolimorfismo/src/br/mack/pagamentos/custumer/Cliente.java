package br.mack.pagamentos.custumer;

import br.mack.pagamentos.domain.common.Entidade;

import java.util.UUID;

public class Cliente implements Entidade {
    private final UUID id;
    private final String nome;
    private final String email;

    public Cliente(String nome, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
    }

    @Override public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}