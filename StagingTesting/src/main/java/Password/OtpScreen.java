package Password;

import Global.OpenBrowser;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class OtpScreen {
    static WebDriver driver;
    private HashMap<String, String> urlKeys;

    private OtpScreen() throws Exception {
        //  System.setProperty("webdriver.chrome.driver", OpenBrowser.CHROME_DRIVER_PATH);
        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println(OS);
        if (OS.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", OpenBrowser.CHROME_DRIVER_PATH_LINUX);
        } else if (OS.contains("win")) {
            System.setProperty("webdriver.chrome.driver", OpenBrowser.CHROME_DRIVER_PATH_WINDOW);
        }
        Properties prop = new Properties();
        FileInputStream FileInputStream = new FileInputStream(
                new File("src/main/resources/object.properties"));
        prop.load(FileInputStream);
        urlKeys = new HashMap<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");


        urlKeys.put(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY, prop.getProperty(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_USERNAME_KEY, prop.getProperty(OpenBrowser.ENTER_USERNAME_KEY));
        urlKeys.put(OpenBrowser.SEND_OTP_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_OTP_BUTTON_KEY));
        urlKeys.put(OpenBrowser.RADIO_BUTTON, prop.getProperty(OpenBrowser.RADIO_BUTTON));
        urlKeys.put(OpenBrowser.SEND_VERIFICATION_CODE_KEY, prop.getProperty(OpenBrowser.SEND_VERIFICATION_CODE_KEY));
        urlKeys.put(OpenBrowser.ENTER_OTP_FIELD_KEY, prop.getProperty(OpenBrowser.ENTER_OTP_FIELD_KEY));
        urlKeys.put(OpenBrowser.SUBMIT_OTP_BUTTON_KEY, prop.getProperty(OpenBrowser.SUBMIT_OTP_BUTTON_KEY));
        urlKeys.put(OpenBrowser.CLICK_RESEND_CODE, prop.getProperty(OpenBrowser.CLICK_RESEND_CODE));

       // driver = new ChromeDriver();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    Boolean perFormTest1() throws InterruptedException {
       try {
           driver.get(OpenBrowser.URL1);
           System.out.println("Open Browser");
           driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY))).click();


           driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_USERNAME_KEY))).sendKeys("nitish01");

           Thread.sleep(500);
           driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_OTP_BUTTON_KEY))).click();
           Thread.sleep(1000);
           driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.RADIO_BUTTON))).click();
           Thread.sleep(500);
           driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_VERIFICATION_CODE_KEY))).click();

           driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_OTP_FIELD_KEY))).sendKeys("5435");
           Thread.sleep(1000);
           driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SUBMIT_OTP_BUTTON_KEY))).click();

       } catch (Exception p) {
        return false;
    }
        return true;

    }


    void shutdownDriver1() {

        driver.quit();

        System.out.println("I am closing Browser ");
    }

    @Description("You reached the maximum number of attempts to apply the verification code")
    Boolean perFormTest2() throws InterruptedException {
        try {
            driver.get(OpenBrowser.URL1);
            System.out.println("Open Browser");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY))).click();

            //takeScreenshot("firstname");
            //(for assertion)
            Thread.sleep(1000);

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_USERNAME_KEY))).clear();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_USERNAME_KEY))).sendKeys("aditi001");

            Thread.sleep(1000);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_OTP_BUTTON_KEY))).click();

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.RADIO_BUTTON))).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_VERIFICATION_CODE_KEY))).click();

            Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_OTP_FIELD_KEY))).sendKeys("432555");
            Thread.sleep(2000);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SUBMIT_OTP_BUTTON_KEY))).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SUBMIT_OTP_BUTTON_KEY))).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SUBMIT_OTP_BUTTON_KEY))).click();

            WebElement web1= driver.findElement(By.xpath("//h3[contains(text(),'You reached the maximum number of attempts to apply the verification code')]"));
            Thread.sleep(2000);
            String ActualTitle = web1.getText();
            System.out.println(ActualTitle);
            String ExpectedTitle = "You reached the maximum number of attempts to apply the verification code";
            Assert.assertEquals(ActualTitle, ExpectedTitle);
            System.out.println("Assert passed");

        } catch (Exception p) {
            return false;
        }
        return true;

    }
    void shutdownDriver2() {

        driver.quit();

        System.out.println("I am closing Browser ");
    }

    @Description("Invalid verification code, try again")
    Boolean perFormTest3() throws InterruptedException {
        try {
            driver.get(OpenBrowser.URL1);
            System.out.println("Open Browser");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY))).click();


            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_USERNAME_KEY))).sendKeys("prajjawal01");

            Thread.sleep(500);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_OTP_BUTTON_KEY))).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.RADIO_BUTTON))).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_VERIFICATION_CODE_KEY))).click();

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_OTP_FIELD_KEY))).sendKeys("765456");
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SUBMIT_OTP_BUTTON_KEY))).click();
            Thread.sleep(1000);
            WebElement web2= driver.findElement(By.xpath("//div[contains(text(),'Invalid verification code, try again')]"));
            Thread.sleep(2000);
            String ActualTitle = web2.getText();
            System.out.println(ActualTitle);
            String ExpectedTitle = "Invalid verification code, try again";
            Assert.assertEquals(ActualTitle, ExpectedTitle);
            System.out.println("Assert passed");


        } catch (Exception p) {
            return false;
        }
        return true;

    }


    void shutdownDriver3() {

        driver.quit();

        System.out.println("I am closing Browser ");
    }
    @Description("You reached the maximum number of attempts to resend")
    Boolean perFormTest4() throws InterruptedException {
        try {
            driver.get(OpenBrowser.URL1);
            System.out.println("Open Browser");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY))).click();


            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_USERNAME_KEY))).sendKeys("ajay01");

            Thread.sleep(500);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_OTP_BUTTON_KEY))).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.RADIO_BUTTON))).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_VERIFICATION_CODE_KEY))).click();

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_OTP_FIELD_KEY))).sendKeys("765456");

            WebElement web0= driver.findElement(By.xpath(urlKeys.get(OpenBrowser.CLICK_RESEND_CODE)));
            Thread.sleep(1000);
            web0.click();
            web0.click();
            WebElement webb= driver.findElement(By.xpath("//div[contains(text(),'You reached the maximum number of attempts to resend')]"));
            String ActualTitle = webb.getText();
            System.out.println(ActualTitle);
            String ExpectedTitle = "You reached the maximum number of attempts to resend";
            Assert.assertEquals(ActualTitle, ExpectedTitle);
            System.out.println("Assert passed");


        } catch (Exception p) {
            return false;
        }
        return true;

    }


    void shutdownDriver4() {

        driver.quit();

        System.out.println("I am closing Browser ");
    }


    public static OtpScreen getInstance() throws Exception {
        return new OtpScreen();
    }





}