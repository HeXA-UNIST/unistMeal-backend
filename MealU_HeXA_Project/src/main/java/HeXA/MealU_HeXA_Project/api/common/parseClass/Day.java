package HeXA.MealU_HeXA_Project.api.common.parseClass;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class Day {


    // 식사 (아침, 점심, 저녁)을 포함할 수 있다.
    // 만약 excel이 학생 or 교직원 식당이라면 (점심, 저녁)만을 포함한다.
    private List<String> rows;

    public void save(String restaurantType, MealTableRepository mealTableRepository, MenuRepository menuRepository) {

    }

}
