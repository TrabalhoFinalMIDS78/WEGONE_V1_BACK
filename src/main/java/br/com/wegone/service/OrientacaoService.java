package br.com.wegone.service;

import br.com.wegone.exception.DadosIncompletosException;

public class OrientacaoService {
    
    // Excessões para o Service das Orientações, EM TRANSIÇÃO

    public static void validarCodigo(String codigo) throws DadosIncompletosException {
        if (codigo == null || codigo.isBlank()) {
            throw new DadosIncompletosException("Código não pode estar vazio.");
        }

        if (!Character.isDigit(codigo.charAt(0))) {
            throw new DadosIncompletosException("Código deve começar com um número.");
        }
    }

}
