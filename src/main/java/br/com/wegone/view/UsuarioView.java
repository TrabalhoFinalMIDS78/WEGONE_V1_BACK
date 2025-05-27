package br.com.wegone.view;

import br.com.wegone.view.*;
import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.Usuario;
import br.com.wegone.service.IdiomaMensagens;
import br.com.wegone.service.UsuarioService;
import br.com.wegone.service.ValidadorService;
import br.com.wegone.view.AuxiliarDeConsole;
import java.util.logging.Logger;
import br.com.wegone.service.ValidadorService;

public class UsuarioView {

    // Atributos

    private static final Logger LOGGER = Logger.getLogger(UsuarioView.class.getName());
    private static IdiomaMensagens mensagem = new IdiomaMensagens();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final int LARGURA_MENU = 50;

    public static void sairSistema() {

        AuxiliarDeConsole.exibirTitulo(mensagem.get("main.sair.titulo"));
        LOGGER.info("\n" + mensagem.get("main.sair.sistema"));

        System.exit(0);

    }

    // View para o Usuário

    // Login e Cadastro do mesmo

    public static void exibirMenuAcesso() {

        if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
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
        }

        AuxiliarDeConsole.escolha(); // Mensagem de escolha de acesso

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

                    if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
                        LOGGER.info("\n╔══════════════════════════════════════════════════╗");
                        LOGGER.info(String.format("║%s║", AuxiliarDeConsole
                                .centralizarTexto(mensagem.get("menu.erro.generico.titulo"), LARGURA_MENU)));
                        LOGGER.info("╠══════════════════════════════════════════════════╣");
                        LOGGER.info(String.format("║%s║", AuxiliarDeConsole
                                .alinharEsquerda(mensagem.get("menu.erro.generico.tentar.novamente"), LARGURA_MENU)));
                        LOGGER.info(String.format("║%s║", AuxiliarDeConsole
                                .alinharEsquerda(mensagem.get("menu.erro.generico.sair"), LARGURA_MENU)));
                        LOGGER.info("╚══════════════════════════════════════════════════╝\n");
                    }

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
                            LOGGER.warning(mensagem.get("main.input.invalido"));
                            break;
                    }

                } else {

                    switch (escolha) {

                        case "1":

                            AuxiliarDeConsole.exibirTitulo(
                                    AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.acesso.login.titulo"),
                                            LARGURA_MENU));

                            LOGGER.info("\n" + mensagem.get("menu.acesso.login.matricula"));
                            String matricula = AuxiliarDeConsole.lerLinha();
                            LOGGER.info("\n" + mensagem.get("menu.acesso.login.senha"));
                            String senha = AuxiliarDeConsole.lerLinha();
                            Usuario usuario = usuarioService.login(matricula, senha); // Falta a criação
                            AuxiliarDeConsole.exibirTitulo(AuxiliarDeConsole.centralizarTexto(
                                    String.format(mensagem.get("exception.user.login_success"), usuario.getNome()),
                                    LARGURA_MENU));

                            break;

                        case "2":

                            AuxiliarDeConsole.exibirTitulo(
                                    AuxiliarDeConsole.centralizarTexto(mensagem.get("menu.acesso.cadastro.titulo"),
                                            LARGURA_MENU));

                            LOGGER.info(mensagem.get("menu.acesso.cadastro.matricula"));
                            String mat = AuxiliarDeConsole.lerLinha();
                            LOGGER.info(mensagem.get("menu.acesso.cadastro.nome"));
                            String nome = AuxiliarDeConsole.lerLinha();
                            LOGGER.info(mensagem.get("menu.acesso.cadastro.email"));
                            String email = AuxiliarDeConsole.lerLinha();
                            LOGGER.info(mensagem.get("menu.acesso.cadastro.senha"));
                            String senhaCadastro = AuxiliarDeConsole.lerLinha();
                            Usuario novoUsuario = usuarioService.cadastrar(mat, nome, email, senhaCadastro); // Falta a
                                                                                                             // criação
                            AuxiliarDeConsole.exibirTitulo(AuxiliarDeConsole.centralizarTexto(
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

                LOGGER.severe(mensagem.get("menu.erro") + e.getMessage());

            }

        }
    }

}