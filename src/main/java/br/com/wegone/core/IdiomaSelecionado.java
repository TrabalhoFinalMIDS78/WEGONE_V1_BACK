package br.com.wegone.core;

import java.util.Locale;
import br.com.wegone.model.Idioma;
import br.com.wegone.model.IdiomasDisponiveis;

public class IdiomaSelecionado {

    private static IdiomasDisponiveis idiomasDisponiveis = new IdiomasDisponiveis();
    private static Idioma idiomaAtual = idiomasDisponiveis.buscarPorCodigo("pt_BR");

    public static void setLocale(String escolha) {
        // Mapeia a escolha numérica para o índice da lista de idiomas
        int index;
        try {
            index = Integer.parseInt(escolha) - 1;
        } catch (NumberFormatException e) {
            index = 0;
        }
        if (index >= 0 && index < idiomasDisponiveis.getListaIdiomas().size()) {
            idiomaAtual = idiomasDisponiveis.getListaIdiomas().get(index);
        } else {
            idiomaAtual = idiomasDisponiveis.buscarPorCodigo("pt_BR");
        }
    }

    public static String getIdiomaAtual() {
        return idiomaAtual != null ? idiomaAtual.getCodigo() : "pt_BR";
    }

    public static String getIdiomaAtualNome() {
        return idiomaAtual != null ? idiomaAtual.getNome() : "Português";
    }

    public static Locale getLocaleIdiomaAtual() {
        if (idiomaAtual != null) {
            String[] parts = idiomaAtual.getCodigo().split("_");
            if (parts.length > 1) {
                return new Locale(parts[0], parts[1]);
            } else {
                return new Locale(parts[0]);
            }
        }
        return new Locale("pt", "BR");
    }

    public static Idioma getObjetoIdiomaAtual() {
        return idiomaAtual;
    }

    public static IdiomasDisponiveis getIdiomasDisponiveis() {
        return idiomasDisponiveis;
    }
}