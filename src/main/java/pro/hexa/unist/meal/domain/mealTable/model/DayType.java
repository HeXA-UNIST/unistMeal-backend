package pro.hexa.unist.meal.domain.mealTable.model;

import lombok.Getter;

@Getter
public enum DayType {
    MON("월요일", 4),
    TUE("화요일", 6),
    WED("수요일", 8),
    THU("목요일", 10),
    FRI("금요일", 12),
    SAT("토요일", 14),
    SUN("일요일", 16);

    private final String value;
    private final int colNum;

    DayType(String day, int colNum) {
        this.value = day;
        this.colNum = colNum;
    }
}
