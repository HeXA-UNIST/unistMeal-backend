package pro.hexa.unist.meal.service.dto;

import lombok.Getter;
import lombok.Setter;
import pro.hexa.unist.meal.domain.restaurantInfo.domain.RestaurantInfo;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestaurantInfoDto {

    private List<RestaurantInfo> breakfast;
    private List<RestaurantInfo> lunch;
    private List<RestaurantInfo> dinner;

    public RestaurantInfoDto() {
        breakfast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();
    }
}
