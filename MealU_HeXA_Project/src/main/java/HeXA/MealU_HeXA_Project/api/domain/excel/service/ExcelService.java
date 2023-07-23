package HeXA.MealU_HeXA_Project.api.domain.excel.service;

import HeXA.MealU_HeXA_Project.api.common.parseClass.DBWriter;
import HeXA.MealU_HeXA_Project.api.common.parseClass.ExcelParser;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.mealTableAndMenuRelationship.repository.MealTableAndMenuRelationshipRepository;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class ExcelService {
    private final MealTableRepository mealTableRepository;
    private final MenuRepository menuRepository;

    private final MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository;

    public ExcelService(MealTableRepository mealTableRepository, MenuRepository menuRepository
            , MealTableAndMenuRelationshipRepository mealTableAndMenuRelationshipRepository) {
        this.mealTableRepository = mealTableRepository;
        this.menuRepository = menuRepository;
        this.mealTableAndMenuRelationshipRepository = mealTableAndMenuRelationshipRepository;
    }

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

        ExcelParser excelParser = new ExcelParser(worksheet);

        excelParser.parse(); // excelParser가 자신의 내부 필드인 days에 엑셀의 내용들을 파싱해서 넣음.

        System.out.println("end of parsing without Exception");
        // excel parser를 넘겨서 db에 정보들을 저장한다.
        writeOnRepository(excelParser);
    }


    public void writeOnRepository(ExcelParser excelParser) {
        DBWriter writer = new DBWriter(excelParser);
        writer.save(mealTableRepository, menuRepository, mealTableAndMenuRelationshipRepository);
    }
}
