package br.com.wegone.service;

import java.sql.SQLException;
import java.util.logging.Logger;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.Usuario;
import br.com.wegone.repository.UsuarioDAO;

public class UsuarioService {

    private final UsuarioDAO dao = new UsuarioDAO();
    private static final IdiomaMensagens mensagem = new IdiomaMensagens();
    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class.getName());

    public Usuario cadastrar(String matricula, String nome, String email, String senha) {

        ValidadorService.validarCadastroUsuario(matricula, nome, email, senha);

        try {

            if (dao.existeMatricula(matricula)) {

                throw new DadosIncompletosException(mensagem.get("exception.user.duplicate_matricula"));

            }

            Usuario novo = new Usuario(matricula, nome, email, senha);

            dao.salvar(novo);

            return novo; // Sucesso

        } catch (SQLException e) {

            LOGGER.severe(mensagem.get("exception.user.erro") + e.getMessage());
            throw new RuntimeException(mensagem.get("exception.user.erro.SQL"));

        }

    }

    public Usuario login(String matricula, String senha) {

        ValidadorService.validarLogin(matricula, senha);

        try {

            Usuario usuario = dao.buscarPorMatriculaESenha(matricula, senha);

            if (usuario == null) {

                throw new DadosIncompletosException(mensagem.get("exception.user.credenciais.invalidas"));

            }

            return usuario;

        } catch (SQLException e) {

            LOGGER.severe(mensagem.get("exception.user.login") + e.getMessage());
            throw new RuntimeException(mensagem.get("exception.user.login"));

        }

    }

}
