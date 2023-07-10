package HeXA.MealU_HeXA_Project.controller;

import HeXA.MealU_HeXA_Project.domain.announcement.domain.Announcement;
import HeXA.MealU_HeXA_Project.domain.ImageUrls;
import HeXA.MealU_HeXA_Project.domain.MenuList;
import HeXA.MealU_HeXA_Project.domain.dto.AnnouncementRequestDto;
import HeXA.MealU_HeXA_Project.domain.dto.ImageUrlsRequestDto;
import HeXA.MealU_HeXA_Project.domain.dto.MainPageDataRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {
    @GetMapping("/mainpage/data")
    ResponseEntity<MenuList> menuListResponseEntity(@RequestBody MainPageDataRequestDto request){
        return new ResponseEntity<>(new MenuList(request.getMenuType(), request.getDate(), request.getDayType(), request.getPrice(),
                request.getTime(), request.getMenuList(), request.getCalories()), HttpStatus.OK);
    }

    @GetMapping("/notice")
    ResponseEntity<Announcement> announcementResponseEntity(@RequestBody AnnouncementRequestDto request){
        return new ResponseEntity<>(new Announcement(request.getContent()), HttpStatus.OK);
    }

    @GetMapping("/image")
    ResponseEntity<ImageUrls> imageUrlsResponseEntity(@RequestBody ImageUrlsRequestDto request){
        return new ResponseEntity<>(new ImageUrls(request.getDormitoryUrl(), request.getStudentUrl(), request.getProfessorUrl()), HttpStatus.OK);
    }
}
