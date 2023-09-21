package HeXA.MealU_HeXA_Project.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementRequestDto {
    private String content;
    public AnnouncementRequestDto(String content){
        this.content = content;
    }
}
