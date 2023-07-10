package HeXA.MealU_HeXA_Project.domain.types;

public enum MealType {
    BREAKFAST("아침밥"),
    LUNCH("점심밥"),
    DINNER("저녁밥");

    private final String value;

    MealType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
