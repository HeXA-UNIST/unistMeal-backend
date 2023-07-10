package HeXA.MealU_HeXA_Project.domain.mealTableImage.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepositoryCustom;
import HeXA.MealU_HeXA_Project.domain.mealTableImage.domain.MealTableImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTableImageRepository extends JpaRepository<MealTableImage, Long> , MealTableRepositoryCustom {
}
