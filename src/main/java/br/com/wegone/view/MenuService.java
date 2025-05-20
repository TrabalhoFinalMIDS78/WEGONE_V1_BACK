package br.com.wegone.view;

import java.util.ResourceBundle;

import br.com.wegone.service.IdiomaMensagens;
import br.com.wegone.

public class MenuService {

    private ResourceBundle mensagens = ResourceBundle.getBundle("i18n.menu", IdiomaSelecionado.getLocaleAtual());

    // Exibir Logo

    private static void exibirLogo() {
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

}