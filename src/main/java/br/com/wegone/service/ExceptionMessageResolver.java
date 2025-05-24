package br.com.wegone.service;

import br.com.wegone.core.IdiomaSelecionado;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ExceptionMessageResolver {
    private static final String BASENAME = "i18n.menu";

    public static String resolve(String key, Object... params) {

        String cod = IdiomaSelecionado.getIdiomaAtual();
        String[] parts = cod.split("_", 2);
        Locale locale = new Locale(parts[0], parts[1]);
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(BASENAME, locale);
        } catch (MissingResourceException e) {
            // fallback para português brasileiro
            bundle = ResourceBundle.getBundle(BASENAME, new Locale("pt", "BR"));
        }
        String pattern = bundle.getString(key);
        return MessageFormat.format(pattern, params);
    }
    
}