package Password.EndToEndFunctionality;


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

public class Content1 {
    public Content1() {
    }


    private static int EMAIL_ID__INDEX = 0;
    private static int USERNAME_INDEX = 1;
    private static  int DESCRIPTION_INDEX = 2;
    private static  int EXPECTED_RESULT = 3;
    private static  List<Integer> testCaseIndex = new ArrayList<>();


    public static Map<SheetColumnHeader1, SheetColumnHeader1> map = new ConcurrentHashMap<>();

    public List<SheetColumnHeader1> readFile() throws Exception {
        List<SheetColumnHeader1> sheetColumnHeaders = new ArrayList<>();
       // List<Integer> excludelist = new ArrayList<>();


        try {
            FileInputStream fis = new FileInputStream("src//main//java//Testcase//sheet1.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //XSSFSheet sheet = workbook.getSheet("Smoke_EndtoEnd");
            XSSFSheet sheet = workbook.getSheet("EndtoEndPassword");
            //name
           // XSSFSheet sheet = workbook.getSheetAt(3);


            Iterator<Row> iterator = sheet.rowIterator();
            int cellIndex = 0;


            String description = null;
            while (iterator.hasNext()) {
                Row row = iterator.next();
                SheetColumnHeader1 testcase1 = new SheetColumnHeader1();
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell cell = row.getCell(i);
                    if (cellIndex == 0) {
                        if (cell.getStringCellValue().equalsIgnoreCase("Email")) {
                            testCaseIndex.add(EMAIL_ID__INDEX, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("Username")) {
                            testCaseIndex.add(USERNAME_INDEX, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("testCase description")){
                            testCaseIndex.add(DESCRIPTION_INDEX, i);
                        }else if (cell.getStringCellValue().equalsIgnoreCase("Expected result")) {
                            testCaseIndex.add(EXPECTED_RESULT, i);
                        }
                    } else {
                        if (cell==null){
                            continue;
                        }

                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (testCaseIndex.get(EMAIL_ID__INDEX) == i ) {
                            // emailList.add(cell.getStringCellValue());
                            testcase1.setEmailId1(cell.getStringCellValue());
                        } else if (testCaseIndex.get(USERNAME_INDEX) == i ) {
                            //emailList.add(cell.getStringCellValue());
                            testcase1.setUsername(cell.getStringCellValue());
                        } else if (testCaseIndex.get(DESCRIPTION_INDEX) == i ) {
                            //emailList.add(cell.getStringCellValue());
                            testcase1.setTestcaseDescription1(cell.getStringCellValue());
                        } else if (testCaseIndex.get(EXPECTED_RESULT) == i) {
                            testcase1.setExpectedResult1(cell.getStringCellValue());
                        }
                    }


                }
                if (cellIndex != 0) {
                    sheetColumnHeaders.add(testcase1);
                    map.put(sheetColumnHeaders.get(cellIndex - 1), testcase1);
                }
                cellIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetColumnHeaders;
    }

}
