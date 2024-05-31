package pro.hexa.unist.meal.service.dto;

import lombok.Getter;
import lombok.Setter;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.domain.RestaurantInfoV2;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestaurantInfoDtoV2 {

    private List<RestaurantInfoV2> breakfast;
    private List<RestaurantInfoV2> lunch;
    private List<RestaurantInfoV2> dinner;

    public RestaurantInfoDtoV2() {
        breakfast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();
    }
}
