package HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuAndAllergyRelationship is a Querydsl query type for MenuAndAllergyRelationship
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuAndAllergyRelationship extends EntityPathBase<MenuAndAllergyRelationship> {

    private static final long serialVersionUID = 1767886605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuAndAllergyRelationship menuAndAllergyRelationship = new QMenuAndAllergyRelationship("menuAndAllergyRelationship");

    public final HeXA.MealU_HeXA_Project.domain.allergy.domain.QAllergy allergy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final HeXA.MealU_HeXA_Project.domain.menu.domain.QMenu menu;

    public QMenuAndAllergyRelationship(String variable) {
        this(MenuAndAllergyRelationship.class, forVariable(variable), INITS);
    }

    public QMenuAndAllergyRelationship(Path<? extends MenuAndAllergyRelationship> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuAndAllergyRelationship(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuAndAllergyRelationship(PathMetadata metadata, PathInits inits) {
        this(MenuAndAllergyRelationship.class, metadata, inits);
    }

    public QMenuAndAllergyRelationship(Class<? extends MenuAndAllergyRelationship> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.allergy = inits.isInitialized("allergy") ? new HeXA.MealU_HeXA_Project.domain.allergy.domain.QAllergy(forProperty("allergy")) : null;
        this.menu = inits.isInitialized("menu") ? new HeXA.MealU_HeXA_Project.domain.menu.domain.QMenu(forProperty("menu")) : null;
    }

}

