package br.com.wegone.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.wegone.model.Usuario;

public class UsuarioDAO {
    
    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (matricula, nome, email, senha, registro, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getMatricula());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setTimestamp(5, Timestamp.valueOf(usuario.getRegistro()));
            stmt.setString(6, usuario.getTipoUsuario().name()); // Armazena o tipo de usuário como string
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
                    Usuario usuario = new Usuario(
                        rs.getString("matricula"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                    usuario.setId(rs.getInt("id"));
                    usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(rs.getString("tipo_usuario"))); // tipoUsuario
                    return usuario;
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

    public Usuario buscarPorMatricula(String matricula) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE matricula = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getString("matricula"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                    usuario.setId(rs.getInt("id"));
                    usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(rs.getString("tipo_usuario"))); // tipoUsuario
                    return usuario;
                }
            }
        }
        return null;
    }

    public void promoverParaAdmin(String matricula) throws SQLException {
        String sql = "UPDATE usuarios SET tipo_usuario = 'ADMIN' WHERE matricula = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.executeUpdate();
        }
    }

    public List<Usuario> listarUsuariosNormais() throws SQLException {
        String sql = "SELECT * FROM usuarios_normais";
        List<Usuario> usuarios = new ArrayList<>();
    
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    "" // senha omitida por segurança
                );
                usuario.setId(rs.getInt("id"));
                usuario.setTipoUsuario(Usuario.TipoUsuario.USUARIO);
                usuarios.add(usuario); // <- aqui estava o erro
            }
        }
    
        return usuarios;
    }
    
    public List<Usuario> listarUsuariosAdmins() throws SQLException{
        String sql = "SELECT * FROM usuarios_normais";
        List<Usuario> admins = new ArrayList<>();
        try (Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

                while(rs.next()){
                    Usuario admin = new Usuario(
                        rs.getString("matricula"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                    admin.setId(rs.getInt("id"));
                    admin.setTipoUsuario(Usuario.TipoUsuario.ADMIN);
                    admin.add(admin);
                }
            }

            return admins;
    }
}
