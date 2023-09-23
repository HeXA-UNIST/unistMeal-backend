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

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public void startGetAnnouncement() {
        log.info("start to get announcement...");
    }

    public void finishGetAnnouncement() {
        log.info("finish to get announcement!");
    }

    public void startChangeAnnouncement() {
        log.info("start to change announcement...");
    }

    public void finishChangeAnnouncement() {
        log.info("start to change announcement!");
    }

    public Announcement getLastAnnouncement() {
        startGetAnnouncement();
        List<Announcement> announcements = announcementRepository.findAll();
        if (announcements.isEmpty()) {
            log.info("WARN - ########## There is no Announcement in database! ##########");
            throw new BadRequestException(BadRequestType.CANNOT_FIND_ANNOUNCEMENT);
        }
        Announcement announcement = announcements.get(0);

        finishGetAnnouncement();
        return announcement;
    }

    @Transactional
    public void updateAnnouncement(String content) {
        startChangeAnnouncement();
        Announcement newAnnouncement = Announcement.create(content);
        announcementRepository.deleteAll();
        announcementRepository.save(newAnnouncement);
        finishChangeAnnouncement();
    }

}
