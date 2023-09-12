package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.announcement.domain.Announcement;
import HeXA.MealU_HeXA_Project.domain.announcement.repository.AnnouncementRepository;
import HeXA.MealU_HeXA_Project.service.Exceptions.BadRequestException;
import HeXA.MealU_HeXA_Project.service.Exceptions.BadRequestType;
import HeXA.MealU_HeXA_Project.service.dto.AnnouncementRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement getLastAnnouncement(){


        List<Announcement> announcements= announcementRepository.findAll();
        if (announcements.isEmpty()){
            throw new BadRequestException(BadRequestType.CANNOT_FIND_ANNOUNCEMENT);
        }


        Announcement announcement = announcements.get(0);

        return announcement;
    }
    @Transactional
    public void updateAnnouncement(String content){

        Announcement newAnnouncement= new Announcement(LocalDate.now(), content);
        announcementRepository.deleteAll();
        announcementRepository.save(newAnnouncement);
    }

}
