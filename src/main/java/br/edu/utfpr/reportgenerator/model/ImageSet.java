package br.edu.utfpr.reportgenerator.model;

/**
 * Representa um conjunto de imagens (tema) para o relatório.
 *
 * @param id O identificador único para este conjunto (usado no formulário).
 * @param displayName O nome amigável para exibir na ComboBox.
 * @param processLogoPath O caminho no classpath para a logo do processo seletivo.
 * @param utfprLogoPath O caminho no classpath para a logo da UTFPR.
 */
public record ImageSet(
        String id,
        String displayName,
        String processLogoPath,
        String utfprLogoPath
) {}