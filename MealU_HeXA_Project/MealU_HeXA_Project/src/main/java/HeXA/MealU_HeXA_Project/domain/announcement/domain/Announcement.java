package HeXA.MealU_HeXA_Project.domain.announcement.domain;

import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long id;

    @Comment(value = "날짜")
    @Column(length = 30)
    private String date;
    @Comment(value = "공지 내용")
    @Column(length = 500)
    private String content;

    public Announcement(String date, String content) {
        this.date = date;
        this.content = content;
    }
}
