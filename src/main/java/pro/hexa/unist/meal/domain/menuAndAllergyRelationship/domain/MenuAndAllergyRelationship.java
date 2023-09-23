package pro.hexa.unist.meal.domain.menuAndAllergyRelationship.domain;

import pro.hexa.unist.meal.domain.AbstractEntity;
import pro.hexa.unist.meal.domain.allergy.domain.Allergy;
import pro.hexa.unist.meal.domain.menu.domain.Menu;
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
public class MenuAndAllergyRelationship extends AbstractEntity {
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
