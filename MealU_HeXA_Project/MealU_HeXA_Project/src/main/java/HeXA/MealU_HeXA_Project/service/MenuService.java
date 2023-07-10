package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;

    public MenuService(MenuRepository menuRepository, MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        this.menuRepository = menuRepository;
        this.mealTableAndMenuRelationshipRepository = mealTableAndMenuRelationshipRepository;
    }
}
