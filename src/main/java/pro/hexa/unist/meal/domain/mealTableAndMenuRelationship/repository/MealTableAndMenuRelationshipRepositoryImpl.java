package pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.domain.QMealTableAndMenuRelationship;

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
