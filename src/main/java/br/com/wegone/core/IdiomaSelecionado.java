package br.com.wegone.core;

import java.util.Locale;
import br.com.wegone.service.IdiomaMensagens;

public class IdiomaSelecionado {

    private static String codigoAtual = "pt_BR";

    public static void setLocale(String escolha) {
        switch (escolha) {
            case "1":
                codigoAtual = "pt_BR";
                break;
            case "2":
                codigoAtual = "en_US";
                break;
            case "3":
                codigoAtual = "es_ES";
                break;
            case "4":
                codigoAtual = "de_DE";
                break;
            case "5":
                codigoAtual = "zh_CN";
                break;
            default:
                codigoAtual = "pt_BR"; // Fallback
        }
    }

    public static String getIdiomaAtual() {

        return codigoAtual;
    }

    public static Locale getLocaleIdiomaAtual() {

        String[] parts = IdiomaSelecionado.getIdiomaAtual().split("_");
        
        Locale locale = parts.length > 1 ? new Locale(parts[0], parts[1]) : new Locale(parts[0]);

        return locale;

    }
}
