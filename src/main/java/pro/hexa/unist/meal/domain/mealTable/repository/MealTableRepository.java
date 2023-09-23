package pro.hexa.unist.meal.domain.mealTable.repository;

import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTableRepository extends JpaRepository<MealTable, Long>, MealTableRepositoryCustom {
}
