package HeXA.MealU_HeXA_Project.api.domain.excel.controller;

import HeXA.MealU_HeXA_Project.api.domain.excel.service.ExcelService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class ExcelController {
    private final ExcelService excelService;

    @Autowired
    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/excel")
    public String main() {

        return "excel";
    }

    @PostMapping("/excel/read")
    public ResponseEntity<Void> readExcel(@RequestParam("file") MultipartFile file) throws URISyntaxException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            return ResponseEntity.badRequest().build();
        }

        try {
            excelService.importExcel(file);
        }catch(IOException e){
            return ResponseEntity.badRequest().build();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.created(new URI("/importSuccess")).build();


    }
}
