package pro.hexa.unist.meal.domain.restaurantInfo.v2.repository;

import pro.hexa.unist.meal.domain.restaurantInfo.v2.domain.RestaurantInfoV2;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.model.DayType;

import java.util.List;

public interface RestaurantInfoRepositoryCustomV2 {
    void deleteByRestaurantNameAndDayType(String restaurantName, DayType dayType);

    List<RestaurantInfoV2> findByDayType(DayType dayType);
}
