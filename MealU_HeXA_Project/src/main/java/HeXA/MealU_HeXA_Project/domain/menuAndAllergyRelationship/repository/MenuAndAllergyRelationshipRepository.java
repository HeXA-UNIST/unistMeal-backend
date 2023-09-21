package HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.repository;

import HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain.MenuAndAllergyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuAndAllergyRelationshipRepository extends JpaRepository<MenuAndAllergyRelationship, Long>, MenuAndAllergyRelationshipRepositoryCustom {
}
