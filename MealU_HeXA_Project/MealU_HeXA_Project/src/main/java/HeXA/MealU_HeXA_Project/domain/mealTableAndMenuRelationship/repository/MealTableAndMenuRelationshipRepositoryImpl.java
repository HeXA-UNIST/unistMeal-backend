package HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

@RequiredArgsConstructor
public class MealTableAndMenuRelationshipRepositoryImpl implements MealTableAndMenuRelationshipRepositoryCustom{
    private JPAQueryFactory queryFactory;
}
