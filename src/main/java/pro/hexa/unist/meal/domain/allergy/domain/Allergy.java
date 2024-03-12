package pro.hexa.unist.meal.domain.allergy.domain;

import pro.hexa.unist.meal.domain.AbstractEntity;
import pro.hexa.unist.meal.domain.allergy.model.AllergyType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Allergy extends AbstractEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private AllergyType allergyType;

//    @OneToMany
//    private List<MenuAndAllergyRelationship> relationshipsWithMenu = new ArrayList<>();
}
