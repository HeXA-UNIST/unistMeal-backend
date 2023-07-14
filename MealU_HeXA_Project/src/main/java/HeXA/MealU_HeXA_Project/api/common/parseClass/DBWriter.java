package HeXA.MealU_HeXA_Project.api.common.parseClass;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBWriter {
    private final ExcelParser excelParser;
    private static final String Regex = "^[0-9]*$Kcal";
    private static final int DayTypeNum = 0;
    private static final int DateNum = 1;
    private static final int MenuStartRowNum = 2;

    public DBWriter(ExcelParser excelParser) {
        this.excelParser = excelParser;
    }

    public void save(MealTableRepository mealTableRepository, MenuRepository menuRepository){
        List<Day> days = excelParser.getDays();
        for(Day day : days){

            String regex = Regex;
            List<String> rows = day.getRows();

            DayType dayType;
            if (rows.get(DayTypeNum) == "MON") {
                dayType = DayType.MON;
            } else if (rows.get(DayTypeNum) == "TUE") {
                dayType = DayType.TUE;
            } else if (rows.get(DayTypeNum) == "WED") {
                dayType = DayType.WED;
            } else if (rows.get(DayTypeNum) == "THU") {
                dayType = DayType.THU;
            } else if (rows.get(DayTypeNum) == "FRI") {
                dayType = DayType.FRI;
            } else if (rows.get(DayTypeNum) == "SAT") {
                dayType = DayType.SAT;
            } else {
                dayType = DayType.SUN;
            }
            String date = rows.get(DateNum);

            boolean breakfast = false;
            boolean lunch = false;
            boolean dinner = false;
            Pattern pattern = Pattern.compile(regex);

            // 다음 반복문을 통해서 기숙사 식당이라면 3개의 mealTable을 repository에 넣고, 나머지 식당은 2개의 mealTable을 repository에 넣는다.
            int sizeOfRows = rows.size();
            for (int i = MenuStartRowNum; i < sizeOfRows; i++) {
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
}
