package pro.hexa.unist.meal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.hexa.unist.meal.domain.restaurantInfo.repository.RestaurantInfoRepository;
import pro.hexa.unist.meal.service.dto.RestaurantInfoDto;

@Service
@RequiredArgsConstructor
public class RestaurantInfoService {
    private final RestaurantInfoRepository restaurantInfoRepository;

    public RestaurantInfoDto findAll() {

        RestaurantInfoDto restaurantInfoDto = new RestaurantInfoDto();

        restaurantInfoRepository.findAll()
                .forEach(restaurantInfo -> {
                    if (restaurantInfo.getMealType().equals("breakfast"))
                        restaurantInfoDto.getBreakfast().add(restaurantInfo);
                    else if (restaurantInfo.getMealType().equals("lunch"))
                        restaurantInfoDto.getLunch().add(restaurantInfo);
                    else if (restaurantInfo.getMealType().equals("dinner"))
                        restaurantInfoDto.getDinner().add(restaurantInfo);
                });

        return restaurantInfoDto;
    }
}
