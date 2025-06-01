package br.com.wegone;

import java.util.logging.*;

import br.com.wegone.service.OrientacaoService;

/* Main do Projeto
 * 
 * Projeto WEGONE versão 1.0
 * 
 *  Croqui do Sistema, tendo todas as funcionalidades Base
 * 
 */

import br.com.wegone.view.MenuService;
import br.com.wegone.view.UsuarioView;

public class App {
    public static void main(String[] args) {

        // Forçar o ConsoleHandler a usar UTF-8
        LogManager.getLogManager().reset();
        Logger rootLogger = Logger.getLogger("");
        ConsoleHandler handler = new ConsoleHandler();
        try {
            handler.setEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Apenas a mensagem, sem data/hora/classe
        handler.setFormatter(new java.util.logging.Formatter() {
            @Override
            public String format(java.util.logging.LogRecord record) {
                return record.getMessage() + System.lineSeparator();
            }
        });
        rootLogger.addHandler(handler);

        // Iniciar as Orientações em Memória
        OrientacaoService.popularOrientacoes();

        // Iniciar Sistema
        MenuService.exibirLogo();

        // Definir Idioma
        MenuService.selecionarIdioma();

        // Definir Usuário
        UsuarioView.selecionarMenuAcesso();

        // Iniciar Menu Principal
        MenuService.selecionarMenuPrincipal();

    }

}
