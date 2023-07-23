package HeXA.MealU_HeXA_Project.api.common.parseClass;

import com.sun.xml.bind.api.impl.NameConverter;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ExcelParser {
    private final Sheet worksheet;

    private static final int DormitoryStartRow = 5;
    private static final int StudentAndProfessorStartRow = 4;

    private static final int RestaurantTypeRow = 1;
    private static final int RestaurantTypeCell = 1;

    private static final int NullColumnNumber = 6;

    private static final int MondayColNum = 4;
    private static final int TuesdayColNum = 6;
    private static final int WednesdayColNum = 8;
    private static final int ThursdayColNum = 10;
    private static final int FridayColNum = 12;
    private static final int SaturdayColNum = 14;
    private static final int SundayColNum = 16;

    private static int weekNumber = 5;
    private static final int DormitoryWeekNumber = 7;


    private List<List<String>> days;
    private String restaurantType;

    public ExcelParser(Sheet worksheet) {
        this.worksheet = worksheet;
    }

    public void parse() throws UnsupportedEncodingException {
        int startRowNum = 0;
        DataFormatter dataFormatter = new DataFormatter();
        /*
         controller
         기숙사 식당이면 4, 6, 8, 10, 12, 14, 16
         나머지는 4, 6, 8, 10, 12 까지
        */
        System.out.println("ExcelParser Log1");
        worksheet.getNumMergedRegions();

        List<Integer> colList;

        System.out.println("ExcelParser Log2");
        // 엑셀의 첫 번째 Row를 통해 식당 종류를 분류함.

        Row restaurantTypeRow = worksheet.getRow(RestaurantTypeRow);

        Cell cell = restaurantTypeRow.getCell(RestaurantTypeRow);
        String restaurantType = excelStringDecodingIntoUtf8(cell);


//        System.out.println(restaurantType);
//        System.out.println("201동 교직원식당 주간식단표");
        if (restaurantType.equals("201동 교직원식당 주간식단표")) {
            this.restaurantType = "교직원 식당";
            startRowNum = StudentAndProfessorStartRow; // 교직원 식당 => day 5개
            colList = new ArrayList<>(Arrays.asList(MondayColNum, TuesdayColNum, WednesdayColNum
                    , ThursdayColNum, FridayColNum));
        } else if (restaurantType.equals("203동 학생식당 주간식단표")) {
            this.restaurantType = "학생 식당";
            startRowNum = StudentAndProfessorStartRow; // 학생 식당 => day 5개
            colList = new ArrayList<>(Arrays.asList(MondayColNum, TuesdayColNum, WednesdayColNum
                    , ThursdayColNum, FridayColNum));
        } else {
            // 기숙사 식당
            this.restaurantType = "기숙사 식당";
            startRowNum = DormitoryStartRow; // 기숙사 식당 => day 7개
            colList = new ArrayList<>(Arrays.asList(MondayColNum, TuesdayColNum, WednesdayColNum
                    , ThursdayColNum, FridayColNum, SaturdayColNum, SundayColNum));
            weekNumber = DormitoryWeekNumber;

        }
        System.out.println("ExcelParser Log3");
        // 일주일 == 7 days를 만든다.

        List<List<String>> days = new ArrayList<>();


        for (int i = 0; i < weekNumber; i++) {
            days.add(new ArrayList<String>());
        }

        int numOfRowsInSheet = worksheet.getLastRowNum();
        System.out.println("ExcelParser Log4");
        System.out.println(numOfRowsInSheet);
        for (int i = startRowNum; i < numOfRowsInSheet; i++) {
            Row row = worksheet.getRow(i);
            System.out.println("ExcelParser Log5");
            if(isMerged(worksheet, i, MondayColNum)) // Merged Row인지 판별해주는 코드
                continue;
            System.out.println("ExcelParser Log6");
            for (Integer colNum : colList) {
                /*
                 4, 6, 8, 10.. 와 같은 정수를 0, 1, 2, 3라는 인덱스에 매핑시켜야한다.
                 왜냐하면 day들을 담은 list에 값을 할당하기 위해서.
                 함수는 y = 2x + 5로 생각되고, index는 x니까 x = y/2 - 5/2이다.
                 여기서 어차피 정수라 반내림될 것이므로 x = y/2 - 2가 되겠다.
                 이렇게 계산하면 차례대로 Mon, Tues, Wed.. 가 인덱스로 들어가게 됨.
                */
                System.out.println("ExcelParser Log7");

//                System.out.println(row.getCell(colNum)
//                        .getStringCellValue());
                System.out.println(dataFormatter.formatCellValue(row.getCell(colNum)));
                System.out.println("Print Successful");
                days.get(changeColNumIntoIndex(colNum))
                        .add(dataFormatter.formatCellValue(row.getCell(colNum)));
//                days.get(changeColNumIntoIndex(colNum))
//                        .add(row.getCell(colNum)
//                                .getStringCellValue()); // 이렇게 하면 한 day(column)에 해당하는 모든 string이 들어가게 될 것.
                System.out.println("ExcelParser Log8");
            }
        }
        System.out.println("ExcelParser Successfully out of For loop");


        this.days = days; // 마지막으로 은닉된 정보(일주일에 대한 식사 정보들)를 excelParser의 필드로 저장한다.
    }
    private String excelStringDecodingIntoUtf8(Cell cell) throws UnsupportedEncodingException {
        return new String(cell.getStringCellValue().getBytes("UTF-8"), "UTF-8");
    }

    private int changeColNumIntoIndex(int colNum) {
        return colNum / 2 - 2;
    }
    private boolean isMerged(Sheet sheet, int rowIdx, int colIdx) {

        for(int i = 0; i < sheet.getNumMergedRegions(); ++i)
        {
            CellRangeAddress range = sheet.getMergedRegion(i);

            if( rowIdx >= range.getFirstRow() && rowIdx <= range.getLastRow() && colIdx >= range.getFirstColumn() && colIdx <= range.getLastColumn() ) {
                return true;
            }
        }
        return false;
    }
}
