package HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain;

import HeXA.MealU_HeXA_Project.domain.allergy.domain.Allergy;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
public class MenuAndAllergyRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Menu menu;

    @ManyToOne
    @JoinColumn
    private Allergy allergy;

}
