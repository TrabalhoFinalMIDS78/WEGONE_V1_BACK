package br.com.wegone.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class IdiomasDisponiveis {

    // Atributos

    private List<Idioma> listaIdiomas = new ArrayList<>();

    // Idiomas suportados no sistema

    public IdiomasDisponiveis() {

        carregarIdiomas();

    }

    public void adicionarIdioma(Idioma idioma) {

        if (idioma != null && !listaIdiomas.contains(idioma)) {

            listaIdiomas.add(idioma);

        }
    }

    // Método auxiliar: adiciona idioma a partir de código e nome
    public void adicionarIdioma(String codigo, String nome) {

        Idioma novo = new Idioma().construtor(codigo, nome);

        adicionarIdioma(novo); // Reutiliza o método acima

    }

    public void carregarIdiomas() {

        listaIdiomas.add(new Idioma().construtor("pt_BR", "Português (Brasil)"));
        listaIdiomas.add(new Idioma().construtor("en_US", "English (United States)"));
        listaIdiomas.add(new Idioma().construtor("es_ES", "Español (España)"));
        listaIdiomas.add(new Idioma().construtor("de_DE", "Deutsch (Deutschland)"));
        listaIdiomas.add(new Idioma().construtor("zh_CN", "中文 (Zhōngwén)"));

    }

    public List<Idioma> getListaIdiomas() {

        return listaIdiomas;

    }

    // IdiomasDisponiveis.java
    public Idioma getIdiomaPadrao() {

        // Retorna o idioma com código "pt_BR" ou o primeiro da lista
        Idioma padrao = buscarPorCodigo("pt_BR");
        if (padrao != null) {
            return padrao;
        } else if (!listaIdiomas.isEmpty()) {
            return listaIdiomas.get(0);
        } else {
            return null;
        }

    }

    public Idioma buscarPorCodigo(String codigo) {
        Optional<Idioma> idioma = listaIdiomas.stream()
                .filter(i -> i.getCodigo().equals(codigo))
                .findFirst();
        return idioma.orElse(null);
    }

}