<p align="center">
  <img src="https://i.postimg.cc/DZhjTmP2/logo-wegone.png" alt="Logo do Projeto" width="300">
</p>

# 📝 Descrição

Este repositório contém o projeto desenvolvido como parte da **Situação de Aprendizagem Interdisciplinar** do **CentroWeg - MIDS 78**. O objetivo foi unir os conhecimentos adquiridos nas disciplinas de:

- **Lógica da Programação**
- **Implementação de Banco de Dados**
- **Inglês Técnico**
- **Metodologias Ágeis e Versionamento**

Além disso, foram aplicados conhecimentos complementares em áreas como **Programação Orientada a Objetos (POO)**, **Java com Maven**, e desenvolvimento de interfaces.

## 🎯 Objetivo

O WegOne é um sistema de gerenciamento de orientações operacionais, projetado para:
- Cadastrar e organizar diferentes tipos de orientações técnicas
- Suportar múltiplos idiomas (Português, Inglês, Espanhol, Alemão e Chinês)
- Facilitar a navegação com menus dinâmicos por idioma
- Proporcionar acesso rápido e padronizado às informações

```
# 📂 Estrutura do Projeto

📁 WEGONE_V1_BACK
├── 📁 src
│ ├── 📁 main
│ │ ├── 📁 java
│ │ │ └── 📁 br/com/wegone
│ │ │ ├── 📁 core
│ │ │ │ ├── IdiomasAeConsdo.java
│ │ │ │ └── 📁 exception
│ │ │ │ └── DadosIncompletosException.java
│ │ │ ├── 📁 model
│ │ │ │ ├── Idioma.java
│ │ │ │ ├── IdiomasDisponiveis.java
│ │ │ │ ├── Orientacao.java
│ │ │ │ ├── TipoOrientacao.java
│ │ │ │ ├── TipoOrientacoesDisponiveis.java
│ │ │ │ └── Usuario.java
│ │ │ ├── 📁 repository
│ │ │ │ ├── ConexaoBD.java
│ │ │ │ └── UsuarioDAO.java
│ │ │ ├── 📁 service
│ │ │ │ ├── IdiomaMensagens.java
│ │ │ │ ├── IdiomasService.java
│ │ │ │ ├── OrientacaoService.java
│ │ │ │ ├── TranslationService.java
│ │ │ │ ├── UsuarioService.java
│ │ │ │ └── ValidacaoService.java
│ │ │ └── 📁 view
│ │ │ ├── AuxiliarDaConsole.java
│ │ │ ├── MenuService.java
│ │ │ ├── UsuarioTestes.java
│ │ │ └── UsuarioView.java
│ │ ├── 📁 resources
│ │ │ ├── 📁 l10n
│ │ │ │ ├── menu_de_DE.properties
│ │ │ │ ├── menu_en_US.properties
│ │ │ │ ├── menu_es_ES.properties
│ │ │ │ ├── menu_pt_BR.properties
│ │ │ │ └── menu_zh_CN.properties
│ │ │ └── logging.properties
│ │ └── App.java
│ └── 📁 test
│ └── 📁 java/br/com/wegone
│ └── AppTest.java
├── 📁 target
│ ├── 📁 classes
│ │ └── 📁 br/com/wegone
│ │ ├── 📁 core
│ │ │ ├── IdiomasAeConsdo.class
│ │ │ └── 📁 exception
│ │ │ └── DadosIncompletosException.class
│ │ └── 📁 model
│ │ ├── Idioma.class
│ │ └── (... demais .class)
│ └── 📁 test-classes
│ └── 📁 br/com/wegone
│ └── AppTest.class
├── .gitignore
└── pom.xml
```

# 🛠️ Tecnologias Utilizadas

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apache/apache-original.svg" height="40" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" height="40" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" height="40" />
  <img src="https://cdn.worldvectorlogo.com/logos/jira-1.svg" height="40" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg" height="40" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg" height="40" />
</div>

- [**Java 21**](https://www.oracle.com/java/) — Desenvolvimento do backend  
- [**Maven 4.0.0**](https://maven.apache.org/) — Build e gerenciamento de dependências  
- [**MySQL**](https://www.mysql.com/) — Banco de dados utilizado  
- [**Git**](https://git-scm.com/) — Versionamento e colaboração  
- [**Jira**](https://www.atlassian.com/software/jira) — Organização e acompanhamento ágil (Scrum)  
- [**HTML/CSS**](https://developer.mozilla.org/en-US/docs/Web/HTML) — Front-end do sistema  

---

# 📱 Versão Atual

A versão atual do sistema contempla:
- Backend completo e funcional, com integração simples ao banco de dados MySQL
- Interface funcional no front-end, porém ainda não integrada devido ao tempo
- Suporte multilíngue implementado diretamente em código
- Estrutura modular preparada para futura melhoria com arquivos de tradução (JSON)

> **📌 Nota:** A versão atual entrega o funcionamento completo do back-end com foco em organização, clareza e acessibilidade. Futuras versões podem expandir para melhorar performance, modularização e visual.

---

# ▶️ Como Executar

## ✅ Pré-requisitos
- Java JDK 21
- Maven versão 4.0.0 instalado
- MySQL disponível localmente
- IDE de sua preferência (IntelliJ, VS Code ou Eclipse)
- Fonte NSimSun para melhor compatibilidade (opcional)

## ⚙️ Passos para Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/TrabalhoFinalMIDS78/WEGONE_V1_BACK.git
   cd WEGONE_V1_BACK
Configure o terminal para UTF-8 (Windows)

```bash
chcp 65001
Compile o projeto
```

```bash
mvn compile
Execute o projeto
```

```bash
mvn exec:java -Dexec.mainClass=br.com.wegone.App
```

>💡 Atenção: Configure corretamente a conexão com o banco de dados no código antes da execução.

## 🤝 Contribuição

Este projeto foi desenvolvido em equipe com foco no aprendizado prático, versionamento real e aplicação de metodologias ágeis.

### 👨‍💻 Equipe

- [**Pablo Tzeliks**](https://github.com/PabloTzeliks) — *Product Owner* e Dev (lógica e estrutura base)  
- [**Nícollas França**](https://github.com/Nicofranca) — Dev (conexão e estrutura do banco de dados)  
- [**Victor Strelow**](https://github.com/VictorStrelow) — Dev (interface e usabilidade)  
- [**Gustavo Kotryk**](https://github.com/GustavoKotryk) — *Scrum Master* e Dev (organização, versionamento, integração)

---

## 📚 Agradecimentos

Agradecemos aos professores que apoiaram e orientaram o desenvolvimento deste projeto:

- Bruno da Silva Andrade  
- João Pedro Silva Valentim  
- Matheus Quost  
- Kristian Erdmann  
- Vinicius Matheus Jacobowski Trindade  
- Willer Rezende Motti  

---

## 💡 Observação Final

O **WegOne** representa nossa evolução como desenvolvedores e o resultado de um trabalho conjunto, aplicando boas práticas, lógica e metodologias ágeis. O projeto está pronto para crescer com novas funcionalidades e melhorias de integração.
