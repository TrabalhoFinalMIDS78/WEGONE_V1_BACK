package br.com.wegone.exception;

public class DadosIncompletosException extends RuntimeException {
    private final String key;
    private final Object[] params;

    public DadosIncompletosException(String key, Object... params) {
        super(key);
        this.key = key;
        this.params = params;
    }

    public String getKey() {
        return key;
    }

    public Object[] getParams() {
        return params;
    }

}
