package HeXA.MealU_HeXA_Project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuList is a Querydsl query type for MenuList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuList extends EntityPathBase<MenuList> {

    private static final long serialVersionUID = -2085551698L;

    public static final QMenuList menuList = new QMenuList("menuList");

    public final NumberPath<Long> calorie = createNumber("calorie", Long.class);

    public final StringPath date = createString("date");

    public final EnumPath<HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType> dayType = createEnum("dayType", HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType.class);

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath menuType = createString("menuType");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath time = createString("time");

    public QMenuList(String variable) {
        super(MenuList.class, forVariable(variable));
    }

    public QMenuList(Path<? extends MenuList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuList(PathMetadata metadata) {
        super(MenuList.class, metadata);
    }

}

