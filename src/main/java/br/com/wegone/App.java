package br.com.wegone;

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
    public static void main(String[] args) {;

        // Iniciar Sistema

        MenuService.exibirLogo();

        MenuService.selecionarIdioma();

        // Definir Usuário
        UsuarioView.selecionarMenuAcesso();

        // Iniciar Menu Principal
        MenuService.selecionarMenuPrincipal();

    }

}
