package pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.repository;

import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;

import java.util.List;

public interface MealTableAndMenuRelationshipRepositoryCustom {
    List<MealTableAndMenuRelationship> findAllByMealTables(List<MealTable> mealTables);
}
