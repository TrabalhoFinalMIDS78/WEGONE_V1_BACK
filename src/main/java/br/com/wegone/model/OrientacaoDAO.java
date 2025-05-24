package br.com.wegone.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.wegone.model.ConexaoBD;

public class OrientacaoDAO {

    private static ConexaoBD ConexaoBD = new ConexaoBD();
    
    public void salvar(Orientacao orientacao) {
        String sqlOrientacao = "INSERT INTO orientacoes (codigo, id_tipo_orientacao) VALUES (?, " +
                              "(SELECT id_tipo_orientacao FROM tipos_orientacao WHERE codigo = ?)) " +
                              "ON DUPLICATE KEY UPDATE id_tipo_orientacao = VALUES(id_tipo_orientacao)";
        
        String sqlTitulo = "INSERT INTO orientacoes_titulos (id_orientacao, id_idioma, titulo) " +
                           "VALUES ((SELECT id_orientacao FROM orientacoes WHERE codigo = ?), " +
                           "(SELECT id_idioma FROM idiomas WHERE codigo = ?), ?) " +
                           "ON DUPLICATE KEY UPDATE titulo = VALUES(titulo)";
        
        String sqlConteudo = "INSERT INTO orientacoes_conteudos (id_orientacao, id_idioma, conteudo) " +
                             "VALUES ((SELECT id_orientacao FROM orientacoes WHERE codigo = ?), " +
                             "(SELECT id_idioma FROM idiomas WHERE codigo = ?), ?) " +
                             "ON DUPLICATE KEY UPDATE conteudo = VALUES(conteudo)";
        
        Connection conn = null;
        try {
            conn = ConexaoBD.getConnection();
            conn.setAutoCommit(false);
            
            // Salva a orientação principal
            try (PreparedStatement pstmt = conn.prepareStatement(sqlOrientacao)) {
                pstmt.setString(1, orientacao.getCodigo());
                pstmt.setString(2, orientacao.getTipo().getCodigo());
                pstmt.executeUpdate();
            }
            
            // Salva títulos e conteúdos para cada idioma
            for (Map.Entry<Idioma, String> entry : orientacao.getTitulos().entrySet()) {
                try (PreparedStatement pstmt = conn.prepareStatement(sqlTitulo)) {
                    pstmt.setString(1, orientacao.getCodigo());
                    pstmt.setString(2, entry.getKey().getCodigo());
                    pstmt.setString(3, entry.getValue());
                    pstmt.executeUpdate();
                }
                
                String conteudo = orientacao.getConteudos().get(entry.getKey());
                if (conteudo != null) {
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlConteudo)) {
                        pstmt.setString(1, orientacao.getCodigo());
                        pstmt.setString(2, entry.getKey().getCodigo());
                        pstmt.setString(3, conteudo);
                        pstmt.executeUpdate();
                    }
                }
            }
            
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Erro ao fazer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Erro ao salvar orientação: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
    
    public List<Orientacao> listarTodas(IdiomasDisponiveis idiomas, TipoOrientacoesDisponiveis tipos) {
        List<Orientacao> orientacoes = new ArrayList<>();
        String sql = "SELECT o.codigo, o.id_tipo_orientacao, t.codigo as codigo_tipo, " +
                     "ot.id_idioma, i.codigo as codigo_idioma, ot.titulo, oc.conteudo " +
                     "FROM orientacoes o " +
                     "JOIN tipos_orientacao t ON o.id_tipo_orientacao = t.id_tipo_orientacao " +
                     "LEFT JOIN orientacoes_titulos ot ON o.id_orientacao = ot.id_orientacao " +
                     "LEFT JOIN idiomas i ON ot.id_idioma = i.id_idioma " +
                     "LEFT JOIN orientacoes_conteudos oc ON o.id_orientacao = oc.id_orientacao AND ot.id_idioma = oc.id_idioma";
        
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            Orientacao orientacaoAtual = null;
            String codigoAtual = null;
            
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                
                if (!codigo.equals(codigoAtual)) {
                    if (orientacaoAtual != null) {
                        orientacoes.add(orientacaoAtual);
                    }
                    
                    TipoOrientacao tipo = tipos.buscarPorCodigo(rs.getString("codigo_tipo"));
                    orientacaoAtual = new Orientacao(codigo, tipo);
                    codigoAtual = codigo;
                }
                
                if (orientacaoAtual != null) {
                    Idioma idioma = idiomas.buscarPorCodigo(rs.getString("codigo_idioma"));
                    if (idioma != null) {
                        String titulo = rs.getString("titulo");
                        if (titulo != null) {
                            orientacaoAtual.adicionarTitulo(idioma, titulo);
                        }
                        
                        String conteudo = rs.getString("conteudo");
                        if (conteudo != null) {
                            orientacaoAtual.adicionarConteudo(idioma, conteudo);
                        }
                    }
                }
            }
            
            if (orientacaoAtual != null) {
                orientacoes.add(orientacaoAtual);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar orientações: " + e.getMessage());
        }
        return orientacoes;
    }
    
    public Orientacao buscarPorCodigo(String codigo, IdiomasDisponiveis idiomas, TipoOrientacoesDisponiveis tipos) {
        String sql = "SELECT o.codigo, o.id_tipo_orientacao, t.codigo as codigo_tipo, " +
                     "ot.id_idioma, i.codigo as codigo_idioma, ot.titulo, oc.conteudo " +
                     "FROM orientacoes o " +
                     "JOIN tipos_orientacao t ON o.id_tipo_orientacao = t.id_tipo_orientacao " +
                     "LEFT JOIN orientacoes_titulos ot ON o.id_orientacao = ot.id_orientacao " +
                     "LEFT JOIN idiomas i ON ot.id_idioma = i.id_idioma " +
                     "LEFT JOIN orientacoes_conteudos oc ON o.id_orientacao = oc.id_orientacao AND ot.id_idioma = oc.id_idioma " +
                     "WHERE o.codigo = ?";
        
        Orientacao orientacao = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    if (orientacao == null) {
                        TipoOrientacao tipo = tipos.buscarPorCodigo(rs.getString("codigo_tipo"));
                        orientacao = new Orientacao(rs.getString("codigo"), tipo);
                    }
                    
                    Idioma idioma = idiomas.buscarPorCodigo(rs.getString("codigo_idioma"));
                    if (idioma != null) {
                        String titulo = rs.getString("titulo");
                        if (titulo != null) {
                            orientacao.adicionarTitulo(idioma, titulo);
                        }
                        
                        String conteudo = rs.getString("conteudo");
                        if (conteudo != null) {
                            orientacao.adicionarConteudo(idioma, conteudo);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar orientação: " + e.getMessage());
        }
        return orientacao;
    }
    
    public void excluir(String codigo) {
        String sql = "DELETE FROM orientacoes WHERE codigo = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, codigo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir orientação: " + e.getMessage());
        }
    }
}