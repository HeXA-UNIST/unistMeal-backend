package pro.hexa.unist.meal.service.dto;

import pro.hexa.unist.meal.domain.mealTable.model.DayType;
import pro.hexa.unist.meal.domain.mealTable.model.DormitoryType;
import pro.hexa.unist.meal.domain.mealTable.model.MealType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class MealTableDto {
    private String restaurantType;

    private LocalDate date;

    private DayType dayType;

    private MealType mealType;

    private DormitoryType dormitoryType;

    private Long calorie;

    private List<String> menus;


}
