package pro.hexa.unist.meal.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.hexa.unist.meal.domain.ImageUrls.domain.ImageUrls;
import pro.hexa.unist.meal.domain.ImageUrls.model.RestaurantType;
import pro.hexa.unist.meal.domain.ImageUrls.repository.ImageUrlsRepository;
import pro.hexa.unist.meal.service.Exceptions.BadRequestException;
import pro.hexa.unist.meal.service.Exceptions.BadRequestType;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageUrlsService {
    final private ImageUrlsRepository imageUrlsRepository;

    public void startGetImgUrls() {
        log.info("start to get the Image Urls - Dormitory, Student, Professor images...");
    }

    public void finishGetImgUrls() {
        log.info("finish to get the Image Urls - Dormitory, Student, Professor images!");
    }

    public void startImportUrl(String restaurantType){
        log.info(String.format("start to import %s url into Database...",restaurantType));
    }
    public void finishImportUrl(String restaurantType){
        log.info(String.format("finish import %s url into Database!", restaurantType));
    }

    @Transactional
    public void importDormitoryUrl(String url) {
        List<ImageUrls> imageUrlsList = imageUrlsRepository.findAll();
        for (ImageUrls imageUrls : imageUrlsList) {
            if (imageUrls.getRestaurantType() == RestaurantType.DORMITORY) {

                imageUrlsRepository.delete(imageUrls);
                break;
            }
        }
        ImageUrls imageUrls = new ImageUrls(RestaurantType.DORMITORY, url);
        imageUrlsRepository.save(imageUrls);
    }

    @Transactional
    public void importStudentUrl(String url) {
        List<ImageUrls> imageUrlsList = imageUrlsRepository.findAll();
        for (ImageUrls imageUrls : imageUrlsList) {
            if (imageUrls.getRestaurantType() == RestaurantType.STUDENT) {

                imageUrlsRepository.delete(imageUrls);
                break;
            }
        }

        ImageUrls imageUrls = new ImageUrls(RestaurantType.STUDENT, url);
        imageUrlsRepository.save(imageUrls);

    }

    @Transactional
    public void importProfessorUrl(String url) {
        List<ImageUrls> imageUrlsList = imageUrlsRepository.findAll();
        for (ImageUrls imageUrls : imageUrlsList) {
            if (imageUrls.getRestaurantType() == RestaurantType.PROFESSOR) {

                imageUrlsRepository.delete(imageUrls);
                break;
            }
        }

        ImageUrls imageUrls = new ImageUrls(RestaurantType.PROFESSOR, url);
        imageUrlsRepository.save(imageUrls);
    }


    public String getDormitoryUrl() {
        ImageUrls imageUrls = imageUrlsRepository.findDormitoryUrl().orElseThrow(() -> new BadRequestException(BadRequestType.CANNOT_FIND_URLS));
        return imageUrls.getUrl();
    }

    public String getStudentUrl() {
        ImageUrls imageUrls = imageUrlsRepository.findStudentUrl().orElseThrow(() -> new BadRequestException(BadRequestType.CANNOT_FIND_URLS));
        return imageUrls.getUrl();
    }

    public String getProfessorUrl() {
        ImageUrls imageUrls = imageUrlsRepository.findProfessorUrl().orElseThrow(() -> new BadRequestException(BadRequestType.CANNOT_FIND_URLS));
        return imageUrls.getUrl();
    }
}
