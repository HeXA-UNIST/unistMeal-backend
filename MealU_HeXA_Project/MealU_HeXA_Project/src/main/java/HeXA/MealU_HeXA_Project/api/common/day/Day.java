package HeXA.MealU_HeXA_Project.api.common.day;

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

    public void parsing(String restaurantType, MealTableRepository mealTableRepository, MenuRepository menuRepository) {
        String regex = "^[0-9]*$Kcal";


        DayType dayType;
        if (rows.get(0) == "MON") {
            dayType = DayType.MON;
        } else if (rows.get(0) == "TUE") {
            dayType = DayType.TUE;
        } else if (rows.get(0) == "WED") {
            dayType = DayType.WED;
        } else if (rows.get(0) == "THU") {
            dayType = DayType.THU;
        } else if (rows.get(0) == "FRI") {
            dayType = DayType.FRI;
        } else if (rows.get(0) == "SAT") {
            dayType = DayType.SAT;
        } else {
            dayType = DayType.SUN;
        }
        String date = rows.get(1);


        boolean breakfast = false;
        boolean lunch = false;
        boolean dinner = false;
        Pattern pattern = Pattern.compile(regex);
        // rows의 index == 2 부터 메뉴가 시작됨.
        // 다음 반복문을 통해서 기숙사 식당이라면 3개의 mealTable을 repository에 넣고, 나머지 식당은 2개의 mealTable을 repository에 넣는다.
        for (int i = 2; i < rows.size(); i++) {
            Matcher matcher = pattern.matcher(rows.get(i));
            if (matcher.matches()) {
                // 형식문으로 Kcal이 나오면 MealTable 만들어서 초기화 해준 후 repository에 넣기.
                MealTable mealTable = new MealTable();
                mealTable.setDayType(dayType);                           // dayType도 마찬가지로 변하지 않으니 계속 써준다.
                mealTable.setDate(date);                                 // date는 한 day 객체에선 변하지 않음.
                mealTable.setCalories(Long.parseLong(matcher.group(1))); // 위에서 구한 표현식에서 칼로리만 따옴
                mealTable.setRestaurantType(restaurantType);

                if (restaurantType == "기숙사 식당" && !breakfast) {

                    breakfast = true;
                    mealTable.setMealType(MealType.BREAKFAST);
                    mealTableRepository.save(mealTable);
                    continue;

                }
                if (!lunch) {
                    lunch = true;
                    mealTable.setMealType(MealType.LUNCH);
                    mealTableRepository.save(mealTable);
                }
                if (!dinner) {
                    dinner = true;
                    mealTable.setMealType(MealType.DINNER);
                    mealTableRepository.save(mealTable);
                }


            }
            Menu menu = new Menu();
            menu.setName(rows.get(i));

            menuRepository.save(menu);

        }
    }

}
