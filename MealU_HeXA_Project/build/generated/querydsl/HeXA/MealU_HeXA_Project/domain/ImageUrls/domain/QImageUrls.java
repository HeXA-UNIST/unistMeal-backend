package HeXA.MealU_HeXA_Project.domain.ImageUrls.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageUrls is a Querydsl query type for ImageUrls
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageUrls extends EntityPathBase<ImageUrls> {

    private static final long serialVersionUID = -1815458283L;

    public static final QImageUrls imageUrls = new QImageUrls("imageUrls");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<HeXA.MealU_HeXA_Project.domain.ImageUrls.model.RestaurantType> restaurantType = createEnum("restaurantType", HeXA.MealU_HeXA_Project.domain.ImageUrls.model.RestaurantType.class);

    public final StringPath url = createString("url");

    public QImageUrls(String variable) {
        super(ImageUrls.class, forVariable(variable));
    }

    public QImageUrls(Path<? extends ImageUrls> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageUrls(PathMetadata metadata) {
        super(ImageUrls.class, metadata);
    }

}

