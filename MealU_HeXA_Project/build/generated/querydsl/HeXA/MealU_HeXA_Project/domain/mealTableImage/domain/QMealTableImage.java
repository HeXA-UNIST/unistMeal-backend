package HeXA.MealU_HeXA_Project.domain.mealTableImage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMealTableImage is a Querydsl query type for MealTableImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMealTableImage extends EntityPathBase<MealTableImage> {

    private static final long serialVersionUID = 698658605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMealTableImage mealTableImage = new QMealTableImage("mealTableImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final HeXA.MealU_HeXA_Project.domain.mealTable.domain.QMealTable mealTable;

    public QMealTableImage(String variable) {
        this(MealTableImage.class, forVariable(variable), INITS);
    }

    public QMealTableImage(Path<? extends MealTableImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMealTableImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMealTableImage(PathMetadata metadata, PathInits inits) {
        this(MealTableImage.class, metadata, inits);
    }

    public QMealTableImage(Class<? extends MealTableImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mealTable = inits.isInitialized("mealTable") ? new HeXA.MealU_HeXA_Project.domain.mealTable.domain.QMealTable(forProperty("mealTable")) : null;
    }

}

