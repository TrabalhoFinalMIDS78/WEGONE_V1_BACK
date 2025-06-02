package br.com.wegone.core;

import java.util.Locale;
import br.com.wegone.model.Idioma;
import br.com.wegone.model.IdiomasDisponiveis;

public final class IdiomaSelecionado {

    private static final IdiomasDisponiveis idiomasDisponiveis = new IdiomasDisponiveis();
    private static final Idioma IDIOMA_PADRAO = idiomasDisponiveis.getIdiomaPadrao();
    private static final String DEFAULT_CODIGO = IDIOMA_PADRAO.getCodigo();
    private static final String DEFAULT_NOME = IDIOMA_PADRAO.getNome();
    private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag(DEFAULT_CODIGO.replace('_', '-'));

    private static Idioma idiomaAtual = IDIOMA_PADRAO;

    // Construtor privado para evitar instanciação
    private IdiomaSelecionado() {}

    public static void setLocale(String escolha) {
        int index;
        try {
            index = Integer.parseInt(escolha) - 1;
        } catch (NumberFormatException e) {
            index = 0;
        }
        if (index >= 0 && index < idiomasDisponiveis.getListaIdiomas().size()) {
            idiomaAtual = idiomasDisponiveis.getListaIdiomas().get(index);
        } else {
            idiomaAtual = idiomasDisponiveis.buscarPorCodigo(DEFAULT_CODIGO);
        }
    }

    public static String getIdiomaAtual() {
        return idiomaAtual != null ? idiomaAtual.getCodigo() : DEFAULT_CODIGO;
    }

    public static Idioma getIdiomaAtualObjeto() {
        return idiomaAtual != null ? idiomaAtual : new Idioma().construtor(DEFAULT_CODIGO, DEFAULT_NOME);
    }

    public static String getIdiomaAtualNome() {
        return idiomaAtual != null ? idiomaAtual.getNome() : DEFAULT_NOME;
    }

    public static Locale getLocaleIdiomaAtual() {
        if (idiomaAtual != null) {
            // pt_BR -> pt-BR
            String tag = idiomaAtual.getCodigo().replace('_', '-');
            return Locale.forLanguageTag(tag);
        }
        return DEFAULT_LOCALE;
    }

    public static IdiomasDisponiveis getIdiomasDisponiveis() {
        return idiomasDisponiveis;
    }
}