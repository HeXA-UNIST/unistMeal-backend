package HeXA.MealU_HeXA_Project.Utils;

import HeXA.MealU_HeXA_Project.domain.mealTable.model.DayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
public class ExcelUtils {

    private static final int DormitoryStartRow = 5;
    private static final int StudentAndProfessorStartRow = 4;

    private static final int RestaurantTypeRow = 1;
    private static final int RestaurantTypeCol = 1;


    private static int weekNumber = 5;
    private static final int DormitoryWeekNumber = 7;


    public static List<List<String>> parse(Sheet worksheet) throws UnsupportedEncodingException {
        // 긱식을 순회돌고 다시 학식이나 교식을 순회를 돌면 5가 아니라 7을 넣어버려서 오류가 뜸.
        weekNumber = 5;

        int startRowNum = 0;
        DataFormatter dataFormatter = new DataFormatter();
        /*
         controller
         기숙사 식당이면 4, 6, 8, 10, 12, 14, 16
         나머지는 4, 6, 8, 10, 12 까지
        */
        System.out.println("ExcelParser Log1");


        List<Integer> colList;

        System.out.println("ExcelParser Log2");
        // 엑셀의 첫 번째 Row를 통해 식당 종류를 분류함.

        //        System.out.println(restaurantType);
//        System.out.println("201동 교직원식당 주간식단표");
        if (parseRestaurantType(worksheet).equals("교직원 식당")) {
            startRowNum = StudentAndProfessorStartRow; // 교직원 식당 => day 5개
            colList = new ArrayList<>(Arrays.asList(DayType.MondayColNum, DayType.TuesdayColNum, DayType.WednesdayColNum
                    , DayType.ThursdayColNum, DayType.FridayColNum));
        } else if (parseRestaurantType(worksheet).equals("학생 식당")) {
            startRowNum = StudentAndProfessorStartRow; // 학생 식당 => day 5개
            colList = new ArrayList<>(Arrays.asList(DayType.MondayColNum, DayType.TuesdayColNum, DayType.WednesdayColNum
                    , DayType.ThursdayColNum, DayType.FridayColNum));
        } else {
            // 기숙사 식당
            startRowNum = DormitoryStartRow; // 기숙사 식당 => day 7개
            colList = new ArrayList<>(Arrays.asList(DayType.MondayColNum, DayType.TuesdayColNum, DayType.WednesdayColNum
                    , DayType.ThursdayColNum, DayType.FridayColNum, DayType.SaturdayColNum, DayType.SundayColNum));
            weekNumber = DormitoryWeekNumber;

        }
        System.out.println("ExcelParser Log3");
        // 일주일 == 7 days를 만든다.

        List<List<String>> days = new ArrayList<>();
        for (int i = 0; i < weekNumber; i++) {
            days.add(new ArrayList<String>());
        }

        int numOfRowsInSheet = getRowNum(worksheet); // 여기가 이슈가 좀 있네요
//        int numOfRowsInSheet = worksheet.getLastRowNum();
        System.out.println("ExcelParser Log4");
        System.out.println(numOfRowsInSheet);
        for (int i = startRowNum; i < numOfRowsInSheet; i++) {
            Row row = worksheet.getRow(i);
            System.out.println("ExcelParser Log5");
            if (isMerged(worksheet, i, DayType.MondayColNum)) // Merged Row인지 판별해주는 코드
            {
                continue;
            }
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

                if(row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null){
                    continue;
                }
                if(dataFormatter.formatCellValue(row.getCell(colNum)).equals("")){
                    System.out.println("if statement");
                    continue;
                }
                System.out.println(dataFormatter.formatCellValue(row.getCell(colNum)));
                System.out.println("ExcelParser Log7-1");

                System.out.println("Print Successful");


                System.out.println("ExcelParser Log7-2");
                days.get(changeColNumIntoIndex(colNum))
                        .add(dataFormatter.formatCellValue(row.getCell(colNum))); // 이렇게 하면 한 day(column)에 해당하는 모든 string이 들어가게 될 것.
                System.out.println("ExcelParser Log8");
            }
        }
        System.out.println("ExcelParser Successfully out of For loop");


        return days;
    }

    public static String parseRestaurantType(Sheet worksheet) throws UnsupportedEncodingException {
        Row restaurantTypeRow = worksheet.getRow(RestaurantTypeRow);
        Cell cell = restaurantTypeRow.getCell(RestaurantTypeCol);
        if(cell == null){
            return "기숙사 식당";
        }
        String restaurantType = excelStringDecodingIntoUtf8(cell);

        if (restaurantType.equals("201동 교직원식당 주간식단표")) {
            return "교직원 식당";
        } else  {
            return "학생 식당";
        }


    }

    private static String excelStringDecodingIntoUtf8(Cell cell) throws UnsupportedEncodingException {
        return new String(cell.getStringCellValue().getBytes("UTF-8"), "UTF-8");
    }

    private static int changeColNumIntoIndex(int colNum) {
        return colNum / 2 - 2;
    }

    private static boolean isMerged(Sheet sheet, int rowIdx, int colIdx) {

        for (int i = 0; i < sheet.getNumMergedRegions(); ++i) {
            CellRangeAddress range = sheet.getMergedRegion(i);

            if (rowIdx >= range.getFirstRow() && rowIdx <= range.getLastRow() && colIdx >= range.getFirstColumn() && colIdx <= range.getLastColumn()) {
                return true;
            }
        }
        return false;
    }
    private static int getRowNum(Sheet worksheet) throws UnsupportedEncodingException {
        int count = 5; // 기본적으로 비어있는 row 가 아님.
        // (Row, Column) == (5, B) 일 때부터 아래로 내려가면서 MergedRow 면 count 를 올림.
        while(isMerged(worksheet, count, 1)){
            count++;
        }
        return count;
    }
}

