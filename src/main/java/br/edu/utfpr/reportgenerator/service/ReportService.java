package br.edu.utfpr.reportgenerator.service;

import br.edu.utfpr.reportgenerator.model.ReportType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class ReportService {

    public byte[] generatePdfReport(ReportType reportType, InputStream csvInputStream) throws JRException, UnsupportedEncodingException {
        // 1. Carrega o arquivo .jrxml do classpath
        InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream(reportType.getJrxmlPath());
        if (jrxmlStream == null) {
            throw new JRException("Arquivo de relatório não encontrado em: " + reportType.getJrxmlPath());
        }

        // 2. Compila o relatório .jrxml para .jasper
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        // 3. Cria a fonte de dados (DataSource) a partir do CSV
        JRCsvDataSource dataSource = new JRCsvDataSource(csvInputStream, "ISO-8859-1");
        dataSource.setFieldDelimiter(';');
        dataSource.setUseFirstRowAsHeader(true); // Usa a primeira linha como cabeçalho

        // 4. Preenche o relatório com os dados
        // O terceiro argumento (parameters) pode ser usado para passar valores únicos, como títulos dinâmicos
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // 5. Exporta o relatório preenchido para PDF (em memória)
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}