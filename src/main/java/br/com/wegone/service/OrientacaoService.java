package br.com.wegone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.repository.*;
import br.com.wegone.model.*;

public class OrientacaoService {

    // Atributos

    private static List<Orientacao> listaOrientacoes = new ArrayList<>();
    private static IdiomasDisponiveis idiomasDisponiveis = new IdiomasDisponiveis();

    // Métodos auxiliares para as Listas de Orientações (BOA PARTE VAI TER QUE MUDAR QUANDO INSERIRMOS O BANCO DE DADOS)
 
    private static Orientacao buscarPorCodigo(String codigo) {
        return listaOrientacoes.stream()
                .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    private static String lerLinha() {

        try {

            return sc.nextLine().trim();

        } catch (Exception e) {

            return "";

        }
    }

    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    // Métodos Validadores para as Orientações

    private static class Validador {

        // Excessões para o Service das Orientações

        private static void validarInputVazio(String texto) {

            if (texto == null || texto.isBlank()) {

                throw new DadosIncompletosException("Informação não pode estar vazia.");

            }

        }

        private static void validarExistenciaOrientacao(Orientacao orientacao) {

            if (orientacao == null) {

                throw new DadosIncompletosException("Orientação não encontrada.");

            }

        }

        // Validações para o código de cadastro
        private static void validarCodigoCadastro(String codigo, List<Orientacao> listaOrientacoes) throws DadosIncompletosException {

            validarInputVazio(codigo);

            if (codigo.length() < 3) {
                throw new DadosIncompletosException("Código deve ter pelo menos 3 caracteres.");
            }

            if (!Character.isDigit(codigo.charAt(0))) {
                throw new DadosIncompletosException("Código deve começar com um número.");
            }

            // Duplicidade

            for (Orientacao orientacao : listaOrientacoes) {
                if (orientacao.getCodigo().equalsIgnoreCase(codigo)) {
                    throw new DadosIncompletosException("Este código já está cadastrado.");
                }
            }

        }

    }

    // Métodos CRUD Service

    // Método para cadastrar uma nova Orientação
    public static void cadastrarOrientacao(
        // Parâmetros para o cadastro de uma nova Orientação
        String codigo,
        TipoOrientacao tipo,
        Map<Idioma, String> titulos,
        Map<Idioma, String> conteudos

    ) throws DadosIncompletosException {

        // Validações

        Validador.validarCodigoCadastro(codigo, listaOrientacoes); // Validar Exceções

        Orientacao orientacao = new Orientacao(codigo, tipo);

        for (Idioma idiomas : titulos.keySet()) {

            String titulo = titulos.get(idiomas);
            String conteudo = conteudos.get(idiomas);

            // Validações

            if (titulo == null || titulo.isBlank()) {
                throw new DadosIncompletosException("Título em " + idiomas.getNome() + " não pode estar vazio.");
            }

            if (conteudo == null || conteudo.isBlank()) {
                throw new DadosIncompletosException("Conteúdo em " + idiomas.getNome() + " não pode estar vazio.");
            }

            // Adiciona os títulos e conteúdos à Orientação

            orientacao.adicionarTitulo(idiomas, titulo);
            orientacao.adicionarConteudo(idiomas, conteudo);

        }

        // Cria a nova Orientação

        // Adiciona à lista de Orientações

        listaOrientacoes.add(orientacao);

    }

    // Método para Editar uma Orientação

    public static void editarOrientacao(
        // Parâmetros para o cadastro de uma nova Orientação
        String codigo,
        TipoOrientacao tipo,
        Map<Idioma, String> novosTitulos,
        Map<Idioma, String> novosConteudos

    ) {

        Validador.validarInputVazio(codigo);

        Orientacao orientacaoEdicao = buscarPorCodigo(codigo);

        Validador.validarExistenciaOrientacao(orientacaoEdicao);

        // Edição dos Títulos e Conteúdos em Cada Idioma

        for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

            String novoTitulo = novosTitulos.get(idioma);
            String novoConteudo = novosConteudos.get(idioma);

            if (novoTitulo != null && !novoTitulo.isEmpty()) {

                orientacaoEdicao.adicionarTitulo(idioma, novoTitulo);

            }

            if (novoConteudo != null && !novoConteudo.isEmpty()) {

                orientacaoEdicao.adicionarConteudo(idioma, novoConteudo);

            }

        }

    }

    public static void deletarOrientacao(String codigo) {

        Validador.validarInputVazio(codigo);

        Orientacao orientacaoExclusao = buscarPorCodigo(codigo);

        Validador.validarExistenciaOrientacao(orientacaoExclusao);

        listaOrientacoes.remove(orientacaoExclusao);

    }

    public Orientacao pesquisarOrientacaoPorCodigo(String pesquisa) {

        Validador.validarInputVazio(pesquisa);

        for (Orientacao orientacaoPesquisa : listaOrientacoes) {

            if (orientacaoPesquisa.getCodigo().equalsIgnoreCase(pesquisa)) {

                    return orientacaoPesquisa;

            } else {

                throw new DadosIncompletosException("Orientação de código " + pesquisa + " não encontrada.");

            }

        }

        return null;

    }

    public Map<Orientacao, Map<Idioma, String>> pesquisarOrientacaoPorTitulo(String termoPesquisa) {

    Map<Orientacao, Map<Idioma, String>> resultados = new LinkedHashMap<>();

    String termoLower = termoPesquisa.toLowerCase();

    Validador.validarInputVazio(termoPesquisa);

    for (Orientacao orientacao : listaOrientacoes) {
        Map<Idioma, String> titulosEncontrados = new LinkedHashMap<>();

        for (Idioma idioma : idiomaService.getListaIdiomas()) {
            String titulo = orientacao.getTitulo(idioma);

            if (titulo != null && titulo.toLowerCase().contains(termoLower)) {
                titulosEncontrados.put(idioma, titulo);
            }
        }

        if (!titulosEncontrados.isEmpty()) {
            resultados.put(orientacao, titulosEncontrados);
        }
    }

    return resultados;
}


}
