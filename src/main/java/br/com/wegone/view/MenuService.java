package br.com.wegone.view;

import java.util.ResourceBundle;

import br.com.wegone.service.IdiomaMensagens;
import br.com.wegone.core.*;

public class MenuService {

    private static IdiomaMensagens mensagem = new IdiomaMensagens();
    private static AuxiliarDeConsole auxiliar = new AuxiliarDeConsole();

    private final static int LARGURA_MENU = 50;

    // Exibir Logo

    public static void exibirLogo() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║            ██     ██ ███████  ██████             ║");
        System.out.println("║            ██     ██ ██      ██                  ║");
        System.out.println("║            ██  █  ██ █████   ██                  ║");
        System.out.println("║            ██ ███ ██ ██      ██    ██            ║");
        System.out.println("║             ███ ███  ███████  ██████             ║");
        System.out.println("║                                                  ║");
        System.out.println("║      Multilingual Guidance System - WEGONE       ║");
        System.out.println("║        Version 1.0 - Developed by MIDS78         ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        try {
            Thread.sleep(1500); // Pausa por 1,5 segundos
        } catch (InterruptedException e) {
            System.out.println("Erro ao pausar execução.");
        }

    }

    // Exibir Menu de Idiomas

    public static void exibirIdiomas() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║               CHOOSE YOUR LANGUAGE               ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Português                                    ║");
        System.out.println("║ 2 - English                                      ║");
        System.out.println("║ 3 - Espanõl                                      ║");
        System.out.println("║ 4 - Deutsch                                      ║");
        System.out.println("║ 5 - 中文 (Zhōngwén)                              ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

    }

    // Exibir Menu de Login ou Cadastro

    public static void exibirMenuAcesso() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║" + auxiliar.centralizarTexto(mensagem.get("menu.acesso.titulo"), LARGURA_MENU) + "║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.acesso.login"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.acesso.cadastro"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.acesso.sair"), LARGURA_MENU) + "║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

    }

    // Exibir Menu Principal

    public static void exibirMenuOrientacoes() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║" + auxiliar.centralizarTexto(mensagem.get("menu.orientacoes.titulo"), LARGURA_MENU) + "║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.orientacoes.cadastrar"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.orientacoes.editar"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.orientacoes.excluir"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.orientacoes.pesquisar"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.orientacoes.listar"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.orientacoes.sair"), LARGURA_MENU) + "║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

    }

    // Menu de Tratamento de Erros

    // Tratar Erro Escolha de Idiomas

    public static void tratarErroEscolhaIdioma() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║" + auxiliar.centralizarTexto(mensagem.get("menu.tratar.erro.idioma.titulo"), LARGURA_MENU) + "║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.tratar.erro.idioma.tentar.novamente"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.tratar.erro.idioma.iniciar.ptbr"), LARGURA_MENU) + "║");
        System.out.println("║" + auxiliar.alinharEsquerda(mensagem.get("menu.tratar.erro.idioma.sair"), LARGURA_MENU) + "║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

    }

    // Tratar Erro Escolha de Acesso


}