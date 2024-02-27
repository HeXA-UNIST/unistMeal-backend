package pro.hexa.unist.meal.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pro.hexa.unist.meal.service.ExcelService;
import pro.hexa.unist.meal.service.MainService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService excelService;
    private final MainService mainService;

    @PostMapping("/read")
    public ResponseEntity<Void> readExcel(
        MultipartFile dormitoryFile,
        MultipartFile studentFile,
        MultipartFile professorFile,
        String theKey
    ) throws URISyntaxException, IOException {
        mainService.verifySecretKey(theKey);
        excelService.readExcel(dormitoryFile, studentFile, professorFile);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }
}
