package br.com.wegone;

/* Main do Projeto
 * 
 * Projeto WEGONE versão 1.0
 * 
 *  Croqui do Sistema, tendo todas as funcionalidades Base
 * 
 */

import java.util.Scanner;

import br.com.wegone.service.*;
import br.com.wegone.view.*;
import br.com.wegone.core.*;
import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.*;

public class App {
    public static void main( String[] args )
    {
        
        MenuService menu = new MenuService();
        OrientacaoService orientacao = new OrientacaoService();
        IdiomaSelecionado idiomaSelecionado = new IdiomaSelecionado();
        IdiomaMensagens mensagem = new IdiomaMensagens();

        AuxiliarDeConsole auxiliar = new AuxiliarDeConsole();

        // Iniciar Sistema

        menu.exibirLogo();

        menu.selecionarIdioma();

        // Definir Usuário




    }  

}
