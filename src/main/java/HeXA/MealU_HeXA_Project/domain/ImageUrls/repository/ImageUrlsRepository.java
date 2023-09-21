package HeXA.MealU_HeXA_Project.domain.ImageUrls.repository;

import HeXA.MealU_HeXA_Project.domain.ImageUrls.domain.ImageUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUrlsRepository extends JpaRepository<ImageUrls, Long>, ImageUrlsRepositoryCustom{
}
