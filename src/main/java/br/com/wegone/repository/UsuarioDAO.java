package br.com.wegone.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.com.wegone.model.Usuario;
;

public class UsuarioDAO {
    
    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (matricula, nome, email, senha, registro) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getMatricula());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setTimestamp(5, Timestamp.valueOf(usuario.getRegistro()));
            stmt.executeUpdate();

            // Recuperar o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }
    }

    public Usuario buscarPorMatriculaESenha(String matricula, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE matricula = ? AND senha = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("matricula"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    public boolean existeMatricula(String matricula) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE matricula = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }
}
