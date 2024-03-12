package pro.hexa.unist.meal.domain.announcement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import pro.hexa.unist.meal.domain.AbstractEntity;

@Entity(name = "announcement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Announcement extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long id;

    @Comment(value = "공지 내용")
    @Column(length = 500)
    private String content;

    public static Announcement create(String content) {
        Announcement announcement = new Announcement();
        announcement.content = content;
        return announcement;
    }
}
