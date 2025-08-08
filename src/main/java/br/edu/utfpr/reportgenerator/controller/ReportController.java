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

import java.util.HashMap;
import java.util.Map;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        // Adiciona os processos seletivos e tipos de relatório ao modelo
        model.addAttribute("selectionProcesses", new String[]{"+Enem", "Vestibular", "PSS", "SiSU"});
        model.addAttribute("reportTypes", ReportType.values());
        return "index";
    }

    @PostMapping("/generate-report")
    public ResponseEntity<?> generateReport(
            @RequestParam("reportType") String reportTypeName,
            @RequestParam("csvFile") MultipartFile file,
            @RequestParam("reportTitle") String reportTitle,
            @RequestParam("reportSubtitle") String reportSubtitle,
            @RequestParam("explanationText") String explanationText,
            @RequestParam("utfprLogoUrl") String utfprLogoUrl,
            @RequestParam("processLogoUrl") String processLogoUrl,
            @RequestParam("defaultCampusImgUrl") String defaultCampusImgUrl,
            @RequestParam(value = "showFooterDate", required = false) boolean showFooterDate,
            @RequestParam("footerText") String footerText,
            @RequestParam("qrCodeUrl") String qrCodeUrl,
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Por favor, selecione um arquivo CSV para enviar.");
            return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/").build();
        }

        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("P_TITLE", reportTitle);
            parameters.put("P_SUBTITLE", reportSubtitle);
            parameters.put("P_EXPLANATION_TEXT", explanationText);
            parameters.put("P_UTFPR_LOGO_URL", utfprLogoUrl);
            parameters.put("P_PROCESS_LOGO_URL", processLogoUrl);
            parameters.put("P_DEFAULT_CAMPUS_IMG_URL", defaultCampusImgUrl);
            parameters.put("P_SHOW_FOOTER_DATE", showFooterDate);
            parameters.put("P_FOOTER_TEXT", footerText);
            parameters.put("P_QR_CODE_URL", qrCodeUrl);


            ReportType reportType = ReportType.valueOf(reportTypeName);
            byte[] pdfBytes = reportService.generatePdfReport(reportType, file.getInputStream(), parameters);

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