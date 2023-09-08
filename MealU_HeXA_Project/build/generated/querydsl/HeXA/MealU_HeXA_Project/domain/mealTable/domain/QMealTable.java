package HeXA.MealU_HeXA_Project.domain.mealTable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMealTable is a Querydsl query type for MealTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMealTable extends EntityPathBase<MealTable> {

    private static final long serialVersionUID = -1347934347L;

    public static final QMealTable mealTable = new QMealTable("mealTable");

    public final NumberPath<Long> calories = createNumber("calories", Long.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final EnumPath<HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType> dayType = createEnum("dayType", HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType> mealType = createEnum("mealType", HeXA.MealU_HeXA_Project.domain.mealTable.model.MealType.class);

    public final ListPath<HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship, HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.QMealTableAndMenuRelationship> relationshipsWithMenu = this.<HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship, HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.QMealTableAndMenuRelationship>createList("relationshipsWithMenu", HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.MealTableAndMenuRelationship.class, HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain.QMealTableAndMenuRelationship.class, PathInits.DIRECT2);

    public final StringPath restaurantType = createString("restaurantType");

    public QMealTable(String variable) {
        super(MealTable.class, forVariable(variable));
    }

    public QMealTable(Path<? extends MealTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMealTable(PathMetadata metadata) {
        super(MealTable.class, metadata);
    }

}

