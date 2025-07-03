package br.com.wegone.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.wegone.exception.DadosIncompletosException;
import br.com.wegone.model.Idioma;
import br.com.wegone.model.Orientacao;
import br.com.wegone.model.TipoOrientacao;

public class OrientacaoService {

    // Refatorar para a integração com Banco de Dados

    // Atributos

    private static List<Orientacao> listaOrientacoes = new ArrayList<>();
    private static IdiomaService idiomaService = new IdiomaService();

    private static IdiomaMensagens mensagem = new IdiomaMensagens();

    // Método para popular Orientações (PARA A VERSÃO INICIAL, NO FUTURO TEREMOS O
    // BANCO DE DADOS CONECTADO)

    public static void popularOrientacoes() {
        List<Idioma> idiomas = idiomaService.getListaIdiomas();
        List<TipoOrientacao> tipos = new ArrayList<>();
        tipos.addAll(
                new br.com.wegone.model.TipoOrientacoesDisponiveis(idiomaService).getListaOrientacoesDisponiveis());

        // Títulos e conteúdos para cada idioma (pt, en, es, de, zh)
        String[][] titulos = {
                { "Instrução de Ligamento do Painel", "Panel Power-On Instruction",
                        "Instrucción de Encendido del Panel", "Anleitung zum Einschalten des Panels", "面板启动说明" },
                { "Procedimento de Emergência", "Emergency Procedure", "Procedimiento de Emergencia",
                        "Notfallverfahren", "紧急程序" },
                { "Manutenção Preventiva de Equipamento", "Equipment Preventive Maintenance",
                        "Mantenimiento Preventivo del Equipo", "Vorbeugende Wartung der Ausrüstung", "设备预防性维护" },
                { "Diagnóstico de Falhas Elétricas", "Electrical Fault Diagnosis", "Diagnóstico de Fallas Eléctricas",
                        "Diagnose elektrischer Fehler", "电气故障诊断" },
                { "Conduta no Ambiente de Trabalho", "Workplace Conduct", "Conducta en el Lugar de Trabajo",
                        "Verhalten am Arbeitsplatz", "工作场所行为" },
                { "Operação Setorial de Máquinas", "Sector Machine Operation", "Operación Sectorial de Máquinas",
                        "Sektorale Maschinenbedienung", "部门机器操作" },
                { "Atualização de Firmware", "Firmware Update", "Actualización de Firmware", "Firmware-Aktualisierung",
                        "固件更新" },
                { "Checklist Diário de Segurança", "Daily Safety Checklist",
                        "Lista de Verificación Diaria de Seguridad", "Tägliche Sicherheits-Checkliste", "每日安全检查表" },
                { "Desligamento Seguro do Equipamento", "Safe Equipment Shutdown", "Apagado Seguro del Equipo",
                        "Sicheres Abschalten der Ausrüstung", "设备安全关机" },
                { "Teste de Sensores Industriais", "Industrial Sensor Test", "Prueba de Sensores Industriales",
                        "Industrieller Sensortest", "工业传感器测试" }
        };

        String[][] conteudos = {
                { // 1
                        "Objetivo: Ensinar o ligamento seguro do painel.\nAplicação: Técnicos autorizados.\nNormas: NR10.\nProcedimento: 1) Verifique tensão; 2) Pressione o botão verde por 3s.\nResponsável: Operador; Supervisor valida.\nEquipamentos: EPI, chave de teste.\nSegurança: Use EPI e siga NR10.",
                        "Objective: Teach safe panel power-on.\nApplication: Authorized technicians.\nNorms: NR10.\nProcedure: 1) Check voltage; 2) Press green button for 3s.\nResponsible: Operator; Supervisor validates.\nEquipment: PPE, test key.\nSafety: Use PPE and follow NR10.",
                        "Objetivo: Enseñar el encendido seguro del panel.\nAplicación: Técnicos autorizados.\nNormas: NR10.\nProcedimiento: 1) Verifique tensión; 2) Presione el botón verde 3s.\nResponsable: Operador; Supervisor valida.\nEquipos: EPI, llave de prueba.\nSeguridad: Use EPI y siga NR10.",
                        "Ziel: Sicheres Einschalten des Panels.\nAnwendung: Autorisierte Techniker.\nNormen: NR10.\nAblauf: 1) Spannung prüfen; 2) Grünen Knopf 3s drücken.\nVerantwortlich: Bediener; Supervisor prüft.\nAusrüstung: PSA, Prüfgerät.\nSicherheit: PSA tragen, NR10 beachten.",
                        "目标：教授面板安全启动。\n适用对象：授权技术员。\n规范：NR10。\n步骤：1）检查电压；2）按住绿色按钮3秒。\n责任人：操作员，主管验证。\n设备：防护装备，测试笔。\n安全：使用防护装备并遵守NR10。"
                },
                { // 2
                        "Objetivo: Orientar em situações de emergência.\nAplicação: Todos os colaboradores.\nNormas: NR23.\nProcedimento: 1) Acione alarme; 2) Evacue pela saída.\nResponsável: Todos; Brigada supervisiona.\nEquipamentos: Extintor, máscara.\nSegurança: Não use elevadores.",
                        "Objective: Guide in emergencies.\nApplication: All employees.\nNorms: NR23.\nProcedure: 1) Trigger alarm; 2) Evacuate via exit.\nResponsible: Everyone; Brigade supervises.\nEquipment: Extinguisher, mask.\nSafety: Do not use elevators.",
                        "Objetivo: Guiar en emergencias.\nAplicación: Todos los empleados.\nNormas: NR23.\nProcedimiento: 1) Active alarma; 2) Evacúe por la salida.\nResponsable: Todos; Brigada supervisa.\nEquipos: Extintor, máscara.\nSeguridad: No use ascensores.",
                        "Ziel: Anleitung bei Notfällen.\nAnwendung: Alle Mitarbeiter.\nNormen: NR23.\nAblauf: 1) Alarm auslösen; 2) Über Ausgang evakuieren.\nVerantwortlich: Alle; Brigade überwacht.\nAusrüstung: Feuerlöscher, Maske.\nSicherheit: Keine Aufzüge benutzen.",
                        "目标：应对紧急情况。\n适用对象：所有员工。\n规范：NR23。\n步骤：1）拉响警报；2）从出口撤离。\n责任人：所有人，救援队监督。\n设备：灭火器，口罩。\n安全：勿用电梯。"
                },
                { // 3
                        "Objetivo: Garantir funcionamento contínuo.\nAplicação: Equipe de manutenção.\nNormas: NR12.\nProcedimento: 1) Inspecione filtros; 2) Lubrifique partes móveis.\nResponsável: Técnico de manutenção.\nEquipamentos: Óleo, ferramentas.\nSegurança: Desligue antes de intervir.",
                        "Objective: Ensure continuous operation.\nApplication: Maintenance team.\nNorms: NR12.\nProcedure: 1) Inspect filters; 2) Lubricate moving parts.\nResponsible: Maintenance technician.\nEquipment: Oil, tools.\nSafety: Power off before intervention.",
                        "Objetivo: Garantizar funcionamiento continuo.\nAplicación: Equipo de mantenimiento.\nNormas: NR12.\nProcedimiento: 1) Inspeccione filtros; 2) Lubrique partes móviles.\nResponsable: Técnico de mantenimiento.\nEquipos: Aceite, herramientas.\nSeguridad: Desconecte antes de intervenir.",
                        "Ziel: Kontinuierlichen Betrieb sichern.\nAnwendung: Wartungsteam.\nNormen: NR12.\nAblauf: 1) Filter prüfen; 2) Bewegliche Teile schmieren.\nVerantwortlich: Wartungstechniker.\nAusrüstung: Öl, Werkzeuge.\nSicherheit: Vor Eingriff abschalten.",
                        "目标：确保持续运行。\n适用对象：维护团队。\n规范：NR12。\n步骤：1）检查过滤器；2）润滑活动部件。\n责任人：维护技师。\n设备：油，工具。\n安全：操作前断电。"
                },
                { // 4
                        "Objetivo: Identificar falhas elétricas.\nAplicação: Técnicos eletricistas.\nNormas: NBR5410.\nProcedimento: 1) Analise painel de erros; 2) Consulte manual.\nResponsável: Eletricista.\nEquipamentos: Multímetro.\nSegurança: Evite contato com partes energizadas.",
                        "Objective: Identify electrical faults.\nApplication: Electricians.\nNorms: NBR5410.\nProcedure: 1) Analyze error panel; 2) Check manual.\nResponsible: Electrician.\nEquipment: Multimeter.\nSafety: Avoid contact with live parts.",
                        "Objetivo: Identificar fallas eléctricas.\nAplicación: Técnicos electricistas.\nNormas: NBR5410.\nProcedimiento: 1) Analice panel de errores; 2) Consulte manual.\nResponsable: Electricista.\nEquipos: Multímetro.\nSeguridad: Evite contacto con partes energizadas.",
                        "Ziel: Elektrische Fehler erkennen.\nAnwendung: Elektriker.\nNormen: NBR5410.\nAblauf: 1) Fehlerpanel analysieren; 2) Handbuch prüfen.\nVerantwortlich: Elektriker.\nAusrüstung: Multimeter.\nSicherheit: Kontakt mit spannungsführenden Teilen vermeiden.",
                        "目标：识别电气故障。\n适用对象：电工。\n规范：NBR5410。\n步骤：1）分析错误面板；2）查阅手册。\n责任人：电工。\n设备：万用表。\n安全：避免接触带电部件。"
                },
                { // 5
                        "Objetivo: Manter ambiente seguro e limpo.\nAplicação: Todos os funcionários.\nNormas: NR6, NR24.\nProcedimento: 1) Use EPI; 2) Limpe área após uso.\nResponsável: Usuário do local.\nEquipamentos: EPI, materiais de limpeza.\nSegurança: Evite obstruções.",
                        "Objective: Keep environment safe and clean.\nApplication: All employees.\nNorms: NR6, NR24.\nProcedure: 1) Use PPE; 2) Clean area after use.\nResponsible: Area user.\nEquipment: PPE, cleaning materials.\nSafety: Avoid obstructions.",
                        "Objetivo: Mantener ambiente seguro y limpio.\nAplicación: Todos los empleados.\nNormas: NR6, NR24.\nProcedimiento: 1) Use EPI; 2) Limpie área tras uso.\nResponsable: Usuario del lugar.\nEquipos: EPI, materiales de limpieza.\nSeguridad: Evite obstrucciones.",
                        "Ziel: Sichere und saubere Umgebung erhalten.\nAnwendung: Alle Mitarbeiter.\nNormen: NR6, NR24.\nAblauf: 1) PSA tragen; 2) Bereich nach Nutzung reinigen.\nVerantwortlich: Nutzer des Bereichs.\nAusrüstung: PSA, Reinigungsmittel.\nSicherheit: Keine Hindernisse.",
                        "目标：保持环境安全清洁。\n适用对象：所有员工。\n规范：NR6, NR24。\n步骤：1）使用防护装备；2）使用后清理。\n责任人：使用者。\n设备：防护装备，清洁用品。\n安全：避免阻塞。"
                },
                { // 6
                        "Objetivo: Padronizar operação de máquinas.\nAplicação: Operadores setoriais.\nNormas: NR12.\nProcedimento: 1) Siga roteiro; 2) Registre operações.\nResponsável: Operador.\nEquipamentos: Tablet, crachá.\nSegurança: Não opere sem autorização.",
                        "Objective: Standardize machine operation.\nApplication: Sector operators.\nNorms: NR12.\nProcedure: 1) Follow script; 2) Log operations.\nResponsible: Operator.\nEquipment: Tablet, badge.\nSafety: Do not operate without authorization.",
                        "Objetivo: Estandarizar operación de máquinas.\nAplicación: Operadores sectoriales.\nNormas: NR12.\nProcedimiento: 1) Siga guion; 2) Registre operaciones.\nResponsable: Operador.\nEquipos: Tablet, credencial.\nSeguridad: No opere sin autorización.",
                        "Ziel: Maschinenbetrieb standardisieren.\nAnwendung: Sektorbetreiber.\nNormen: NR12.\nAblauf: 1) Ablaufplan befolgen; 2) Vorgänge protokollieren.\nVerantwortlich: Bediener.\nAusrüstung: Tablet, Ausweis.\nSicherheit: Nicht ohne Genehmigung bedienen.",
                        "目标：规范机器操作。\n适用对象：部门操作员。\n规范：NR12。\n步骤：1）遵循流程；2）记录操作。\n责任人：操作员。\n设备：平板，工牌。\n安全：未经授权勿操作。"
                },
                { // 7
                        "Objetivo: Atualizar software do equipamento.\nAplicação: Técnicos de TI.\nNormas: Manual do fabricante.\nProcedimento: 1) Baixe arquivo; 2) Atualize via USB.\nResponsável: Técnico autorizado.\nEquipamentos: Pen drive.\nSegurança: Não desligue durante atualização.",
                        "Objective: Update equipment software.\nApplication: IT technicians.\nNorms: Manufacturer's manual.\nProcedure: 1) Download file; 2) Update via USB.\nResponsible: Authorized technician.\nEquipment: USB drive.\nSafety: Do not power off during update.",
                        "Objetivo: Actualizar software del equipo.\nAplicación: Técnicos de TI.\nNormas: Manual del fabricante.\nProcedimiento: 1) Descargue archivo; 2) Actualice por USB.\nResponsable: Técnico autorizado.\nEquipos: USB.\nSeguridad: No apague durante actualización.",
                        "Ziel: Gerätesoftware aktualisieren.\nAnwendung: IT-Techniker.\nNormen: Herstellerhandbuch.\nAblauf: 1) Datei herunterladen; 2) Über USB aktualisieren.\nVerantwortlich: Autorisierter Techniker.\nAusrüstung: USB-Stick.\nSicherheit: Während Update nicht ausschalten.",
                        "目标：更新设备软件。\n适用对象：IT技术员。\n规范：厂家手册。\n步骤：1）下载文件；2）通过USB更新。\n责任人：授权技术员。\n设备：U盘。\n安全：更新时勿断电。"
                },
                { // 8
                        "Objetivo: Garantir rotina de segurança.\nAplicação: Todos os turnos.\nNormas: NR1.\nProcedimento: 1) Marque itens do checklist; 2) Reporte falhas.\nResponsável: Supervisor.\nEquipamentos: Lista de verificação.\nSegurança: Corrija falhas imediatamente.",
                        "Objective: Ensure safety routine.\nApplication: All shifts.\nNorms: NR1.\nProcedure: 1) Check items; 2) Report failures.\nResponsible: Supervisor.\nEquipment: Checklist.\nSafety: Correct failures immediately.",
                        "Objetivo: Garantizar rutina de seguridad.\nAplicación: Todos los turnos.\nNormas: NR1.\nProcedimiento: 1) Marque ítems; 2) Reporte fallas.\nResponsable: Supervisor.\nEquipos: Lista de verificación.\nSeguridad: Corrija fallas de inmediato.",
                        "Ziel: Sicherheitsroutine gewährleisten.\nAnwendung: Alle Schichten.\nNormen: NR1.\nAblauf: 1) Punkte abhaken; 2) Fehler melden.\nVerantwortlich: Supervisor.\nAusrüstung: Checkliste.\nSicherheit: Fehler sofort beheben.",
                        "目标：确保安全例行。\n适用对象：所有班次。\n规范：NR1。\n步骤：1）勾选清单项目；2）报告故障。\n责任人：主管。\n设备：检查表。\n安全：立即纠正故障。"
                },
                { // 9
                        "Objetivo: Desligar equipamento com segurança.\nAplicação: Operadores.\nNormas: NR10.\nProcedimento: 1) Pressione botão vermelho; 2) Aguarde LED apagar.\nResponsável: Operador.\nEquipamentos: Nenhum específico.\nSegurança: Confirme desligamento total.",
                        "Objective: Safely shut down equipment.\nApplication: Operators.\nNorms: NR10.\nProcedure: 1) Press red button; 2) Wait for LED to turn off.\nResponsible: Operator.\nEquipment: None specific.\nSafety: Confirm total shutdown.",
                        "Objetivo: Apagar equipo de forma segura.\nAplicación: Operadores.\nNormas: NR10.\nProcedimiento: 1) Presione botón rojo; 2) Espere LED apagarse.\nResponsable: Operador.\nEquipos: Ninguno específico.\nSeguridad: Confirme apagado total.",
                        "Ziel: Gerät sicher abschalten.\nAnwendung: Bediener.\nNormen: NR10.\nAblauf: 1) Roten Knopf drücken; 2) Auf LED-Aus warten.\nVerantwortlich: Bediener.\nAusrüstung: Keine spezifisch.\nSicherheit: Gesamtes Abschalten bestätigen.",
                        "目标：安全关闭设备。\n适用对象：操作员。\n规范：NR10。\n步骤：1）按下红色按钮；2）等待LED熄灭。\n责任人：操作员。\n设备：无特殊要求。\n安全：确认完全关机。"
                },
                { // 10
                        "Objetivo: Testar sensores industriais.\nAplicação: Técnicos de manutenção.\nNormas: Manual técnico.\nProcedimento: 1) Ative modo teste; 2) Verifique resposta dos sensores.\nResponsável: Técnico.\nEquipamentos: Painel de controle.\nSegurança: Siga instruções do fabricante.",
                        "Objective: Test industrial sensors.\nApplication: Maintenance technicians.\nNorms: Technical manual.\nProcedure: 1) Activate test mode; 2) Check sensor response.\nResponsible: Technician.\nEquipment: Control panel.\nSafety: Follow manufacturer instructions.",
                        "Objetivo: Probar sensores industriales.\nAplicación: Técnicos de mantenimiento.\nNormas: Manual técnico.\nProcedimiento: 1) Active modo prueba; 2) Verifique respuesta de sensores.\nResponsable: Técnico.\nEquipos: Panel de control.\nSeguridad: Siga instrucciones del fabricante.",
                        "Ziel: Industrielle Sensoren testen.\nAnwendung: Wartungstechniker.\nNormen: Technisches Handbuch.\nAblauf: 1) Testmodus aktivieren; 2) Sensorantwort prüfen.\nVerantwortlich: Techniker.\nAusrüstung: Bedienfeld.\nSicherheit: Herstelleranweisungen beachten.",
                        "目标：测试工业传感器。\n适用对象：维护技师。\n规范：技术手册。\n步骤：1）启用测试模式；2）检查传感器响应。\n责任人：技师。\n设备：控制面板。\n安全：遵循厂家说明。"
                }
        };

        int tiposCount = tipos.size();
        int idiomasCount = idiomas.size();

        for (int i = 0; i < 10; i++) {
            TipoOrientacao tipo = tipos.get(i % tiposCount);
            Orientacao orientacao = new Orientacao(String.format("%03d", i + 1), tipo);

            for (int j = 0; j < idiomasCount; j++) {
                Idioma idioma = idiomas.get(j);
                String titulo = titulos[i][j < titulos[i].length ? j : titulos[i].length - 1];
                String conteudo = conteudos[i][j < conteudos[i].length ? j : conteudos[i].length - 1];
                orientacao.adicionarTitulo(idioma, titulo);
                orientacao.adicionarConteudo(idioma, conteudo);
            }

            listaOrientacoes.add(orientacao);
        }
    }

    // Métodos auxiliares para as Listas de Orientações (BOA PARTE VAI TER QUE MUDAR
    // QUANDO INSERIRMOS O BANCO DE DADOS)

    private static Orientacao buscarPorCodigo(String codigo) {
        return listaOrientacoes.stream()
                .filter(o -> o.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public static List<Orientacao> getListaOrientacoes() {
        return listaOrientacoes;
    }

    // Métodos CRUD Service

    // Método para cadastrar uma nova Orientação
    public static void cadastrarOrientacao(
            // Parâmetros para o cadastro de uma nova Orientação
            String codigo,
            TipoOrientacao tipo,
            Map<Idioma, String> titulos,
            Map<Idioma, String> conteudos

    ) throws DadosIncompletosException {

        // Validações

        ValidadorService.validarCodigoCadastro(codigo, listaOrientacoes); // Validar Exceções

        Orientacao orientacao = new Orientacao(codigo, tipo);

        for (Idioma idiomas : titulos.keySet()) {

            String titulo = titulos.get(idiomas);
            String conteudo = conteudos.get(idiomas);

            // Validações

            if (titulo == null || titulo.isBlank()) {
                throw new DadosIncompletosException(
                        mensagem.get("exception.orientacao.cadastro.titulo.vazio") + " " + idiomas.getNome());
            }

            if (conteudo == null || conteudo.isBlank()) {
                throw new DadosIncompletosException(
                        mensagem.get("exception.orientacao.cadastro.conteudo.vazio") + " " + idiomas.getNome());
            }

            // Adiciona os títulos e conteúdos à Orientação

            orientacao.adicionarTitulo(idiomas, titulo);
            orientacao.adicionarConteudo(idiomas, conteudo);

        }

        // Cria a nova Orientação

        // Adiciona à lista de Orientações

        listaOrientacoes.add(orientacao);

    }

    // Método para Editar uma Orientação

    public static void editarOrientacao(
            // Parâmetros para o cadastro de uma nova Orientação
            String codigo,
            Map<Idioma, String> novosTitulos,
            Map<Idioma, String> novosConteudos

    ) {

        ValidadorService.validarInputVazio(codigo);

        Orientacao orientacaoEdicao = buscarPorCodigo(codigo);

        ValidadorService.validarExistenciaOrientacao(orientacaoEdicao);

        // Edição dos Títulos e Conteúdos em Cada Idioma

        for (Idioma idioma : idiomaService.getListaIdiomas()) {

            String novoTitulo = novosTitulos.get(idioma);
            String novoConteudo = novosConteudos.get(idioma);

            if (novoTitulo != null && !novoTitulo.isEmpty()) {

                orientacaoEdicao.adicionarTitulo(idioma, novoTitulo);

            }

            if (novoConteudo != null && !novoConteudo.isEmpty()) {

                orientacaoEdicao.adicionarConteudo(idioma, novoConteudo);

            }

        }

    }

    public static void deletarOrientacao(String codigo) {

        ValidadorService.validarInputVazio(codigo);

        Orientacao orientacaoExclusao = buscarPorCodigo(codigo);

        ValidadorService.validarExistenciaOrientacao(orientacaoExclusao);

        listaOrientacoes.remove(orientacaoExclusao);

    }

    public static Orientacao pesquisarOrientacaoPorCodigo(String pesquisa) {

        ValidadorService.validarInputVazio(pesquisa);

        for (Orientacao orientacaoPesquisa : listaOrientacoes) {

            if (orientacaoPesquisa.getCodigo().equalsIgnoreCase(pesquisa)) {

                return orientacaoPesquisa;

            }
        }

        throw new DadosIncompletosException(
                mensagem.get("exception.orientacao.pesquisa.codigo_nao_encontrado") + " " + pesquisa);

    }

    public static Map<Orientacao, Map<Idioma, String>> pesquisarOrientacaoPorTitulo(String termoPesquisa) {

        ValidadorService.validarInputVazio(termoPesquisa);

        Map<Orientacao, Map<Idioma, String>> resultados = new LinkedHashMap<>();

        String termoLower = termoPesquisa.toLowerCase();

        String termoNorm = normalize(termoLower);

        for (Orientacao orientacao : listaOrientacoes) {
            Map<Idioma, String> titulosEncontrados = new LinkedHashMap<>();

            for (Idioma idioma : idiomaService.getListaIdiomas()) {

                String titulo = orientacao.getTitulo(idioma);

                if (titulo != null) {
                    String tituloNorm = normalize(titulo);
                    if (tituloNorm.contains(termoNorm)) {
                        titulosEncontrados.put(idioma, titulo);
                    }

                }

            }

            if (!titulosEncontrados.isEmpty()) {
                resultados.put(orientacao, titulosEncontrados);
            }

        }

        if (resultados.isEmpty()) {
            throw new DadosIncompletosException(
                    mensagem.get("exception.orientacao.pesquisa.termo_nao_encontrado") + " " + termoPesquisa);
        }

        return resultados;
    }

    private static String normalize(String text) {

        String n = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        return n.toLowerCase(Locale.ROOT);

    }

    public static List<Orientacao> listarOrientacoes() {

        return new ArrayList<>(listaOrientacoes);

    }

    public static List<Orientacao> listarOrientacoesPorTipo(TipoOrientacao tipoOrientacaoFiltro) {

        List<Orientacao> listaFiltrada = new ArrayList<>();

        for (Orientacao orientacao : listaOrientacoes) {

            if (tipoOrientacaoFiltro == null || orientacao.getTipo().equals(tipoOrientacaoFiltro)) {

                listaFiltrada.add(orientacao);

            }

        }

        return listaFiltrada;

    }

}