package pro.hexa.unist.meal.service.dto;

import lombok.Getter;
import lombok.Setter;
import pro.hexa.unist.meal.domain.restaurantInfo.v1.domain.RestaurantInfoV1;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestaurantInfoDtoV1 {

    private List<RestaurantInfoV1> breakfast;
    private List<RestaurantInfoV1> lunch;
    private List<RestaurantInfoV1> dinner;

    public RestaurantInfoDtoV1() {
        breakfast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();
    }
}
