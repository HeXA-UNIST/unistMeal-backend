package HeXA.MealU_HeXA_Project.domain.mealTable.domain;

import HeXA.MealU_HeXA_Project.domain.types.DayType;
import HeXA.MealU_HeXA_Project.domain.types.MealType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class MealTable {
    @Id

    private Long id;
    private String date;
//    @ManyToOne
//    private DayType dayType;
//    @ManyToOne
//    private MealType mealType;
}
