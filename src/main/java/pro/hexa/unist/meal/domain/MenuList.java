package pro.hexa.unist.meal.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pro.hexa.unist.meal.domain.mealTable.model.DayType;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuList extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuList_id")
    private Long Id;

    @Comment(value = "메뉴 타입")
    @Column
    private String menuType;

    @Comment(value = "날짜")
    @Column
    private String date;

    @Comment(value = "요일")
    @Column
    private DayType dayType;

    @Comment(value = "가격")
    @Column
    private Long price;

    @Comment(value = "시간")
    @Column
    private String time;

    @Comment(value = "칼로리")
    @Column
    private Long calorie;

    public static MenuList create(String menuType, String date, DayType dayType, Long price, String time, Long calories) {
        MenuList menuList = new MenuList();
        menuList.menuType = menuType;
        menuList.date = date;
        menuList.dayType = dayType;
        menuList.price = price;
        menuList.time = time;
        menuList.calorie = calories;
        return menuList;
    }
}
