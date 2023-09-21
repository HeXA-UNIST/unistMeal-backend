package HeXA.MealU_HeXA_Project.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
