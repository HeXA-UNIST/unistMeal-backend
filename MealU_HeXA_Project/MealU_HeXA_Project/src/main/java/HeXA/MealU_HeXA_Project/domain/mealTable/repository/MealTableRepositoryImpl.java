package HeXA.MealU_HeXA_Project.domain.mealTable.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MealTableRepositoryImpl implements MealTableRepositoryCustom{
    private JPAQueryFactory queryFactory;
}
