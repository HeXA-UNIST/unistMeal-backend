package pro.hexa.unist.meal.domain.menu.domain;

import pro.hexa.unist.meal.domain.AbstractEntity;
import pro.hexa.unist.meal.domain.menuAndAllergyRelationship.domain.MenuAndAllergyRelationship;
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
public class Menu extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Comment(value = "메뉴 이름")
    @Column
    private String name;

//    @OneToMany(mappedBy = "menu")
//    private List<MealTableAndMenuRelationship> relationshipsWithMealTable = new ArrayList<>();

    @OneToMany(mappedBy = "menu")
    private List<MenuAndAllergyRelationship> relationshipsWithAllergy = new ArrayList<>();


    public Menu(String name) {
        this.name = name;
    }
}
