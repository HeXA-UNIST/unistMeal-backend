package pro.hexa.unist.meal.domain.menuAndAllergyRelationship.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuAndAllergyRelationshipRepositoryImpl implements MenuAndAllergyRelationshipRepositoryCustom{
    private final JPAQueryFactory queryFactory;
}
