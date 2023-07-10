package HeXA.MealU_HeXA_Project.domain.allergy.domain;

import HeXA.MealU_HeXA_Project.domain.allergy.model.AllergyType;
import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
@Entity
@Getter
@NoArgsConstructor
public class Allergy {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private AllergyType allergyType;


}
