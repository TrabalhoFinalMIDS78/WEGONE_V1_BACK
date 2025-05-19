package br.com.wegone.service;

import br.com.wegone.model.Idioma;
import br.com.wegone.model.IdiomasDisponiveis;

import java.util.List;

public class IdiomaService {

    private IdiomasDisponiveis idiomasDisponiveis;

    public IdiomaService() {
        this.idiomasDisponiveis = new IdiomasDisponiveis();
    }

    // Adicionar idioma a partir de objeto Idioma
    public void adicionarIdioma(Idioma idioma) {
        idiomasDisponiveis.adicionarIdioma(idioma);
    }

    // Adicionar idioma a partir de código e nome
    public void adicionarIdioma(String codigo, String nome) {
        idiomasDisponiveis.adicionarIdioma(codigo, nome);
    }

    public Idioma buscarPorCodigo(String codigo) {
        return idiomasDisponiveis.buscarPorCodigo(codigo);
    }

    public List<Idioma> getListaIdiomas() {
        return idiomasDisponiveis.getListaIdiomas();
    }

    public void listarIdiomasDisponiveis() {

        System.out.println("\nIdiomas Disponíveis:");

        for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

            System.out.printf("- [%s] %s\n", idioma.getCodigo(), idioma.getNome());
            
        }
    }
}
