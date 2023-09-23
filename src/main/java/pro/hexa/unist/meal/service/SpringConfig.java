package pro.hexa.unist.meal.service;

import pro.hexa.unist.meal.domain.ImageUrls.repository.ImageUrlsRepository;
import pro.hexa.unist.meal.domain.allergy.repository.AllergyRepository;
import pro.hexa.unist.meal.domain.announcement.repository.AnnouncementRepository;
import pro.hexa.unist.meal.domain.mealTable.repository.MealTableRepository;
import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;

import pro.hexa.unist.meal.domain.menu.repository.MenuRepository;
import pro.hexa.unist.meal.domain.menuAndAllergyRelationship.repository.MenuAndAllergyRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final AnnouncementRepository announcementRepository;
    private final MealTableRepository mealTableRepository;
    private final MenuRepository menuRepository;
    private final AllergyRepository allergyRepository;

    private final MenuAndAllergyRelationshipRepository menuAndAllergyRelationshipRepository;
    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;
    private final ImageUrlsRepository imageUrlsRepository;

    @Autowired
    public SpringConfig(AnnouncementRepository announcementRepository, MealTableRepository mealTableRepository, MenuRepository menuRepository, AllergyRepository allergyRepository, MenuAndAllergyRelationshipRepository menuAndAllergyRelationshipRepository, MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository, ImageUrlsRepository imageUrlsRepository) {
        this.imageUrlsRepository = imageUrlsRepository;
        this.announcementRepository = announcementRepository;
        this.mealTableRepository = mealTableRepository;
        this.menuRepository = menuRepository;
        this.allergyRepository = allergyRepository;
        this.menuAndAllergyRelationshipRepository = menuAndAllergyRelationshipRepository;
        this.mealTableAndMenuRelationshipRepository = mealTableAndMenuRelationshipRepository;
    }

//    @Bean
//    public MealTableService mealTableService() {
//        return new MealTableService(mealTableRepository, mealTableAndMenuRelationshipRepository);
//    }
//
//    @Bean
//    public MenuService menuService(){
//        return new MenuService(menuRepository, mealTableAndMenuRelationshipRepository);
//    }
//
//    @Bean
//    public AllergyService allergyService(){return new AllergyService(allergyRepository, menuAndAllergyRelationshipRepository);}
//


}
