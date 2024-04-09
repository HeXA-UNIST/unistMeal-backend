package pro.hexa.unist.meal.domain.restaurantInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.hexa.unist.meal.domain.restaurantInfo.domain.RestaurantInfo;

@Repository
public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo, Long>, RestaurantInfoRepositoryCustom {
}
