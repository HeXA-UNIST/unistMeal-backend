package HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMealTableAndMenuRelationship is a Querydsl query type for MealTableAndMenuRelationship
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMealTableAndMenuRelationship extends EntityPathBase<MealTableAndMenuRelationship> {

    private static final long serialVersionUID = 846090067L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMealTableAndMenuRelationship mealTableAndMenuRelationship = new QMealTableAndMenuRelationship("mealTableAndMenuRelationship");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final HeXA.MealU_HeXA_Project.domain.mealTable.domain.QMealTable mealTable;

    public final HeXA.MealU_HeXA_Project.domain.menu.domain.QMenu menu;

    public QMealTableAndMenuRelationship(String variable) {
        this(MealTableAndMenuRelationship.class, forVariable(variable), INITS);
    }

    public QMealTableAndMenuRelationship(Path<? extends MealTableAndMenuRelationship> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMealTableAndMenuRelationship(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMealTableAndMenuRelationship(PathMetadata metadata, PathInits inits) {
        this(MealTableAndMenuRelationship.class, metadata, inits);
    }

    public QMealTableAndMenuRelationship(Class<? extends MealTableAndMenuRelationship> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mealTable = inits.isInitialized("mealTable") ? new HeXA.MealU_HeXA_Project.domain.mealTable.domain.QMealTable(forProperty("mealTable")) : null;
        this.menu = inits.isInitialized("menu") ? new HeXA.MealU_HeXA_Project.domain.menu.domain.QMenu(forProperty("menu")) : null;
    }

}

