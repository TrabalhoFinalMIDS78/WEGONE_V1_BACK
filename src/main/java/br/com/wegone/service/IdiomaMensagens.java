package br.com.wegone.service;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import br.com.wegone.core.IdiomaSelecionado;

public class IdiomaMensagens {

    private final ResourceBundle bundle;
    private static final String BASENAME = "i18n.menu";

    /**
     * Tenta carregar o bundle do idioma; se falhar,
     * recai para o bundle padrão (sem sufixo).
     */
    public IdiomaMensagens() {
        String codigoAtual = IdiomaSelecionado.getIdiomaAtual();
        ResourceBundle tmp;

        try {
            Locale locale;
            String[] partesCodigo = codigoAtual.split("_");

            if (partesCodigo.length == 2) {
                // Idioma com região (ex.: pt_BR)
                locale = new Locale(partesCodigo[0], partesCodigo[1]);
            } else {
                // Idioma sem região (ex.: pt)
                locale = new Locale(partesCodigo[0]);
            }

            tmp = ResourceBundle.getBundle(BASENAME, locale);

        } catch (MissingResourceException e) {
            System.err.println("Aviso: não encontrei propriedades para idioma '" + codigoAtual + "'. Usando padrão pt_BR.");
            tmp = ResourceBundle.getBundle(BASENAME, Locale.ROOT);
        }
        this.bundle = tmp;
    }

    /**
     * Busca uma mensagem pelo bundle carregado.
     * 
     * @param chave Chave da mensagem no arquivo .properties
     * @return Mensagem correspondente à chave
     * @throws IllegalArgumentException se a chave não existir nem no bundle padrão
     */
    public String get(String chave) {
        try {
            return bundle.getString(chave);
        } catch (MissingResourceException e) {
            throw new IllegalArgumentException("Chave de mensagem não encontrada: " + chave, e);
        }
    }
}