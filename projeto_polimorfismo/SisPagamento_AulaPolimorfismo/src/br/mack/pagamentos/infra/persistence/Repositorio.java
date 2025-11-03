package br.mack.pagamentos.infra.persistence;

import br.mack.pagamentos.domain.common.Entidade;

import java.util.*;

public class Repositorio<T extends Entidade> {
    private final Map<java.util.UUID, T> store = new LinkedHashMap<>();

    public void salvar(T t) { store.put(t.getId(), t); }
    public T porId(java.util.UUID id) { return store.get(id); }
    public List<T> listar() { return new ArrayList<>(store.values()); }

    // Generics + PECS
    public static <T> void copiar(List<? super T> destino, List<? extends T> origem) {
        destino.addAll(origem);
    }
}