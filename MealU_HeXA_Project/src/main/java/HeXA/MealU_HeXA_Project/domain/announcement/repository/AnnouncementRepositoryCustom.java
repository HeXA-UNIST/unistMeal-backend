package HeXA.MealU_HeXA_Project.domain.announcement.repository;

import HeXA.MealU_HeXA_Project.domain.announcement.domain.Announcement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface AnnouncementRepositoryCustom {
    Optional<Announcement> findLastAnnouncement(LocalDate recentDay);
}
