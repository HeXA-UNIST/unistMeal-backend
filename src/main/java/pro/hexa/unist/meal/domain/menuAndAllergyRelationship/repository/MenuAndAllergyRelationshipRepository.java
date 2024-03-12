package pro.hexa.unist.meal.domain.menuAndAllergyRelationship.repository;

import pro.hexa.unist.meal.domain.menuAndAllergyRelationship.domain.MenuAndAllergyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuAndAllergyRelationshipRepository extends JpaRepository<MenuAndAllergyRelationship, Long>, MenuAndAllergyRelationshipRepositoryCustom {
}
