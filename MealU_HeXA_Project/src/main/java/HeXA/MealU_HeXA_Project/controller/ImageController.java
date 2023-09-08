package HeXA.MealU_HeXA_Project.controller;

import HeXA.MealU_HeXA_Project.service.ImageUrlsService;
import HeXA.MealU_HeXA_Project.service.dto.ImageUrlsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ImageController {

    private ImageUrlsService imageUrlsService;

    @Autowired
    public ImageController(ImageUrlsService imageUrlsService) {
        this.imageUrlsService = imageUrlsService;
    }

    @PostMapping("/imageDormitoryUrlImport")
    public ResponseEntity<Void> importDormitoryImageUrl(String dormitoryUrl) throws URISyntaxException {
        imageUrlsService.importDormitoryUrl(dormitoryUrl);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    @PostMapping("/imageStudentUrlImport")
    public ResponseEntity<Void> importStudentImageUrl(String studentUrl) throws URISyntaxException {
        imageUrlsService.importStudentUrl(studentUrl);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    @PostMapping("/imageProfessorUrlImport")
    public ResponseEntity<Void> importProfessorImageUrl(String professorUrl) throws URISyntaxException {
        imageUrlsService.importProfessorUrl(professorUrl);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }


    @GetMapping("/image")
    public ResponseEntity<ImageUrlsDto> imageUrlsResponse() {
        String dormitoryUrl = imageUrlsService.getDormitoryUrl();
        String studentUrl = imageUrlsService.getStudentUrl();
        String professorUrl = imageUrlsService.getProfessorUrl();
        ImageUrlsDto imageUrlsDto = new ImageUrlsDto(dormitoryUrl, studentUrl, professorUrl);
        return new ResponseEntity<>(imageUrlsDto, HttpStatus.OK);
    }

}
