package pro.hexa.unist.meal.domain.mealTable.repository;

import pro.hexa.unist.meal.domain.mealTable.domain.MealTable;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import pro.hexa.unist.meal.domain.mealTable.domain.QMealTable;

@RequiredArgsConstructor
public class MealTableRepositoryImpl implements MealTableRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MealTable> findByMonday(LocalDate date) {
        // ??? + 6? ?? ??? ~ ???? ???? ?? ??? List? ????.
        final Long numOfDaysToPlus = 6L;
        QMealTable mealTable = QMealTable.mealTable;
        return queryFactory.selectFrom(mealTable)
                .where(mealTable.date.between(date, date.plusDays(numOfDaysToPlus)))
                .fetch();
    }

    @Override
    public List<MealTable> findByDateRange(LocalDate startDate, LocalDate endDate) {
        QMealTable mealTable = QMealTable.mealTable;
        return queryFactory.selectFrom(mealTable)
                .where(mealTable.date.between(startDate, endDate))
                .fetch();
    }

}
