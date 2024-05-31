package pro.hexa.unist.meal.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.domain.RestaurantInfoV2;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.model.DayType;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.repository.RestaurantInfoRepositoryV2;
import pro.hexa.unist.meal.service.MainService;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/restaurantInfo/weekDay")
@RequiredArgsConstructor
@Transactional
public class RestaurantInfoControllerV2WeekDay {

    private static final Logger log = LoggerFactory.getLogger(RestaurantInfoControllerV2WeekDay.class);
    private final RestaurantInfoRepositoryV2 repository;
    private final MainService mainService;

    @PostMapping("/dormitoryKorean")
    public ResponseEntity<Void> saveDormitoryKoreanRestaurantInfo(
            String breakfastStartTime,
            String breakfastEndTime,
            Integer breakfastPrice,
            Boolean breakfastIsOpened,
            String lunchStartTime,
            String lunchEndTime,
            Integer lunchPrice,
            Boolean lunchIsOpened,
            String dinnerStartTime,
            String dinnerEndTime,
            Integer dinnerPrice,
            Boolean dinnerIsOpened,
            String theKey
    ) throws URISyntaxException {

        uploadRestaurantInfo("기숙사 식당(한식)", breakfastStartTime, breakfastEndTime, breakfastPrice, breakfastIsOpened, lunchStartTime, lunchEndTime, lunchPrice, lunchIsOpened, dinnerStartTime, dinnerEndTime, dinnerPrice, dinnerIsOpened, theKey);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    @PostMapping("/dormitoryHalal")
    public ResponseEntity<Void> saveDormitoryHalalRestaurantInfo(
            String breakfastStartTime,
            String breakfastEndTime,
            Integer breakfastPrice,
            Boolean breakfastIsOpened,
            String lunchStartTime,
            String lunchEndTime,
            Integer lunchPrice,
            Boolean lunchIsOpened,
            String dinnerStartTime,
            String dinnerEndTime,
            Integer dinnerPrice,
            Boolean dinnerIsOpened,
            String theKey
    ) throws URISyntaxException {

        uploadRestaurantInfo("기숙사 식당(할랄)", breakfastStartTime, breakfastEndTime, breakfastPrice, breakfastIsOpened, lunchStartTime, lunchEndTime, lunchPrice, lunchIsOpened, dinnerStartTime, dinnerEndTime, dinnerPrice, dinnerIsOpened, theKey);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    @PostMapping("/student")
    public ResponseEntity<Void> saveStudentRestaurantInfo(
            String breakfastStartTime,
            String breakfastEndTime,
            Integer breakfastPrice,
            Boolean breakfastIsOpened,
            String lunchStartTime,
            String lunchEndTime,
            Integer lunchPrice,
            Boolean lunchIsOpened,
            String dinnerStartTime,
            String dinnerEndTime,
            Integer dinnerPrice,
            Boolean dinnerIsOpened,
            String theKey
    ) throws URISyntaxException {

        uploadRestaurantInfo("학생 식당", breakfastStartTime, breakfastEndTime, breakfastPrice, breakfastIsOpened, lunchStartTime, lunchEndTime, lunchPrice, lunchIsOpened, dinnerStartTime, dinnerEndTime, dinnerPrice, dinnerIsOpened, theKey);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    @PostMapping("/professor")
    public ResponseEntity<Void> saveProfessorRestaurantInfo(
            String breakfastStartTime,
            String breakfastEndTime,
            Integer breakfastPrice,
            Boolean breakfastIsOpened,
            String lunchStartTime,
            String lunchEndTime,
            Integer lunchPrice,
            Boolean lunchIsOpened,
            String dinnerStartTime,
            String dinnerEndTime,
            Integer dinnerPrice,
            Boolean dinnerIsOpened,
            String theKey
    ) throws URISyntaxException {

        uploadRestaurantInfo("교직원 식당", breakfastStartTime, breakfastEndTime, breakfastPrice, breakfastIsOpened, lunchStartTime, lunchEndTime, lunchPrice, lunchIsOpened, dinnerStartTime, dinnerEndTime, dinnerPrice, dinnerIsOpened, theKey);
        return ResponseEntity.created(new URI("/importSuccess")).build();
    }

    private void uploadRestaurantInfo(String restaurantName, String breakfastStartTime, String breakfastEndTime, Integer breakfastPrice, Boolean breakfastIsOpened, String lunchStartTime, String lunchEndTime, Integer lunchPrice, Boolean lunchIsOpened, String dinnerStartTime, String dinnerEndTime, Integer dinnerPrice, Boolean dinnerIsOpened, String theKey) {

        mainService.verifySecretKey(theKey);

        RestaurantInfoV2 breakfast;
        RestaurantInfoV2 lunch;
        RestaurantInfoV2 dinner;

        if (breakfastIsOpened != null) {
            String[] parsedBreakfastStartTime = breakfastStartTime.split(":");
            String[] parsedBreakfastEndTime = breakfastEndTime.split(":");
            breakfast = new RestaurantInfoV2(restaurantName, breakfastPrice, true, Integer.parseInt(parsedBreakfastStartTime[0]), Integer.parseInt(parsedBreakfastStartTime[1]), Integer.parseInt(parsedBreakfastEndTime[0]), Integer.parseInt(parsedBreakfastEndTime[1]), "breakfast", DayType.WEEKDAY);
        } else breakfast = new RestaurantInfoV2(restaurantName, false, "breakfast", DayType.WEEKDAY);

        if (lunchIsOpened != null) {
            String[] parsedLunchStartTime = lunchStartTime.split(":");
            String[] parsedLunchEndTime = lunchEndTime.split(":");
            lunch = new RestaurantInfoV2(restaurantName, lunchPrice, lunchIsOpened, Integer.parseInt(parsedLunchStartTime[0]), Integer.parseInt(parsedLunchStartTime[1]), Integer.parseInt(parsedLunchEndTime[0]), Integer.parseInt(parsedLunchEndTime[1]), "lunch", DayType.WEEKDAY);
        } else lunch = new RestaurantInfoV2(restaurantName, false, "lunch", DayType.WEEKDAY);

        if (dinnerIsOpened != null) {
            String[] parsedDinnerStartTime = dinnerStartTime.split(":");
            String[] parsedDinnerEndTime = dinnerEndTime.split(":");
            dinner = new RestaurantInfoV2(restaurantName, dinnerPrice, dinnerIsOpened, Integer.parseInt(parsedDinnerStartTime[0]), Integer.parseInt(parsedDinnerStartTime[1]), Integer.parseInt(parsedDinnerEndTime[0]), Integer.parseInt(parsedDinnerEndTime[1]), "dinner", DayType.WEEKDAY);
        } else dinner = new RestaurantInfoV2(restaurantName, false, "dinner", DayType.WEEKDAY);

        repository.deleteByRestaurantNameAndDayType(restaurantName, DayType.WEEKDAY);
        repository.save(breakfast);
        repository.save(lunch);
        repository.save(dinner);

        log.info("{} restaurant info uploaded", restaurantName);
    }
}
