package Password.PasswordNotification;




import Password.EndToEndFunctionality.SheetColumnHeader1;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Content3 {
    public Content3() {
    }


    private static int EMAIL_ID1_INDEX = 0;
    private static int EMAIL_ID2_INDEX = 1;
    private static int USERNAME_INDEX = 2;
    private static  int DESCRIPTION_INDEX = 3;
    private static  int EXPECTED_RESULT = 4;
    private static  List<Integer> testCaseIndex = new ArrayList<>();


    public static Map<SheetColumn, SheetColumn> map = new ConcurrentHashMap<>();

    public List<SheetColumn> readFile() throws Exception {
        List<SheetColumn> sheetColumns = new ArrayList<>();
        // List<Integer> excludelist = new ArrayList<>();


        try {
            FileInputStream fis = new FileInputStream("src//main//java//Testcase//sheet1.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Password_Notification");
            //name
            // XSSFSheet sheet = workbook.getSheetAt(3);


            Iterator<Row> iterator = sheet.rowIterator();
            int cellIndex = 0;


            String description = null;
            while (iterator.hasNext()) {
                Row row = iterator.next();
                SheetColumn testcase1 = new SheetColumn();
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell cell = row.getCell(i);
                    if (cellIndex == 0) {
                        if (cell.getStringCellValue().equalsIgnoreCase("Email1")) {
                            testCaseIndex.add(EMAIL_ID1_INDEX, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("Email2")) {
                            testCaseIndex.add(EMAIL_ID2_INDEX, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("Username")) {
                            testCaseIndex.add(USERNAME_INDEX, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("Testcase description")){
                            testCaseIndex.add(DESCRIPTION_INDEX, i);
                        }else if (cell.getStringCellValue().equalsIgnoreCase("Expected Result")) {
                            testCaseIndex.add(EXPECTED_RESULT, i);
                        }
                    } else {
                        if (cell==null){
                            continue;
                        }

                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (testCaseIndex.get(EMAIL_ID1_INDEX) == i ) {
                            // emailList.add(cell.getStringCellValue());
                            testcase1.setEmailId1(cell.getStringCellValue());
                        } else if (testCaseIndex.get(EMAIL_ID2_INDEX) == i ) {
                            //emailList.add(cell.getStringCellValue());
                            testcase1.setEmailId2(cell.getStringCellValue());
                        } else if (testCaseIndex.get(USERNAME_INDEX) == i ) {
                            //emailList.add(cell.getStringCellValue());
                            testcase1.setUsername(cell.getStringCellValue());
                        } else if (testCaseIndex.get(DESCRIPTION_INDEX) == i ) {
                            //emailList.add(cell.getStringCellValue());
                            testcase1.setTestcaseDescription(cell.getStringCellValue());
                        } else if (testCaseIndex.get(EXPECTED_RESULT) == i) {
                            testcase1.setExpectedResult(cell.getStringCellValue());
                        }
                    }


                }
                if (cellIndex != 0) {
                    sheetColumns.add(testcase1);
                    map.put(sheetColumns.get(cellIndex - 1), testcase1);
                }
                cellIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetColumns;
    }

}
