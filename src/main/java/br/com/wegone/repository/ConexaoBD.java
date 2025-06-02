package br.com.wegone.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoBD {
    private static final String URL = "jdbc:mysql://caboose.proxy.rlwy.net:41283/railway";
    private static final String USUARIO = "root";
    private static final String SENHA = "cnTSUGezfuakMIEFDQLxosqYXfQmGZEW";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            System.err.println(e.getMessage());
            return null;
        }
    }
}
