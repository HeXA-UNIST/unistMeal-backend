package pro.hexa.unist.meal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pro.hexa.unist.meal.domain.allergy.repository.AllergyRepository;
import pro.hexa.unist.meal.domain.menuAndAllergyRelationship.repository.MenuAndAllergyRelationshipRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AllergyService {
    private final AllergyRepository allergyRepository;
    private final MenuAndAllergyRelationshipRepository menuAndAllergyRelationshipRepository;

}
