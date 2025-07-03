<div align="center" style="display: display_block">

# Gerador de Relatórios Jasper para Processos Seletivos

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Java](https://img.shields.io/badge/java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)

</div>

## Descrição

Esta é uma aplicação web desenvolvida em **Java** com **Spring Boot** que facilita a geração de relatórios em formato PDF utilizando a biblioteca **JasperReports**. Permite que usuários façam o upload de um arquivo CSV e customizem dinamicamente os relatórios gerados a partir de templates `.jrxml`.

A interface permite a personalização de títulos, subtítulos, links, textos e imagens, tornando os templates de relatório reutilizáveis para diferentes editais e processos seletivos.

## Funcionalidades

* **Geração de Relatórios em PDF**: Converte dados de arquivos CSV em relatórios PDF.
* **Templates JasperReports**: Utiliza arquivos `.jrxml` para definir o layout dos relatórios.
* **Interface Web**: Interface simples para upload de arquivos e preenchimento de parâmetros.
* **Múltiplos Tipos de Relatório**: Suporte para diferentes relatórios, como "Relação de Chamada", "Lista de Espera", "Maiores e Menores Notas", etc.
* **Parametrização Dinâmica**: Títulos, subtítulos, imagens, textos de instrução e links podem ser definidos dinamicamente pela interface.
* **UI Condicional**: A interface se adapta ao tipo de relatório selecionado.

## Tecnologias Utilizadas

* **Backend**:
    * [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    * [Spring Boot](https://spring.io/projects/spring-boot)
    * [JasperReports](https://community.jaspersoft.com/project/jasperreports-library)
* **Frontend**:
    * [Thymeleaf](https://www.thymeleaf.org/)
    * HTML5, CSS3, JavaScript
* **Build**:
    * [Apache Maven](https://maven.apache.org/)

## ⚙Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em seu ambiente:

1.  **JDK 17**: [Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2.  **Apache Maven 3.9.10**: [Download](https://maven.apache.org/download.cgi)
3.  **Git**: [Download](https://git-scm.com/downloads)
4.  Uma IDE de sua preferência (opcional, mas recomendado):
    * [IntelliJ IDEA](https://www.jetbrains.com/idea/)
    * [VS Code com o Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

## Como Rodar

**1. Clone o repositório:**

```bash
git clone https://github.com/Mei0-Metr0/report-generator.git
cd <nome-do-repositorio>
```

**2. Construa o projeto com o Maven:**

Abra um terminal na raiz do projeto e execute o seguinte comando. Isso irá baixar as dependências e compilar o código.

```bash
mvn clean install
```

**3. Execute a aplicação::**

Após a construção bem-sucedida, você pode iniciar o servidor Spring Boot com o seguinte comando:
```bash
mvn spring-boot:run
# Ou clique em RUN no 
```

O servidor será iniciado, e a aplicação estará disponível no seu navegador.

---

## Como Usar a Aplicação
Abra seu navegador e acesse a URL: http://localhost:8080

#### Preencha o Formulário:

- `Processo Seletivo`: Selecione o Processo ao qual o relatório se refere (ex: +Enem, Vestibular).

- `Tipo de Relatório`: Escolha o Relatório.

- `Campos Dinâmicos`: Preencha os campos de título, subtítulo, URLs e textos. Note que alguns campos só aparecerão se o tipo de relatório selecionado os utilizar (ex: os campos de matrícula só aparecem para "Relação de Chamada" e "Lista de Espera").

- `Arquivo CSV`: Clique em "Escolher arquivo" e selecione o arquivo CSV com os dados. Certifique-se de que o CSV segue as instruções de formatação exibidas na tela para o tipo de relatório selecionado.

- `Gere o Relatório`: Clique no botão "Gerar Relatório".

- `Download`: O download do arquivo PDF será iniciado automaticamente.

### Estrutura do Projeto
Uma visão geral da estrutura de pastas e arquivos:

```
.
├── pom.xml                             # Arquivo de configuração do Maven
└── src
├── main
│   ├── java
│   │   └── br/edu/utfpr/reportgenerator
│   │       ├── controller/             # Controladores
│   │       ├── model/                  # Enums e classes de modelo
│   │       └── service/                # Lógica de negócio (serviço de relatório)
│   └── resources
│       ├── application.properties # Configurações da aplicação
│       ├── reports/                    # Templates de relatório (.jrxml)
│       ├── static/
│       │   └── js/main.js              # JavaScript
│       └── templates/
│           └── index.html              # Templates de view (Thymeleaf)
└── test
```