package br.com.wegone.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoOrientacaoDAO {
    public List<TipoOrientacao> carregarTiposOrientacao(IdiomasDisponiveis idiomas) {
        List<TipoOrientacao> tipos = new ArrayList<>();
        String sql = "SELECT t.codigo, tn.id_idioma, i.codigo as codigo_idioma, tn.nome " +
                     "FROM tipos_orientacao t " +
                     "JOIN tipos_orientacao_nomes tn ON t.id_tipo_orientacao = tn.id_tipo_orientacao " +
                     "JOIN idiomas i ON tn.id_idioma = i.id_idioma";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            TipoOrientacao tipoAtual = null;
            String codigoAtual = null;
            
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                
                if (!codigo.equals(codigoAtual)) {
                    if (tipoAtual != null) {
                        tipos.add(tipoAtual);
                    }
                    tipoAtual = new TipoOrientacao(codigo);
                    codigoAtual = codigo;
                }
                
                Idioma idioma = idiomas.buscarPorCodigo(rs.getString("codigo_idioma"));
                if (idioma != null && tipoAtual != null) {
                    tipoAtual.setNomePorIdioma(idioma, rs.getString("nome"));
                }
            }
            
            if (tipoAtual != null) {
                tipos.add(tipoAtual);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar tipos de orientação: " + e.getMessage());
        }
        return tipos;
    }
    
    public TipoOrientacao buscarPorCodigo(String codigo, IdiomasDisponiveis idiomas) {
        String sql = "SELECT t.codigo, tn.id_idioma, i.codigo as codigo_idioma, tn.nome " +
                     "FROM tipos_orientacao t " +
                     "JOIN tipos_orientacao_nomes tn ON t.id_tipo_orientacao = tn.id_tipo_orientacao " +
                     "JOIN idiomas i ON tn.id_idioma = i.id_idioma " +
                     "WHERE t.codigo = ?";
        
        TipoOrientacao tipo = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    if (tipo == null) {
                        tipo = new TipoOrientacao(rs.getString("codigo"));
                    }
                    
                    Idioma idioma = idiomas.buscarPorCodigo(rs.getString("codigo_idioma"));
                    if (idioma != null) {
                        tipo.setNomePorIdioma(idioma, rs.getString("nome"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tipo de orientação: " + e.getMessage());
        }
        return tipo;
    }
}