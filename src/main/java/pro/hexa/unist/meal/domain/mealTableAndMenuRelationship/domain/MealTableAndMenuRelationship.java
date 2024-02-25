package pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.domain;

import javax.persistence.CascadeType;
import pro.hexa.unist.meal.domain.AbstractEntity;
import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import pro.hexa.unist.meal.domain.menu.domain.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor
public class MealTableAndMenuRelationship extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = MealTable.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "mealTable_id")
    private MealTable mealTable;

    @ManyToOne(targetEntity = Menu.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MealTableAndMenuRelationship(MealTable mealTable, Menu menu) {
        this.mealTable = mealTable;
        this.menu = menu;
    }
}
