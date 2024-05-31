package pro.hexa.unist.meal.domain.restaurantInfo.v2.model;

import lombok.Getter;

@Getter
public enum DayType {
    WEEKDAY(0),
    WEEKEND(1);

    private final int value;

    DayType(int value) {
        this.value = value;
    }
}
