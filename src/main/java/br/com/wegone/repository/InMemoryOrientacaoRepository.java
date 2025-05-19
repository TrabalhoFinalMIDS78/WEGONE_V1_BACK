/*

package br.com.wegone.repository;

import br.com.wegone.model.Orientacao;
import br.com.wegone.model.TipoOrientacao;
import br.com.wegone.model.Idioma;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryOrientacaoRepository implements OrientacaoRepository {

    // A nossa "base de dados" em memória
    private final List<Orientacao> armazenamento = new ArrayList<>();

    @Override
    public Optional<Orientacao> findByCodigo(String codigo) {
        return armazenamento.stream()
            .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
            .findFirst();
    }

    @Override
    public List<Orientacao> findByTipo(TipoOrientacao tipo) {
        return armazenamento.stream()
            .filter(o -> o.getTipo().equals(tipo))
            .collect(Collectors.toList());
    }

    @Override
    public List<Orientacao> findByTituloContains(String palavraChave) {
        String chaveLower = palavraChave.toLowerCase();
        return armazenamento.stream()
            .filter(o -> o.getTitulos().values().stream()   // percorre todos os títulos
                .anyMatch(t -> t.toLowerCase().contains(chaveLower))
            )
            .collect(Collectors.toList());
    }

    @Override
    public List<Orientacao> findByFilters(
        TipoOrientacao tipo,
        Idioma idioma,
        String palavraChave
    ) {
        String chaveLower = (palavraChave == null ? "" : palavraChave.toLowerCase());
        return armazenamento.stream()
            .filter(o -> tipo == null || o.getTipo().equals(tipo))
            .filter(o -> idioma == null || o.getTitulos().containsKey(idioma))
            .filter(o -> chaveLower.isEmpty()
                || o.getTitulos().get(idioma).toLowerCase().contains(chaveLower)
                || o.getConteudos().get(idioma).toLowerCase().contains(chaveLower)
            )
            .collect(Collectors.toList());
    }

    @Override
    public void save(Orientacao orientacao) {
        // se já existe, substitui
        findByCodigo(orientacao.getCodigo())
          .ifPresentOrElse(
            existing -> {
              armazenamento.remove(existing);
              armazenamento.add(orientacao);
            },
            () -> armazenamento.add(orientacao)
          );
    }

    @Override
    public void delete(Orientacao orientacao) {
        armazenamento.removeIf(o -> o.getCodigo().equalsIgnoreCase(orientacao.getCodigo()));
    }

    @Override
    public List<Orientacao> findAll() {
        return new ArrayList<>(armazenamento);
    }
}
    
*/

