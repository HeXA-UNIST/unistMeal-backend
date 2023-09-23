package pro.hexa.unist.meal.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import pro.hexa.unist.meal.domain.mealTable.repository.MealTableRepository;
import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import pro.hexa.unist.meal.domain.menu.domain.Menu;
import pro.hexa.unist.meal.service.dto.MealTableDto;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealTableService {
    private final MealTableRepository mealTableRepository;
    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;

    public void startGetMealTableLog() {
        log.info("start to get (meal table)...");
    }

    public void finishGetMealTableLog() {
        log.info("finish to get (meal table)!");
    }

    public List<MealTableDto> findByMealTables(List<MealTable> mealTables) {
        /* 이 메서드를 통해 RelationshipRepository에서 Relationship을 찾고, 실제로 클라이언트에 전달할 MealTableDtos 를 리턴해줄 수 있다.
         모든 MealTable에 대한 relationship 을 가지는 리스트.
         (원래 인자로 mealTable 한개씩 넘기려고 하다가, 쿼리를 줄여야 해서 List로 넘겨서 한번에 받는 것으로 바꿈.)
         그래서 mealTable 한 개씩 순회하면서 이에 대응하는 List<Menu>를 얻어내는 것이 중요함.*/
        List<MealTableAndMenuRelationship> relationships = mealTableAndMenuRelationshipRepository.findAllByMealTables(mealTables);
        log.info("start to make the mealTableDtos... ");
        List<MealTableDto> mealTableDtos = new ArrayList<>();
        for (MealTable mealTable : mealTables) {
            // 식단 한 개에 대응
            List<MealTableAndMenuRelationship> distinctMealTableAndMenuRelationships = relationships.stream().filter(r -> r.getMealTable().equals(mealTable)).collect(Collectors.toList());
            List<Menu> menus = distinctMealTableAndMenuRelationships.stream()
                    .map(MealTableAndMenuRelationship::getMenu)
                    .collect(Collectors.toList());
            // 모은 정보를 바탕으로 전달할 한 개의 정보 만듬. 이걸 통신으로 넘길 것.
            MealTableDto dto = MealTableDto.builder()
                    .mealType(mealTable.getMealType())
                    .dayType(mealTable.getDayType())
                    .date(mealTable.getDate())
                    .calorie(mealTable.getCalories())
                    .restaurantType(mealTable.getRestaurantType())
                    .menus(menus.stream().map(Menu::getName).collect(Collectors.toList()))
                    .build();
            mealTableDtos.add(dto);
        }
        log.info("finish to make the mealTableDtos!");
        return mealTableDtos;
    }

    public List<MealTable> findAllByMondayDate() {
        // 월요일 기준으로 MealTableRepository 에서 월~일에 해당하는 모든 식단표를 가져와 리스트로 반환.

        LocalDate mondayDate = findMondayDateByLocalDate();

        return mealTableRepository.findByMonday(mondayDate);
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
