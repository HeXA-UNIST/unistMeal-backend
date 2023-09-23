package pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.repository;

import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTableAndMenuRelationshipRepository extends JpaRepository<MealTableAndMenuRelationship, Long>, MealTableAndMenuRelationshipRepositoryCustom{
}
