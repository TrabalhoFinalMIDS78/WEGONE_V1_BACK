package br.com.wegone.view;

import java.util.logging.Logger;

public class AuxiliarDeConsole {

    private static final Logger LOGGER = Logger.getLogger(AuxiliarDeConsole.class.getName());

    public static String lerLinha() {
        return new java.util.Scanner(System.in).nextLine();
    }

    public static void exibirTitulo(String titulo) {

        int largura = 50;
        String borda = repeat('═', largura);
        String linhaCentral = String.format("║%-50s║", "  " + titulo);

        LOGGER.info("\n╔" + borda + "╗");
        LOGGER.info(linhaCentral);
        LOGGER.info("╚" + borda + "╝\n");

    }

    private static String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void separador() {

        LOGGER.info("\n" + repeat('═', 52));

    }

    /**
     * Centraliza um texto dentro de uma largura fixa.
     *
     * @param texto Texto a ser centralizado.
     * @param largura Largura total do espaço onde o texto será centralizado.
     * @return Texto formatado com espaços para centralização.
     */
    public static String centralizarTexto(String texto, int largura) {
        if (texto == null || texto.isEmpty()) {
            return " ".repeat(largura);
        }

        int tamanhoTexto = texto.length();
        if (tamanhoTexto >= largura) {
            return texto.substring(0, largura); // Trunca o texto se for maior que a largura
        }

        int espacosTotais = largura - tamanhoTexto;
        int espacosEsquerda = espacosTotais / 2;
        int espacosDireita = espacosTotais - espacosEsquerda;

        return " ".repeat(espacosEsquerda) + texto + " ".repeat(espacosDireita);
    }

    // Método para alinhar texto à esquerda
    public static String alinharEsquerda(String texto, int largura) {

        if (texto == null || texto.isEmpty()) {
            return " ".repeat(largura);
        }

        int tamanhoTexto = texto.length();
        if (tamanhoTexto >= largura) {
            return texto.substring(0, largura); // Trunca o texto se for maior que a largura
        }

        return texto + " ".repeat(Math.max(0, largura - texto.length()));
    }

}
