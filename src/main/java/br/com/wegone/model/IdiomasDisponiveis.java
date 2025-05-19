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

        listaIdiomas.add(new Idioma().construtor("pt", "Português"));
        listaIdiomas.add(new Idioma().construtor("en", "Inglês"));
        listaIdiomas.add(new Idioma().construtor("es", "Espanhol"));
        listaIdiomas.add(new Idioma().construtor("de", "Alemão"));
        listaIdiomas.add(new Idioma().construtor("zh", "Mandarim"));

    }

    public List<Idioma> getListaIdiomas() {

        return listaIdiomas;

    }

    public Idioma buscarPorCodigo(String codigo) {
        Optional<Idioma> idioma = listaIdiomas.stream()
            .filter(i -> i.getCodigo().equals(codigo))
            .findFirst();
        return idioma.orElse(null);
    }

}