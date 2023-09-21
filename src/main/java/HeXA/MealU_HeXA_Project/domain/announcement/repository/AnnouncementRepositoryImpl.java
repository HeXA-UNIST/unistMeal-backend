package HeXA.MealU_HeXA_Project.domain.announcement.repository;

import HeXA.MealU_HeXA_Project.domain.announcement.domain.Announcement;
import HeXA.MealU_HeXA_Project.domain.announcement.domain.QAnnouncement;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class AnnouncementRepositoryImpl implements AnnouncementRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Announcement> findLastAnnouncement(LocalDate recentDay) {
        QAnnouncement announcement = QAnnouncement.announcement;

        return Optional.ofNullable(queryFactory.selectFrom(announcement)
                .where(announcement.date.eq(LocalDate.parse(recentDay.toString())))
                .fetchOne());
    }
}
