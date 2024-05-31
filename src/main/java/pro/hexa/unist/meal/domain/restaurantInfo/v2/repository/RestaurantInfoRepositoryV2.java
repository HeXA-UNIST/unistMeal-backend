package pro.hexa.unist.meal.domain.restaurantInfo.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.hexa.unist.meal.domain.restaurantInfo.v2.domain.RestaurantInfoV2;

@Repository
public interface RestaurantInfoRepositoryV2 extends JpaRepository<RestaurantInfoV2, Long>, RestaurantInfoRepositoryCustomV2 {
}
