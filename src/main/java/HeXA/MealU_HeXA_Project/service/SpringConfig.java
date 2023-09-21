package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.ImageUrls.repository.ImageUrlsRepository;
import HeXA.MealU_HeXA_Project.domain.allergy.repository.AllergyRepository;
import HeXA.MealU_HeXA_Project.domain.announcement.repository.AnnouncementRepository;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;

import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.repository.MenuAndAllergyRelationshipRepository;
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
    private final ImageUrlsRepository  imageUrlsRepository;

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
