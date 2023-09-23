package pro.hexa.unist.meal.domain.ImageUrls.repository;

import pro.hexa.unist.meal.domain.ImageUrls.domain.ImageUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUrlsRepository extends JpaRepository<ImageUrls, Long>, ImageUrlsRepositoryCustom{
}
