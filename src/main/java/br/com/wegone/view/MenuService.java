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
import br.com.wegone.model.IdiomasDisponiveis;
import br.com.wegone.model.TipoOrientacao;
import br.com.wegone.model.TipoOrientacoesDisponiveis;
import br.com.wegone.model.Usuario;
import br.com.wegone.exception.*;
import br.com.wegone.model.Orientacao;
import br.com.wegone.view.AuxiliarDeConsole;

public class MenuService {

    private static final Logger LOGGER = Logger.getLogger(MenuService.class.getName());
    private static IdiomaMensagens mensagem = new IdiomaMensagens();
    private static final String MAIN_INICIAR_IDIOMA = "main.iniciar.idioma";
    private final static int LARGURA_MENU = 50;
    private static AuxiliarDeConsole auxiliar;

    private static OrientacaoService orientacaoService = new OrientacaoService();
    private static IdiomasDisponiveis idiomasDisponiveis = new IdiomasDisponiveis();
    private static IdiomaService idiomaService = new IdiomaService();
    private static TipoOrientacoesDisponiveis tipoOrientacoesDisponiveis = new TipoOrientacoesDisponiveis(idiomaService);
    private static String idiomaAtualNome = IdiomaSelecionado.getIdiomaAtualNome();

    // Escolha

    public static void escolha() {

        LOGGER.info("\n" + mensagem.get("menu.escolha"));

    }

    public static void sairSistema() {

        auxiliar.exibirTitulo(mensagem.get("main.sair.titulo"));
        LOGGER.info("\n" + mensagem.get("main.sair.sistema"));

        System.exit(0);

    }

    public static void erroGenerico() {
        while (true) {
            LOGGER.info("\n╔══════════════════════════════════════════════════╗");
            LOGGER.info("║" + auxiliar.centralizarTexto(mensagem.get("menu.erro.generico.titulo"), LARGURA_MENU) + "║");
            LOGGER.info("╠══════════════════════════════════════════════════╣");
            LOGGER.info(
                    "║" + auxiliar.alinharEsquerda(mensagem.get("menu.erro.generico.tentar.novamente"), LARGURA_MENU)
                            + "║");
            LOGGER.info("║" + auxiliar.alinharEsquerda(mensagem.get("menu.erro.generico.sair"), LARGURA_MENU) + "║");
            LOGGER.info("╚══════════════════════════════════════════════════╝\n");

            LOGGER.info(mensagem.get("menu.erro.generico.escolha")); // Ex: "Escolha uma opção: "
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

    // Exibir Menu de Login ou Cadastro

    public static void exibirMenuAcesso() {

        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        LOGGER.info("║" + auxiliar.centralizarTexto(mensagem.get("menu.acesso.titulo"), LARGURA_MENU) + "║");
        LOGGER.info("╠══════════════════════════════════════════════════╣");
        LOGGER.info("║" + auxiliar.alinharEsquerda(mensagem.get("menu.acesso.login"), LARGURA_MENU) + "║");
        LOGGER.info("║" + auxiliar.alinharEsquerda(mensagem.get("menu.acesso.cadastro"), LARGURA_MENU) + "║");
        LOGGER.info("║" + auxiliar.alinharEsquerda(mensagem.get("menu.acesso.sair"), LARGURA_MENU) + "║");
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");

        escolha(); // Mensagem de escolha de acesso

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
                    LOGGER.warning("║             Error! Invalid Language              ║");
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
                            selecionarIdioma();
                            break;
                        case "2":
                            // Iniciar com Português
                            IdiomaSelecionado.setLocale("1"); // Definir escolha como Português
                            AuxiliarDeConsole.exibirTitulo(AuxiliarDeConsole
                                    .centralizarTexto(mensagem.get(MAIN_INICIAR_IDIOMA), LARGURA_MENU));
                            idiomaSelecionadoComSucesso = true;
                            break;
                        case "0":
                            auxiliar.exibirTitulo("Close System");
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

                LOGGER.severe("Error: " + e.getMessage());

            }

        }

    }

    // Menu de Acesso do Sistema (Login e Cadastro)

    public static void selecionarMenuAcesso() {

        boolean opcaoSelecionadaComSucesso = false;
        while (!opcaoSelecionadaComSucesso) {

            exibirMenuAcesso(); // Exibir menu de acesso

            String escolha = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

            int escolhaInt;
            try {
                escolhaInt = Integer.parseInt(escolha); // Converter escolha para inteiro
            } catch (NumberFormatException nfe) {
                LOGGER.warning(mensagem.get("main.input.invalido.numero"));
                continue;
            }

            // Validar escolha

            try {

                ValidadorService.validarInputVazio(escolha); // Validar se a escolha não está vazia

                if (escolhaInt < 0 || escolhaInt > 2) {

                    LOGGER.warning("\n╔══════════════════════════════════════════════════╗");
                    LOGGER.warning("║             Error! Invalid Access               ║");
                    LOGGER.warning("╠══════════════════════════════════════════════════╣");
                    LOGGER.warning("║ 1 - Try Again                                    ║");
                    LOGGER.warning("║ 0 - Exit                                         ║");
                    LOGGER.warning("╚══════════════════════════════════════════════════╝\n");

                    LOGGER.info("\nChoose: ");
                    String escolhaErroMenuPrincipal = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

                    switch (escolhaErroMenuPrincipal) {
                        case "1":
                            // Tentar novamente
                            selecionarMenuAcesso();
                            break;
                        case "0":
                            sairSistema(); // Sair do sistema
                            break;
                        default:
                            LOGGER.warning(mensagem.get("main.input.invalido"));
                            break;
                    }

                } else {

                    switch (escolha) {

                        case "1":

                            auxiliar.exibirTitulo(
                                    auxiliar.centralizarTexto(mensagem.get("menu.acesso.login.titulo"), LARGURA_MENU));

                            LOGGER.info("\n" + mensagem.get("menu.acesso.login.matricula"));
                            String matricula = AuxiliarDeConsole.lerLinha();
                            LOGGER.info("\n" + mensagem.get("menu.acesso.login.senha"));
                            String senha = AuxiliarDeConsole.lerLinha();
                            Usuario usuario = usuarioService.login(matricula, senha);
                            auxiliar.exibirTitulo(auxiliar.centralizarTexto(
                                    String.format(mensagem.get("exception.user.login_success"), usuario.getNome()),
                                    LARGURA_MENU));

                            break;

                        case "2":

                            auxiliar.exibirTitulo(auxiliar.centralizarTexto(mensagem.get("menu.acesso.cadastro.titulo"),
                                    LARGURA_MENU));

                            LOGGER.info(mensagem.get("menu.acesso.cadastro.matricula"));
                            String mat = AuxiliarDeConsole.lerLinha();
                            LOGGER.info(mensagem.get("menu.acesso.cadastro.nome"));
                            String nome = AuxiliarDeConsole.lerLinha();
                            LOGGER.info(mensagem.get("menu.acesso.cadastro.email"));
                            String email = AuxiliarDeConsole.lerLinha();
                            LOGGER.info(mensagem.get("menu.acesso.cadastro.senha"));
                            String senhaCadastro = AuxiliarDeConsole.lerLinha();
                            Usuario novoUsuario = usuarioService.cadastrar(mat, nome, email, senhaCadastro);
                            auxiliar.exibirTitulo(auxiliar.centralizarTexto(
                                    String.format(mensagem.get("exception.user.register_success"),
                                            novoUsuario.getNome()),
                                    LARGURA_MENU));

                            break;

                        case "0":

                            sairSistema(); // Sair do sistema

                            break;
                        default:

                            LOGGER.warning(mensagem.get("main.input.invalido"));

                            break;
                    }

                }

            } catch (DadosIncompletosException e) {

                LOGGER.severe("Error: " + e.getMessage());

            }
        }
    }

    public static void selecionarMenuPrincipal() {

        boolean opcaoSelecionadaComSucesso = false;
        while (!opcaoSelecionadaComSucesso) {

            LOGGER.info("\n╔══════════════════════════════════════════════════╗");
            LOGGER.info("║" + AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.orientacoes.titulo"), LARGURA_MENU)
                    + "║");
            LOGGER.info("╠══════════════════════════════════════════════════╣");
            LOGGER.info(
                    "║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.orientacoes.cadastrar"), LARGURA_MENU)
                            + "║");
            LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.orientacoes.editar"), LARGURA_MENU)
                    + "║");
            LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.orientacoes.excluir"), LARGURA_MENU)
                    + "║");
            LOGGER.info(
                    "║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.orientacoes.pesquisar"), LARGURA_MENU)
                            + "║");
            LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.orientacoes.listar"), LARGURA_MENU)
                    + "║");
            LOGGER.info(
                    "║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.orientacoes.sair"), LARGURA_MENU) + "║");
            LOGGER.info("╚══════════════════════════════════════════════════╝\n");

            escolha(); // Mensagem de escolha de acesso

            String escolha = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

            int escolhaInt;
            try {
                escolhaInt = Integer.parseInt(escolha); // Converter escolha para inteiro
            } catch (NumberFormatException nfe) {
                LOGGER.warning(mensagem.get("main.input.invalido.numero"));
                continue;
            }

            // Validar escolha

            try {

                ValidadorService.validarInputVazio(escolha); // Validar se a escolha não está vazia

                if (escolhaInt < 0 || escolhaInt > 5) {

                    LOGGER.info("\n╔══════════════════════════════════════════════════╗");
                    LOGGER.info("║" + AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.tratar.erro.menu.orientacao.titulo"), LARGURA_MENU) + "║");
                    LOGGER.info("╠══════════════════════════════════════════════════╣");
                    LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                            mensagem.get("menu.tratar.erro.metodo.tentar.novamente"), LARGURA_MENU) + "║");
                    LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                            mensagem.get("menu.tratar.erro.metodo.fechar.sistema"), LARGURA_MENU) + "║");
                    LOGGER.info("╚══════════════════════════════════════════════════╝\n");

                    LOGGER.info("\nChoose: ");
                    String escolhaErroMenuPrincipal = AuxiliarDeConsole.lerLinha(); // Ler escolha do usuário

                    switch (escolhaErroMenuPrincipal) {
                        case "1":
                            // Tentar novamente

                            break;
                        case "0":
                            sairSistema(); // Sair do sistema
                            break;
                        default:
                            LOGGER.warning(mensagem.get("main.input.invalido"));
                            break;
                    }

                } else {

                    switch (escolha) {

                        case "1":

                            auxiliar.exibirTitulo(auxiliar.centralizarTexto(mensagem.get("menu.exibir.cadastro.titulo"),
                                    LARGURA_MENU));
                            cadastrarOrientacao();
                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "2":

                            auxiliar.exibirTitulo(
                                    auxiliar.centralizarTexto(mensagem.get("menu.exibir.edicao.titulo"), LARGURA_MENU));

                            editarOrientacao();
                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "3":

                            auxiliar.exibirTitulo(auxiliar.centralizarTexto(mensagem.get("menu.exibir.exclusao.titulo"),
                                    LARGURA_MENU));
                            excluirOrientacao();
                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "4":

                            auxiliar.exibirTitulo(auxiliar.centralizarTexto(mensagem.get("menu.exibir.pesquisa.titulo"),
                                    LARGURA_MENU));
                            pesquisarOrientacao();
                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "5":

                            auxiliar.exibirTitulo(auxiliar.centralizarTexto(mensagem.get("menu.exibir.listagem.titulo"),
                                    LARGURA_MENU));
                            listarOrientacoes();
                            opcaoSelecionadaComSucesso = true;

                            break;

                        case "6":

                            auxiliar.exibirTitulo(auxiliar
                                    .centralizarTexto(mensagem.get("menu.exibir.trocar.idioma.titulo"), LARGURA_MENU));
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

                LOGGER.severe("Error: " + e.getMessage());

            } finally {

                auxiliar.separador(); // Separador após cada operação

            }

        }

    }

    // Métodos de Cadastro, Edição, Exclusão, Pesquisa e Listagem

    private static TipoOrientacao escolhaTipoOrientacao() {

        List<TipoOrientacao> tipos = tipoOrientacoesDisponiveis.getListaOrientacoesDisponiveis();

        auxiliar.exibirTitulo(
                auxiliar.centralizarTexto(
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
        String input = auxiliar.lerLinha();
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

    public static void cadastrarOrientacao() {

        auxiliar.exibirTitulo(
                auxiliar.centralizarTexto(mensagem.get("menu.exibir.cadastro.titulo"), LARGURA_MENU));

        String codigo;
        while (true) {
            System.out.print(mensagem.get("menu.prompt.codigo") + ": ");
            codigo = auxiliar.lerLinha();
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
                System.out.print(mensagem.get("menu.prompt.titulo") + " " + idioma.getNome() + ": ");
                t = auxiliar.lerLinha();
                if (t == null || t.isBlank()) {
                    LOGGER.warning(
                            mensagem.get("exception.orientacao.cadastro.titulo.vazio") + " " + idioma.getNome());
                    continue;
                }
                break;
            }
            while (true) {
                System.out.print(mensagem.get("menu.prompt.conteudo") + " " + idioma.getNome() + ": ");
                c = auxiliar.lerLinha();
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
    }

    private static Orientacao buscarPorCodigo(String codigo) {
        return orientacaoService.getListaOrientacoes().stream()
                .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public void editarOrientacao() {
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

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(
                        mensagem.get("menu.orientacao.encontrada"),
                        LARGURA_MENU));

        LOGGER.info(mensagem.get("menu.orientacao.codigo") + ": " + orientacao.getCodigo());
        LOGGER.info(mensagem.get("menu.orientacao.tipo") + ": "
                + orientacao.getTipo().getNome(IdiomaSelecionado.getIdiomaAtualObjeto()));

        LOGGER.info(mensagem.get("menu.orientacao.titulos") + ":"); // Mostra os títulos em cada Idioma
        for (Idioma idioma : idiomaService.getListaIdiomas()) {
            String titulo = orientacao.getTitulo(idioma);
            LOGGER.info("  " + idioma.getNome() + ": " + (titulo != null ? titulo : "[vazio]"));
        }

        LOGGER.info(mensagem.get("menu.orientacao.conteudos") + ":"); // Mostra os conteúdos em cada Idioma
        for (Idioma idioma : idiomaService.getListaIdiomas()) {
            String conteudo = orientacao.getConteudo(idioma);
            LOGGER.info("  " + idioma.getNome() + ": " + (conteudo != null ? conteudo : "[vazio]"));
        }

        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.prompt.confirmar"), LARGURA_MENU) + "║"); // 1- Confirmar
        LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.prompt.nao_confirmar"), LARGURA_MENU) + "║"); // 2- Incorreta
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");

        escolha();

        String inputConfirmacaoEdicao = auxiliar.lerLinha();

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
                LOGGER.warning(e.getMessage());
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

        auxiliar.exibirTitulo(
                auxiliar.centralizarTexto(mensagem.get("menu.exibir.exclusao.titulo"), LARGURA_MENU));

        String codigo = null;

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

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(
                        mensagem.get("menu.orientacao.encontrada"),
                        LARGURA_MENU));

        LOGGER.info(mensagem.get("menu.orientacao.codigo") + ": " + orientacao.getCodigo());
        LOGGER.info(mensagem.get("menu.orientacao.tipo") + ": "
                + orientacao.getTipo().getNome(IdiomaSelecionado.getIdiomaAtualObjeto()));

        LOGGER.info(mensagem.get("menu.orientacao.titulos") + ":"); // Mostra os títulos em cada Idioma
        for (Idioma idioma : idiomaService.getListaIdiomas()) {
            String titulo = orientacao.getTitulo(idioma);
            LOGGER.info("  " + idioma.getNome() + ": " + (titulo != null ? titulo : "[vazio]"));
        }

        LOGGER.info(mensagem.get("menu.orientacao.conteudos") + ":"); // Mostra os conteúdos em cada Idioma
        for (Idioma idioma : idiomaService.getListaIdiomas()) {
            String conteudo = orientacao.getConteudo(idioma);
            LOGGER.info("  " + idioma.getNome() + ": " + (conteudo != null ? conteudo : "[vazio]"));
        }

        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.prompt.confirmar"), LARGURA_MENU) + "║"); // 1- Confirmar
        LOGGER.info("║" + AuxiliarDeConsole.alinharEsquerda(
                mensagem.get("menu.prompt.nao_confirmar"), LARGURA_MENU) + "║"); // 2- Incorreta
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");

        escolha();

        String inputConfirmacaoEdicao = auxiliar.lerLinha();

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
                orientacaoService.deletarOrientacao(codigo);
                LOGGER.info(mensagem.get("exception.orientacao.exclusao.sucesso"));
            } catch (DadosIncompletosException e) {
                LOGGER.warning(e.getMessage());
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

        

        

    }

    // Métodos de Pesquisa

    public void pesquisarOrientacao() {
        while (true) {
            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.pesquisa.titulo"), LARGURA_MENU));

            System.out.println(mensagem.get("menu.pesquisa.opcao.codigo")); // Criar Menu
            System.out.println(mensagem.get("menu.pesquisa.opcao.titulo"));
            System.out.println(mensagem.get("menu.pesquisa.opcao.voltar"));
            System.out.print(mensagem.get("menu.pesquisa.prompt.opcao") + " ");

            String opcao = AuxiliarDeConsole.lerLinha();

            switch (opcao) {
                case "1":
                    pesquisarPorCodigo();
                    break;
                case "2":
                    pesquisarPorTitulo();
                    break;
                case "0":
                    return;
                default:
                    LOGGER.warning(mensagem.get("main.input.invalido"));
            }
            AuxiliarDeConsole.separador();
        }
    }

    private void pesquisarPorCodigo() {

        System.out.print(mensagem.get("menu.pesquisa.prompt.codigo") + " ");
        String codigo = AuxiliarDeConsole.lerLinha();

        try {

            Orientacao o = orientacaoService.pesquisarOrientacaoPorCodigo(codigo);
            exibirDetalhesOrientacao(o);

        } catch (DadosIncompletosException e) {

            LOGGER.warning(e.getMessage());

        }

    }

    private void pesquisarPorTitulo() {

        System.out.print(mensagem.get("menu.pesquisa.prompt.titulo") + " ");
        String termo = AuxiliarDeConsole.lerLinha();

        try {

            Map<Orientacao, Map<Idioma, String>> resultados = orientacaoService.pesquisarOrientacaoPorTitulo(termo);
            visualizarResultadosPesquisa(resultados);

        } catch (DadosIncompletosException e) {

            LOGGER.warning(e.getMessage());

        }
    }

    private void visualizarResultadosPesquisa(Map<Orientacao, Map<Idioma, String>> resultados) {

        List<Orientacao> lista = new ArrayList<>(resultados.keySet());
        boolean loop = true;

        while (loop) {

            AuxiliarDeConsole.exibirTitulo(
                    AuxiliarDeConsole.centralizarTexto(
                            mensagem.get("menu.pesquisa.resultado.sucesso"), LARGURA_MENU));

            for (int i = 0; i < lista.size(); i++) {

                Orientacao o = lista.get(i);
                System.out.printf("%d - %s%n", i + 1, o.getCodigo());
                resultados.get(o)
                        .forEach((idioma, titulo) -> System.out.println("    [" + idioma.getNome() + "] " + titulo));

            }

            System.out.println("0 - " + mensagem.get("menu.pesquisa.opcao.voltar"));
            System.out.print(mensagem.get("menu.pesquisa.resultado.selecionar") + " ");
            String escolha = AuxiliarDeConsole.lerLinha();

            if ("0".equals(escolha))
                return;
            try {

                int idx = Integer.parseInt(escolha);
                if (idx >= 1 && idx <= lista.size()) {
                    exibirDetalhesOrientacao(lista.get(idx - 1));
                    System.out.println("1 - " + mensagem.get("menu.pesquisa.resultado.visualizar_outra"));
                    System.out.println("0 - " + mensagem.get("menu.pesquisa.resultado.voltar"));
                    String prox = AuxiliarDeConsole.lerLinha();

                    if ("0".equals(prox)) {

                        loop = false;

                    }

                } else {
                    LOGGER.warning(mensagem.get("main.input.invalido"));
                }
            } catch (NumberFormatException ex) {

                LOGGER.warning(mensagem.get("main.input.invalido"));

            }
        }
    }

    private void exibirDetalhesOrientacao(Orientacao o) {

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(
                        mensagem.get("menu.pesquisa.resultado.orientacao") + ": " + o.getCodigo(), LARGURA_MENU));

        System.out.println(mensagem.get("menu.orientacao.tipo") + ": " + o.getTipo().getNome()); // REVISAR
        o.getTitulos().forEach((idioma, titulo) -> {

            System.out.println("[" + idioma.getNome() + "]");
            System.out.println(mensagem.getString("menu.pesquisa.resultado.titulo") + ": " + titulo);
            System.out.println(mensagem.getString("menu.pesquisa.resultado.conteudo") + ": " + o.getConteudo(idioma));

        });

        AuxiliarDeConsole.separador();

    }

    public static void listarOrientacoes() {

        // orientacaoService.listarOrientacoes();

    }

}