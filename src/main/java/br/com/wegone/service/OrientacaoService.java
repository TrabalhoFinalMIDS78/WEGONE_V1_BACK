package br.com.wegone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Comparator;
import java.text.Normalizer;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.repository.*;
import br.com.wegone.model.*;
import br.com.wegone.service.*;

public class OrientacaoService {

    // Refatorar para a integração com Banco de Dados

    // Atributos

    private static List<Orientacao> listaOrientacoes = new ArrayList<>();
    private static IdiomaService idiomaService = new IdiomaService();

    private static IdiomaMensagens mensagem = new IdiomaMensagens();

    // Métodos auxiliares para as Listas de Orientações (BOA PARTE VAI TER QUE MUDAR
    // QUANDO INSERIRMOS O BANCO DE DADOS)

    private static Orientacao buscarPorCodigo(String codigo) {
        return listaOrientacoes.stream()
                .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public static List<Orientacao> getListaOrientacoes() {
        return listaOrientacoes;
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

        ValidadorService.validarCodigoCadastro(codigo, listaOrientacoes); // Validar Exceções

        Orientacao orientacao = new Orientacao(codigo, tipo);

        for (Idioma idiomas : titulos.keySet()) {

            String titulo = titulos.get(idiomas);
            String conteudo = conteudos.get(idiomas);

            // Validações

            if (titulo == null || titulo.isBlank()) {
                throw new DadosIncompletosException(
                        mensagem.get("exception.orientacao.cadastro.titulo.vazio") + " " + idiomas.getNome());
            }

            if (conteudo == null || conteudo.isBlank()) {
                throw new DadosIncompletosException(
                        mensagem.get("exception.orientacao.cadastro.conteudo.vazio") + " " + idiomas.getNome());
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
            Map<Idioma, String> novosTitulos,
            Map<Idioma, String> novosConteudos

    ) {

        ValidadorService.validarInputVazio(codigo);

        Orientacao orientacaoEdicao = buscarPorCodigo(codigo);

        ValidadorService.validarExistenciaOrientacao(orientacaoEdicao);

        // Edição dos Títulos e Conteúdos em Cada Idioma

        for (Idioma idioma : idiomaService.getListaIdiomas()) {

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

        ValidadorService.validarInputVazio(codigo);

        Orientacao orientacaoExclusao = buscarPorCodigo(codigo);

        ValidadorService.validarExistenciaOrientacao(orientacaoExclusao);

        listaOrientacoes.remove(orientacaoExclusao);

    }

    public static Orientacao pesquisarOrientacaoPorCodigo(String pesquisa) {

        ValidadorService.validarInputVazio(pesquisa);

        for (Orientacao orientacaoPesquisa : listaOrientacoes) {

            if (orientacaoPesquisa.getCodigo().equalsIgnoreCase(pesquisa)) {

                return orientacaoPesquisa;

            }
        }

        throw new DadosIncompletosException(
                mensagem.get("exception.orientacao.pesquisa.codigo_nao_encontrado") + " " + pesquisa);

    }

    public static Map<Orientacao, Map<Idioma, String>> pesquisarOrientacaoPorTitulo(String termoPesquisa) {

        ValidadorService.validarInputVazio(termoPesquisa);

        Map<Orientacao, Map<Idioma, String>> resultados = new LinkedHashMap<>();

        String termoLower = termoPesquisa.toLowerCase();

        String termoNorm = normalize(termoLower);

        for (Orientacao orientacao : listaOrientacoes) {
            Map<Idioma, String> titulosEncontrados = new LinkedHashMap<>();

            for (Idioma idioma : idiomaService.getListaIdiomas()) {

                String titulo = orientacao.getTitulo(idioma);

                if (titulo != null) {
                    String tituloNorm = normalize(titulo);
                    if (tituloNorm.contains(termoNorm)) {
                        titulosEncontrados.put(idioma, titulo);
                    }

                }

            }

            if (!titulosEncontrados.isEmpty()) {
                resultados.put(orientacao, titulosEncontrados);
            }

        }

        if (resultados.isEmpty()) {
            throw new DadosIncompletosException(
                    mensagem.get("exception.orientacao.pesquisa.termo_nao_encontrado") + " " + termoPesquisa);
        }

        return resultados;
    }

    private static String normalize(String text) {

        String n = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        return n.toLowerCase(Locale.ROOT);

    }

    public static List<Orientacao> listarOrientacoes(

            TipoOrientacao tipoOrientacaoFiltro,
            boolean ordenarPorMaisRecente

    ) {

        List<Orientacao> listaFiltrada = new ArrayList<>();

        for (Orientacao orientacao : listaOrientacoes) {

            if (tipoOrientacaoFiltro == null || orientacao.getTipo().equals(tipoOrientacaoFiltro)) {

                listaFiltrada.add(orientacao);

            }

        }

        // Se true ele busca as mais recentes primeiro
        // Se false ele busca as mais antigas primeiro
        if (ordenarPorMaisRecente) {

            Collections.sort(listaFiltrada, new Comparator<Orientacao>() {

                public int compare(Orientacao o1, Orientacao o2) {

                    return o2.getDataCriacao().compareTo(o1.getDataCriacao());

                }
            });

        } else {
            Collections.sort(listaFiltrada, new Comparator<Orientacao>() {

                public int compare(Orientacao o1, Orientacao o2) {

                    return o1.getDataCriacao().compareTo(o2.getDataCriacao());

                }
            });
        }

        return listaFiltrada;

    }

}