package HeXA.MealU_HeXA_Project.domain.types;

public enum DayType {
    MON("월요일"),
    TUE("화요일"),
    WED("수요일"),
    THU("목요일"),
    FRI("금요일"),
    SAT("토요일"),
    SUN("일요일");
    private final String value;
    DayType(String day) {
        this.value = day;
    }

    public String getValue(){
        return this.value;
    }
}
