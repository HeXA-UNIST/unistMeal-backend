package pro.hexa.unist.meal.domain.restaurantInfo.v1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pro.hexa.unist.meal.domain.AbstractEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RestaurantInfoV1 extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restaurantName;
    private Integer price;
    private Boolean isOpened;
    private Integer startTimeHour;
    private Integer startTimeMinute;
    private Integer endTimeHour;
    private Integer endTimeMinute;
    private String mealType;

    public RestaurantInfoV1(String restaurantName, Integer price, Boolean isOpened, Integer startTimeHour, Integer startTimeMinute, Integer endTimeHour, Integer endTimeMinute, String mealType) {
        this.restaurantName = restaurantName;
        this.price = price;
        this.isOpened = isOpened;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.mealType = mealType;
    }

    public RestaurantInfoV1(String restaurantName, Boolean isOpened, String mealType) {
        this.restaurantName = restaurantName;
        this.isOpened = isOpened;
        this.mealType = mealType;
    }
}
