package HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor
public class MealTableAndMenuRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mealTable_id")
    private MealTable mealTable;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MealTableAndMenuRelationship(MealTable mealTable, Menu menu) {
        this.mealTable = mealTable;
        this.menu = menu;
    }
}
