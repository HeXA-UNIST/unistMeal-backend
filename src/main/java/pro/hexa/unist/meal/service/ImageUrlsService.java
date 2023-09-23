package pro.hexa.unist.meal.service;

import pro.hexa.unist.meal.domain.ImageUrls.domain.ImageUrls;
import pro.hexa.unist.meal.domain.ImageUrls.model.RestaurantType;
import pro.hexa.unist.meal.domain.ImageUrls.repository.ImageUrlsRepository;
import pro.hexa.unist.meal.service.Exceptions.BadRequestException;
import pro.hexa.unist.meal.service.Exceptions.BadRequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImageUrlsService {
    final private ImageUrlsRepository imageUrlsRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    public ImageUrlsService(ImageUrlsRepository imageUrlsRepository) {
        this.imageUrlsRepository = imageUrlsRepository;
    }


    public void startGetImgUrls() {
        logger.info("start to get the Image Urls - Dormitory, Student, Professor images...");
    }

    public void finishGetImgUrls() {
        logger.info("finish to get the Image Urls - Dormitory, Student, Professor images!");
    }

    public void startImportUrl(String restaurantType){
        logger.info(String.format("start to import %s url into Database...",restaurantType));
    }
    public void finishImportUrl(String restaurantType){
        logger.info(String.format("finish import %s url into Database!", restaurantType));
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
