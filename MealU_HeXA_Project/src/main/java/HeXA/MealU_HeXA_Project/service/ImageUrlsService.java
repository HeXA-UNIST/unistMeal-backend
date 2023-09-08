package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.domain.ImageUrls.domain.ImageUrls;
import HeXA.MealU_HeXA_Project.domain.ImageUrls.model.RestaurantType;
import HeXA.MealU_HeXA_Project.domain.ImageUrls.repository.ImageUrlsRepository;
import HeXA.MealU_HeXA_Project.service.Exceptions.BadRequestException;
import HeXA.MealU_HeXA_Project.service.Exceptions.BadRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImageUrlsService {
    final private ImageUrlsRepository imageUrlsRepository;
    @Autowired
    public ImageUrlsService(ImageUrlsRepository imageUrlsRepository) {
        this.imageUrlsRepository = imageUrlsRepository;
    }
    @Transactional
    public void importDormitoryUrl(String url) {
        List<ImageUrls> imageUrlsList = imageUrlsRepository.findAll();
        for(ImageUrls imageUrls: imageUrlsList){
            if(imageUrls.getRestaurantType() == RestaurantType.DORMITORY){
                // ?? ?? ??? ??? url? ??
                imageUrlsRepository.delete(imageUrls);
                break;
            }
        }
        // ??? ?? ??? url? ??? ??.
        ImageUrls imageUrls = new ImageUrls(RestaurantType.DORMITORY, url);
        imageUrlsRepository.save(imageUrls);
    }
    @Transactional
    public void importStudentUrl(String url) {
        List<ImageUrls> imageUrlsList = imageUrlsRepository.findAll();
        for(ImageUrls imageUrls: imageUrlsList){
            if(imageUrls.getRestaurantType() == RestaurantType.STUDENT){
                // ?? ?? ?? ??? url? ??
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
        for(ImageUrls imageUrls: imageUrlsList){
            if(imageUrls.getRestaurantType() == RestaurantType.PROFESSOR){
                // ?? ?? ??? ??? url? ??
                imageUrlsRepository.delete(imageUrls);
                break;
            }
        }
        
        ImageUrls imageUrls = new ImageUrls(RestaurantType.PROFESSOR, url);
        imageUrlsRepository.save(imageUrls);
    }


    public String getDormitoryUrl() {
        ImageUrls imageUrls = imageUrlsRepository.findDormitoryUrl().orElseThrow(()-> new BadRequestException(BadRequestType.CANNOT_FIND_URLS));
        return imageUrls.getUrl();
    }

    public String getStudentUrl() {
        ImageUrls imageUrls = imageUrlsRepository.findStudentUrl().orElseThrow(()-> new BadRequestException(BadRequestType.CANNOT_FIND_URLS));
        return imageUrls.getUrl();
    }

    public String getProfessorUrl() {
        ImageUrls imageUrls = imageUrlsRepository.findProfessorUrl().orElseThrow(()-> new BadRequestException(BadRequestType.CANNOT_FIND_URLS));
        return imageUrls.getUrl();
    }
}
