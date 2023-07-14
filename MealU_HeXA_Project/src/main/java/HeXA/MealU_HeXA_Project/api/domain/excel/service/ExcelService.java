package HeXA.MealU_HeXA_Project.api.domain.excel.service;

import HeXA.MealU_HeXA_Project.api.common.parseClass.DBWriter;
import HeXA.MealU_HeXA_Project.api.common.parseClass.ExcelParser;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExcelService {
    private final MealTableRepository mealTableRepository;
    private final MenuRepository menuRepository;

    public ExcelService(MealTableRepository mealTableRepository, MenuRepository menuRepository) {
        this.mealTableRepository = mealTableRepository;
        this.menuRepository = menuRepository;
    }

    public void importExcel(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        Workbook workbook = null;


        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        ExcelParser excelParser = new ExcelParser(worksheet);
        excelParser.parse(); // excelParser가 자신의 내부 필드인 days에 엑셀의 내용들을 파싱해서 넣음.

        // excel parser를 넘겨서 db에 정보들을 저장한다.
        writeOnRepository(excelParser);
    }

    @Transactional
    public void writeOnRepository(ExcelParser excelParser) {
        DBWriter writer = new DBWriter(excelParser);
        writer.save(mealTableRepository, menuRepository);
    }
}
