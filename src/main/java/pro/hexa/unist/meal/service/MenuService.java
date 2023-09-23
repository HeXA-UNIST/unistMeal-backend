package pro.hexa.unist.meal.service;

import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import pro.hexa.unist.meal.domain.menu.repository.MenuRepository;
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
