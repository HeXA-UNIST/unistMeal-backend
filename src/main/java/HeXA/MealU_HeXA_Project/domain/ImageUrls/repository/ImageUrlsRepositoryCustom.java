package HeXA.MealU_HeXA_Project.domain.ImageUrls.repository;

import HeXA.MealU_HeXA_Project.domain.ImageUrls.domain.ImageUrls;

import java.util.Optional;

public interface ImageUrlsRepositoryCustom {
    Optional<ImageUrls> findDormitoryUrl();
    Optional<ImageUrls> findStudentUrl();
    Optional<ImageUrls> findProfessorUrl();
}
