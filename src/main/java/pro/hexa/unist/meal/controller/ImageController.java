package pro.hexa.unist.meal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.hexa.unist.meal.service.ImageUrlsService;
import pro.hexa.unist.meal.service.MainService;
import pro.hexa.unist.meal.service.dto.ImageUrlsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageUrlsService imageUrlsService;

    private final MainService mainService;

    @PostMapping("/dormitoryUrlImport")
    public ResponseEntity<Void> importDormitoryImageUrl(String dormitoryUrl, String theKey) throws URISyntaxException {
        mainService.verifySecretKey(theKey);
        imageUrlsService.startImportUrl("Dormitory");
        imageUrlsService.importDormitoryUrl(dormitoryUrl);
        imageUrlsService.finishImportUrl("Dormitory");
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    @PostMapping("/studentUrlImport")
    public ResponseEntity<Void> importStudentImageUrl(String studentUrl, String theKey) throws URISyntaxException {
        mainService.verifySecretKey(theKey);
        imageUrlsService.startImportUrl("Student");
        imageUrlsService.importStudentUrl(studentUrl);
        imageUrlsService.finishImportUrl("Student");
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    @PostMapping("/professorUrlImport")
    public ResponseEntity<Void> importProfessorImageUrl(String professorUrl, String theKey) throws URISyntaxException {
        mainService.verifySecretKey(theKey);
        imageUrlsService.startImportUrl("Professor");
        imageUrlsService.importProfessorUrl(professorUrl);
        imageUrlsService.finishImportUrl("Professor");
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }


    @GetMapping("")
    public ResponseEntity<ImageUrlsDto> imageUrlsResponse() {
        imageUrlsService.startGetImgUrls();
        String dormitoryUrl = imageUrlsService.getDormitoryUrl();
        String studentUrl = imageUrlsService.getStudentUrl();
        String professorUrl = imageUrlsService.getProfessorUrl();
        ImageUrlsDto imageUrlsDto = new ImageUrlsDto(dormitoryUrl, studentUrl, professorUrl);
        imageUrlsService.finishGetImgUrls();
        return new ResponseEntity<>(imageUrlsDto, HttpStatus.OK);
    }

}
