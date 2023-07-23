package HeXA.MealU_HeXA_Project.domain.menu.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = -1632393141L;

    public static final QMenu menu = new QMenu("menu");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain.MenuAndAllergyRelationship, HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain.QMenuAndAllergyRelationship> relationshipsWithAllergy = this.<HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain.MenuAndAllergyRelationship, HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain.QMenuAndAllergyRelationship>createList("relationshipsWithAllergy", HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain.MenuAndAllergyRelationship.class, HeXA.MealU_HeXA_Project.domain.menuAndAllergyRelationship.domain.QMenuAndAllergyRelationship.class, PathInits.DIRECT2);

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

