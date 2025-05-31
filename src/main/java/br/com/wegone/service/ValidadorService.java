package br.com.wegone.service;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.Orientacao;

import java.util.List;

public class ValidadorService {

    // Private constructor to prevent instantiation
    private ValidadorService() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static final IdiomaMensagens mensagem = new IdiomaMensagens();

    // Excessões para o Service das Orientações

    public static void validarInputVazio(String texto) {

        if (texto == null || texto.isBlank()) {

            throw new DadosIncompletosException(mensagem.get("exception.dados.vazio"));

        }

    }

    public static void validarExistenciaOrientacao(Orientacao orientacao) {

        if (orientacao == null) {

            throw new DadosIncompletosException(mensagem.get("exception.orientacao.nao_encontrada"));

        }

    }

    // Validações para o código de cadastro
    public static void validarCodigoCadastro(String codigo, List<Orientacao> listaOrientacoes)
            throws DadosIncompletosException {

        validarInputVazio(codigo);

        if (codigo.length() < 3) {
            throw new DadosIncompletosException(mensagem.get("exception.orientacao.codigo.tamanho"));
        }

        if (!Character.isDigit(codigo.charAt(0))) {
            throw new DadosIncompletosException(mensagem.get("exception.orientacao.codigo.inicio_numero"));
        }

        // Duplicidade

        for (Orientacao orientacao : listaOrientacoes) {
            if (orientacao.getCodigo().equalsIgnoreCase(codigo)) {
                throw new DadosIncompletosException(mensagem.get("exception.orientacao.codigo.duplicado"));
            }
        }

    }

    public static void validarLogin(String matricula, String senha) {

        validarMatricula(matricula);
        validarSenha(senha);

    }

    public static void validarCadastroUsuario(String matricula, String nome, String email, String senha) {
        validarMatricula(matricula);
        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);
    }

    public static void validarMatricula(String matricula) {

        validarInputVazio(matricula);

        if (matricula.length() != 5) {
            throw new DadosIncompletosException(mensagem.get("exception.user.matricula.tamanho"));
        }
        if (!matricula.matches("^[a-zA-Z0-9]+$")) {
            throw new DadosIncompletosException(mensagem.get("exception.user.matricula.formato"));
        }

    }

    public static void validarNome(String nome) {

        validarInputVazio(nome);
        if (nome.length() < 3) {
            throw new DadosIncompletosException(mensagem.get("exception.user.nome.tamanho"));
        }
        if (!nome.matches("^[A-Za-zÀ-ÿ ']+$")) {
            throw new DadosIncompletosException(mensagem.get("exception.user.nome.formato"));
        }

    }

    public static void validarEmail(String email) {

        validarInputVazio(email);

        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            throw new DadosIncompletosException(mensagem.get("exception.user.email.formato"));
        }

    }

    public static void validarSenha(String senha) {

        validarInputVazio(senha);

        if (senha.length() < 6) {
            throw new DadosIncompletosException(mensagem.get("exception.user.senha.tamanho"));
        }

        if (!senha.matches(".*[A-Z].*")) {
            throw new DadosIncompletosException(mensagem.get("exception.user.senha.maiuscula"));
        }

        if (!senha.matches(".*[a-z].*")) {
            throw new DadosIncompletosException(mensagem.get("exception.user.senha.minuscula"));
        }

        if (!senha.matches(".*\\d.*")) {
            throw new DadosIncompletosException(mensagem.get("exception.user.senha.numero"));
        }
    }

}
