package pro.hexa.unist.meal.domain.restaurantInfo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pro.hexa.unist.meal.domain.restaurantInfo.domain.QRestaurantInfo;

@RequiredArgsConstructor
public class RestaurantInfoRepositoryImpl implements RestaurantInfoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByRestaurantName(String restaurantName) {
        QRestaurantInfo restaurantInfo = QRestaurantInfo.restaurantInfo;
        queryFactory.delete(restaurantInfo)
                .where(restaurantInfo.restaurantName.eq(restaurantName))
                .execute();
    }
}
