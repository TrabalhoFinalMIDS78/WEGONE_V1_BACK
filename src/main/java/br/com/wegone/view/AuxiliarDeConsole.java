package br.com.wegone.view;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import br.com.wegone.service.IdiomaMensagens;

public class AuxiliarDeConsole {

    private static final IdiomaMensagens mensagem = new IdiomaMensagens();
    private static final Logger LOGGER = Logger.getLogger(AuxiliarDeConsole.class.getName());
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8.name());

    // Private constructor to prevent instantiation
    AuxiliarDeConsole() {
        throw new UnsupportedOperationException("Utility class"); // Fiz para evitar instância
    }

    public static String lerLinha() {
        return SCANNER.nextLine();
    }

    public static void escolha() {

        if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
            LOGGER.info(() -> "\n" + mensagem.get("menu.escolha"));
        }

    }

    public static void exibirTitulo(String titulo) {

        int largura = 65;
        String borda = repeat('═', largura);
        String linhaCentral = String.format("║%-50s║", titulo);

        if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
            LOGGER.info(String.format("%n╔%s╗", borda));
            LOGGER.info(linhaCentral);
            LOGGER.info(String.format("╚%s╝%n", borda));
        }

    }

    private static String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void separador() {

        if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
            LOGGER.info(String.format("%n%s", repeat('-', 67)));
        }

    }

    public static void pularLinha(int largura) {

        largura -= 2;

        String espacoEmBranco = repeat(' ', largura);

        LOGGER.info(String.format("║%s║", espacoEmBranco));

    }

    public static List<String> quebrarEmLinhasComPrefixo(String prefixo, String texto, int largura) {

        List<String> linhas = new ArrayList<>();
        if (texto == null) {
            texto = "";
        }

        int larguraPrimeiraLinha = largura - prefixo.length();
        String[] palavras = texto.split(" ");
        StringBuilder linhaAtual = new StringBuilder();

        boolean primeiraLinha = true;

        for (String palavra : palavras) {
            // Se a palavra for maior que a largura disponível, quebra a palavra
            while (palavra.length() > (primeiraLinha ? larguraPrimeiraLinha : largura)) {
                String parte = palavra.substring(0, (primeiraLinha ? larguraPrimeiraLinha : largura));
                palavra = palavra.substring((primeiraLinha ? larguraPrimeiraLinha : largura));

                String linha = (primeiraLinha ? prefixo : repeat(' ', prefixo.length())) + parte;
                linhas.add(alinharEsquerda(linha, largura));

                primeiraLinha = false;
            }

            int larguraDisponivel = (primeiraLinha ? larguraPrimeiraLinha : largura) - linhaAtual.length();

            if (linhaAtual.length() > 0 && palavra.length() + 1 > larguraDisponivel) {
                String linha = (primeiraLinha ? prefixo : repeat(' ', prefixo.length())) + linhaAtual.toString().trim();
                linhas.add(alinharEsquerda(linha, largura));
                linhaAtual = new StringBuilder();
                primeiraLinha = false;
            }

            if (linhaAtual.length() > 0) {
                linhaAtual.append(" ");
            }
            linhaAtual.append(palavra);
        }

        if (linhaAtual.length() > 0) {
            String linha = (primeiraLinha ? prefixo : repeat(' ', prefixo.length())) + linhaAtual.toString().trim();
            linhas.add(alinharEsquerda(linha, largura));
        }

        return linhas;
    }

    /**
     * Centraliza um texto dentro de uma largura fixa.
     *
     * @param texto   Texto a ser centralizado.
     * @param largura Largura total do espaço onde o texto será centralizado.
     * @return Texto formatado com espaços para centralização.
     */
    public static String centralizarTexto(String texto, int largura) {
        if (texto == null || texto.isEmpty()) {
            return repeat(' ', largura);
        }

        int tamanhoTexto = texto.length();
        if (tamanhoTexto >= largura) {
            return texto.substring(0, largura); // Trunca o texto se for maior que a largura
        }

        int espacosTotais = largura - tamanhoTexto;
        int espacosEsquerda = espacosTotais / 2;
        int espacosDireita = espacosTotais - espacosEsquerda;

        return repeat(' ', espacosEsquerda) + texto + repeat(' ', espacosDireita);
    }

    // Método para alinhar texto à esquerda
    public static String alinharEsquerda(String texto, int largura) {

        if (texto == null || texto.isEmpty()) {
            return repeat(' ', largura);
        }

        int tamanhoTexto = texto.length();
        if (tamanhoTexto >= largura) {
            return texto.substring(0, largura); // Trunca o texto se for maior que a largura
        }

        return texto + repeat(' ', Math.max(0, largura - texto.length()));
    }

}
