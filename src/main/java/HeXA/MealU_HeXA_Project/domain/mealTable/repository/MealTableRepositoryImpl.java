package HeXA.MealU_HeXA_Project.domain.mealTable.repository;

import HeXA.MealU_HeXA_Project.domain.mealTable.domain.MealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.domain.QMealTable;
import HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

}
