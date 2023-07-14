package HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuAndAllergyRelationshipRepositoryImpl implements MenuAndAllergyRelationshipRepositoryCustom{
    private JPAQueryFactory queryFactory;
}
