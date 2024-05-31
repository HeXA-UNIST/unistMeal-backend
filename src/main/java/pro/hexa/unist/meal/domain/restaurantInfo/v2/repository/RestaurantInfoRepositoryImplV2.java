package pro.hexa.unist.meal.domain.restaurantInfo.v2.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.domain.QRestaurantInfoV2;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.domain.RestaurantInfoV2;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.model.DayType;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantInfoRepositoryImplV2 implements RestaurantInfoRepositoryCustomV2 {
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByRestaurantNameAndDayType(String restaurantName, DayType dayType) {
        QRestaurantInfoV2 restaurantInfo = QRestaurantInfoV2.restaurantInfoV2;
        queryFactory.delete(restaurantInfo)
                .where(restaurantInfo.restaurantName.eq(restaurantName))
                .where(restaurantInfo.dayType.eq(dayType))
                .execute();
    }

    @Override
    public List<RestaurantInfoV2> findByDayType(DayType dayType) {
        QRestaurantInfoV2 restaurantInfo = QRestaurantInfoV2.restaurantInfoV2;
        return queryFactory.selectFrom(restaurantInfo)
                .where(restaurantInfo.dayType.eq(dayType))
                .fetch();
    }
}
