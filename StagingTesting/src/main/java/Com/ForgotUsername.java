package Com;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.util.List;


public class ForgotUsername {
    void utSetUp() throws Exception {
    }

    public void setUp() throws Exception {

    }

    public void getResult(ITestResult result) throws Exception{


    }

    @DataProvider(name = "data-provider")
    public Object[] dpMethod() throws Exception {
        // return new Object[][] {{"First-Value"}, {"Second-Value"}};
        Content fileUtil = new Content();
        List<SheetColumnHeader> sheetColumnHeaders = fileUtil.readFile();
        return sheetColumnHeaders.toArray();
    }

    @Test(dataProvider = "data-provider")
    void test(SheetColumnHeader sheetColumnHeader) throws Exception {
        boolean testPassed = false;


        DriverUtility driverUtility = DriverUtility.getInstance();
        testPassed = driverUtility.performTest(sheetColumnHeader.getEmailId());
        driverUtility.shutdownDriver();

        if(sheetColumnHeader.getExpectedResult().equalsIgnoreCase("pass")){
            Assert.assertEquals(true,testPassed);
        }else{
            Assert.assertEquals(false,testPassed);

        }








    }



    @AfterMethod
    public void tearDown() {
       // extent.flush();
    }

}
