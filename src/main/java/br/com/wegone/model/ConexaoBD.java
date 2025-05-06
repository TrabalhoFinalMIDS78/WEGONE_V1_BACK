package br.com.wegone.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexaoBD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        for(int i=0; i<3; i++) {
        System.out.print("Digite um nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite a data de nascimento (yyyy/mm/dd): ");
        String dataNascimento = scanner.nextLine();

        String url = "jdbc:mysql://yamabiko.proxy.rlwy.net:25284/railway";
        String usuario = "root"; 
        String senha = "bYUafjZKoGvIjsdEvkTKwRVIWDeePBPG";     // Alterar dependeno da senha  

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            String sql = "INSERT INTO pessoas (nome, email, telefone, data_nascimento) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Parâmetros para a consulta no SQL
            stmt.setString(1, nome);         
            stmt.setString(2, email);        
            stmt.setString(3, telefone);     
            stmt.setString(4, dataNascimento);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Dados inseridos com sucesso!");
            }

            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
    }
}



