package pro.hexa.unist.meal.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import pro.hexa.unist.meal.service.MealTableService;
import pro.hexa.unist.meal.service.RestaurantInfoServiceV1;
import pro.hexa.unist.meal.service.RestaurantInfoServiceV2WeekDay;
import pro.hexa.unist.meal.service.RestaurantInfoServiceV2WeekEnd;
import pro.hexa.unist.meal.service.dto.MealTableDto;
import pro.hexa.unist.meal.service.dto.RestaurantInfoDtoV1;
import pro.hexa.unist.meal.service.dto.RestaurantInfoDtoV2;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {

    private final MealTableService mealTableService;
    private final RestaurantInfoServiceV1 restaurantInfoServiceV1;
    private final RestaurantInfoServiceV2WeekDay restaurantInfoServiceV2WeekDay;
    private final RestaurantInfoServiceV2WeekEnd restaurantInfoServiceV2WeekEnd;

    @GetMapping("/health")
    public ResponseEntity<Boolean> healthCheck() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/mainpage/data")
    public ResponseEntity<List<MealTableDto>> menuListResponseEntity(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<MealTable> mealTables;

        if (startDate != null && endDate != null) {
            mealTables = mealTableService.findAllByDateRange(startDate, endDate);
        } else {
            mealTables = mealTableService.findAllByMondayDate();
        }

        List<MealTableDto> mealTableDtos = mealTableService.findByMealTables(mealTables);

        return new ResponseEntity<>(mealTableDtos, HttpStatus.OK);
    }

    @GetMapping("/mainpage/restaurantInfo")
    public ResponseEntity<RestaurantInfoDtoV1> restaurantInfoV1ResponseEntity() {

        RestaurantInfoDtoV1 restaurantInfo = restaurantInfoServiceV1.findAll();

        return new ResponseEntity<>(restaurantInfo, HttpStatus.OK);
    }

    @GetMapping("/mainpage/restaurantInfo/weekDay")
    public ResponseEntity<RestaurantInfoDtoV2> restaurantInfoV1WeekDayResponseEntity() {

        RestaurantInfoDtoV2 restaurantInfo = restaurantInfoServiceV2WeekDay.findAllWeekDay();

        return new ResponseEntity<>(restaurantInfo, HttpStatus.OK);
    }

    @GetMapping("/mainpage/restaurantInfo/weekEnd")
    public ResponseEntity<RestaurantInfoDtoV2> restaurantInfoV1WeekEndResponseEntity() {

        RestaurantInfoDtoV2 restaurantInfo = restaurantInfoServiceV2WeekEnd.findAllWeekEnd();

        return new ResponseEntity<>(restaurantInfo, HttpStatus.OK);
    }
}
