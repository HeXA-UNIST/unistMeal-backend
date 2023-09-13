package HeXA.MealU_HeXA_Project.controller;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.service.AnnouncementService;
import HeXA.MealU_HeXA_Project.service.MealTableService;
import HeXA.MealU_HeXA_Project.service.dto.MealTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    private MealTableService mealTableService;



    @Autowired
    public HomeController(MealTableService mealTableService) {
        this.mealTableService = mealTableService;

    }

    @GetMapping("/mainpage/data")
    public ResponseEntity<List<MealTableDto>> menuListResponseEntity(/*@RequestBody MainPageDataRequestDto request*/){

        List<MealTable> mealTables = mealTableService.findAllByMondayDate();
        List<MealTableDto>  mealTableDtos = mealTableService.findByMealTables(mealTables);
//        mealTableService.
        return new ResponseEntity<>(mealTableDtos, HttpStatus.OK);
    }




//
////    @GetMapping("/notice")
////    ResponseEntity<Announcement> announcementResponseEntity(@RequestBody AnnouncementRequestDto request){
////        return new ResponseEntity<>(new Announcement(request.getContent()), HttpStatus.OK);
////    }
////
////    @GetMapping("/image")
////    ResponseEntity<ImageUrls> imageUrlsResponseEntity(@RequestBody ImageUrlsRequestDto request){
////        return new ResponseEntity<>(new ImageUrls(request.getDormitoryUrl(), request.getStudentUrl(), request.getProfessorUrl()), HttpStatus.OK);
////    }
}
