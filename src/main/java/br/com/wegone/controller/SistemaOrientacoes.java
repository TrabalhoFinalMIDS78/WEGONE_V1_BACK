package br.com.wegone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.wegone.model.Idioma;
import br.com.wegone.model.IdiomasDisponiveis;
import br.com.wegone.model.Orientacao;
import br.com.wegone.model.TipoOrientacoesDisponiveis;

public class SistemaOrientacoes {

    private static List<Orientacao> listaOrientacoes = new ArrayList<>();
    private static IdiomasDisponiveis idiomasDisponiveis = new IdiomasDisponiveis();
    private static TipoOrientacoesDisponiveis tipoOrientacoes = new TipoOrientacoesDisponiveis(idiomasDisponiveis);
    private static Scanner sc = new Scanner(System.in);
    private static Idioma idiomaAtual;

    public static void main(String[] args) {

        // Mensagem Inicial WEGONE
        exibirLogo();

        // Definindo o Idioma do Sistema
        escolherIdiomaInicial();

        // Adicionando o CRUD
        // menuPrincipal();

    }

    private static void exibirLogo() {

        System.out.println("==========================================");
        System.out.println("|                                        |");
        System.out.println("|        ██     ██ ███████  ██████       |");
        System.out.println("|        ██     ██ ██      ██            |");
        System.out.println("|        ██  █  ██ █████   ██            |");
        System.out.println("|        ██ ███ ██ ██      ██    ██      |");
        System.out.println("|         ███ ███  ███████  ██████       |");
        System.out.println("|                                        |");
        System.out.println("|   Sistema de Orientações Multilíngue   |");
        System.out.println("|        Versão Inicial - WEG DS         |");
        System.out.println("==========================================\n");

        try {
            Thread.sleep(1500); // Pausa por 1,5 segundos
        } catch (InterruptedException e) {
            System.out.println("Erro ao pausar execução.");
        }

    }

    private static void escolherIdiomaInicial() {

        System.out.print("\nEscolha o Idioma Inicial:");
        List<Idioma> idiomas = idiomasDisponiveis.getListaIdiomas();

        for (int i = 0; i < idiomas.size(); i++) {

            System.out.println((i + 1) + "- " + idiomas.get(i).getNome() + "\n"); // Mostrar os nomes dos Idiomas Disponiveis

        }

        int escolhaIdioma = sc.nextInt();

        if (escolhaIdioma >= 1 && escolhaIdioma <= 5) {

            idiomaAtual = idiomas.get(escolhaIdioma - 1); // Linkei entre a Lista de Idiomas que fiz para definir o Idioma do Sistema

        } else {

            System.out.println("Erro! Usuário deve adicionar um valor correto para a escolha dos Idiomas.");
            System.out.println("\nEscolha:\n1- Fechar Sistema\n2- Continuar em Português");

            int escolhaErroIdioma = sc.nextInt();

            if (escolhaErroIdioma == 1) {

                System.out.println("\nEncerrando Sistema....");

                return;

            } else if (escolhaErroIdioma == 2) {

                idiomaAtual = idiomasDisponiveis.buscarPorCodigo("pt");

            } else {

                System.out.println("Erro! Adicione uma opção válida!");

            }

        }

        System.out.println("\nIdioma selecionado " + idiomaAtual.getNome());

    }

    private static void menuPrincipal() {

        while (true) {
            
            System.out.println("========= MENU ORIENTAÇÕES =========");
            System.out.println("\n1- Cadastrar nova Orientação");
            System.out.println("2- Editar uma Orientação");
            System.out.println("3- Excluir uma Orientação");
            System.out.println("4- Pesquisar uma Orientação");
            System.out.println("5- Listar as Orientações Existentes");
            System.out.println("6- Sair");
            System.out.println("\nEscolha: ");

            String escolhaMenu = sc.nextLine();

            switch (escolhaMenu) {
                case "1":
                    
                    //cadastrarOrientacao();

                    break;
                


                case "2":
                    
                    //editarOrientacao();

                    break;



                case "3":
                    
                    //excluirOrientacao();

                    break;
                


                case "4":
                    
                    //pesquisarOrientacao();

                    break;



                case "5":
                    
                    //listarOrientacoes();

                    break;
                



                case "6":
                    
                    System.out.println("\nEncerrando Sistema....");

                    return;
            


                default:

                    System.out.println("Erro! Insira uma Opção válida do Menu!");

                    continue;
            }



        }

    }


}
