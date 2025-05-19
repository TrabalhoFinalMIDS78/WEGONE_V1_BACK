package br.com.wegone.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.wegone.exception.DadosIncompletosException;
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

    // Tratamento de Excessões

    // Métodos para tratamento de erros de entrada do usuário

    // Tratamento de Erro para Inputs Vazios
    public static void validarInputVazio(String texto) {

        if (texto == null || texto.isBlank()) {

            throw new DadosIncompletosException("Informação não pode estar vazia.");

        }

    }

    // Método para tratamento de erro de idioma (escolha do idioma inicial)
    private static void tratarErroIdioma() {

        exibirTitulo("Erro Idioma");

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║          Erro! Valor inválido. Escolha:          ║ ");
        System.out.println("║                                                  ║ ");
        System.out.println("║ 1- Fechar Sistema                                ║");
        System.out.println("║ 2- Tentar Novamente                              ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String escolhaErro = lerLinha();

        if ("1".equals(escolhaErro)) {

            exibirTitulo("Encerrando Sistema...");
            System.exit(0);

        } else {

            escolherIdiomaInicial(); // Chama o método novamente para escolher o idioma

        }
    }

    // Método para tratamento de erro de menu (escolha do menu)
    private static void tratarErroMenu() {

        exibirTitulo("Erro Menu Principal");

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║          Erro! Valor inválido. Escolha:          ║ ");
        System.out.println("║                                                  ║ ");
        System.out.println("║ 1- Fechar Sistema                                ║");
        System.out.println("║ 2- Tentar Novamente                              ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String escolhaErro = lerLinha();

        if ("1".equals(escolhaErro)) {

            exibirTitulo("Encerrando Sistema...");
            System.exit(0);

        } else {

            menuPrincipal(); // Chama o método novamente para escolher o idioma
        }
    }

    // Método para tratamento de erro de Cadastro de Orientações (escolha do menu de
    // Cadastro de Orientações)

    private static void tratarErroCadastro() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║      Erro Cadastro da Orientação. Escolha:       ║ ");
        System.out.println("║                                                  ║ ");
        System.out.println("║ 1- Fechar Sistema                                ║");
        System.out.println("║ 2- Tentar Novamente                              ║");
        System.out.println("║ 3- Voltar ao Menu Principal                      ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String escolhaErro = lerLinha();

        if ("1".equals(escolhaErro)) {

            exibirTitulo("Encerrando Sistema...");
            System.exit(0);

        } else if ("2".equals(escolhaErro)) {

            cadastrarOrientacao(); // Chama o método novamente para cadastrar uma nova orientação

        } else {

            menuPrincipal(); // Chama o método novamente para escolher o idioma

        }

    }

    private static void tratarErroEdicao() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║       Erro Edição da Orientação. Escolha:        ║ ");
        System.out.println("║                                                  ║ ");
        System.out.println("║ 1- Fechar Sistema                                ║");
        System.out.println("║ 2- Tentar Novamente                              ║");
        System.out.println("║ 3- Voltar ao Menu Principal                      ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String escolhaErro = lerLinha();

        if ("1".equals(escolhaErro)) {

            exibirTitulo("Encerrando Sistema...");
            System.exit(0);

        } else if ("2".equals(escolhaErro)) {

            editarOrientacao(); // Chama o método novamente para editar uma nova orientação

        } else {

            menuPrincipal(); // Chama o método novamente para escolher o idioma

        }

    }

    private static void tratarErroExclusao() {

        exibirTitulo("Erro Exclusão de Orientação");

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║      Erro Exclusão da Orientação. Escolha:       ║ ");
        System.out.println("║                                                  ║ ");
        System.out.println("║ 1- Fechar Sistema                                ║");
        System.out.println("║ 2- Tentar Novamente                              ║");
        System.out.println("║ 3- Voltar ao Menu Principal                      ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String escolhaErro = lerLinha();

        if ("1".equals(escolhaErro)) {

            exibirTitulo("Encerrando Sistema...");
            System.exit(0);

        } else if ("2".equals(escolhaErro)) {

            excluirOrientacao(); // Chama o método novamente para excluir uma nova orientação

        } else {

            menuPrincipal(); // Chama o método novamente para escolher o idioma

        }

    }

    public static void validarCodigoCadastro(String codigo) throws DadosIncompletosException {

        validarInputVazio(codigo);

        if (!Character.isDigit(codigo.charAt(0))) {

            throw new DadosIncompletosException("\n╔══════════════════════════════════════════════════╗"
                                                + "\n║    Código da Orientação deve obrigatoriamente    ║"
                                                + "\n║              começar com um número.              ║"
                                                + "\n╚══════════════════════════════════════════════════╝\n");

        }

        if (buscarPorCodigo(codigo) != null) {

            throw new DadosIncompletosException("\n╔══════════════════════════════════════════════════╗"
                                                + "\n║    Já existe uma orientação com esse código.     ║"
                                                + "\n╚══════════════════════════════════════════════════╝\n");

        }
        
    }

    // Métodos para exibir mensagens e separadores (auxiliares para a visualização
    // do Usuário no Terminal)

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

    public static void exibirIdiomas() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                  ESCOLHA IDIOMA                  ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Português                                    ║");
        System.out.println("║ 2 - Inglês                                       ║");
        System.out.println("║ 3 - Espanhol                                     ║");
        System.out.println("║ 4 - Alemão                                       ║");
        System.out.println("║ 5 - Mandarim                                     ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

    }

    private static void escolherIdiomaInicial() {

        System.out.print("\nEscolha o Idioma Inicial:\n");
        List<Idioma> idiomas = idiomasDisponiveis.getListaIdiomas();

        exibirIdiomas(); // Mostra os idiomas do sistema

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

            exibirMenu();

            String entradaMenu = sc.nextLine().trim();

            try {

                int escolhaMenu = Integer.parseInt(entradaMenu);

                if (escolhaMenu < 1 || escolhaMenu > 6) {

                    tratarErroMenu();

                } else {

                    switch (escolhaMenu) {
                        case 1:

                            exibirTitulo("Cadastrar Nova Orientação");
                            cadastrarOrientacao();

                            break;

                        case 2:

                            exibirTitulo("Editar Orientação");
                            editarOrientacao();

                            break;

                        case 3:

                            exibirTitulo("Excluir Orientação");
                            excluirOrientacao();

                            break;

                        case 4:

                            exibirTitulo("Pesquisar Orientação");
                            pesquisarOrientacao();

                            break;

                        case 5:

                            exibirTitulo("Listar Orientações Existentes");
                            listarOrientacoes();

                            break;

                        case 6:

                            exibirTitulo("Sair do Sistema");
                            exibirTitulo("Encerrando Sistema...");

                            System.exit(0);

                            break;

                        default:

                            exibirTitulo("Erro! Insira uma Opção válida do Menu!");

                            continue;

                    }

                }

            } catch (NumberFormatException e) {

                tratarErroMenu();

            } finally {

                separador();

            }

        }

    }

    // Métodos CRUD

    // Método para o Cadastro de Orientações

    // Método auxiliar para chamar as Orientações Disponíveis
    private static void escolhaTipoOrientacao() {

        List<TipoOrientacao> tipos = tipoOrientacoesDisponiveis.getListaOrientacoesDisponiveis();

        for (int i = 0; i < tipos.size(); i++) {

            System.out.printf("%d- Nome: %-35s | Código: %s\n", i + 1, tipos.get(i).getNome(idiomaAtual),
                    tipos.get(i).getCodigo());

        }

    }
    
    private static void cadastrarOrientacao() {

        exibirTitulo("Código da nova orientação: ");
        String codigoCadastrar = lerLinha();

        try {

            validarCodigoCadastro(codigoCadastrar);

        } catch(DadosIncompletosException e) {

            exibirTitulo("Erro no Cadastro! " + e.getMessage());

            tratarErroCadastro();
            return;

        }

        // Selecionar tipo

        exibirTitulo("Escolha o tipo:");

        escolhaTipoOrientacao();

        System.out.print("\nDigite o código do tipo desejado: ");

        String codigoTipoEscolhido = sc.next();

        sc.nextLine(); // limpar buffer

        TipoOrientacao tipoSelecionado = tipoOrientacoesDisponiveis.buscarPorCodigo(codigoTipoEscolhido);

        if (tipoSelecionado == null) {
            
            exibirTitulo("\nErro! Tipo de orientação não encontrado.");

            tratarErroCadastro();
            return;

        }

        Orientacao novaOrientacao = new Orientacao(codigoCadastrar, tipoSelecionado);

        // Adicionando títulos e conteúdos para cada idioma
        for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

            // Título da Orientação
            exibirTitulo("\nTítulo em " + idioma.getNome() + ": ");

            String titulo = sc.nextLine();

            try {

                validarInputVazio(titulo); // Verifica se o título é vazio ou nulo.

            } catch (DadosIncompletosException e) {

                exibirTitulo("\nErro! " + e.getMessage());

                tratarErroCadastro();
                return;

            }

            novaOrientacao.adicionarTitulo(idioma, titulo);

            // Conteúdo da Orientação
            exibirTitulo("Conteúdo em " + idioma.getNome() + ": ");

            String conteudo = sc.nextLine();

            try {

                validarInputVazio(conteudo); // Verifica se o título é vazio ou nulo.

            } catch (DadosIncompletosException e) {

                exibirTitulo("\nErro! " + e.getMessage());

                tratarErroCadastro();
                return;

            }

            novaOrientacao.adicionarConteudo(idioma, conteudo);

        }

        listaOrientacoes.add(novaOrientacao);

        exibirTitulo("Sucesso! Orientação cadastrada com sucesso!");

    }

    // Método para a Edição de Orientações

    private static void editarOrientacao() {

        System.out.print("\nDigite o código da orientação a ser editada: ");
        String codigoOrientacao = sc.nextLine();

        // Validação do Código da Orientação para Edição
        try {
            
            validarInputVazio(codigoOrientacao); // Verifica se o código é vazio ou nulo.

        } catch (DadosIncompletosException e) {

            System.out.println("\nErro! " + e.getMessage());
            tratarErroEdicao();

        }

        Orientacao orientacaoEdicao = buscarPorCodigo(codigoOrientacao); // Busca a orientação pelo código

        // Validação da existência da orientação para Edição
        try {
            
            if (orientacaoEdicao == null) {
                 throw new DadosIncompletosException("\n╔══════════════════════════════════════════════════╗"
                                                   + "\n║      Código da orientação não encontrado.        ║"
                                                   + "\n╚══════════════════════════════════════════════════╝\n");
            }

        } catch (DadosIncompletosException e) {

            exibirTitulo("\nErro! " + e.getMessage());
            tratarErroEdicao();

        }

        // Validação da Orientação para Edição

        exibirTitulo("\nOrientação Encontrada.");

        exibirTitulo("Validação Orientação para Edição"); // Titulo para Validação da Orientação

        exibirTitulo("Código: " + orientacaoEdicao.getCodigo());
        exibirTitulo("Tipo: " + orientacaoEdicao.getTipo().getNome(idiomaAtual));
        exibirTitulo("Título: " + orientacaoEdicao.getTitulo(idiomaAtual));
        exibirTitulo("Conteúdo: " + orientacaoEdicao.getConteudo(idiomaAtual));

        // Confirmação que a Orientação está correta
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║ 1 - Confirmar Orientação                         ║");
        System.out.println("║ 2 - Orientação Incorreta                         ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String inputConfirmacaoEdicao = sc.nextLine();

        // Validação do Input para escolha de Edição
        try {
            
            validarInputVazio(inputConfirmacaoEdicao); // Verifica se o Input é vazio ou nulo.

        } catch (DadosIncompletosException e) {

            exibirTitulo("\nErro! " + e.getMessage());
            tratarErroEdicao();

        }

        if (inputConfirmacaoEdicao.equals("1")) {

            exibirTitulo("Orientação confirmada para edição\n");

            for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

                System.out.printf("\nIdioma Sistema: %s\n", idioma.getNome());
                System.out.printf("%-40s: ", "Novo título (vazio para manter)");

                String novoTitulo = sc.nextLine();

                if (!novoTitulo.isEmpty()) {

                    orientacaoEdicao.adicionarTitulo(idioma, novoTitulo);

                }

                System.out.printf("%-40s: ", "Novo conteúdo (vazio para manter)");
                String novoConteudo = sc.nextLine();

                if (!novoConteudo.isEmpty()) {

                    orientacaoEdicao.adicionarConteudo(idioma, novoConteudo);

                }

            }

        } else if (inputConfirmacaoEdicao.equals("2")) {

            exibirTitulo("Orientação não confirmada para edição");

            tratarErroEdicao();

            return;

        } else {

            exibirTitulo("Erro! Opção inválida.");

            tratarErroEdicao();

            return;
            
        }

        exibirTitulo("Sucesso! Orientação atualizada com sucesso!");

    }

    // Método para a Exclusão de Orientações

    private static void excluirOrientacao() {

        exibirTitulo("Digite o código da orientação a excluir: ");
        String codigoOrientacaoExclusao = sc.nextLine();

        Orientacao orientacaoExclusao = buscarPorCodigo(codigoOrientacaoExclusao);

        try {

            validarInputVazio(codigoOrientacaoExclusao);
            
        } catch (DadosIncompletosException e) {
            
            exibirTitulo("\nErro! " + e.getMessage());
            tratarErroExclusao();
            
            return;

        }

        try {

            if (orientacaoExclusao == null) {
                throw new DadosIncompletosException("\n╔══════════════════════════════════════════════════╗"
                                                  + "\n║      Código da orientação não encontrado.        ║"
                                                  + "\n╚══════════════════════════════════════════════════╝\n");
            }
            
        } catch (DadosIncompletosException e) {
            
            exibirTitulo("Erro! " + e.getMessage());
            tratarErroExclusao();

            return;

        }

        // Validação da Orientação para Exclusão

        exibirTitulo("Orientação encontrada");

        exibirTitulo("Validação Orientação para Exclusão"); // Titulo para Validação da Orientação

        exibirTitulo("Código: " + orientacaoExclusao.getCodigo());
        exibirTitulo("Tipo: " + orientacaoExclusao.getTipo().getNome(idiomaAtual));
        exibirTitulo("Título: " + orientacaoExclusao.getTitulo(idiomaAtual));
        exibirTitulo("Conteúdo: " + orientacaoExclusao.getConteudo(idiomaAtual));

        // Confirmação que a Orientação está correta
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║ 1 - Confirmar Orientação                         ║");
        System.out.println("║ 2 - Orientação Incorreta                         ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String inputConfirmacaoExclusao = sc.nextLine();

        // Validação do Input para escolha de Exclusão
        try {
            
            validarInputVazio(inputConfirmacaoExclusao); // Verifica se o Input é vazio ou nulo.

        } catch (DadosIncompletosException e) {

            exibirTitulo("\nErro! " + e.getMessage());
            tratarErroExclusao();

        }

        if (inputConfirmacaoExclusao.equals("1")) {

            exibirTitulo("Orientação confirmada para exclusão");

            listaOrientacoes.remove(orientacaoExclusao);

        } else if (inputConfirmacaoExclusao.equals("2")) {

            exibirTitulo("Orientação não confirmada para exclusão");
            tratarErroExclusao();

            return;

        } else {

            exibirTitulo("Erro! Opção inválida.");
            tratarErroExclusao();

            return;

        }

        exibirTitulo("Sucesso! Orientação removida com sucesso! ");

    }

    // Método para a Pesquisa de Orientações

    private static void pesquisarOrientacao() {

        System.out.print("\nDigite o titulo da Orientação para buscar (Uma parte já é Suficiente): ");
        String pesquisa = sc.nextLine().toLowerCase();

        boolean encontrou = false;

        exibirTitulo("Resultados da busca:");

        for (Orientacao orientacaoPesquisa : listaOrientacoes) {

            for (Idioma idioma : idiomasDisponiveis.getListaIdiomas()) {

                String titulo = orientacaoPesquisa.getTitulo(idioma);

                if (titulo.toLowerCase().contains(pesquisa)) {

                    System.out.printf("- [%-10s] Código: %-8s | Titulo: %s\n", idioma.getNome(),
                            orientacaoPesquisa.getCodigo(), titulo);

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

            exibirTitulo("Erro! Nenhuma orientação cadastrada.");

            return;

        }

        exibirTitulo("Todas as orientações cadastradas:");

        for (Orientacao o : listaOrientacoes) {

            exibirTitulo("\nCódigo: " + o.getCodigo());
            exibirTitulo("Tipo: " + o.getTipo().getNome(idiomaAtual));
            exibirTitulo("Título: " + o.getTitulo(idiomaAtual));
            exibirTitulo("Conteúdo: " + o.getConteudo(idiomaAtual));

        }
    }
}