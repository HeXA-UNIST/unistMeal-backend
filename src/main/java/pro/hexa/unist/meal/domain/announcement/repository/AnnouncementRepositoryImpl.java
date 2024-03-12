package pro.hexa.unist.meal.domain.announcement.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import pro.hexa.unist.meal.domain.announcement.domain.Announcement;
import pro.hexa.unist.meal.domain.announcement.domain.QAnnouncement;

@RequiredArgsConstructor
public class AnnouncementRepositoryImpl implements AnnouncementRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Announcement> findLastAnnouncement(LocalDate recentDay) {
        QAnnouncement announcement = QAnnouncement.announcement;

        return Optional.ofNullable(queryFactory.selectFrom(announcement)
            .where(announcement.createdAt.between(recentDay.atStartOfDay(), recentDay.atStartOfDay().plusDays(1)))
            .fetchOne());
    }

    @Override
    public Optional<Announcement> findLastestByQuery() {
        QAnnouncement announcement = QAnnouncement.announcement;
        return Optional.ofNullable(queryFactory.selectFrom(announcement)
            .orderBy(announcement.createdAt.desc())
            .limit(1)
            .fetchOne());
    }
}
