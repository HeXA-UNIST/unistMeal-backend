package pro.hexa.unist.meal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.hexa.unist.meal.service.AnnouncementService;
import pro.hexa.unist.meal.service.MainService;
import pro.hexa.unist.meal.service.dto.AnnouncementRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("notice")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final MainService mainService;

    @GetMapping("")
    public ResponseEntity<AnnouncementRequestDto> announcementContentResponse(){

        AnnouncementRequestDto announcementRequestDto = announcementService.getLastAnnouncement();
        return new ResponseEntity<>(announcementRequestDto, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateAnnouncement(String content, String theKey) throws URISyntaxException {
        mainService.verifySecretKey(theKey);

        // 다 삭제하고 하나만 남기는게 맞는걸까... 애초에 DB에 있는걸 삭제하는 방식 자체가 좋지 않다.
        // 참고해서 다시 생각해보길.
        announcementService.updateAnnouncement(content);
        return ResponseEntity.created(new URI("/updateSuccess")).build();
    }
}
