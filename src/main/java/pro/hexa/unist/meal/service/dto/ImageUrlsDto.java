package pro.hexa.unist.meal.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUrlsDto {


    private String dormitoryUrl;

    private String studentUrl;

    private String professorUrl;

    public ImageUrlsDto(String dormitoryUrl, String studentUrl, String professorUrl) {
        this.dormitoryUrl = dormitoryUrl;
        this.studentUrl = studentUrl;
        this.professorUrl = professorUrl;
    }
}
