package HeXA.MealU_HeXA_Project.domain.ImageUrls.repository;

import HeXA.MealU_HeXA_Project.domain.ImageUrls.domain.ImageUrls;
import HeXA.MealU_HeXA_Project.domain.ImageUrls.domain.QImageUrls;
import HeXA.MealU_HeXA_Project.domain.ImageUrls.model.RestaurantType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
