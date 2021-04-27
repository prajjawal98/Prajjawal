package Password.ForgotPasswordScreen;


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

public class Content2 {
    public Content2() {
    }



    private static int USERNAME_INDEX = 0;
    private static  int DESCRIPTION_INDEX = 1;
    private static  int EXPECTED_RESULT = 2;
    private static  List<Integer> testCaseIndex = new ArrayList<>();


    public static Map<SheetColumnHeader2, SheetColumnHeader2> map = new ConcurrentHashMap<>();

    public List<SheetColumnHeader2> readFile() throws Exception {
        List<SheetColumnHeader2> sheetColumnHeaders = new ArrayList<>();
       // List<Integer> excludelist = new ArrayList<>();


        try {
            FileInputStream fis = new FileInputStream("src//main//java//Testcase//sheet1.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
          //  XSSFSheet sheet = workbook.getSheet("Smoke_PasswordScreen");
            XSSFSheet sheet = workbook.getSheet("Password_Screen");
            //name
           // XSSFSheet sheet = workbook.getSheetAt(3);


            Iterator<Row> iterator = sheet.rowIterator();
            int cellIndex = 0;


            String description = null;
            while (iterator.hasNext()) {
                Row row = iterator.next();
                SheetColumnHeader2 testcase2 = new SheetColumnHeader2();
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell cell = row.getCell(i);
                    if (cellIndex == 0) {
                      if (cell.getStringCellValue().equalsIgnoreCase("Username")) {
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
                        if (testCaseIndex.get(USERNAME_INDEX) == i ) {
                            //emailList.add(cell.getStringCellValue());
                            testcase2.setUsername1(cell.getStringCellValue());
                        } else if (testCaseIndex.get(DESCRIPTION_INDEX) == i ) {
                            //emailList.add(cell.getStringCellValue());
                            testcase2.setTestcaseDescription2(cell.getStringCellValue());
                        } else if (testCaseIndex.get(EXPECTED_RESULT) == i) {
                            testcase2.setExpectedResult2(cell.getStringCellValue());
                        }
                    }


                }
                if (cellIndex != 0) {
                    sheetColumnHeaders.add(testcase2);
                    map.put(sheetColumnHeaders.get(cellIndex - 1), testcase2);
                }
                cellIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetColumnHeaders;
    }

}
