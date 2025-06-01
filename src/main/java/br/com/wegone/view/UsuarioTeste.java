package br.com.wegone.view;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import br.com.wegone.model.Usuario;
import br.com.wegone.service.UsuarioService;
public class UsuarioTeste {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();

        while (true) {
            System.out.println("\n=== MENU INICIAL ===");
            System.out.println("1. Fazer login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    fazerLogin(scanner, usuarioService);
                    break;

                case "2":
                    cadastrarUsuario(scanner, usuarioService);
                    break;

                case "0":
                    System.out.println("Encerrando o sistema. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarUsuario(Scanner scanner, UsuarioService usuarioService) {
        System.out.println("\n==> CADASTRO DE USUÁRIO");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Console console = System.console();
        if(console == null){
            System.out.println("Erro: Console - senha - não disponivel");
            return;
        }

        char[] senhaChars = console.readPassword("Senha: ");
        String senha = new String(senhaChars);

        try {
            Usuario novoUsuario = usuarioService.cadastrar(matricula, nome, email, senha);
            System.out.println("Usuário cadastrado com sucesso: " + novoUsuario.getNome());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void fazerLogin(Scanner scanner, UsuarioService usuarioService) {
        System.out.println("\n==> LOGIN");
        System.out.print("Matrícula: ");
        String loginMatricula = scanner.nextLine();

        Console console = System.console();
        if (console == null) {
            System.out.println("Erro: Console não disponível. Execute este programa em um terminal real.");
            return;
        }

        char[] senhaChars = console.readPassword("Senha: ");
        String loginSenha = new String(senhaChars);

        try {
            Usuario usuarioLogado = usuarioService.login(loginMatricula, loginSenha);
            System.out.println("\nLogin bem-sucedido! Bem-vindo, " + usuarioLogado.getNome());

            if (usuarioLogado.getTipoUsuario() == Usuario.TipoUsuario.ADMIN) {
                menuAdmin(scanner, usuarioService);
            } else {
                System.out.println("Você é um usuário comum. Acesso limitado.");
            }

        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
    }

    private static void menuAdmin(Scanner scanner, UsuarioService usuarioService) {
        int opcao;
        do {
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1. Promover usuário a ADMIN");
            System.out.println("2. Listar usuários comuns");
            System.out.println("3. Listar administradores");
            System.out.println("0. Sair do menu admin");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Digite a matrícula do usuário a ser promovido: ");
                    String matricula = scanner.nextLine();
                    try {
                        usuarioService.promoverParaAdmin(matricula);
                        System.out.println("Usuário promovido com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 2:
                    List<Usuario> usuarios = usuarioService.listarUsuariosNormais();
                    System.out.println("\n== USUÁRIOS COMUNS ==");
                    usuarios.forEach(u -> 
                        System.out.println("Matrícula: " + u.getMatricula() + " | Nome: " + u.getNome()));
                    break;
                    /* 
                case 3:
                    List<Usuario> admins = usuarioService.listarAdmins();
                    System.out.println("\n== ADMINISTRADORES ==");
                    admins.forEach(a -> 
                        System.out.println("Matrícula: " + a.getMatricula() + " | Nome: " + a.getNome()));
                    break; */

                case 0:
                    System.out.println("Saindo do menu admin...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
