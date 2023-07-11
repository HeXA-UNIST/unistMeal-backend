package HeXA.MealU_HeXA_Project.domain;

import HeXA.MealU_HeXA_Project.domain.menu.domain.Menu;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import org.hibernate.annotations.Comment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MenuList {
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

    @Comment(value = "메뉴")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Menu> menuList;

    @Comment(value = "칼로리")
    @Column
    private Long calorie;
    public MenuList(String menuType, String date, DayType dayType, Long price, String time, List<Menu> menuList, Long calories) {
        this.menuType = menuType;
        this.date = date;
        this.dayType = dayType;
        this.price = price;
        this.time = time;
        this.menuList = menuList;
        this.calorie = calories;
    }
}
