package HeXA.MealU_HeXA_Project.api.domain.excel.service;

import HeXA.MealU_HeXA_Project.api.common.day.Day;
import HeXA.MealU_HeXA_Project.api.common.excel.AbstractExcel;
import HeXA.MealU_HeXA_Project.domain.mealTable.repository.MealTableRepository;
import HeXA.MealU_HeXA_Project.domain.menu.repository.MenuRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        int startLowNum;
        // controller
        // 기숙사 식당이면 5, 7, 9, 11, 13, 15, 17
        // 나머지는 5, 7, 9, 11, 13 까지
        List<Integer> colList;
        // checking Dormitory or not.

        AbstractExcel excel = null;
        Row firstRow = worksheet.getRow(1);
        if (firstRow.getCell(1).getStringCellValue() == "301동 기숙사식당 식단표") {
            excel.setRestaurantType("기숙사 식당");
            startLowNum = 6; // 기숙사 식당 => day 7개
            colList = new ArrayList<>(Arrays.asList(5, 7, 9, 11, 13, 15, 17));
        } else if (firstRow.getCell(1).getStringCellValue() == "203동 학생식당 주간식단표") {
            excel.setRestaurantType("학생 식당");
            startLowNum = 5; // 학생 식당 => day 5개
            colList = new ArrayList<>(Arrays.asList(5, 7, 9, 11, 13));
        } else {
            // 교직원 식당
            excel.setRestaurantType("교직원 식당");
            startLowNum = 5; // 교직원 식당 => day 5개
            colList = new ArrayList<>(Arrays.asList(5, 7, 9, 11, 13));

        }


        Day monday = new Day();
        Day tuesday = new Day();
        Day wednesday = new Day();
        Day thursday = new Day();
        Day friday = new Day();
        Day saturday = new Day();
        Day sunday = new Day();
        List<Day> days = new ArrayList<>(Arrays.asList(monday, tuesday, wednesday,
                thursday, friday, saturday, sunday));

        for (int i = startLowNum; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            for (Integer colNum : colList) {
                // 5, 7, 9, 11.. 와 같은 정수를 0, 1, 2, 3 에 매핑시켜야한다.
                // 왜냐하면 day들을 담은 list에 값을 할당하기 위해서.
                // 함수는 y = 2x + 5로 생각되고, index는 x니까 x = y/2 - 5/2이다.
                // 여기서 어차피 정수라 반내림될 것이므로 x = y/2 - 2가 되겠다.
                // 이렇게 계산하면 차례대로 Mon, Tues, Wed.. 가 인덱스로 들어가게 됨.
                days.get(colNum / 2 - 2)
                        .getRows()
                        .add(row
                                .getCell(colNum)
                                .getStringCellValue()
                        ); // 이렇게 하면 한 day(column)에 해당하는 모든 string이 들어가게 될 것.
            }
        }
        excel.setDays(days);
        parsing(excel); // parsing(excel) 를 통해서 excel 내용을 day단위로 파싱해서 DB에 저장한다.
    }

    public void parsing(AbstractExcel excel) {
        List<Day> days = excel.getDays();
        for (Day day : days) {
            day.parsing(excel.getRestaurantType(), this.mealTableRepository, this.menuRepository); // 레포지토리를 인자로 넘김으로써 save를 day에 위임함.
        }
    }
}
