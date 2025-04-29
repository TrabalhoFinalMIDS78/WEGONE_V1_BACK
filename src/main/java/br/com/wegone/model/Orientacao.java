package br.com.wegone.model;

import java.util.HashMap;
import java.util.Map;

public class Orientacao {
    
    private String codigo;
    private TipoOrientacao tipo;
    private Map<Idioma, String> titulos = new HashMap<>();
    private Map<TipoOrientacao, String> conteudos = new HashMap<>();

    public Orientacao(String codigo, TipoOrientacao tipo) {

        this.codigo = codigo;
        this.tipo = tipo;

    }

    public void adicionarTitulo(Idioma idioma, String titulo) {
        titulos.put(idioma, titulo);
    }

    public void adicionarConteudo(Idioma idioma, String conteudo) {
        conteudos.put(idioma, conteudo);
    }

    public String getTitulo(Idioma idioma) {
        return titulos.getOrDefault(idioma, "[no title]");
    }

    public String getConteudo(Idioma idioma) {
        return conteudos.getOrDefault(idioma, "[no]");
    }

    public TipoOrientacao getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

}
