package pro.hexa.unist.meal.domain.ImageUrls.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import pro.hexa.unist.meal.domain.ImageUrls.domain.ImageUrls;
import pro.hexa.unist.meal.domain.ImageUrls.domain.QImageUrls;
import pro.hexa.unist.meal.domain.ImageUrls.model.RestaurantType;

@RequiredArgsConstructor
public class ImageUrlsRepositoryImpl implements ImageUrlsRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ImageUrls> findDormitoryUrl() {
        QImageUrls imageUrls = QImageUrls.imageUrls;
        return Optional.ofNullable(
                queryFactory.selectFrom(imageUrls)
                        .where(imageUrls.restaurantType.eq(RestaurantType.DORMITORY))
                        .fetchOne()
        );
    }
    @Override
    public Optional<ImageUrls> findStudentUrl() {
        QImageUrls imageUrls = QImageUrls.imageUrls;
        return Optional.ofNullable(
                queryFactory.selectFrom(imageUrls)
                        .where(imageUrls.restaurantType.eq(RestaurantType.STUDENT))
                        .fetchOne()
        );
    }
    @Override
    public Optional<ImageUrls> findProfessorUrl() {
        QImageUrls imageUrls = QImageUrls.imageUrls;
        return Optional.ofNullable(
                queryFactory.selectFrom(imageUrls)
                        .where(imageUrls.restaurantType.eq(RestaurantType.PROFESSOR))
                        .fetchOne()
        );
    }
}
