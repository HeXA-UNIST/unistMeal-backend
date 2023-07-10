package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealTableService {
    private final MealTableRepository mealTableRepository;
    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;


    public MealTableService(MealTableRepository mealTableRepository, MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        this.mealTableRepository = mealTableRepository;
        this.mealTableAndMenuRelationshipRepository = mealTableAndMenuRelationshipRepository;
    }
}
