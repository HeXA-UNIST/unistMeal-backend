package HeXA.MealU_HeXA_Project.domain.announcement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnnouncement is a Querydsl query type for Announcement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnnouncement extends EntityPathBase<Announcement> {

    private static final long serialVersionUID = -217848037L;

    public static final QAnnouncement announcement = new QAnnouncement("announcement");

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAnnouncement(String variable) {
        super(Announcement.class, forVariable(variable));
    }

    public QAnnouncement(Path<? extends Announcement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnnouncement(PathMetadata metadata) {
        super(Announcement.class, metadata);
    }

}

