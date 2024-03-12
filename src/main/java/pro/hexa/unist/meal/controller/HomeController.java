package pro.hexa.unist.meal.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import pro.hexa.unist.meal.service.MealTableService;
import pro.hexa.unist.meal.service.dto.MealTableDto;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {

    private final MealTableService mealTableService;

    @GetMapping("/health")
    public ResponseEntity<Boolean> healthCheck() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/mainpage/data")
    public ResponseEntity<List<MealTableDto>> menuListResponseEntity(){

        List<MealTable> mealTables = mealTableService.findAllByMondayDate();
        List<MealTableDto>  mealTableDtos = mealTableService.findByMealTables(mealTables);

        return new ResponseEntity<>(mealTableDtos, HttpStatus.OK);
    }
}
