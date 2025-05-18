package br.com.wegone.model;

import java.util.Objects;

public class Idioma {

    private String codigo;
    private String nome;

    public Idioma() {
        codigo = "nl";
        nome = "sem-idioma";
    }

    public Idioma construtor(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        return this;
    }

    public Idioma setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public Idioma setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void imprimir() {
        System.out.printf("Código Idioma: %s | Nome Idioma: %s%n", codigo, nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idioma idioma = (Idioma) o;
        return codigo.equals(idioma.codigo); // ou outro critério de unicidade
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo); // precisa importar java.util.Objects
    }
}
