package HeXA.MealU_HeXA_Project.domain.mealTable.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MealTableRepositoryCustom {

    List<MealTable> findByMonday(LocalDate date);

}
