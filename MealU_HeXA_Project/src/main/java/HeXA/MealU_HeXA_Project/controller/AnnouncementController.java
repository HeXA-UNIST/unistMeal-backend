package HeXA.MealU_HeXA_Project.controller;

import HeXA.MealU_HeXA_Project.service.AnnouncementService;
import HeXA.MealU_HeXA_Project.service.dto.AnnouncementRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class AnnouncementController {
    private AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @Value("${secretKey}")
    String secretKey;

    @GetMapping("/notice")
    public ResponseEntity<AnnouncementRequestDto> announcementContentResponse(){
        String content = announcementService.getLastAnnouncement().getContent();
        AnnouncementRequestDto announcementRequestDto = new AnnouncementRequestDto(content);
        return new ResponseEntity<>(announcementRequestDto, HttpStatus.OK);
    }

    @PostMapping("/updateAnnouncement")
    public ResponseEntity<Void> updateAnnouncement(String content, String theKey) throws URISyntaxException {
        if (!theKey.equals(secretKey)) {
            return ResponseEntity.badRequest().build();
        }

        announcementService.updateAnnouncement(content);
        return ResponseEntity.created(new URI("/updateSuccess")).build();
    }
}
