package HeXA.MealU_HeXA_Project.domain.mealTable.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTableRepository extends JpaRepository<MealTable, Long>, MealTableRepositoryCustom {
}
