package HeXA.MealU_HeXA_Project.domain.mealTable.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;

import java.util.Optional;

public interface MealTableRepositoryCustom {

    Optional<MealTable> findByDateAndRestaurantAndMealType(String date, String restaurantType, MealType mealType);

}
