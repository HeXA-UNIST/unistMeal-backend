package HeXA.MealU_HeXA_Project.domain.mealTable.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.domain.QMealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MealTableRepositoryImpl implements MealTableRepositoryCustom {
    private JPAQueryFactory queryFactory;

    @Override
    public Optional<MealTable> findByDateAndRestaurantAndMealType(String date, String restaurantType, MealType mealType) {
        QMealTable mealTable = QMealTable.mealTable;
        return Optional.ofNullable(queryFactory.selectFrom(mealTable)

                .where(mealTable.date.eq(date),
                        mealTable.restaurantType.eq(restaurantType),
                        mealTable.mealType.eq(mealType))
                .fetchOne());
    }
}
