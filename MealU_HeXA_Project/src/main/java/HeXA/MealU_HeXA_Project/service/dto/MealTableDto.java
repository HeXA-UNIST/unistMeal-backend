package HeXA.MealU_HeXA_Project.service.dto;

import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MealTableDto {
    private String restaurantType;

    private String date;

    private DayType dayType;

    private MealType mealType;

    private Long calorie;

    private List<String> menus;


}
