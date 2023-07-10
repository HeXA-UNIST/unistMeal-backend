package HeXA.MealU_HeXA_Project.domain.mealTableImage.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MealTableRepositoryImpl implements MealTableRepositoryCustom {
    private JPAQueryFactory queryFactory;
}
