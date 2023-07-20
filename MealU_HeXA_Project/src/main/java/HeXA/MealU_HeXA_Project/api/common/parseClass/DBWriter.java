package HeXA.MealU_HeXA_Project.api.common.parseClass;

import HeXA.MealU_HeXA_Project.api.Utils.DateUtils;
import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private static final int FirstDayIndex = 0;

    private static final Long WeekCountLongNum = 0L;

    private static final int Breakfast = 0;
    private static final int Lunch = 1;

    public DBWriter(ExcelParser excelParser) {
        this.excelParser = excelParser;
    }

    public void save(MealTableRepository mealTableRepository, MenuRepository menuRepository
            , MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        List<List<String>> days = excelParser.getDays();
        /* 첫 날짜 제외하고 나머지 날짜는 함수로 되어있어 첫날을 기준으로 date + n 을 해줘야 할 듯.
           1. Monday의 Date를 String으로 받는다.
           2. String을 LocalDate type으로 바꿔준다
           3. for문을 순회하면서 count 변수를 +1 하면서 Monday를 기준으로 Date를 더한다.
           4. 더한 Date를 다시 String으로 formatting 시킨다.
           5. 그 Date를 MealTable의 필드로 저장한다.
        */
        String date = days.get(FirstDayIndex).get(DateNum);
        LocalDate mondayDate = DateUtils.parseToDate(date);
        Long count = WeekCountLongNum;
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
            date = DateUtils.toFormat(mondayDate.plusDays(count), DateUtils.YYYY_MM_DD);
            count++;

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
                if (mealTypeIdx == 4) // 마지막 공지사항을 피하기 위함. Kcal를 총 3번 순회를 다한 경우엔 멈춰야 함.
                    break;

                MealType mealType = MealType.values()[mealTypeIdx];
                MealTable mealTable = new MealTable(restaurantType, date, dayType, mealType);

                Matcher matcher = pattern.matcher(rows.get(r));
                while (r != sizeOfRows && !matcher.matches()) {
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
