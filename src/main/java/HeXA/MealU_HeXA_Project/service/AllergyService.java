package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.allergy.repository.AllergyRepository;
import HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.repository.MenuAndAllergyRelationshipRepository;
import org.springframework.stereotype.Service;

@Service
public class AllergyService {
    private final AllergyRepository allergyRepository;
    private final MenuAndAllergyRelationshipRepository menuAndAllergyRelationshipRepository;


    public AllergyService(AllergyRepository allergyRepository, MenuAndAllergyRelationshipRepository menuAndAllergyRelationshipRepository) {
        this.allergyRepository = allergyRepository;
        this.menuAndAllergyRelationshipRepository = menuAndAllergyRelationshipRepository;
    }
}
