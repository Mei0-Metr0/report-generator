package br.edu.utfpr.reportgenerator.service;

import br.edu.utfpr.reportgenerator.model.ReportType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map; // Importa a classe Map

@Service
public class ReportService {

    // Assinatura do método atualizada para aceitar o mapa de parâmetros
    public byte[] generatePdfReport(ReportType reportType, InputStream csvInputStream, Map<String, Object> parameters) throws JRException, UnsupportedEncodingException {
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
        dataSource.setUseFirstRowAsHeader(true);

        // 4. Preenche o relatório com os dados e os PARÂMETROS
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 5. Exporta o relatório preenchido para PDF (em memória)
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}