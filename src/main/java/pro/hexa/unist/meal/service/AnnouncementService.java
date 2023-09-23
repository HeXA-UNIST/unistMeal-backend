package pro.hexa.unist.meal.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.hexa.unist.meal.domain.announcement.domain.Announcement;
import pro.hexa.unist.meal.domain.announcement.repository.AnnouncementRepository;
import pro.hexa.unist.meal.service.Exceptions.BadRequestException;
import pro.hexa.unist.meal.service.Exceptions.BadRequestType;
import pro.hexa.unist.meal.service.dto.AnnouncementRequestDto;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementRequestDto getLastAnnouncement() {
        Announcement announcement = announcementRepository.findLastestByQuery()
            .orElseThrow(() -> new BadRequestException(BadRequestType.CANNOT_FIND_ANNOUNCEMENT));

        return AnnouncementRequestDto.builder()
            .content(announcement.getContent())
            .build();
    }

    @Transactional
    public void updateAnnouncement(String content) {
        Announcement newAnnouncement = Announcement.create(content);
        announcementRepository.save(newAnnouncement);
    }

}
