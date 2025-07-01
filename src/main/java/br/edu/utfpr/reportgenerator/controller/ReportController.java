package br.edu.utfpr.reportgenerator.controller;

import br.edu.utfpr.reportgenerator.model.ReportType;
import br.edu.utfpr.reportgenerator.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        // Adiciona os tipos de processo seletivo e relatórios ao modelo para o Thymeleaf usar
        model.addAttribute("selectionProcesses", new String[]{"+Enem", "Vestibular", "PSS", "SiSU"});
        model.addAttribute("reportTypes", ReportType.values());
        return "index";
    }

    @PostMapping("/generate-report")
    public ResponseEntity<?> generateReport(
            @RequestParam("reportType") String reportTypeName,
            @RequestParam("csvFile") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Por favor, selecione um arquivo CSV para enviar.");
            return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/").build();
        }

        try {
            ReportType reportType = ReportType.valueOf(reportTypeName);
            byte[] pdfBytes = reportService.generatePdfReport(reportType, file.getInputStream());

            String filename = reportType.name().toLowerCase() + "_report.pdf";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erro ao gerar o relatório: " + e.getMessage());
            return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/").build();
        }
    }
}