package HeXA.MealU_HeXA_Project.service;

import HeXA.MealU_HeXA_Project.Utils.ExcelUtils;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExcelService {
    private final MealTableRepository mealTableRepository;
    private final MenuRepository menuRepository;

    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public ExcelService(MealTableRepository mealTableRepository, MenuRepository menuRepository
            , MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        this.mealTableRepository = mealTableRepository;
        this.menuRepository = menuRepository;
        this.mealTableAndMenuRelationshipRepository = mealTableAndMenuRelationshipRepository;
    }
    public void startReadLog(){
        logger.info("start to read excel...");
    }
    public void finishReadLog(){
        logger.info("success read to excel!");
    }
    @Transactional
    public void importExcel(MultipartFile file) throws IOException, InvalidFormatException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        file.getInputStream().close();
        Sheet worksheet = workbook.getSheetAt(0);


        logger.info("start parsing");
        List<List<String>> parsedDays = ExcelUtils.parse(worksheet);
        String parsedRestaurantType = ExcelUtils.parseRestaurantType(worksheet);
        logger.info("end of parsing without exception");

        writeOnRepository(parsedDays, parsedRestaurantType);
    }

    @Transactional
    public void writeOnRepository(List<List<String>> days, String restaurantType) {
        logger.info("start write on database");
        DBWriteService writer = new DBWriteService(mealTableRepository, menuRepository, mealTableAndMenuRelationshipRepository);
        writer.save(days, restaurantType);
        logger.info("end write on database");
    }
}
