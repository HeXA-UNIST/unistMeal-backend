package pro.hexa.unist.meal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.hexa.unist.meal.domain.restaurantInfo.v1.repository.RestaurantInfoRepositoryV1;
import pro.hexa.unist.meal.service.dto.RestaurantInfoDtoV1;

@Service
@RequiredArgsConstructor
public class RestaurantInfoServiceV1 {
    private final RestaurantInfoRepositoryV1 restaurantInfoRepositoryV1;

    public RestaurantInfoDtoV1 findAll() {

        RestaurantInfoDtoV1 restaurantInfoDtoV1 = new RestaurantInfoDtoV1();

        restaurantInfoRepositoryV1.findAll()
                .forEach(restaurantInfo -> {
                    if (restaurantInfo.getMealType().equals("breakfast"))
                        restaurantInfoDtoV1.getBreakfast().add(restaurantInfo);
                    else if (restaurantInfo.getMealType().equals("lunch"))
                        restaurantInfoDtoV1.getLunch().add(restaurantInfo);
                    else if (restaurantInfo.getMealType().equals("dinner"))
                        restaurantInfoDtoV1.getDinner().add(restaurantInfo);
                });

        return restaurantInfoDtoV1;
    }
}
