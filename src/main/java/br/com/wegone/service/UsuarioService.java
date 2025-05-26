package br.com.wegone.service;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.Usuario;
import br.com.wegone.repository.UsuarioDAO;

public class UsuarioService {

    private final UsuarioDAO dao = new UsuarioDAO();

    public Usuario cadastrar(String matricula, String nome, String email, String senha) {
        if (matricula == null || matricula.isBlank() || nome == null || nome.isBlank() || email == null || email.isBlank() || senha == null || senha.isBlank()) {
            throw new DadosIncompletosException("Todos os campos são obrigatórios.");
        }

        try {
            if (dao.existeMatricula(matricula)) {
                throw new DadosIncompletosException("Matrícula já cadastrada.");
            }

            Usuario novo = new Usuario(matricula, nome, email, senha);
            dao.salvar(novo);
            return novo;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar usuário: " + e.getMessage(), e);
        }
    }

    public Usuario login(String matricula, String senha) {
        if (matricula == null || senha == null || matricula.isBlank() || senha.isBlank()) {
            throw new DadosIncompletosException("Matrícula e senha são obrigatórias.");
        }

        try {
            Usuario usuario = dao.buscarPorMatriculaESenha(matricula, senha);
            if (usuario == null) {
                throw new DadosIncompletosException("Credenciais inválidas.");
            }
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException("Erro no login: " + e.getMessage(), e);
        }
    }
}
