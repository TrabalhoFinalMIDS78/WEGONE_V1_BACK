package br.com.wegone.service;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.wegone.core.IdiomaSelecionado;

public class IdiomaMensagens {

    private static final Logger LOGGER = Logger.getLogger(IdiomaMensagens.class.getName());
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
            Locale locale = determinarLocale(codigoAtual);
            tmp = ResourceBundle.getBundle(BASENAME, locale);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.WARNING, "Aviso: não encontrei propriedades para idioma ''{0}''. Usando padrão pt_BR.", codigoAtual);
            tmp = ResourceBundle.getBundle(BASENAME, Locale.ROOT);
        }
        this.bundle = tmp;
    }

    /**
     * Determina o Locale com base no código do idioma.
     * 
     * @param codigoAtual Código do idioma (ex.: "pt_BR" ou "pt")
     * @return Locale correspondente
     */
    private Locale determinarLocale(String codigoAtual) {
        String[] partesCodigo = codigoAtual.split("_");
        return switch (partesCodigo.length) {
            case 2 -> Locale.of(partesCodigo[0], partesCodigo[1]); // Idioma com região (ex.: pt_BR)
            case 1 -> Locale.of(partesCodigo[0]); // Idioma sem região (ex.: pt)
            default -> Locale.ROOT; // Fallback para o padrão
        };
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