package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.announcement.domain.Announcement;
import HeXA.MealU_HeXA_Project.domain.announcement.repository.AnnouncementRepository;
import HeXA.MealU_HeXA_Project.service.Exceptions.BadRequestException;
import HeXA.MealU_HeXA_Project.service.Exceptions.BadRequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public void startGetAnnouncement() {
        logger.info("start to get announcement...");
    }

    public void finishGetAnnouncement() {
        logger.info("finish to get announcement!");
    }

    public void startChangeAnnouncement() {
        logger.info("start to change announcement...");
    }

    public void finishChangeAnnouncement() {
        logger.info("start to change announcement!");
    }

    public Announcement getLastAnnouncement() {
        startGetAnnouncement();
        List<Announcement> announcements = announcementRepository.findAll();
        if (announcements.isEmpty()) {
            logger.info("WARN - ########## There is no Announcement in database! ##########");
            throw new BadRequestException(BadRequestType.CANNOT_FIND_ANNOUNCEMENT);
        }
        Announcement announcement = announcements.get(0);

        finishGetAnnouncement();
        return announcement;
    }

    @Transactional
    public void updateAnnouncement(String content) {
        startChangeAnnouncement();
        Announcement newAnnouncement = new Announcement(LocalDate.now(), content);
        announcementRepository.deleteAll();
        announcementRepository.save(newAnnouncement);
        finishChangeAnnouncement();
    }

}
