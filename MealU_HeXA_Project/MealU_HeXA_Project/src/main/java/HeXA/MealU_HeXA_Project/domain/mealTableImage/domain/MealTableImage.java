package HeXA.MealU_HeXA_Project.domain.mealTableImage.domain;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class MealTableImage {

    @OneToOne
    private MealTable mealTable;

    private String imageUrl;
}
