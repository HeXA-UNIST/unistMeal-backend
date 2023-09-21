package HeXA.MealU_HeXA_Project.domain.announcement.repository;

import HeXA.MealU_HeXA_Project.domain.announcement.domain.Announcement;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, AnnouncementRepositoryCustom {
}
