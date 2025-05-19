package br.com.wegone.repository;

import br.com.wegone.model.Orientacao;
import br.com.wegone.model.TipoOrientacao;
import br.com.wegone.model.Idioma;

import java.util.List;
import java.util.Optional;

public interface OrientacaoRepository {

    Optional<Orientacao> findByCodigo(String codigo);
    List<Orientacao> findByTipo(TipoOrientacao tipo);
    List<Orientacao> findByTituloContains(String palavraChave);
    List<Orientacao> findByFilters(
        TipoOrientacao tipo,
        Idioma idioma,
        String palavraChave

    );

    void save(Orientacao orientacao);
    void delete(Orientacao orientacao);
    List<Orientacao> findAll();
}
