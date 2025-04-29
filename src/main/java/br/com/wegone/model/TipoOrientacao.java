package br.com.wegone.model;

import java.util.HashMap;
import java.util.Map;

public class TipoOrientacao {
    
    private String codigo;
    private Map<Idioma, String> nomePorIdioma = new HashMap<>();

    public TipoOrientacao(String codigo) {

        this.codigo = codigo;

    }

    public void setNomePorIdioma(Idioma idioma, String nome) {

        nomePorIdioma.put(idioma, nome); // Adiciona elementos na lista chave-valor, no caso cada elemento é um tipo de orientação (o nome do mesmo)

    }

    public String getNome(Idioma idioma) {

        return nomePorIdioma.getOrDefault(idioma, "[no translation]"); // Caso nenhum resultado, irá retornar [no translation]

    }

    public String getCodigo() {

        return codigo;

    }

}