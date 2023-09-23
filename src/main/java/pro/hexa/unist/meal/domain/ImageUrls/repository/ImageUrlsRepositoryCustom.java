package pro.hexa.unist.meal.domain.ImageUrls.repository;

import pro.hexa.unist.meal.domain.ImageUrls.domain.ImageUrls;

import java.util.Optional;

public interface ImageUrlsRepositoryCustom {
    Optional<ImageUrls> findDormitoryUrl();
    Optional<ImageUrls> findStudentUrl();
    Optional<ImageUrls> findProfessorUrl();
}
