package pro.hexa.unist.meal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.model.DayType;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.repository.RestaurantInfoRepositoryV2;
import pro.hexa.unist.meal.service.dto.RestaurantInfoDtoV2;

@Service
@RequiredArgsConstructor
public class RestaurantInfoServiceV2WeekDay {
    private final RestaurantInfoRepositoryV2 restaurantInfoRepositoryV2;

    public RestaurantInfoDtoV2 findAllWeekDay() {

        RestaurantInfoDtoV2 restaurantInfoDtoV2 = new RestaurantInfoDtoV2();

        restaurantInfoRepositoryV2.findByDayType(DayType.WEEKDAY)
                .forEach(restaurantInfo -> {
                    if (restaurantInfo.getMealType().equals("breakfast"))
                        restaurantInfoDtoV2.getBreakfast().add(restaurantInfo);
                    else if (restaurantInfo.getMealType().equals("lunch"))
                        restaurantInfoDtoV2.getLunch().add(restaurantInfo);
                    else if (restaurantInfo.getMealType().equals("dinner"))
                        restaurantInfoDtoV2.getDinner().add(restaurantInfo);
                });

        return restaurantInfoDtoV2;
    }
}
