package br.edu.utfpr.reportgenerator.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum ReportType {
    CHAMADA(
            "Relação de Chamada",
            "reports/Chamada.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DE CHAMADA <=
            1. Acesse o sistema de onboarding e exporte o relatório de classificação.
            2. Filtre os dados para incluir apenas os candidatos convocados na chamada desejada.
            3. Salve o arquivo CSV com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Curso", "Turno", "Inscrição", "Nome", "Situação do requerimento de matrícula", "Nota final", "Class ACP1", "Cota do candidato", "Class LI_EPP2", "Class LI_PCDP3", "Class LI_QP4", "Class LI_PPIP5", "Class LB_EPP6", "Class LB_PCDP7", "Class LB_QP8", "Class LB_PPIP9")
    ),
    LISTA_ESPERA(
            "Lista de Espera",
            "reports/ListaEspera.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DA LISTA DE ESPERA <=
            1. No sistema de onboarding, exporte o relatório de classificação geral do processo seletivo.
            2. Não aplique filtros de situação, a lista de espera inclui todos os não convocados.
            3. Salve o arquivo CSV com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Curso", "Turno", "Inscrição", "Nome", "Situação do requerimento de matrícula", "Nota final", "Class ACP1", "Cota do candidato", "Class LI_EPP2", "Class LI_PCDP3", "Class LI_QP4", "Class LI_PPIP5", "Class LB_EPP6", "Class LB_PCDP7", "Class LB_QP8", "Class LB_PPIP9")
    ),
    MAIOR_MENOR_NOTA(
            "Maiores e Menores Notas",
            "reports/MaiorMenorNota.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DE MAIOR/MENOR NOTA <=
            1. Exporte o relatório consolidado de notas por curso e cota.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Grau", "Curso", "Turno", "Cota", "Maior Nota", "Menor Nota")
    ),
    VAGAS_DISPONIVEIS(
            "Vagas Disponíveis por Curso",
            "reports/VagasDisponiveisCursos.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DE VAGAS DISPONÍVEIS <=
            1. Exporte o relatório de vagas disponíveis do sistema de gestão acadêmica.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Grau", "Curso", "Turno", "AC", "LB_PPI", "LB_Q", "LB_PCD", "LB_EP", "LI_PPI", "LI_PCD", "LI_EP", "LI_Q", "Total de vagas")
    );

    private final String displayName;
    private final String jrxmlPath;
    private final String instructions;
    private final List<String> requiredHeaders;

    ReportType(String displayName, String jrxmlPath, String instructions, List<String> requiredHeaders) {
        this.displayName = displayName;
        this.jrxmlPath = jrxmlPath;
        this.instructions = instructions;
        this.requiredHeaders = requiredHeaders;
    }
}