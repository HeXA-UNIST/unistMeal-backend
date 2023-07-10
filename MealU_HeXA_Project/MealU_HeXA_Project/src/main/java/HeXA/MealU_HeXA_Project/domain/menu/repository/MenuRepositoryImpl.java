package HeXA.MealU_HeXA_Project.domain.menu.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuRepositoryImpl implements MealTableRepositoryCustom {
    private JPAQueryFactory queryFactory;
}
