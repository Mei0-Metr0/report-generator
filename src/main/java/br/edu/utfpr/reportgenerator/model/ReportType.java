package br.edu.utfpr.reportgenerator.model;

import lombok.Getter;

@Getter
public enum ReportType {
    CHAMADA(
            "Relação de Chamada",
            "reports/Chamada.jrxml",
            """
            => INSTRUÇÕES PARA GERAR O CSV DE CHAMADA <=
            1. Acesse o sistema de onboarding e exporte o relatório de classificação.
            2. Filtre os dados para incluir APENAS os candidatos convocados.
            3. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            4. O cabeçalho do CSV DEVE conter os seguintes campos:
               Campus;Curso;Turno;Inscrição;Nome;Situação do requerimento de matrícula;Nota final;Class ACP1;Cota do candidato;Class LI_EPP2;Class LI_PCDP3;Class LI_QP4;Class LI_PPIP5;Class LB_EPP6;Class LB_PCDP7;Class LB_QP8;Class LB_PPIP9
            """
    ),
    LISTA_ESPERA(
            "Lista de Espera",
            "reports/ListaEspera.jrxml",
            """
            => INSTRUÇÕES PARA GERAR O CSV DA LISTA DE ESPERA <=
            1. Exporte o relatório de classificação geral do sistema.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            3. O cabeçalho do CSV DEVE conter os seguintes campos, na ordem correta:
               Campus;Curso;Turno;Inscrição;Nome;Situação do requerimento de matrícula;Nota final;Class ACP1;Cota do candidato;Class LI_EPP2;Class LI_PCDP3;Class LI_QP4;Class LI_PPIP5;Class LB_EPP6;Class LB_PCDP7;Class LB_QP8;Class LB_PPIP9
            """
    ),
    MAIOR_MENOR_NOTA(
            "Maiores e Menores Notas",
            "reports/MaiorMenorNota.jrxml",
            """
            => INSTRUÇÕES PARA GERAR O CSV DE MAIOR/MENOR NOTA <=
            1. Exporte o relatório consolidado de notas por curso e cota.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            3. O cabeçalho do CSV DEVE conter os seguintes campos:
               Campus;Grau;Curso;Turno;Cota;Maior Nota;Menor Nota
            """
    ),
    VAGAS_DISPONIVEIS(
            "Vagas Disponíveis por Curso",
            "reports/VagasDisponiveisCursos.jrxml",
            """
            => INSTRUÇÕES PARA GERAR O CSV DE VAGAS DISPONÍVEIS <=
            1. Exporte o relatório de vagas disponíveis do sistema.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            3. O cabeçalho do CSV DEVE conter os seguintes campos
               Campus;Grau;Curso;Turno;Cota;Maior Nota;Menor Nota
            """
    );

    private final String displayName;
    private final String jrxmlPath;
    private final String instructions;

    ReportType(String displayName, String jrxmlPath, String instructions) {
        this.displayName = displayName;
        this.jrxmlPath = jrxmlPath;
        this.instructions = instructions;
    }
}
