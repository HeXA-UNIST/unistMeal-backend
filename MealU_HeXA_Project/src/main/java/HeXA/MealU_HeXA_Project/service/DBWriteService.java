package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@Transactional(readOnly = true)
public class DBWriteService {

    private final String KcalRegex = "[0-9]*(,)*[0-9]*(Kcal)";
    private final int KcalMatcherGroupNum = 1;
    private final int DayTypeNum = 0;
    private final int DateNum = 1;
    private final int MenuStartRowNum = 2;

    private final int FirstDayIndex = 0;

    private final Long WeekCountLongNum = 0L;

    private final int Breakfast = 0;
    private final int Lunch = 1;
    private MealTableRepository mealTableRepository;
    private MenuRepository menuRepository;
    private MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;

    public DBWriteService(MealTableRepository mealTableRepository, MenuRepository menuRepository, MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        this.mealTableRepository = mealTableRepository;
        this.menuRepository = menuRepository;
        this.mealTableAndMenuRelationshipRepository = mealTableAndMenuRelationshipRepository;
    }

    public void save(List<List<String>> days, String restaurantType) {
        System.out.println("DBWriter log 1");

        /* 첫 날짜 제외하고 나머지 날짜는 함수로 되어있어 첫날을 기준으로 date + n 을 해줘야 할 듯.
           1. Monday의 Date를 String으로 받는다.
           2. String을 LocalDate type으로 바꿔준다
           3. for문을 순회하면서 count 변수를 +1 하면서 Monday를 기준으로 Date를 더한다.
           4. 더한 Date를 다시 String으로 formatting 시킨다.
           5. 그 Date를 MealTable의 필드로 저장한다.
        */
        System.out.println("DBWriter log 1-1");
        /*
         여기가 문제임. date가 2023-07-17 같은 포맷으로 나오는게 아니라 그냥 45124 이런식으로 정수로 설정되어 있음.
         계산을 해본 결과 1899-12-30 을 기준으로 일수를 세면 45124일이 나오게 된다. (왜 그런지는 모르겠음)
         결론적으로 두 가지 방법이 있다.
         1. 1899-12-30일 기준으로 date에 해당하는 정수를 더해서 그에 해당하는 날을 LocalDate로 가져옴
         2. excel에서 그냥 첫 날짜를 직접 "2023-07-17"과 같이 "일반"형식으로 만들어 준다. (어차피 다른 날짜는 이 날짜 기준으로 더해서 DB에 저장함)
         1번 방식은 좀 더러우나 우리가 할게 딱히 없고
         2번 방식은 우리가 조금 건들여야하지만 코드는 깔끔함.

        */
        String date = days.get(FirstDayIndex).get(DateNum);
//        LocalDate date = days.get(FirstDayIndex).get(DateNum)
        System.out.println("DBWriter log 1-2");


//      LocalDate mondayDate = DateUtils.parseToDate(date);

        LocalDate mondayDate = transformDateFromMealTableDays(transformDateStringToLong(date));


        System.out.println("DBWriter log 1-3");
        Long count = WeekCountLongNum;
        System.out.println("DBWriter log 2");
        System.out.println(days.size());


        List<MealTable> mealTables = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();
        List<MealTableAndMenuRelationship> relationships = new ArrayList<>();

        for (List<String> day : days) {
            System.out.println("update log1");
            List<String> rows = day; // 하루(day)는 rows와 같다.

            DayType dayType;
            switch (rows.get(DayTypeNum)) {
                case "MON":
//                    DayType.valueOf(rows.get(DayTypeNum));
//                    DayType.valueOf(rows.get(DayTypeNum));
                    dayType = DayType.MON;
                    break;
                case "TUE":
                    dayType = DayType.TUE;
                    break;
                case "WED":
                    dayType = DayType.WED;
                    break;
                case "THU":
                    dayType = DayType.THU;
                    break;
                case "FRI":
                    dayType = DayType.FRI;
                    break;
                case "SAT":
                    dayType = DayType.SAT;
                    break;
                default:
                    dayType = DayType.SUN;
                    break;
            }
//            LocalDate localDate = DateUtils.toFormat(mondayDate.plusDays(count), DateUtils.YYYY_MM_DD);
            LocalDate localDate = mondayDate.plusDays(count);
            count++;


            Pattern pattern = Pattern.compile(KcalRegex);

            // 다음 반복문을 통해서 기숙사 식당이라면 3개의 mealTable을 repository에 넣고, 나머지 식당은 2개의 mealTable을 repository에 넣는다.
            int sizeOfRows = rows.size();
            int r = MenuStartRowNum;
            int mealTypeIdx = restaurantType.equals("기숙사 식당") ? Breakfast : Lunch;

            System.out.println("update log2");
            while (r != sizeOfRows) {
                if (mealTypeIdx == 3) // 마지막 공지사항을 피하기 위함. Kcal를 총 3번 순회를 다한 경우엔 멈춰야 함.
                {
                    break;
                }
                System.out.println("update log3");
                MealType mealType = MealType.values()[mealTypeIdx];
                MealTable mealTable = new MealTable(restaurantType, localDate, dayType, mealType);
                System.out.println("update log3-1");
//                mealTableRepository.save(mealTable);
                System.out.println("update log4");
                Matcher matcher = pattern.matcher(rows.get(r));
                while (!matcher.matches()) {
                    System.out.println("DBWriter log 3");
                    Menu menu = new Menu(rows.get(r));
                    menus.add(menu);
                    MealTableAndMenuRelationship mealTableAndMenuRelationship = new MealTableAndMenuRelationship(mealTable, menu);
                    relationships.add(mealTableAndMenuRelationship);
                    System.out.println("DBWriter log 4");
                    r++;
                    matcher = pattern.matcher(rows.get(r));
                    if(r == sizeOfRows){
                        break;
                    }
                    System.out.println(rows.get(r));
                    System.out.println(rows.get(r).matches(KcalRegex));
                }
                System.out.println("DBWriter log 5");
                mealTable.setCalories(parseCalorie(rows.get(r)));
                mealTables.add(mealTable);
                r++;
                mealTypeIdx++;
            }
        }
        saveAll(mealTables, menus, relationships);
    }

    private Long transformDateStringToLong(String date) {
        return (long) Float.parseFloat(date);
    }

    private LocalDate transformDateFromMealTableDays(Long date) {
        LocalDate fixedDate = LocalDate.of(1899, 12, 30); // 정수 증가되는 표준 날짜임
        System.out.println(fixedDate.plusDays(date).toString());
        return fixedDate.plusDays(date);
    }

    private Long parseCalorie(String calories) {
        // 칼로리가 123Kcal나 1,234Kcal 처럼 표현식에 따라 파싱이 까다롭기 때문에 그냥 저 수에 해당하는 Long으로 반환해주는 함수이다.
        String slicedCalories = calories.substring(0, calories.length() - 4); // 이렇게 하면 일단 Kcal를 떼어낼 수 있음.
        slicedCalories = slicedCalories.replace(",", "");
        return Long.parseLong(slicedCalories);
    }
    @Transactional
    private void saveAll(List<MealTable> mealTables, List<Menu> menus, List<MealTableAndMenuRelationship> relationships) {
        System.out.println("saveAll method starts");
        menuRepository.saveAll(menus);
        mealTableRepository.saveAll(mealTables);
        mealTableAndMenuRelationshipRepository.saveAll(relationships);
//        for(MealTable mealTable: mealTables){
//            mealTableRepository.save(mealTable);
//            List<MealTableAndMenuRelationship> matchedRelationships = relationships.stream()
//                    .filter(r -> r.getMealTable().equals(mealTable))
//                    .collect(Collectors.toList());
//            mealTableAndMenuRelationshipRepository.saveAll(matchedRelationships);
//        }
    }
}