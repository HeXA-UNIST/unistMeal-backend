package HeXA.MealU_HeXA_Project.domain.mealTableImage.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MealTableImageRepositoryImpl implements MealTableImageRepositoryCustom {
    private JPAQueryFactory queryFactory;


}
