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
import pro.hexa.unist.meal.service.RestaurantInfoService;
import pro.hexa.unist.meal.service.dto.MealTableDto;
import pro.hexa.unist.meal.service.dto.RestaurantInfoDto;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {

    private final MealTableService mealTableService;
    private final RestaurantInfoService restaurantInfoService;

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
    public ResponseEntity<RestaurantInfoDto> restaurantInfoResponseEntity() {

        RestaurantInfoDto restaurantInfo = restaurantInfoService.findAll();

        return new ResponseEntity<>(restaurantInfo, HttpStatus.OK);
    }
}
