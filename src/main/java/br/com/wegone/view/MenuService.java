package br.com.wegone.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import br.com.wegone.service.IdiomaMensagens;
import br.com.wegone.service.IdiomaService;
import br.com.wegone.service.OrientacaoService;
import br.com.wegone.service.ValidadorService;
import br.com.wegone.core.*;
import br.com.wegone.model.Idioma;
import br.com.wegone.model.TipoOrientacao;
import br.com.wegone.model.TipoOrientacoesDisponiveis;
import br.com.wegone.model.Usuario;
import br.com.wegone.exception.*;
import br.com.wegone.model.Orientacao;
import br.com.wegone.view.AuxiliarDeConsole;

public class MenuService {

    private static final Logger LOGGER = Logger.getLogger(MenuService.class.getName());
    private static final IdiomaMensagens mensagem = new IdiomaMensagens();
    private static final String MAIN_INICIAR_IDIOMA = "main.iniciar.idioma";
    private static final int LARGURA_MENU = 50;
    private static final int LARGURA_MENU_ORIENTACAO = 65;

    private static OrientacaoService orientacaoService = new OrientacaoService();
    private static IdiomaService idiomaService = new IdiomaService();
    private static TipoOrientacoesDisponiveis tipoOrientacoesDisponiveis = new TipoOrientacoesDisponiveis(
            idiomaService);

    public static void sairSistema() {

        AuxiliarDeConsole.exibirTitulo(mensagem.get("main.sair.titulo"));

        LOGGER.info("\n" + mensagem.get("main.sair.sistema"));

        System.exit(0);

    }

    public static void erroGenerico() {
        while (true) {
            LOGGER.info("\n╔══════════════════════════════════════════════════╗");
            LOGGER.info(
                    "║" + AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.erro.generico.titulo"), LARGURA_MENU)
                            + "║");
            LOGGER.info("╠══════════════════════════════════════════════════╣");
            LOGGER.info(
                    "║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.erro.generico.tentar.novamente"),
                            LARGURA_MENU) + "║");
            LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.erro.generico.sair"), LARGURA_MENU)
                    + "║");
            LOGGER.info("╚══════════════════════════════════════════════════╝\n");

            LOGGER.info(mensagem.get("menu.escolha")); // Ex: "Escolha uma opção: "
            String escolha = AuxiliarDeConsole.lerLinha();

            switch (escolha) {
                case "1":
                    // Tentar novamente: pode chamar o menu principal ou outro método conforme o
                    // contexto
                    selecionarMenuPrincipal();
                    return; // Sai do loop e do método
                case "0":
                    sairSistema(); // Encerra o sistema
                default:
                    LOGGER.warning(mensagem.get("main.input.invalido"));
                    // O loop continua até o usuário digitar uma opção válida
            }
        }
    }

    // Exibir Logo

    public static void exibirLogo() {
        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        LOGGER.info("║                                                  ║");
        LOGGER.info("║            ██     ██ ███████  ██████             ║");
        LOGGER.info("║            ██     ██ ██      ██                  ║");
        LOGGER.info("║            ██  █  ██ █████   ██                  ║");
        LOGGER.info("║            ██ ███ ██ ██      ██    ██            ║");
        LOGGER.info("║             ███ ███  ███████  ██████             ║");
        LOGGER.info("║                                                  ║");
        LOGGER.info("║      Multilingual Guidance System - WEGONE       ║");
        LOGGER.info("║        Version 1.0 - Developed by MIDS78         ║");
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");

        try {
            Thread.sleep(1500); // Pausa por 1,5 segundos
        } catch (InterruptedException e) {
            LOGGER.severe("Error in sleep: " + e.getMessage());
        }

    }

    // Tratar Erro Escolha de Acesso

    public static void selecionarIdioma() {

        boolean idiomaSelecionadoComSucesso = false;
        while (!idiomaSelecionadoComSucesso) {

            LOGGER.info("\n╔══════════════════════════════════════════════════╗");
            LOGGER.info("║               CHOOSE YOUR LANGUAGE               ║");
            LOGGER.info("╠══════════════════════════════════════════════════╣");
            LOGGER.info("║ 1 - Português                                    ║");
            LOGGER.info("║ 2 - English                                      ║");
            LOGGER.info("║ 3 - Espanõl                                      ║");
            LOGGER.info("║ 4 - Deutsch                                      ║");
            LOGGER.info("║ 5 - 中文 (Zhōngwén)                              ║");
            LOGGER.info("╚══════════════════════════════════════════════════╝\n");

            LOGGER.info("\nChoose your language: "); // Mensagem de seleção de idioma
            String escolha = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

            int escolhaInt;
            try {
                escolhaInt = Integer.parseInt(escolha); // Converter escolha para inteiro
            } catch (NumberFormatException nfe) {
                LOGGER.warning("Invalid input. Please enter a number.");
                continue;
            }

            // Validar escolha

            try {

                ValidadorService.validarInputVazio(escolha); // Validar se a escolha não está vazia

                if (escolhaInt < 1 || escolhaInt > 5) {

                    LOGGER.warning("\n╔══════════════════════════════════════════════════╗");
                    LOGGER.warning("║             ❌ Error! Invalid Language          ║");
                    LOGGER.warning("╠══════════════════════════════════════════════════╣");
                    LOGGER.warning("║ 1 - Try Again                                    ║");
                    LOGGER.warning("║ 2 - Start with Brazilian Portuguese              ║");
                    LOGGER.warning("║ 0 - Exit                                         ║");
                    LOGGER.warning("╚══════════════════════════════════════════════════╝\n");

                    LOGGER.info("\nChoose: ");
                    String escolhaErroIdioma = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

                    switch (escolhaErroIdioma) {
                        case "1":
                            // Tentar novamente
                            break;
                        case "2":
                            // Iniciar com Português
                            IdiomaSelecionado.setLocale("1"); // Definir escolha como Português
                            AuxiliarDeConsole.exibirTitulo(AuxiliarDeConsole
                                    .centralizarTexto(mensagem.get(MAIN_INICIAR_IDIOMA), LARGURA_MENU));
                            idiomaSelecionadoComSucesso = true;
                            break;
                        case "0":
                            AuxiliarDeConsole.exibirTitulo("Close System");
                            System.out.println("\nClosing System....");

                            System.exit(0);
                            break;
                        default:
                            LOGGER.warning("Invalid choice. Please try again.");
                            break;
                    }

                } else {

                    switch (escolha) {

                        case "1":
                        case "2":
                        case "3":
                        case "4":
                        case "5":
                            IdiomaSelecionado.setLocale(escolha); // Definir idioma
                            AuxiliarDeConsole.exibirTitulo(AuxiliarDeConsole
                                    .centralizarTexto(mensagem.get(MAIN_INICIAR_IDIOMA), LARGURA_MENU));
                            idiomaSelecionadoComSucesso = true;
                            break;
                        default:
                            break;
                    }

                }

            } catch (DadosIncompletosException e) {

                LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());

            }

        }

    }

    public static void selecionarMenuPrincipal() {

        boolean opcaoSelecionadaComSucesso = false;
        while (!opcaoSelecionadaComSucesso) {

            LOGGER.info("\n╔══════════════════════════════════════════════════╗");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.principal.titulo"), LARGURA_MENU) + "║");
            LOGGER.info("╠══════════════════════════════════════════════════╣");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.principal.cadastrar"), LARGURA_MENU)
                    + "║");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.principal.editar"), LARGURA_MENU) + "║");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.principal.excluir"), LARGURA_MENU) + "║");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.principal.pesquisar"), LARGURA_MENU)
                    + "║");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.principal.listar"), LARGURA_MENU) + "║");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.principal.trocar.idioma"), LARGURA_MENU) + "║");
            LOGGER.info(() -> "║"
                    + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.principal.sair"), LARGURA_MENU) + "║");
            LOGGER.info("╚══════════════════════════════════════════════════╝\n");

            AuxiliarDeConsole.escolha(); // Mensagem de escolha de acesso

            String escolha = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

            int escolhaInt;
            try {
                escolhaInt = Integer.parseInt(escolha); // Converter escolha para inteiro
            } catch (NumberFormatException nfe) {
                LOGGER.warning(mensagem.get("main.opcao.invalida"));
                continue;
            }

            // Validar escolha

            try {

                ValidadorService.validarInputVazio(escolha); // Validar se a escolha não está vazia

                if (escolhaInt < 0 || escolhaInt > 6) {

                    LOGGER.info("\n╔══════════════════════════════════════════════════╗");
                    LOGGER.info("║" + AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.tratar.erro.menu.orientacao.titulo"), LARGURA_MENU) + "║");
                    LOGGER.info("╠══════════════════════════════════════════════════╣");
                    LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                            mensagem.get("menu.tratar.erro.metodo.tentar.novamente"), LARGURA_MENU) + "║");
                    LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                            mensagem.get("menu.tratar.erro.metodo.fechar.sistema"), LARGURA_MENU) + "║");
                    LOGGER.info("╚══════════════════════════════════════════════════╝\n");

                    AuxiliarDeConsole.escolha();
                    String escolhaErroMenuPrincipal = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

                    switch (escolhaErroMenuPrincipal) {
                        case "1":
                            // Tentar novamente

                            break;
                        case "0":
                            sairSistema(); // Sair do sistema
                            break;
                        default:
                            LOGGER.warning(mensagem.get("main.opcao.invalidao"));
                            break;
                    }

                } else {

                    switch (escolha) {

                        case "1":

                            cadastrarOrientacao();
                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "2":

                            editarOrientacao();

                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "3":

                            excluirOrientacao();

                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "4":

                            pesquisarOrientacao();

                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "5":

                            listarOrientacoes();

                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "6":

                            selecionarIdioma();

                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "0":

                            sairSistema(); // Sair do sistema

                            break;
                        default:
                            break;
                    }

                }

            } catch (DadosIncompletosException e) {

                LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());

            } finally {

                AuxiliarDeConsole.separador(); // Separador após cada operação

            }

        }

    }

    // Métodos adicionais

    private static Orientacao buscarPorCodigo(String codigo) {
        return orientacaoService.getListaOrientacoes().stream()
                .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    private static TipoOrientacao escolhaTipoOrientacao() {

        List<TipoOrientacao> tipos = tipoOrientacoesDisponiveis.getListaOrientacoesDisponiveis();

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(
                        mensagem.get("menu.exibir.titulo.tipos_orientacoes") + " ("
                                + IdiomaSelecionado.getIdiomaAtualNome() + ")",
                        LARGURA_MENU));

        for (int i = 0; i < tipos.size(); i++) {
            TipoOrientacao tipo = tipos.get(i);
            String template = "%d - %s %-35s | %s (%s)%n";
            LOGGER.info(String.format(
                    template,
                    i + 1,
                    mensagem.get("menu.exibir.nome.tipos_orientacoes"),
                    tipo.getNome(IdiomaSelecionado.getIdiomaAtualObjeto()),
                    mensagem.get("menu.exibir.codigo.tipos_orientacoes"),
                    tipo.getCodigo()));
        }

        LOGGER.info("\n" + mensagem.get("menu.exibir.selecionar.tipo_orientacao") + ": ");
        String input = AuxiliarDeConsole.lerLinha();
        int idx;

        try {

            idx = Integer.parseInt(input);

        } catch (NumberFormatException e) {

            LOGGER.warning(mensagem.get("menu.tratar.erro.input.invalido"));
            erroGenerico();
            return null;

        }

        if (idx < 1 || idx > tipos.size()) {

            LOGGER.warning(mensagem.get("menu.tratar.erro.input.invalido") + "%n");

            erroGenerico();

            return null;

        }

        return tipos.get(idx - 1);

    }

    private static void confirmarOrientacao() {

        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.prompt.confirmar"), LARGURA_MENU) + "║"); // 1- Confirmar
        LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.prompt.nao_confirmar"), LARGURA_MENU) + "║"); // 2- Incorreta
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");

        AuxiliarDeConsole.escolha();

    }

    // Métodos de Cadastro, Edição, Exclusão, Pesquisa e Listagem

    // Método para Cadastrar Orientação
    public static void cadastrarOrientacao() {

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.exibir.cadastro.titulo"), LARGURA_MENU));

        String codigo;
        while (true) {
            System.out.print(mensagem.get("menu.prompt.codigo") + ": ");
            codigo = AuxiliarDeConsole.lerLinha();
            try {
                ValidadorService.validarCodigoCadastro(codigo, OrientacaoService.getListaOrientacoes());
                break;
            } catch (DadosIncompletosException e) {
                LOGGER.warning(e.getMessage());

                erroGenerico();
            }
        }

        TipoOrientacao tipo = escolhaTipoOrientacao();
        if (tipo == null) {
            LOGGER.warning(mensagem.get("menu.tratar.erro.input.invalido"));

            erroGenerico();

            return;
        }

        Map<Idioma, String> titulos = new LinkedHashMap<>();
        Map<Idioma, String> conteudos = new LinkedHashMap<>();

        for (Idioma idioma : idiomaService.getListaIdiomas()) {
            String t, c;

            while (true) {
                LOGGER.info(mensagem.get("menu.prompt.titulo") + " " + idioma.getNome() + ": ");
                t = AuxiliarDeConsole.lerLinha();
                if (t == null || t.isBlank()) {
                    LOGGER.warning(
                            mensagem.get("exception.orientacao.cadastro.titulo.vazio") + " " + idioma.getNome());
                    continue;
                }
                break;
            }
            while (true) {
                LOGGER.info(mensagem.get("menu.prompt.conteudo") + " " + idioma.getNome() + ": ");
                c = AuxiliarDeConsole.lerLinha();
                if (c == null || c.isBlank()) {
                    LOGGER.warning(
                            mensagem.get("exception.orientacao.cadastro.conteudo.vazio") + " " + idioma.getNome());
                    continue;
                }
                break;
            }

            titulos.put(idioma, t);
            conteudos.put(idioma, c);

        }

        try {

            OrientacaoService.cadastrarOrientacao(codigo, tipo, titulos, conteudos);
            LOGGER.info(mensagem.get("exception.orientacao.cadastro.sucesso"));

        } catch (DadosIncompletosException e) {
            LOGGER.warning(e.getMessage());

            erroGenerico();

        }

        Orientacao orientacao = buscarPorCodigo(codigo);

        if (orientacao != null) {
            exibirDetalhesOrientacaoPorCadaIdioma(orientacao); // Mostrar detalhes da orientação cadastrada
        } else {
            LOGGER.warning(mensagem.get("exception.orientacao.pesquisa.codigo_nao_encontrado") + " " + codigo);
        }

    }

    // Método para Editar Orientação
    public static void editarOrientacao() {

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(
                        mensagem.get("menu.exibir.edicao.titulo"),
                        LARGURA_MENU));

        String codigo = null;

        // 1. Solicitar código da orientação
        while (true) {
            LOGGER.info(mensagem.get("menu.prompt.codigo") + ": ");
            String entrada = AuxiliarDeConsole.lerLinha();
            try {
                ValidadorService.validarInputVazio(entrada);
                codigo = entrada.trim();
                break;
            } catch (DadosIncompletosException e) {
                LOGGER.warning(e.getMessage());
                erroGenerico();

                return;
            }
        }

        // 2. Buscar orientação existente
        Orientacao orientacao = buscarPorCodigo(codigo);
        if (orientacao == null) {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("exception.orientacao.pesquisa.codigo_nao_encontrado") + " " + codigo,
                            LARGURA_MENU));

            erroGenerico();

            return;
        }

        // Validação da Orientação

        exibirDetalhesOrientacaoPorCadaIdioma(orientacao);

        confirmarOrientacao();

        String inputConfirmacaoEdicao = AuxiliarDeConsole.lerLinha();

        try {

            ValidadorService.validarInputVazio(inputConfirmacaoEdicao);

        } catch (DadosIncompletosException e) {

            LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());

            erroGenerico();

            return;
        }

        if (inputConfirmacaoEdicao.equals("1")) {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.orientacao.confirmada.edicao"),
                            LARGURA_MENU));

            // 3. Coletar novos títulos e conteúdos
            Map<Idioma, String> novosTitulos = new LinkedHashMap<>();
            Map<Idioma, String> novosConteudos = new LinkedHashMap<>();

            for (Idioma idioma : idiomaService.getListaIdiomas()) {
                String atualT = orientacao.getTitulo(idioma);
                LOGGER.info("\n" + idioma.getNome() + ":");
                LOGGER.info(mensagem.get("menu.editar.titulo.atual") + ": " + (atualT != null ? atualT : "[vazio]"));
                LOGGER.info(mensagem.get("menu.editar.titulo.novo") + mensagem.get("menu.exibir.enter_manter") + ": ");
                String t = AuxiliarDeConsole.lerLinha();
                if (t != null && !t.isBlank())
                    novosTitulos.put(idioma, t);

                String atualC = orientacao.getConteudo(idioma);
                LOGGER.info(mensagem.get("menu.editar.conteudo.atual") + ": " + (atualC != null ? atualC : "[vazio]"));
                LOGGER.info(
                        mensagem.get("menu.editar.conteudo.novo") + mensagem.get("menu.exibir.enter_manter") + ": ");
                String c = AuxiliarDeConsole.lerLinha();
                if (c != null && !c.isBlank())
                    novosConteudos.put(idioma, c);
            }

            // 4. Chamar service para editar
            try {

                OrientacaoService.editarOrientacao(codigo, novosTitulos, novosConteudos);
                LOGGER.info(mensagem.get("exception.orientacao.edicao.sucesso"));

            } catch (DadosIncompletosException e) {

                LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());
                erroGenerico();
            }

        } else if (inputConfirmacaoEdicao.equals("2"))

        {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.orientacao.nao_confirmada.edicao"),
                            LARGURA_MENU));

            erroGenerico();

        } else {
            erroGenerico();
        }

    }

    // Método para Exluir Orientação
    public static void excluirOrientacao() {

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.exibir.exclusao.titulo"), LARGURA_MENU));

        String codigo = null;

        while (true) {
            LOGGER.info(mensagem.get("menu.prompt.codigo") + ": ");
            String entrada = AuxiliarDeConsole.lerLinha();
            try {
                ValidadorService.validarInputVazio(entrada);
                codigo = entrada.trim();
                break;
            } catch (DadosIncompletosException e) {

                LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());

                erroGenerico();

                return;
            }

        }

        // 2. Buscar orientação existente

        Orientacao orientacao = buscarPorCodigo(codigo);

        if (orientacao == null) {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("exception.orientacao.pesquisa.codigo_nao_encontrado") + " " + codigo,
                            LARGURA_MENU));

            erroGenerico();

            return;
        }

        // Validação da Orientação

        exibirDetalhesOrientacaoPorIdiomaAtual(orientacao);

        confirmarOrientacao();

        AuxiliarDeConsole.escolha();

        String inputConfirmacaoEdicao = AuxiliarDeConsole.lerLinha();

        try {

            ValidadorService.validarInputVazio(inputConfirmacaoEdicao);

        } catch (DadosIncompletosException e) {

            LOGGER.warning(e.getMessage());

            erroGenerico();

            return;
        }

        if (inputConfirmacaoEdicao.equals("1")) {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.exclusao.confirmada.edicao"),
                            LARGURA_MENU));

            // 4. Chamar service para deletar
            try {

                OrientacaoService.deletarOrientacao(codigo);

                LOGGER.info(mensagem.get("exception.orientacao.exclusao.sucesso"));

            } catch (DadosIncompletosException e) {

                LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());
                erroGenerico();

            }

        } else if (inputConfirmacaoEdicao.equals("2")) {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.orientacao.nao_confirmada.edicao"),
                            LARGURA_MENU));

            erroGenerico();

        } else {
            erroGenerico();
        }

    }

    // Métodos de Pesquisa

    public static void pesquisarOrientacao() {

        boolean continuar = true;
        while (continuar) {

            if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
                LOGGER.info("\n╔══════════════════════════════════════════════════╗");
                LOGGER.info(String.format("║%s║",
                        AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.pesquisa.titulo"), LARGURA_MENU)));
                LOGGER.info("╠══════════════════════════════════════════════════╣");
                LOGGER.info(String.format("║%s║",
                        AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.pesquisa.opcao.codigo"), LARGURA_MENU)));
                LOGGER.info(String.format("║%s║",
                        AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.pesquisa.opcao.titulo"), LARGURA_MENU)));
                LOGGER.info("╚══════════════════════════════════════════════════╝\n");
            }

            if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
                LOGGER.info(mensagem.get("menu.pesquisa.prompt.opcao") + " ");
            }

            String opcao = AuxiliarDeConsole.lerLinha();

            switch (opcao) {
                case "1":
                    pesquisarPorCodigo();
                    break;
                case "2":
                    pesquisarPorTitulo();
                    break;
                case "0":
                    continuar = false;
                    break;
                default:
                    if (LOGGER.isLoggable(java.util.logging.Level.WARNING)) {
                        LOGGER.warning(mensagem.get("main.input.invalido"));
                    }
            }

            AuxiliarDeConsole.separador();

        }
    }

    private static void pesquisarPorCodigo() {

        LOGGER.log(java.util.logging.Level.INFO, "{0} ", mensagem.get("menu.pesquisa.prompt.codigo"));

        String codigo = AuxiliarDeConsole.lerLinha();

        try {

            Orientacao o = OrientacaoService.pesquisarOrientacaoPorCodigo(codigo);

            menuVizualizarOrientacao();

            String escolhaVizualizacao = AuxiliarDeConsole.lerLinha();

            vizualizarOrientacao(o, escolhaVizualizacao);

        } catch (DadosIncompletosException e) {

            LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());

        }

    }

    // Método para Pesquisar e Visualizar Orientação
    private static void pesquisarPorTitulo() {

        LOGGER.log(java.util.logging.Level.INFO, "{0} ", mensagem.get("menu.pesquisa.prompt.titulo"));
        String termo = AuxiliarDeConsole.lerLinha();

        try {

            Map<Orientacao, Map<Idioma, String>> resultados = OrientacaoService.pesquisarOrientacaoPorTitulo(termo);
            visualizarResultadosPesquisa(resultados);

        } catch (DadosIncompletosException e) {

            LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());

        }
    }

    private static void visualizarResultadosPesquisa(Map<Orientacao, Map<Idioma, String>> resultados) {

        List<Orientacao> lista = new ArrayList<>(resultados.keySet());
        boolean loop = true;

        while (loop) {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.pesquisa.resultado.sucesso"), LARGURA_MENU));

            // Exibe cada orientação encontrada, com títulos por idioma
            for (int i = 0; i < lista.size(); i++) {
                Orientacao o = lista.get(i);
                StringBuilder linha = new StringBuilder();
                linha.append(String.format("%d - %s", i + 1, o.getCodigo()));
                linha.append(" | ");
                linha.append(mensagem.get("menu.pesquisa.tipo_orientacao")).append(": ");
                linha.append(o.getTipo().getNome(IdiomaSelecionado.getIdiomaAtualObjeto()));
                LOGGER.info(linha.toString());

                Map<Idioma, String> titulos = resultados.get(o);
                for (Map.Entry<Idioma, String> entry : titulos.entrySet()) {
                    String idiomaNome = entry.getKey().getNome();
                    String titulo = entry.getValue();
                    LOGGER.info(String.format("    [%s] %s", idiomaNome, titulo));
                }
            }

            LOGGER.info("\n╔══════════════════════════════════════════════════╗");
            String selecionarMsg = AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.pesquisa.resultado.selecionar"),
                    LARGURA_MENU);
            String voltarMsg = AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.pesquisa.opcao.voltar"),
                    LARGURA_MENU);
            LOGGER.info(String.format("║%s║", selecionarMsg));
            LOGGER.info(String.format("║%s║", voltarMsg));
            LOGGER.info("╚══════════════════════════════════════════════════╝\n");

            String escolha = AuxiliarDeConsole.lerLinha();

            if ("0".equals(escolha)) {

                return;

            }

            try {

                int idx = Integer.parseInt(escolha);

                if (idx >= 1 && idx <= lista.size()) {

                    menuVizualizarOrientacao();

                    String escolhaVizualizacao = AuxiliarDeConsole.lerLinha();

                    vizualizarOrientacao(lista.get(idx - 1), escolhaVizualizacao);

                    LOGGER.info("\n╔══════════════════════════════════════════════════╗");
                    String visualizarOutra = AuxiliarDeConsole.alinharEsquerda(
                            mensagem.get("menu.pesquisa.resultado.visualizar_outra"), LARGURA_MENU);
                    String voltar = AuxiliarDeConsole.alinharEsquerda(
                            mensagem.get("menu.pesquisa.resultado.voltar"), LARGURA_MENU);
                    LOGGER.info(String.format("║%s║", visualizarOutra));
                    LOGGER.info(String.format("║%s║", voltar));
                    LOGGER.info("╚══════════════════════════════════════════════════╝\n");

                    String prox = AuxiliarDeConsole.lerLinha();

                    if ("1".equals(prox)) {

                        visualizarResultadosPesquisa(resultados);

                    }

                    if ("0".equals(prox)) {

                        loop = false;

                    }

                } else {
                    LOGGER.warning(mensagem.get("main.input.invalido"));
                }
            } catch (NumberFormatException ex) {

                LOGGER.severe(mensagem.get("menu.erro") + ex.getMessage());

            }
        }
    }

    private static void exibirDetalhesOrientacaoPorIdiomaAtual(Orientacao o) {

        String titulo = o.getTitulo(IdiomaSelecionado.getIdiomaAtualObjeto());
        String tipo = o.getTipo().getNome(IdiomaSelecionado.getIdiomaAtualObjeto());
        String conteudo = o.getConteudo(IdiomaSelecionado.getIdiomaAtualObjeto());

        // Cabeçalho
        LOGGER.info("\n╔═════════════════════════════════════════════════════════════════╗");
        LOGGER.info(() -> String.format("║%s║",
                AuxiliarDeConsole.centralizarTexto(
                        mensagem.get("menu.pesquisa.resultado.orientacao") + ": " + o.getCodigo(),
                        LARGURA_MENU_ORIENTACAO)));
        LOGGER.info("╠═════════════════════════════════════════════════════════════════╣");

        // Título
        LOGGER.info(() -> String.format("║%s║",
                AuxiliarDeConsole.alinharEsquerda(
                        mensagem.get("menu.titulo") + ": " + titulo, LARGURA_MENU_ORIENTACAO)));

        // Tipo
        LOGGER.info(() -> String.format("║%s║",
                AuxiliarDeConsole.alinharEsquerda(
                        mensagem.get("menu.pesquisa.tipo_orientacao") + ": " + tipo, LARGURA_MENU_ORIENTACAO)));

        // Conteúdo (quebra em linhas)
        List<String> linhasConteudo = AuxiliarDeConsole.quebrarEmLinhas(
                mensagem.get("menu.pesquisa.conteudo") + ": " + conteudo, LARGURA_MENU_ORIENTACAO);
        for (String linha : linhasConteudo) {
            LOGGER.info(() -> String.format("║%s║", AuxiliarDeConsole.alinharEsquerda(linha, LARGURA_MENU_ORIENTACAO)));
        }

        // Rodapé
        LOGGER.info("╚═════════════════════════════════════════════════════════════════╝\n");

        AuxiliarDeConsole.separador();

    }

    private static void exibirDetalhesOrientacaoPorCadaIdioma(Orientacao o) {
        String tipo = o.getTipo().getNome(IdiomaSelecionado.getIdiomaAtualObjeto());

        // Cabeçalho
        LOGGER.info("\n╔═════════════════════════════════════════════════════════════════╗");
        LOGGER.info(() -> String.format("║%s║",
                AuxiliarDeConsole.centralizarTexto(
                        mensagem.get("menu.pesquisa.resultado.orientacao") + ": " + o.getCodigo(),
                        LARGURA_MENU_ORIENTACAO)));
        LOGGER.info("╠═════════════════════════════════════════════════════════════════╣");

        LOGGER.info(() -> String.format("║%s║",
                AuxiliarDeConsole.alinharEsquerda(
                        mensagem.get("menu.pesquisa.tipo_orientacao") + ": " + tipo, LARGURA_MENU_ORIENTACAO)));
        LOGGER.info("╠═════════════════════════════════════════════════════════════════╣");

        // Para cada idioma, exibe título e conteúdo
        for (Idioma idioma : idiomaService.getListaIdiomas()) {
            String titulo = o.getTitulo(idioma);
            String conteudo = o.getConteudo(idioma);

            // Nome do idioma centralizado
            LOGGER.info(() -> String.format("║%s║",
                    AuxiliarDeConsole.centralizarTexto("[" + idioma.getNome() + "]", LARGURA_MENU_ORIENTACAO)));
            LOGGER.info("╟─────────────────────────────────────────────────────────────────╢");

            // Título
            LOGGER.info(() -> String.format("║%s║",
                    AuxiliarDeConsole.alinharEsquerda(
                            mensagem.get("menu.titulo") + ": " + (titulo != null ? titulo : "[vazio]"),
                            LARGURA_MENU_ORIENTACAO)));

            // Conteúdo (quebra em linhas)
            List<String> linhasConteudo = AuxiliarDeConsole.quebrarEmLinhas(
                    mensagem.get("menu.pesquisa.conteudo") + ": " + (conteudo != null ? conteudo : "[vazio]"),
                    LARGURA_MENU_ORIENTACAO);
            for (String linha : linhasConteudo) {
                LOGGER.info(
                        () -> String.format("║%s║", AuxiliarDeConsole.alinharEsquerda(linha, LARGURA_MENU_ORIENTACAO)));
            }

            // Separador entre idiomas (exceto o último)
            LOGGER.info("╠═════════════════════════════════════════════════════════════════╣");
        }

        // Rodapé
        LOGGER.info("╚═════════════════════════════════════════════════════════════════╝\n");

        AuxiliarDeConsole.separador();

    }

    private static void menuVizualizarOrientacao() {

        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        String visualizarNesteIdioma = AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.orientacao.vizualizar.idioma_atual"), LARGURA_MENU);
        String vizualizarTodosIdiomas = AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.orientacao.vizualizar.cada_idioma"), LARGURA_MENU);
        LOGGER.info(String.format("║%s║", visualizarNesteIdioma));
        LOGGER.info(String.format("║%s║", vizualizarTodosIdiomas));
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");

    }

    private static void vizualizarOrientacao(Orientacao orientacao, String tipoVisualizacao) {

        if (orientacao != null) {

            if ("1".equals(tipoVisualizacao)) {

                exibirDetalhesOrientacaoPorIdiomaAtual(orientacao);

            } else if ("2".equals(tipoVisualizacao)) {

                exibirDetalhesOrientacaoPorCadaIdioma(orientacao);

            } else {

                LOGGER.warning(mensagem.get("menu.erro.generico.titulo"));

                erroGenerico();

            }

        } else {

            LOGGER.warning(mensagem.get("exception.orientacao.pesquisa.codigo_nao_encontrado"));

            erroGenerico();
        }

    }

    public static void listarOrientacoes() {

        // orientacaoService.listarOrientacoes();

    }

}