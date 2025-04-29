package br.com.wegone.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class IdiomasDisponiveis {
    
    // Atributos

    private List<Idioma> ListaIdiomas = new ArrayList<>();

    // Idiomas suportados no sistema

    public void idiomasDisponiveis() {

        ListaIdiomas.add(new Idioma().construtor("pt", "Português"));
        ListaIdiomas.add(new Idioma().construtor("en", "Inglês"));
        ListaIdiomas.add(new Idioma().construtor("es", "Espanhol"));
        ListaIdiomas.add(new Idioma().construtor("de", "Alemão"));
        ListaIdiomas.add(new Idioma().construtor("zh", "Mandarim"));

    }

    public List<Idioma> getListaIdiomas() {

        return ListaIdiomas;

    }

    public Idioma buscarPorCodigo(String codigo) {
        Optional<Idioma> idioma = ListaIdiomas.stream()
            .filter(i -> i.getCodigo().equals(codigo))
            .findFirst();
        return idioma.orElse(null);
    }

}