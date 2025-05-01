package br.com.wegone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.wegone.model.Idioma;
import br.com.wegone.model.IdiomasDisponiveis;
import br.com.wegone.model.Orientacao;
import br.com.wegone.model.TipoOrientacao;
import br.com.wegone.model.TipoOrientacoesDisponiveis;

public class SistemaOrientacoes {

    private static List<Orientacao> listaOrientacoes = new ArrayList<>();
    private static IdiomasDisponiveis idiomasDisponiveis = new IdiomasDisponiveis();
    private static TipoOrientacoesDisponiveis tipoOrientacoesDisponiveis = new TipoOrientacoesDisponiveis(idiomasDisponiveis);
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

            System.out.println((i + 1) + "- " + idiomas.get(i).getNome()); // Mostrar os nomes dos Idiomas Disponiveis

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

    // Métodos Afins

    private static Orientacao buscarPorCodigo(String codigo) {
        return listaOrientacoes.stream()
            .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
            .findFirst()
            .orElse(null);
    }

    // Métodos CRUD

    private static void cadastrarOrientacao() {

        System.out.print("Código da nova orientação: ");
        String codigo = sc.nextLine();

        // Selecionar tipo
        System.out.println("Escolha o tipo:");
        List<TipoOrientacao> tipos = tipoOrientacoesDisponiveis.getListaOrientacoesDisponiveis();
        
        for (int i = 0; i < tipos.size(); i++) {

            System.out.println((i + 1) + "- Nome: " + tipos.get(i).getNome(idiomaAtual) + " | Código: " + tipos.get(i).getCodigo());

        }

        System.out.print("\nDigite o código do tipo desejado: ");

        String codigoTipoEscolhido = sc.next();

        sc.nextLine(); // limpar buffer

        TipoOrientacao tipoSelecionado = tipoOrientacoesDisponiveis.buscarPorCodigo(codigoTipoEscolhido);

        if (tipoSelecionado == null) {

            System.out.println("Erro! Tipo de orientação não encontrado.");

            return;

        }

        Orientacao novaOrientacao = new Orientacao(codigoTipoEscolhido, tipoSelecionado);

        // Adicionando títulos e conteúdos para cada idioma
        for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

            System.out.print("Título em " + idioma.getNome() + ": ");
            novaOrientacao.adicionarTitulo(idioma, sc.nextLine());

            System.out.print("Conteúdo em " + idioma.getNome() + ": ");
            novaOrientacao.adicionarConteudo(idioma, sc.nextLine());
        }

        listaOrientacoes.add(novaOrientacao);
        
        System.out.println("Sucesso! Orientação cadastrada com sucesso!");
        
    }

    // Método para a Edição de Orientações

    private static void editarOrientacao() {

        System.out.print("\nDigite o código da orientação a ser editada: ");
        String codigoOrientacao = sc.nextLine();

        Orientacao orientacaoEdicao = buscarPorCodigo(codigoOrientacao);

        if (orientacaoEdicao == null) {

            System.out.println("Orientação não encontrada.");

            return;

        }

        for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

            System.out.print("Novo título | Idioma Sistema [" + idioma.getNome() + "] (deixe vazio para manter): ");
            String novoTitulo = sc.nextLine();

            if (!novoTitulo.isEmpty()) orientacaoEdicao.adicionarTitulo(idioma, novoTitulo); // Adiciona o novo título

            System.out.print("Novo conteúdo | Idioma Sistema [" + idioma.getNome() + "] (deixe vazio para manter): ");
            String novoConteudo = sc.nextLine();

            if (!novoConteudo.isEmpty()) orientacaoEdicao.adicionarConteudo(idioma, novoConteudo); // Adiciona o novo conteúdo

        }

        System.out.println("Sucesso! Orientação atualizada.");
    }

    // Método para a Exlusão de Orientações

    private static void excluirOrientacao() {

        System.out.print("\nDigite o código da orientação a excluir: ");
        String codigoOrientacaoExclusao = sc.nextLine();

        Orientacao orientacaoExclusao = buscarPorCodigo(codigoOrientacaoExclusao);

        if (orientacaoExclusao != null) {

            listaOrientacoes.remove(orientacaoExclusao);
            System.out.println("Sucesso! Orientação removida com sucesso.");

        } else {

            System.out.println("Erro! Orientação não encontrada.");

        }

    }

    // Método para a Pesquisa de Orientações

    private static void pesquisarOrientacao() {

        System.out.print("\nDigite o titulo da Orientação para buscar (Uma parte já é Suficiente): ");
        String pesquisa = sc.nextLine().toLowerCase();

        System.out.println("\nResultados da busca:");

        for (Orientacao orientacaoPesquisa : listaOrientacoes) {

            for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

                String titulo = orientacaoPesquisa.getTitulo(idioma);

                if (titulo.toLowerCase().contains(pesquisa)) {

                    System.out.println("- [" + idioma.getNome() + "] " + titulo + " (" + orientacaoPesquisa.getCodigo() + ")");

                }
            }
        }
    }

    private static void listarOrientacoes() {

        if (listaOrientacoes.isEmpty()) {

            System.out.println("Erro! Nenhuma orientação cadastrada.");

            return;

        }

        for (Orientacao o : listaOrientacoes) {

            System.out.println("\nCódigo: " + o.getCodigo());
            System.out.println("Tipo: " + o.getTipo().getNome(idiomaAtual));
            System.out.println("Título: " + o.getTitulo(idiomaAtual));
            System.out.println("Conteúdo: " + o.getConteudo(idiomaAtual));

        }
    }


}
