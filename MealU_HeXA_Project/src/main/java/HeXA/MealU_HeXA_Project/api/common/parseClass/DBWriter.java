package HeXA.MealU_HeXA_Project.api.common.parseClass;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBWriter {
    private final ExcelParser excelParser;
    private static final String Regex = "^[0-9]*$Kcal";
    private static final int KcalMatcherGroupNum = 1;
    private static final int DayTypeNum = 0;
    private static final int DateNum = 1;
    private static final int MenuStartRowNum = 2;

    private static final int Breakfast = 0;
    private static final int Lunch = 1;

    public DBWriter(ExcelParser excelParser) {
        this.excelParser = excelParser;
    }

    public void save(MealTableRepository mealTableRepository, MenuRepository menuRepository
            , MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        List<List<String>> days = excelParser.getDays();
        for (List<String> day : days) {

            String regex = Regex;
            List<String> rows = day; // 하루(day)는 rows와 같다.

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
            String restaurantType = excelParser.getRestaurantType();

            Pattern pattern = Pattern.compile(regex);

            // 다음 반복문을 통해서 기숙사 식당이라면 3개의 mealTable을 repository에 넣고, 나머지 식당은 2개의 mealTable을 repository에 넣는다.
            int sizeOfRows = rows.size();
            int r = MenuStartRowNum;
            int mealTypeIdx = Breakfast; // 0 이면 아침을 뜻함.
            int mealTypeIdxOnNotDormitory = Lunch;
            if (!restaurantType.equals("기숙사 식당")) {
                mealTypeIdx = mealTypeIdxOnNotDormitory;
            }
            while (r != sizeOfRows) {
                MealType mealType = MealType.values()[mealTypeIdx];
                MealTable mealTable = new MealTable(restaurantType, date, dayType, mealType);

                Matcher matcher = pattern.matcher(rows.get(r));
                while (r != sizeOfRows && matcher.matches()) {
                    Menu menu = new Menu(rows.get(r));
                    menuRepository.save(menu);
                    MealTableAndMenuRelationship mealTableAndMenuRelationship = new MealTableAndMenuRelationship(mealTable, menu);
                    mealTableAndMenuRelationshipRepository.save(mealTableAndMenuRelationship);
                    r++;
                    matcher = pattern.matcher(rows.get(r));
                }
                mealTable.setCalories(Long.parseLong(matcher.group(KcalMatcherGroupNum)));
                mealTypeIdx++;
                mealTableRepository.save(mealTable);

            }

            /*
            for (int i = MenuStartRowNum; i < sizeOfRows; i++) {
                Matcher matcher = pattern.matcher(rows.get(i));
                if (matcher.matches()) {

                    MealTable mealTable = new MealTable(id, restaurantType, date, dayType, Long.parseLong(matcher.group(1)));

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
                Menu menu = new Menu(rows.get(i));


                menuRepository.save(menu);

            }
            */
        }

    }
}
