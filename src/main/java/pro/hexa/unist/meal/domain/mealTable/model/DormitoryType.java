package pro.hexa.unist.meal.domain.mealTable.model;

public enum DormitoryType {
    KOREAN("아침밥"),
    HALAL("점심밥");

    private final String value;

    DormitoryType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
