package br.edu.utfpr.reportgenerator.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum ReportType {
    INSCRICOES_HOMOLOGADAS(
            "Inscrições Homologadas",
            "reports/InscricoesHomologadasFULL.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DE INSCRIÇÕES HOMOLOGADAS <=
            1. Exporte o relatório de inscrições homologadas do sistema.
            2. Salve o arquivo CSV com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Protocolo", "Candidato", "Cota", "Campus1", "Curso1", "Campus2", "Curso2"),
            "EDITAL 12/2025 - PROGRAD PROCESSO SELETIVO %s - 2025/2",
            "RELAÇÃO DE INSCRIÇÕES HOMOLOGADAS",
            "Este relatório apresenta a lista de todas as inscrições homologadas para o processo seletivo.",
            "https://www.utfpr.edu.br/cursos/estudenautfpr/maisenem/cronograma",
            "Acompanhe o cronograma completo e as próximas etapas no portal."
    ),
    LISTA_ESPERA(
            "Lista de Espera",
            "reports/MaisENEM_ListaEspera.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DA LISTA DE ESPERA <=
            1. No sistema de onboarding, exporte o relatório de classificação geral do processo seletivo.
            2. Não aplique filtros de situação, a lista de espera inclui todos os não convocados.
            3. Salve o arquivo CSV com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Curso", "Turno", "Inscrição", "Nome", "Situação do requerimento de matrícula", "Nota final", "Class ACP1", "Cota do candidato", "Class LI_EPP2", "Class LI_PCDP3", "Class LI_QP4", "Class LI_PPIP5", "Class LB_EPP6", "Class LB_PCDP7", "Class LB_QP8", "Class LB_PPIP9"),
            "EDITAL 12/2025 - PROGRAD PROCESSO SELETIVO %s - 2025/2",
            "RELAÇÃO DE CANDIDATOS CONVOCADOS NA PRIMEIRA CHAMADA",
            "<b>Os candidatos convocados deverão realizar a Etapa 1 do Requerimento de Matrícula no período de 11/julho (a partir das 8h) até 15/julho (às 23h59).</b>         Nessa etapa, o candidato deve acessar a Plataforma de Requerimento de Matrícula dos Cursos de Graduação da UTFPR, disponível no link: <u>https://sistemas2.utfpr.edu.br/ords/f?p=req_matricula</u>. Em seguida, deverá realizar o upload da documentação pessoal, acadêmica e, quando for o caso, da documentação exigida conforme sua categoria de cota,  descrito no Anexo III deste edital. Todo o processo deve seguir os procedimentos indicados na própria plataforma. <br/> O acesso à plataforma estará disponível exclusivamente durante o período de 11 de julho a 15 de julho, mediante login e senha que serão enviados ao e-mail cadastrado no momento da inscrição no Mais Enem 2025.",
            "https://sistemas2.utfpr.edu.br/ords/f?p=req_matricula",
            "Efetue agora o upload da documentação necessária através do link: https://sistemas2.utfpr.edu.br/ords/f?p=req_matricula"
    ),
    MAIOR_MENOR_NOTA(
            "Maiores e Menores Notas",
            "reports/MaisENEM_MaiorMenor.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DE MAIOR/MENOR NOTA <=
            1. Exporte o relatório consolidado de notas por curso e cota.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Grau", "Curso", "Turno", "Cota", "Maior Nota", "Menor Nota"),
            "EDITAL 12/2025 - PROGRAD PROCESSO SELETIVO %s - 2025/2",
            "Maior e Menor Nota",
            "Esta relação apresenta as maiores e menores notas por cota em cada curso/turno referentes a <br><b>1.ª Chamada</b>.",
            "http://www.utfpr.edu.br/maisenem",
            "Acompanhe as chamadas complementares em www.utfpr.edu.br/maisenem"
    ),
    RESULTADO_ETAPAS(
            "Resultado das Etapas",
            "reports/ResultadoEtapas.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DE RESULTADO DAS ETAPAS <=
            1. Exporte o relatório de resultado das etapas do sistema de gestão acadêmica.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Curso", "Turno", "Inscrição", "Nome", "Situação do requerimento de matrícula", "Nota final", "Class ACP1", "Cota do candidato", "Class LI_EPP2", "Class LI_PCDP3", "Class LI_QP4", "Class LI_PPIP5", "Class LB_EPP6", "Class LB_PCDP7", "Class LB_QP8", "Class LB_PPIP9", "Cota da vaga garantida", "Cotas homologadas"),
            "EDITAL 12/2025 - PROGRAD PROCESSO SELETIVO %s - 2025/2",
            "RESULTADO PRELIMINAR DA ETAPA 1 - 1ª chamada",
            "Candidatos na situação <b>ETAPA 1 CONCLUÍDA</b>, devem nos dias dd e dd/mmmm, acessar novamente a Plataforma e realizar a Etapa 2 (Confirmação). Candidatos na situação <b>ENVIAR SUBSTITUIÇÃO DE DOCUMENTOS</b>, devem nos dias dd e dd/mmm, acessar novamente a Plataforma e, de acordo com a orientação, substituir/completar a documentação exigida. Candidatos na situação <b>DESISTIU DA VAGA</b>, são candidatos que optaram por desistir de participar do Requerimento de Matrícula e por isso, são considerados desistentes. Os candidatos na situação <b>NÃO ENVIOU DOCUMENTAÇÃO</b>, são candidatos que não acessaram a Plataforma, no prazo indicado e por este motivo, perderam o direito à vaga e são considerados desistentes.",
            "https://sistemas2.utfpr.edu.br/ords/f?p=req_matricula",
            "Efetue agora a substituição da documentação necessária, se for o caso, através do link: https://sistemas2.utfpr.edu.br/ords/f?p=req_matricula"
    ),
    RESULTADO_ETAPAS_FULL(
            "Resultado das Etapas (Completo)",
            "reports/ResultadoEtapasFull.jrxml",
            """
            => INSTRUÇÕES PARA OBTER O CSV DE RESULTADO DAS ETAPAS (COMPLETO) <=
            1. Exporte o relatório completo de resultado das etapas do sistema de gestão acadêmica.
            2. O arquivo CSV deve ser salvo com codificação ISO-8859-1 e delimitador ponto e vírgula (;).
            """,
            List.of("Campus", "Curso", "Turno", "Inscrição", "Nome", "Situação do requerimento de matrícula", "Nota final", "Class ACP1", "Cota do candidato", "Class LI_EPP2", "Class LI_PCDP3", "Class LI_QP4", "Class LI_PPIP5", "Class LB_EPP6", "Class LB_PCDP7", "Class LB_QP8", "Class LB_PPIP9", "Cota da vaga garantida", "Cotas homologadas"),
            "EDITAL 12/2025 - PROGRAD PROCESSO SELETIVO %s - 2025/2",
            "RESULTADO PRELIMINAR DA ETAPA 1 - 1ª chamada",
            "Candidatos na situação <b>ETAPA 1 CONCLUÍDA</b>, devem nos dias dd e dd/mmmm, acessar novamente a Plataforma e realizar a Etapa 2 (Confirmação). Candidatos na situação <b>ENVIAR SUBSTITUIÇÃO DE DOCUMENTOS</b>, devem nos dias dd e dd/mmm, acessar novamente a Plataforma e, de acordo com a orientação, substituir/completar a documentação exigida. Candidatos na situação <b>DESISTIU DA VAGA</b>, são candidatos que optaram por desistir de participar do Requerimento de Matrícula e por isso, são considerados desistentes. Os candidatos na situação <b>NÃO ENVIOU DOCUMENTAÇÃO</b>, são candidatos que não acessaram a Plataforma, no prazo indicado e por este motivo, perderam o direito à vaga e são considerados desistentes.",
            "https://sistemas2.utfpr.edu.br/ords/f?p=req_matricula",
            "Efetue agora a substituição da documentação necessária, se for o caso, através do link: https://sistemas2.utfpr.edu.br/ords/f?p=req_matricula"
    );

    private final String displayName;
    private final String jrxmlPath;
    private final String instructions;
    private final List<String> requiredHeaders;
    private final String defaultTitleTemplate;
    private final String defaultSubtitle;
    private final String defaultExplanation;
    private final String defaultQrCodeUrl;
    private final String defaultFooterText;


    ReportType(String displayName, String jrxmlPath, String instructions, List<String> requiredHeaders, String defaultTitleTemplate, String defaultSubtitle, String defaultExplanation, String defaultQrCodeUrl, String defaultFooterText) {
        this.displayName = displayName;
        this.jrxmlPath = jrxmlPath;
        this.instructions = instructions;
        this.requiredHeaders = requiredHeaders;
        this.defaultTitleTemplate = defaultTitleTemplate;
        this.defaultSubtitle = defaultSubtitle;
        this.defaultExplanation = defaultExplanation;
        this.defaultQrCodeUrl = defaultQrCodeUrl;
        this.defaultFooterText = defaultFooterText;
    }
}