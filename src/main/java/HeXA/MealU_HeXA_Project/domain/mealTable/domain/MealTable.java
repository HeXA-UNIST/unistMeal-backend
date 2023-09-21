package HeXA.MealU_HeXA_Project.domain.mealTable.domain;

import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class MealTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mealTable_id")
    private Long id;

    @Comment(value = "식당 종류")
    private String restaurantType;

    @Comment(value = "날짜")
//    private String date;
    private LocalDate date;

    @OneToMany(mappedBy = "mealTable")
    private List<MealTableAndMenuRelationship> relationshipsWithMenu = new ArrayList<>();

    @Comment("요일")
    @Enumerated(EnumType.STRING)
    private DayType dayType;

    @Comment("식사 종류")
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Comment("칼로리")
    private Long calories;


    public MealTable(String restaurantType, LocalDate date, DayType dayType, MealType mealType) {

        this.restaurantType = restaurantType;
        this.date = date;
        this.dayType = dayType;
        this.mealType = mealType;
    }
    public void setCalories(Long calories){
        this.calories = calories;
    }
}
