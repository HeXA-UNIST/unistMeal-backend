package HeXA.MealU_HeXA_Project.controller;

import HeXA.MealU_HeXA_Project.service.ExcelService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;

    @Autowired
    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("")
    public ResponseEntity<String> main() {

        return new ResponseEntity<>("excel", HttpStatus.OK);
    }

    @PostMapping("/read")
    public ResponseEntity<Void> readExcel(MultipartFile dormitoryFile,
                                          MultipartFile studentFile,
                                          MultipartFile professorFile
                                          ) throws URISyntaxException, IOException, InvalidFormatException {
        String dormitoryExtension = FilenameUtils.getExtension(dormitoryFile.getOriginalFilename());
        String studentExtension = FilenameUtils.getExtension(studentFile.getOriginalFilename());
        String professorExtension = FilenameUtils.getExtension(professorFile.getOriginalFilename());
        if (!dormitoryExtension.equals("xlsx") && !dormitoryExtension.equals("xls")) {
            return ResponseEntity.badRequest().build();
        }else if (!studentExtension.equals("xlsx") && !studentExtension.equals("xls")) {
            return ResponseEntity.badRequest().build();
        }else if (!professorExtension.equals("xlsx") && !professorExtension.equals("xls")) {
            return ResponseEntity.badRequest().build();
        }
        excelService.importExcel(dormitoryFile);
        excelService.importExcel(studentFile);
        excelService.importExcel(professorFile);
        return ResponseEntity.created(new URI("/importSuccess")).build();


    }
}
