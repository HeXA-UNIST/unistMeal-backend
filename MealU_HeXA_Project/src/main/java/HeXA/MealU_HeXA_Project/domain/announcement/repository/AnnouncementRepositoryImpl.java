package HeXA.MealU_HeXA_Project.domain.announcement.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnnouncementRepositoryImpl implements AnnouncementRepositoryCustom{
    private JPAQueryFactory queryFactory;
}
