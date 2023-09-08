package HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.QMealTableAndMenuRelationship;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

@RequiredArgsConstructor
public class MealTableAndMenuRelationshipRepositoryImpl implements MealTableAndMenuRelationshipRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MealTableAndMenuRelationship> findAllByMealTables(List<MealTable> mealTables){
        QMealTableAndMenuRelationship mealTableAndMenuRelationship= QMealTableAndMenuRelationship.mealTableAndMenuRelationship;
        return queryFactory.selectFrom(mealTableAndMenuRelationship)
                .where(mealTableAndMenuRelationship.mealTable.in(mealTables))
                .fetch();
    }
}
