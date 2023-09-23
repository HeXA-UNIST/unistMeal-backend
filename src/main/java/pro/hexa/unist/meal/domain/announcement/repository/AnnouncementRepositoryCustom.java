package pro.hexa.unist.meal.domain.announcement.repository;

import pro.hexa.unist.meal.domain.announcement.domain.Announcement;

import java.time.LocalDate;
import java.util.Optional;

public interface AnnouncementRepositoryCustom {
    Optional<Announcement> findLastAnnouncement(LocalDate recentDay);
}
