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
        menuPrincipal();

    }

    // Métodos Auxiliares

    private static void exibirLogo() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║            ██     ██ ███████  ██████             ║");
        System.out.println("║            ██     ██ ██      ██                  ║");
        System.out.println("║            ██  █  ██ █████   ██                  ║");
        System.out.println("║            ██ ███ ██ ██      ██    ██            ║");
        System.out.println("║             ███ ███  ███████  ██████             ║");
        System.out.println("║                                                  ║");
        System.out.println("║   Sistema de Orientações Multilíngue - WEGONE    ║");
        System.out.println("║   Versão Inicial - Desenvolvido por MIDS78       ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        try {
            Thread.sleep(1500); // Pausa por 1,5 segundos
        } catch (InterruptedException e) {
            System.out.println("Erro ao pausar execução.");
        }

    }

    private static Orientacao buscarPorCodigo(String codigo) {
        return listaOrientacoes.stream()
            .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
            .findFirst()
            .orElse(null);
    }

    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static String lerLinha() {

        try {

            return sc.nextLine().trim();

        } catch (Exception e) {

            return "";

        }
    }

    private static void tratarErroIdioma() {

        System.out.println("Erro! Valor inválido. Escolha:\n1- Fechar Sistema\n2- Continuar em Português");
        String escolhaErro = lerLinha();

        if ("1".equals(escolhaErro)) {

            System.out.println("Encerrando Sistema...");
            System.exit(0);

        } else {

            idiomaAtual = idiomasDisponiveis.buscarPorCodigo("pt");
        }
    }

    private static void exibirTitulo(String titulo) {
        
        int largura = 50;
        String borda = repeatChar('═', largura);
        String linhaCentral = String.format("║%-50s║", "  " + titulo);

        System.out.println("\n╔" + borda + "╗");
        System.out.println(linhaCentral);
        System.out.println("╚" + borda + "╝\n");
        
    }

    private static void pausarExecucao() {

        System.out.print("\nPressione Enter para continuar...");

    }

    private static void separador() {

        System.out.println("\n" + repeatChar('═', 52));

    }

    private static void exibirMenu() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                 MENU ORIENTAÇÕES                 ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Cadastrar nova Orientação                    ║");
        System.out.println("║ 2 - Editar uma Orientação                        ║");
        System.out.println("║ 3 - Excluir uma Orientação                       ║");
        System.out.println("║ 4 - Pesquisar uma Orientação                     ║");
        System.out.println("║ 5 - Listar as Orientações Existentes             ║");
        System.out.println("║ 6 - Sair                                         ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

    }

    private static void escolherIdiomaInicial() {

        System.out.print("\nEscolha o Idioma Inicial:\n");
        List<Idioma> idiomas = idiomasDisponiveis.getListaIdiomas();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                  ESCOLHA IDIOMA                  ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Português                                    ║");
        System.out.println("║ 2 - Inglês                                       ║");
        System.out.println("║ 3 - Espanhol                                     ║");
        System.out.println("║ 4 - Alemão                                       ║");
        System.out.println("║ 5 - Mandarim                                     ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String entradaIdioma = lerLinha();

        try {
            int escolhaIdioma = Integer.parseInt(entradaIdioma);

            if (escolhaIdioma >= 1 && escolhaIdioma <= idiomas.size()) {

                idiomaAtual = idiomas.get(escolhaIdioma - 1);

            } else {

                tratarErroIdioma();

            }

        } catch (NumberFormatException e) {

            tratarErroIdioma();
            
        }

        System.out.println("\nIdioma selecionado: " + idiomaAtual.getNome());
        separador();
        
    }

    private static void menuPrincipal() {

        while (true) {
            
            try {

                sc.nextLine(); // Limpa o buffer do scanner
                
                exibirMenu();

                String escolhaMenu = sc.nextLine().trim();

                switch (escolhaMenu) {
                    case "1":
                    
                        exibirTitulo("Cadastrar Nova Orientação");
                        cadastrarOrientacao();

                        break;
                 
                    case "2":
                    
                        exibirTitulo("Editar Orientação");
                        editarOrientacao();

                        break;

                    case "3":
                    
                        exibirTitulo("Excluir Orientação");
                        excluirOrientacao();

                        break;
                
                    case "4":

                        exibirTitulo("Pesquisar Orientação");
                        pesquisarOrientacao();

                        break;

                    case "5":
                    
                        exibirTitulo("Listar Orientações Existentes");
                        listarOrientacoes();

                        break;
                
                    case "6":
                    
                        exibirTitulo("Sair do Sistema");
                        System.out.println("\nEncerrando Sistema...."); 

                        return;

                    default:

                        System.out.println("Erro! Insira uma Opção válida do Menu!");

                        continue;
                }

            } catch (Exception e) {
                
                System.out.println("Erro! Ocorreu um erro inesperado " + e.getMessage() + "\nPor favor, tente novamente.");

            } finally {

                pausarExecucao();

            }

        }

    }

    // Métodos CRUD

    // Método para o Cadastro de Orientações

    private static void cadastrarOrientacao() {

        System.out.print("Código da nova orientação: ");
        String codigoCadastrar = sc.nextLine();

        // Selecionar tipo
        System.out.println("Escolha o tipo:");
        List<TipoOrientacao> tipos = tipoOrientacoesDisponiveis.getListaOrientacoesDisponiveis();
        
        for (int i = 0; i < tipos.size(); i++) {

            System.out.printf("%d- Nome: %-35s | Código: %s\n", i + 1, tipos.get(i).getNome(idiomaAtual), tipos.get(i).getCodigo());

        }

        System.out.print("\nDigite o código do tipo desejado: ");

        String codigoTipoEscolhido = sc.next();

        sc.nextLine(); // limpar buffer

        TipoOrientacao tipoSelecionado = tipoOrientacoesDisponiveis.buscarPorCodigo(codigoTipoEscolhido);

        if (tipoSelecionado == null) {

            System.out.println("\nErro! Tipo de orientação não encontrado.");

            return;

        }

        Orientacao novaOrientacao = new Orientacao(codigoCadastrar, tipoSelecionado);

        // Adicionando títulos e conteúdos para cada idioma
        for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

            System.out.print("\nTítulo em " + idioma.getNome() + ": ");
            novaOrientacao.adicionarTitulo(idioma, sc.nextLine());

            System.out.print("Conteúdo em " + idioma.getNome() + ": ");
            novaOrientacao.adicionarConteudo(idioma, sc.nextLine());
        }

        listaOrientacoes.add(novaOrientacao);
        
        System.out.println("\nSucesso! Orientação cadastrada com sucesso!");
        
    }

    // Método para a Edição de Orientações

    private static void editarOrientacao() {

        System.out.print("\nDigite o código da orientação a ser editada: ");
        String codigoOrientacao = sc.nextLine();

        Orientacao orientacaoEdicao = buscarPorCodigo(codigoOrientacao);

        if (orientacaoEdicao == null) {

            System.out.println("\nErro! Tipo de orientação não encontrado.");

            return;

        }

        for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

            System.out.print("\nNovo título | Idioma Sistema [" + idioma.getNome() + "] (deixe vazio para manter): ");
            String novoTitulo = sc.nextLine();

            if (!novoTitulo.isEmpty()) orientacaoEdicao.adicionarTitulo(idioma, novoTitulo); // Adiciona o novo título

            System.out.print("\nNovo conteúdo | Idioma Sistema [" + idioma.getNome() + "] (deixe vazio para manter): ");
            String novoConteudo = sc.nextLine();

            if (!novoConteudo.isEmpty()) orientacaoEdicao.adicionarConteudo(idioma, novoConteudo); // Adiciona o novo conteúdo

        }

        System.out.println("\nSucesso! Orientação atualizada.");

    }

    // Método para a Exlusão de Orientações

    private static void excluirOrientacao() {

        System.out.print("\nDigite o código da orientação a excluir: ");
        String codigoOrientacaoExclusao = sc.nextLine();

        Orientacao orientacaoExclusao = buscarPorCodigo(codigoOrientacaoExclusao);

        if (orientacaoExclusao != null) {

            listaOrientacoes.remove(orientacaoExclusao);
            System.out.println("\nSucesso! Orientação removida com sucesso.");

        } else {

            System.out.println("Erro! Orientação não encontrada.");

        }

    }

    // Método para a Pesquisa de Orientações

    private static void pesquisarOrientacao() {

        System.out.print("\nDigite o titulo da Orientação para buscar (Uma parte já é Suficiente): ");
        String pesquisa = sc.nextLine().toLowerCase();

        boolean encontrou = false;

        System.out.println("\nResultados da busca:");

        for (Orientacao orientacaoPesquisa : listaOrientacoes) {

            for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

                String titulo = orientacaoPesquisa.getTitulo(idioma);

                if (titulo.toLowerCase().contains(pesquisa)) {

                    System.out.printf("- [%-10s] Código: %-8s | Titulo: %s\n", idioma.getNome(), orientacaoPesquisa.getCodigo(), titulo);

                    encontrou = true;

                }
            }
        }

        if (!encontrou) {

            System.out.println("Nenhum resultado encontrado para a pesquisa: " + pesquisa);
        }

    }

    // Método para listar todas as Orientações cadastradas

    private static void listarOrientacoes() {

        if (listaOrientacoes.isEmpty()) {

            System.out.println("Erro! Nenhuma orientação cadastrada.");

            return;

        }

        System.out.println("\nTodas as orientações cadastradas:");

        for (Orientacao o : listaOrientacoes) {

            System.out.println("\nCódigo: " + o.getCodigo());
            System.out.println("Tipo: " + o.getTipo().getNome(idiomaAtual));
            System.out.println("Título: " + o.getTitulo(idiomaAtual));
            System.out.println("Conteúdo: " + o.getConteudo(idiomaAtual));

        }
    }
}