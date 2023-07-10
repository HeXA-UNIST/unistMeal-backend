package HeXA.MealU_HeXA_Project.domain.mealTable.domain;

import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class MealTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mealTable_id")
    private Long id;

    @Comment(value = "날짜")
    private String date;

    @OneToMany(mappedBy = "meal_table")
    private List<MealTableAndMenuRelationship> relationshipsWithMenu = new ArrayList<>();

//    @ManyToOne
//    private DayType dayType;
//    @ManyToOne
//    private MealType mealType;
}
