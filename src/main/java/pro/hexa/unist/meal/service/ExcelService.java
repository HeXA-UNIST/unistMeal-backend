package pro.hexa.unist.meal.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import pro.hexa.unist.meal.Utils.ExcelUtils;
import pro.hexa.unist.meal.domain.mealTable.repository.MealTableRepository;
import pro.hexa.unist.meal.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import pro.hexa.unist.meal.domain.menu.repository.MenuRepository;
import pro.hexa.unist.meal.service.Exceptions.BadRequestException;
import pro.hexa.unist.meal.service.Exceptions.BadRequestType;

import static pro.hexa.unist.meal.service.Exceptions.BadRequestType.CANNOT_READ_FILE;
import static pro.hexa.unist.meal.service.Exceptions.BadRequestType.WRONG_SECRET_KEY;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExcelService {

    private final MealTableRepository mealTableRepository;
    private final MenuRepository menuRepository;
    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;
    private final DBWriteService dbWriteService;

    @Value("${secretKey}")
    String secretKey;

    @Transactional
    public void readExcel(MultipartFile dormitoryFile, MultipartFile studentFile, MultipartFile professorFile, String theKey) throws IOException {
        if (!theKey.equals(secretKey)) {
            throw new BadRequestException(WRONG_SECRET_KEY);
        }

        log.info("start to read excel...");
        importExcel(dormitoryFile);
        importExcel(studentFile);
        importExcel(professorFile);
        log.info("success read to excel!");
    }

    private void importExcel(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        Workbook workbook;
        switch (extension) {
            case "xlsx":
                workbook = new XSSFWorkbook(file.getInputStream());
                break;
            case "xls":
                workbook = new HSSFWorkbook(file.getInputStream());
                break;
            default:
                throw new BadRequestException(CANNOT_READ_FILE);
        }

        file.getInputStream().close();
        Sheet worksheet = workbook.getSheetAt(0);

        log.info("start parsing");
        List<List<String>> parsedDays = ExcelUtils.parse(worksheet);
        String parsedRestaurantType = ExcelUtils.parseRestaurantType(worksheet);
        log.info("end of parsing without exception");

        writeOnRepository(parsedDays, parsedRestaurantType);
    }

    public void writeOnRepository(List<List<String>> days, String restaurantType) {
        log.info("start write on database");
        dbWriteService.save(days, restaurantType);
        log.info("end write on database");
    }
}
