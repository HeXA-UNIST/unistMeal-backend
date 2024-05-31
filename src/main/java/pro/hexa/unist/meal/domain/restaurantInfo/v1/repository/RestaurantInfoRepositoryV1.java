package pro.hexa.unist.meal.domain.restaurantInfo.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.hexa.unist.meal.domain.restaurantInfo.v1.domain.RestaurantInfoV1;

@Repository
public interface RestaurantInfoRepositoryV1 extends JpaRepository<RestaurantInfoV1, Long>, RestaurantInfoRepositoryCustomV1 {
}
