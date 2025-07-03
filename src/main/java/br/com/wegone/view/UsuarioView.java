package br.com.wegone.view;

import java.util.logging.Logger;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.Usuario;
import br.com.wegone.service.IdiomaMensagens;
import br.com.wegone.service.UsuarioService;

public class UsuarioView {

    private static final Logger LOGGER = Logger.getLogger(UsuarioView.class.getName());
    private IdiomaMensagens mensagem = new IdiomaMensagens();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final int LARGURA_MENU = 50;
    private static final int LARGURA_MENU_MAIOR = 65;

    public static void sairSistema() {

        IdiomaMensagens mensagem = new IdiomaMensagens();

        AuxiliarDeConsole.exibirTitulo(mensagem.get("main.sair.titulo"));
        LOGGER.info("\n" + mensagem.get("main.sair.sistema"));
        System.exit(0);

    }

    public static void selecionarMenuAcesso() {

        IdiomaMensagens mensagem = new IdiomaMensagens();

        while (true) {
            exibirMenuAcesso();
            String escolha = AuxiliarDeConsole.lerLinha();

            switch (escolha) {
                case "1":
                    realizarLogin();
                    return;
                case "2":
                    realizarCadastro();
                    return;
                case "0":
                    sairSistema();
                    return;

                default:
                    LOGGER.warning(mensagem.get("main.opcao.invalida"));
            }
        }
    }

    private static void exibirMenuAcesso() {

        IdiomaMensagens mensagem = new IdiomaMensagens();

        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        LOGGER.info(String.format("║%s║",
                AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.acesso.titulo"), LARGURA_MENU)));
        LOGGER.info("╠══════════════════════════════════════════════════╣");
        LOGGER.info(String.format("║%s║",
                AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.acesso.login"), LARGURA_MENU)));
        LOGGER.info(String.format("║%s║",
                AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.acesso.cadastro"), LARGURA_MENU)));
        LOGGER.info(String.format("║%s║",
                AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.acesso.sair"), LARGURA_MENU)));
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");

        AuxiliarDeConsole.escolha();

    }

    private static void realizarLogin() {

        IdiomaMensagens mensagem = new IdiomaMensagens();

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.acesso.login.titulo"), LARGURA_MENU_MAIOR));

        LOGGER.info("\n" + mensagem.get("menu.acesso.login.matricula"));
        String matricula = AuxiliarDeConsole.lerLinha();

        LOGGER.info("\n" + mensagem.get("menu.acesso.login.senha"));
        String senha = AuxiliarDeConsole.lerLinha();

        try {

            Usuario usuario = usuarioService.login(matricula, senha);
            AuxiliarDeConsole.exibirTitulo(AuxiliarDeConsole.centralizarTexto(
                    String.format(mensagem.get("exception.user.login_success"), usuario.getNome()),
                    LARGURA_MENU_MAIOR));

            MenuService.selecionarMenuPrincipal();

        } catch (DadosIncompletosException e) {

            LOGGER.warning(e.getMessage());

            erroAcesso();

        } catch (RuntimeException e) {

            LOGGER.severe(e.getMessage());

            erroAcesso();

        }
    }

    private static void realizarCadastro() {

        IdiomaMensagens mensagem = new IdiomaMensagens();

        AuxiliarDeConsole.exibirTitulo(
                AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.acesso.cadastro.titulo"), LARGURA_MENU_MAIOR));
        LOGGER.info(mensagem.get("menu.acesso.cadastro.matricula"));
        String mat = AuxiliarDeConsole.lerLinha();
        LOGGER.info(mensagem.get("menu.acesso.cadastro.nome"));
        String nome = AuxiliarDeConsole.lerLinha();
        LOGGER.info(mensagem.get("menu.acesso.cadastro.email"));
        String email = AuxiliarDeConsole.lerLinha();
        LOGGER.info(mensagem.get("menu.acesso.cadastro.senha"));
        String senhaCadastro = AuxiliarDeConsole.lerLinha();

        try {

            Usuario novoUsuario = usuarioService.cadastrar(mat, nome, email, senhaCadastro);
            AuxiliarDeConsole.exibirTitulo(AuxiliarDeConsole.centralizarTexto(
                    String.format(mensagem.get("exception.user.register_success"), novoUsuario.getNome()),
                    LARGURA_MENU_MAIOR));

            MenuService.selecionarMenuPrincipal();

        } catch (DadosIncompletosException e) {

            LOGGER.warning(e.getMessage());
            if (erroAcesso() == 0) {

                sairSistema();

            }

        } catch (RuntimeException e) {

            LOGGER.severe(e.getMessage());
            if (erroAcesso() == 0) {

                sairSistema();

            }

        }
    }

    private static int erroAcesso() {

        IdiomaMensagens mensagem = new IdiomaMensagens();

        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
        LOGGER.info(
                "║" + AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.acesso.erro.titulo"), LARGURA_MENU) + "║");
        LOGGER.info("╠══════════════════════════════════════════════════╣");
        LOGGER.info("║"
                + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.erro.generico.tentar.novamente"), LARGURA_MENU)
                + "║");
        LOGGER.info(
                "║" + AuxiliarDeConsole.alinharEsquerda(mensagem.get("menu.erro.generico.sair"), LARGURA_MENU) + "║");
        LOGGER.info("╚══════════════════════════════════════════════════╝\n");
        LOGGER.info(mensagem.get("menu.escolha"));
        String escolha = AuxiliarDeConsole.lerLinha();
        switch (escolha) {

            case "1":

                selecionarMenuAcesso();

            case "0":

                sairSistema();

            default:

                LOGGER.warning(mensagem.get("main.opcao.invalida"));
                return erroAcesso();

        }
    }
}