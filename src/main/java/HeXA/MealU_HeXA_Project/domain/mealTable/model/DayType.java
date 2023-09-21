package HeXA.MealU_HeXA_Project.domain.mealTable.model;

public enum DayType {
    MON("월요일"),
    TUE("화요일"),
    WED("수요일"),
    THU("목요일"),
    FRI("금요일"),
    SAT("토요일"),
    SUN("일요일");
    private final String value;

    public static final int MondayColNum = 4;
    public static final int TuesdayColNum = 6;
    public static final int WednesdayColNum = 8;
    public static final int ThursdayColNum = 10;
    public static final int FridayColNum = 12;
    public static final int SaturdayColNum = 14;
    public static final int SundayColNum = 16;
    DayType(String day) {
        this.value = day;
    }

    public String getValue(){
        return this.value;
    }
}
