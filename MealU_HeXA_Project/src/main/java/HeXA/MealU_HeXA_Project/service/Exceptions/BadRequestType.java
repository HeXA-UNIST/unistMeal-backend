package HeXA.MealU_HeXA_Project.service.Exceptions;

public enum BadRequestType {
    CANNOT_FIND_MEALTABLE("식단을 찾을 수 없습니다"),
    CANNOT_FIND_ANNOUNCEMENT("공지사항을 찾을 수 없습니다."),
    CANNOT_FIND_URLS("식단 사진 url을 찾을 수 없습니다");


    private final String message;

    BadRequestType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}