package br.com.wegone.exception;

public class AutenticacaoException extends RuntimeException {
    private final String key;

    public AutenticacaoException(String key) {
        super(key);
        this.key = key;
    }

    public String getKey() { return key; }
    
}