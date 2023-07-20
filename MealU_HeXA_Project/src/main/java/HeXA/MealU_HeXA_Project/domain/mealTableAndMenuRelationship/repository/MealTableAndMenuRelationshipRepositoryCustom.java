package HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;

import java.util.List;

public interface MealTableAndMenuRelationshipRepositoryCustom {
    List<MealTableAndMenuRelationship> findByMealTable(MealTable mealTable);
}
