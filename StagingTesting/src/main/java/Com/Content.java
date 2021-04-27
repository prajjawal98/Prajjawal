package Com;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import Password.SheetColumnHeader;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;

public class Content {
    public Content() {
    }

    private static int EMAIL_ID_INDEX = 0;
    private static int DESCRIPTION = 1;
    private static int EXPECTED_RESULT = 2;
    private static List<Integer> testCaseIndex = new ArrayList<>();
    public static Map<SheetColumnHeader, SheetColumnHeader> map = new ConcurrentHashMap<SheetColumnHeader, SheetColumnHeader>();

    public List<SheetColumnHeader> readFile() throws Exception {
        List<SheetColumnHeader> sheetColumnHeaders = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("src//main//java//Testcase//sheet1.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
          //  XSSFSheet sheet = workbook.getSheetAt(0);
           // XSSFSheet sheet = workbook.getSheet("Smoke_Username");
            XSSFSheet sheet = workbook.getSheet("Username");

            Iterator<Row> iterator = sheet.rowIterator();
            int cellIndex = 0;


            String description = null;
            while (iterator.hasNext()) {
                Row row = iterator.next();
                SheetColumnHeader testcase = new SheetColumnHeader();
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell cell = row.getCell(i);
                    if (cellIndex == 0) {
                        if (cell.getStringCellValue().equalsIgnoreCase("Email")) {
                            testCaseIndex.add(EMAIL_ID_INDEX, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("test Case description")) {
                            testCaseIndex.add(DESCRIPTION, i);
                        } else if (cell.getStringCellValue().equalsIgnoreCase("Expected result")) {
                            testCaseIndex.add(EXPECTED_RESULT, i);
                        }
                    } else {
                        if (cell==null){
                            continue;
                        }

                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (testCaseIndex.get(EMAIL_ID_INDEX) == i ) {
                            //testcase.add(cell.getStringCellValue());
                            testcase.setEmailId(cell.getStringCellValue());
                        } else if (testCaseIndex.get(DESCRIPTION) == i ) {
                            testcase.setTestCaseDescription(cell.getStringCellValue());


                        } else if (testCaseIndex.get(EXPECTED_RESULT) == i ) {
                            testcase.setExpectedResult(cell.getStringCellValue());
                        }
                    }


                }
                if (cellIndex != 0) {
                    sheetColumnHeaders.add(testcase);
                    map.put(sheetColumnHeaders.get(cellIndex - 1), testcase);
                }
                cellIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetColumnHeaders;
    }

}