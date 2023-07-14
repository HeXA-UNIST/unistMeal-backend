package HeXA.MealU_HeXA_Project.domain;

import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ImageUrls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageUrls_id")
    private Long id;
    @Comment(value = "기숙사 식당 식단 메뉴판 사진 url")
    @Column(length = 500)
    private String dormitoryUrl;
    @Comment(value = "학생회관 식당 식단 메뉴판 사진 url")
    @Column(length = 500)
    private String studentUrl;
    @Comment(value = "교직원 식당 식단 메뉴판 사진 url")
    @Column(length = 500)
    private String professorUrl;

    public ImageUrls(String dormitoryUrl, String studentUrl, String professorUrl) {
        this.dormitoryUrl = dormitoryUrl;
        this.studentUrl = studentUrl;
        this.professorUrl = professorUrl;
    }
}
