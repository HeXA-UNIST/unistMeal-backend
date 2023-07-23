package HeXA.MealU_HeXA_Project.domain.dto;

import HeXA.MealU_HeXA_Project.domain.allergy.domain.Allergy;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MainPageDataRequestDto {
    List<Allergy> allergies;
}
