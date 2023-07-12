package HeXA.MealU_HeXA_Project.api.common.excel;

import HeXA.MealU_HeXA_Project.api.common.day.Day;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class AbstractExcel {
    // 엑셀을 파싱하기 위한 객체임.
    // 각 엑셀은 여러개의 day를 가진다. 기숙사 식당이면 7개의 days, 나머지는 5개의 days를 가진다.
    private List<Day> days;
    private String restaurantType;


}
