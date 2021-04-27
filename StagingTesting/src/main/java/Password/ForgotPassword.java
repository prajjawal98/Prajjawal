package Password;

import Password.EndToEndFunctionality.Content1;
import Password.EndToEndFunctionality.DriverUtility;
import Password.EndToEndFunctionality.SheetColumnHeader1;
import Password.ForgotPasswordScreen.Content2;
import Password.ForgotPasswordScreen.Drivers;
import Password.ForgotPasswordScreen.SheetColumnHeader2;
import Password.PasswordNotification.Content3;
import Password.PasswordNotification.DriverUtilityNotification;
import Password.PasswordNotification.SheetColumn;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import java.util.List;


public class ForgotPassword {
    void utSetUp() throws Exception {
    }

    public void setUp() throws Exception {

    }

    public void getResult(ITestResult result) throws Exception{

    }


    @DataProvider(name = "data-provider")
    public Object[] dpMethod() throws Exception{
        Content1 fileUtil = new Content1();
        List<SheetColumnHeader1> sheetColumnHeaders = fileUtil.readFile();
        return sheetColumnHeaders.toArray();
    }

    @Test(dataProvider = "data-provider")
    void EndToEnd(SheetColumnHeader1 sheetColumnHeader) throws Exception {
        boolean testPassed = false;


        DriverUtility driverUtility2 = DriverUtility.getInstance();
        testPassed = driverUtility2.performTest(sheetColumnHeader);
        driverUtility2.shutdownDriver();


        if(sheetColumnHeader.getExpectedResult1().equalsIgnoreCase("pass")){
            Assert.assertEquals(true, testPassed);
        }else{
            Assert.assertEquals(false, testPassed);

        }


    }

    @Test
    @Description("Verify the otp screen with less then 6 digits")
    public void otpScreenTest1() throws Exception {
        boolean testPassed = false;
        OtpScreen otpScreen = OtpScreen.getInstance();
        testPassed = otpScreen.perFormTest1();
        otpScreen.shutdownDriver1();
        Assert.assertEquals(true,testPassed);
    }



    @Test
    @Description("You reached the maximum number of attempts to apply the verification code")
    public  void otpScreenTest2() throws Exception {
        boolean testPassed = false;
        OtpScreen otpScreen =OtpScreen.getInstance();
        testPassed =otpScreen.perFormTest2();
        otpScreen.shutdownDriver2();
        Assert.assertEquals(true,testPassed);
    }


    @Test
    @Description("Invalid verification code, try again")
    public  void otpScreenTest3() throws Exception {
        boolean testPassed = false;
        OtpScreen otpScreen =OtpScreen.getInstance();
        testPassed =otpScreen.perFormTest3();
        otpScreen.shutdownDriver3();
        Assert.assertEquals(true,testPassed);
    }

    @Test
    @Description("You reached the maximum number of attempts to resend")
    public  void otpScreenTest4() throws Exception {
        boolean testPassed = false;
        OtpScreen otpScreen =OtpScreen.getInstance();
        testPassed =otpScreen.perFormTest4();
        otpScreen.shutdownDriver4();
        Assert.assertEquals(true,testPassed);
    }










//////////////////////////////////////////////////////////////////////

    @DataProvider(name = "data-provider1")
    public Object[] dpMethod1() throws Exception{
        Content2 fileUtil = new Content2();
        List<SheetColumnHeader2> sheetColumnHeaders = fileUtil.readFile();
        return sheetColumnHeaders.toArray();
    }
    @Test(dataProvider = "data-provider1")
    void Screen(SheetColumnHeader2 sheetColumnHeader) throws Exception {
        boolean testPassed = false;

        Drivers forgotPasswordScreen = Drivers.getInstance();
        testPassed = forgotPasswordScreen.performTest(sheetColumnHeader);
        forgotPasswordScreen.shutdownDriver();

        if (sheetColumnHeader.getExpectedResult2().equalsIgnoreCase("pass")) {
            System.out.println(sheetColumnHeader.getExpectedResult2());
            Assert.assertEquals(true, testPassed);
        } else {
            System.out.println(sheetColumnHeader.getExpectedResult2());
            Assert.assertEquals(false, testPassed);

        }


    }



/*

    ///////////////////////////////////////////////////////////
    @DataProvider(name = "data-provider2")
    public Object[] dpMethod2() throws Exception{
        Content3 fileUtil = new Content3();
        List<SheetColumn> sheetColumns = fileUtil.readFile();
        return sheetColumns.toArray();
    }
    @Test(dataProvider = "data-provider2")
    void Notification(SheetColumn sheetColumn) throws Exception {
        boolean testPassed = false;
        DriverUtilityNotification notification1 = DriverUtilityNotification.getInstance();
        testPassed = notification1.performTest(sheetColumn);
        notification1.shutdownDriver();

        if (sheetColumn.getExpectedResult().equalsIgnoreCase("pass")) {
            Assert.assertEquals(true, testPassed);
        } else {
            Assert.assertEquals(false, testPassed);

        }
    }

 */













    @AfterMethod
    public void tearDown() {

    }

}
