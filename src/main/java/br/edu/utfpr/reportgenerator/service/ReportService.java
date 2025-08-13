package br.edu.utfpr.reportgenerator.service;

import br.edu.utfpr.reportgenerator.model.ReportType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generatePdfReport(ReportType reportType, InputStream csvInputStream, Map<String, Object> parameters, String encoding) throws JRException, UnsupportedEncodingException {
        // 1. Carrega o arquivo .jrxml
        InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream(reportType.getJrxmlPath());
        if (jrxmlStream == null) {
            throw new JRException("Arquivo de relatório não encontrado em: " + reportType.getJrxmlPath());
        }

        // 2. Compila o relatório .jrxml para .jasper
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        // 3. Cria a fonte de dados a partir do CSV usando o encoding selecionado
        JRCsvDataSource dataSource = new JRCsvDataSource(csvInputStream, encoding);
        dataSource.setFieldDelimiter(';');
        dataSource.setUseFirstRowAsHeader(true);

        // 4. Preenche o relatório com os dados e os Parametros
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 5. Exporta o relatório preenchido para PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}