package HeXA.MealU_HeXA_Project.domain.allergy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAllergy is a Querydsl query type for Allergy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllergy extends EntityPathBase<Allergy> {

    private static final long serialVersionUID = 848417173L;

    public static final QAllergy allergy = new QAllergy("allergy");

    public final EnumPath<HeXA.MealU_HeXA_Project.domain.allergy.model.AllergyType> allergyType = createEnum("allergyType", HeXA.MealU_HeXA_Project.domain.allergy.model.AllergyType.class);

    public QAllergy(String variable) {
        super(Allergy.class, forVariable(variable));
    }

    public QAllergy(Path<? extends Allergy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAllergy(PathMetadata metadata) {
        super(Allergy.class, metadata);
    }

}

