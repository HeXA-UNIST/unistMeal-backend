package pro.hexa.unist.meal.domain.mealTable.repository;

import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;

import java.time.LocalDate;
import java.util.List;

public interface MealTableRepositoryCustom {

    List<MealTable> findByMonday(LocalDate date);

    List<MealTable> findByDateRange(LocalDate startDate, LocalDate endDate);

}
