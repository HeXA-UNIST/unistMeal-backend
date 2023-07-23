package HeXA.MealU_HeXA_Project.controller;

import HeXA.MealU_HeXA_Project.domain.announcement.domain.Announcement;
import HeXA.MealU_HeXA_Project.domain.ImageUrls;
import HeXA.MealU_HeXA_Project.domain.MenuList;
import HeXA.MealU_HeXA_Project.domain.dto.AnnouncementRequestDto;
import HeXA.MealU_HeXA_Project.domain.dto.ImageUrlsRequestDto;
import HeXA.MealU_HeXA_Project.domain.dto.MainPageDataRequestDto;
import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.service.MealTableService;
import HeXA.MealU_HeXA_Project.service.dto.MealTableDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class HomeController {
    private MealTableService mealTableService;

//    @GetMapping("/mainpage/data")
//    ResponseEntity<List<MealTableDto>> menuListResponseEntity(@RequestBody MainPageDataRequestDto request){
//        List<MealTable> mealTables = mealTableService.findAllByDateAndRestaurantAndMealType();
//
//
//    }
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
