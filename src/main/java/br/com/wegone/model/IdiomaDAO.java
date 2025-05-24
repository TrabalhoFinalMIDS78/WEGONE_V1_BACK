package br.com.wegone.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdiomaDAO {
    public List<Idioma> carregarIdiomas() {
        List<Idioma> idiomas = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM idiomas";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Idioma idioma = new Idioma()
                    .construtor(rs.getString("codigo"), rs.getString("nome"));
                idiomas.add(idioma);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar idiomas: " + e.getMessage());
        }
        return idiomas;
    }
    
    public Idioma buscarPorCodigo(String codigo) {
        String sql = "SELECT codigo, nome FROM idiomas WHERE codigo = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Idioma()
                        .construtor(rs.getString("codigo"), rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar idioma: " + e.getMessage());
        }
        return null;
    }
}