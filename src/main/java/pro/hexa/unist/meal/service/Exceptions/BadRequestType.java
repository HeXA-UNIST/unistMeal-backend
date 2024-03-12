package pro.hexa.unist.meal.service.Exceptions;

import lombok.Getter;

@Getter
public enum BadRequestType {
    WRONG_SECRET_KEY("잘못된 secret key로 요청했습니다."),
    CANNOT_READ_FILE("파일을 읽을 수 없습니다."),
    CANNOT_FIND_MEALTABLE("식단을 찾을 수 없습니다."),
    CANNOT_FIND_ANNOUNCEMENT("공지사항을 찾을 수 없습니다."),
    CANNOT_FIND_URLS("식단 사진 url을 찾을 수 없습니다");


    private final String message;

    BadRequestType(String message) {
        this.message = message;
    }

}
