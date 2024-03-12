package pro.hexa.unist.meal.domain.allergy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AllergyRepositoryImpl implements AllergyRepositoryCustom{

    private final JPAQueryFactory queryFactory;
}
