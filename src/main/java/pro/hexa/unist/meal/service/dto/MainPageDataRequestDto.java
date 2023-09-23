package pro.hexa.unist.meal.service.dto;

import pro.hexa.unist.meal.domain.allergy.domain.Allergy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MainPageDataRequestDto {
    List<Allergy> allergies;
}
