package br.com.wegone.service;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class IdiomaMensagens {

    private final ResourceBundle bundle;
    private static final String BASENAME = "i18n.menu";

    /**
     * Tenta carregar o bundle do idioma; se falhar,
     * recai para o bundle padrão (sem sufixo).
     */
    public IdiomaMensagens(String codigoIdioma) {

        ResourceBundle tmp;

        try {

            Locale locale = new Locale(codigoIdioma);
            tmp = ResourceBundle.getBundle(BASENAME, locale);

        } catch (MissingResourceException e) {

            System.err.println("Aviso: não encontrei propriedades para idioma '"
                + codigoIdioma + "', usando padrão.");

            tmp = ResourceBundle.getBundle(BASENAME, Locale.ROOT);

        }

        this.bundle = tmp;

    }

    /**
     * @throws IllegalArgumentException se a chave não existir nem no bundle padrão
     */
    public String get(String chave) {

        try {

            return bundle.getString(chave);

        } catch (MissingResourceException e) {

            // última salvação: lançar uma exceção clara de configuração
            throw new IllegalArgumentException(

                "Chave de mensagem não encontrada: " + chave, e);
                
        }
    }
}
