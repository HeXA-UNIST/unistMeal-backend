package HeXA.MealU_HeXA_Project.domain.mealTableImage.domain;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class MealTableImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private MealTable mealTable;
    @Comment(value = "이미지 주소")
    private String imageUrl;
}
