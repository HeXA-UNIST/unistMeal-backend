package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.api.Utils.DateUtils;
import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.service.dto.MealTableDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MealTableService {
    private final MealTableRepository mealTableRepository;
    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;


    public MealTableService(MealTableRepository mealTableRepository, MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        this.mealTableRepository = mealTableRepository;
        this.mealTableAndMenuRelationshipRepository = mealTableAndMenuRelationshipRepository;
    }

    public MealTableDto findByMealTable(MealTable entity) throws IOException {
        // 이 메서드를 통해 RelationshipRepository에서 Relationship을 찾고, 실제로 클라이언트에 전달할 MealTableDto 를 리턴해줄 수 있다.


        List<MealTableAndMenuRelationship> relationships = mealTableAndMenuRelationshipRepository.findByMealTable(entity);

        List<Menu> menus = relationships.stream()
                .map(MealTableAndMenuRelationship::getMenu)
                .collect(Collectors.toList());

        return MealTableDto.builder()
                .mealType(entity.getMealType())
                .dayType(entity.getDayType())
                .date(entity.getDate())
                .calorie(entity.getCalories())
                .restaurantType(entity.getRestaurantType())
                .menus(menus.stream().map(Menu::getName).collect(Collectors.toList()))
                .build();
    }

    public List<MealTable> findAllByDateAndRestaurantAndMealType() {
        List<MealTable> mealTables = new ArrayList<>();
        final int DormitoryIndex = 3;
        List<String> restaurantTypes = new ArrayList<>(Arrays.asList("학생 식당", "교직원 식당", "기숙사 식당"));
        LocalDate mondayDate = findMondayDateByLocalDate();
        List<LocalDate> dates = new ArrayList<>(Arrays.asList(mondayDate));
        for (int i = 1; i < 7; i++) {
            dates.add(mondayDate.plusDays(i)); // 월 ~ 일의 date를 리스트에 넣는다.
        }

        // 일주일 -> 긱식 -> 아 점 저
        for(LocalDate date: dates){
            for(int j = 0; j < 3; j++){
                MealTable mealTable = mealTableRepository.findByDateAndRestaurantAndMealType(DateUtils.toFormat(date, DateUtils.YYYY_MM_DD)
                        , restaurantTypes.get(DormitoryIndex), MealType.values()[j]).orElseThrow(()->new IOException());
                mealTables.add(mealTable);
            }
        }
        // 평일 -> 교식 & 학식 -> 점 저
        for(int i = 0; i < 5; i++){
            LocalDate date = dates.get(i);
            for(int j = 0; j < 2; j++){
                for(int k = 1; k < 3; k++){
                    MealTable mealTable = mealTableRepository.findByDateAndRestaurantAndMealType(DateUtils.toFormat(date, DateUtils.YYYY_MM_DD)
                            , restaurantTypes.get(j), MealType.values()[k]).orElseThrow(() -> new IOException());
                }
            }
        }
        return mealTables;
    }

    public LocalDate findMondayDateByLocalDate() {
        LocalDate localDate = LocalDate.now();
        Calendar calendar = Calendar.getInstance();
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(zoneId).toInstant());
        calendar.setTime(date);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
        DayOfWeek weekStart = DayOfWeek.MONDAY;
        return localDate.with(TemporalAdjusters.previousOrSame(weekStart));
    }
}
