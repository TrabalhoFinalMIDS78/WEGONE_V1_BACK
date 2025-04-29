package br.com.wegone.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TipoOrientacoesDisponiveis {
    
    private List<TipoOrientacao> ListaOrientacoesDisponiveis = new ArrayList<>();

    public TipoOrientacoesDisponiveis(IdiomasDisponiveis idiomas) {

        // Criando as Orientações do Sistema WEGONE

        // Orientação 001-WO Manual de Operação
        TipoOrientacao manualOperacaoOrientacao = new TipoOrientacao("001-WO");

        manualOperacaoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("pt"), "Manual de Operação");
        manualOperacaoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("en"), "Operation Manual");
        manualOperacaoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("es"), "Manual de Operación");
        manualOperacaoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("de"), "Betriebshandbuch");
        manualOperacaoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("zh"), "操作手册");

        ListaOrientacoesDisponiveis.add(manualOperacaoOrientacao); //Adicionando as Orientações a Lista das Orientações Disponiveis do Sistema * Todas as criadas estão disponiveis 29/04/2025

        // Orientação 002 Procedimentos de Segurança
        TipoOrientacao procedimentoSegurancaOrientacao = new TipoOrientacao("002-WO");

        procedimentoSegurancaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("pt"), "Procedimento de Segurança");
        procedimentoSegurancaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("en"), "Safety Procedure");
        procedimentoSegurancaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("es"), "Procedimiento de Seguridad");
        procedimentoSegurancaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("de"), "Sicherheitsverfahren");
        procedimentoSegurancaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("zh"), "安全程序");

        ListaOrientacoesDisponiveis.add(procedimentoSegurancaOrientacao);

        // Orientação 003 Manuntenção e Reparos
        TipoOrientacao manutencaoReparosOrientacao = new TipoOrientacao("003-WO");

        manutencaoReparosOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("pt"), "Manutenção e Reparos");
        manutencaoReparosOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("en"), "Maintenance and Repairs");
        manutencaoReparosOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("es"), "Mantenimiento y Reparaciones");
        manutencaoReparosOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("de"), "Wartung und Reparaturen");
        manutencaoReparosOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("zh"), "维护和维修");

        ListaOrientacoesDisponiveis.add(manutencaoReparosOrientacao);

        // Orientação 004 Testes e Diagnosticos
        TipoOrientacao testesDiagnosticoOrientacao = new TipoOrientacao("004-WO");

        testesDiagnosticoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("pt"), "Testes e Diagnóstico");
        testesDiagnosticoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("en"), "Testing and Diagnostics");
        testesDiagnosticoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("es"), "Pruebas y Diagnóstico");
        testesDiagnosticoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("de"), "Tests und Diagnose");
        testesDiagnosticoOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("zh"), "测试和诊断");

        ListaOrientacoesDisponiveis.add(testesDiagnosticoOrientacao);

        // Orientação 005 Manual de Conduta
        TipoOrientacao manualCondutaOrientacao = new TipoOrientacao("004-WO");

        manualCondutaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("pt"), "Manual de Conduta");
        manualCondutaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("en"), "Code of Conduct");
        manualCondutaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("es"), "Manual de Conducta");
        manualCondutaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("de"), "Verhaltenshandbuch");
        manualCondutaOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("zh"), "行为守则手册");

        ListaOrientacoesDisponiveis.add(manualCondutaOrientacao);

        // Orientação 006 Operações Setoriais
        TipoOrientacao operacoesSetoriaisOrientacao = new TipoOrientacao("005-WO");

        operacoesSetoriaisOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("pt"), "Operações Setoriais");
        operacoesSetoriaisOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("en"), "Sectoral Operations");
        operacoesSetoriaisOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("es"), "Operaciones Sectoriales");
        operacoesSetoriaisOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("de"), "Bereichsbetrieb");
        operacoesSetoriaisOrientacao.setNomePorIdioma(idiomas.buscarPorCodigo("zh"), "部门操作");

        ListaOrientacoesDisponiveis.add(operacoesSetoriaisOrientacao);
        
    }

    public List<TipoOrientacao> getListaOrientacoesDisponivei() {

        return ListaOrientacoesDisponiveis;

    }

    public TipoOrientacao buscarPorCodigo(String codigo) {
        Optional<TipoOrientacao> orientacao = ListaOrientacoesDisponiveis.stream()
            .filter(i -> i.getCodigo().equals(codigo))
            .findFirst();
        return orientacao.orElse(null);
    }

}