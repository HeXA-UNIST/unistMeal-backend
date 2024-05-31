package pro.hexa.unist.meal.domain.restaurantInfo.v1.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pro.hexa.unist.meal.domain.restaurantInfo.v1.domain.QRestaurantInfoV1;

@RequiredArgsConstructor
public class RestaurantInfoRepositoryImplV1 implements RestaurantInfoRepositoryCustomV1 {
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByRestaurantName(String restaurantName) {
        QRestaurantInfoV1 restaurantInfo = QRestaurantInfoV1.restaurantInfoV1;
        queryFactory.delete(restaurantInfo)
                .where(restaurantInfo.restaurantName.eq(restaurantName))
                .execute();
    }
}
