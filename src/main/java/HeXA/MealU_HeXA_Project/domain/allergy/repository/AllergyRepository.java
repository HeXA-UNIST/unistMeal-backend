package HeXA.MealU_HeXA_Project.domain.allergy.repository;

import HeXA.MealU_HeXA_Project.domain.allergy.domain.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long>, AllergyRepositoryCustom{

}
