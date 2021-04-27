package Password.EndToEndFunctionality;

import Global.OpenBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class DriverUtility {
    static WebDriver driver;
    private HashMap<String, String> urlKeys;


    public DriverUtility() throws Exception {
      //  System.setProperty("webdriver.chrome.driver", OpenBrowser.CHROME_DRIVER_PATH);
        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println(OS);
        if(OS.contains("linux") )
        {
            System.setProperty("webdriver.chrome.driver", OpenBrowser.CHROME_DRIVER_PATH_LINUX);
        }
        else if(OS.contains("win") )
        {
            System.setProperty("webdriver.chrome.driver", OpenBrowser.CHROME_DRIVER_PATH_WINDOW);
        }





        Properties prop = new Properties();
        FileInputStream FileInputStream = new FileInputStream(
                new File("src/main/resources/object.properties"));
        prop.load(FileInputStream);
        urlKeys = new HashMap<>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");


        // urlKeys.put(OpenBrowser.CLICK_BUTTON_KEY, prop.getProperty(OpenBrowser.CLICK_BUTTON_KEY));
        urlKeys.put(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY, prop.getProperty(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_USERNAME_KEY, prop.getProperty(OpenBrowser.ENTER_USERNAME_KEY));
        urlKeys.put(OpenBrowser.SEND_OTP_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_OTP_BUTTON_KEY));

        urlKeys.put(OpenBrowser.RADIO_BUTTON, prop.getProperty(OpenBrowser.RADIO_BUTTON));
        urlKeys.put(OpenBrowser.SEND_VERIFICATION_CODE_KEY, prop.getProperty(OpenBrowser.SEND_VERIFICATION_CODE_KEY));

        urlKeys.put(OpenBrowser.ENTER_EMAIL1_KEY, prop.getProperty(OpenBrowser.ENTER_EMAIL1_KEY));
        urlKeys.put(OpenBrowser.SEND_EMAIL1_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_EMAIL1_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_OTP_FIELD_KEY, prop.getProperty(OpenBrowser.ENTER_OTP_FIELD_KEY));
        urlKeys.put(OpenBrowser.SUBMIT_OTP_BUTTON_KEY, prop.getProperty(OpenBrowser.SUBMIT_OTP_BUTTON_KEY));
        urlKeys.put(OpenBrowser.VERIFICATION_SCREEN_KEY, prop.getProperty(OpenBrowser.VERIFICATION_SCREEN_KEY));


        driver = new ChromeDriver(options);
       //driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }


     public boolean performTest(SheetColumnHeader1 sheetColumnHeader) throws InterruptedException, IOException {
        try {

            ////////////////////////////////////////////////////////////
            ////for deleteing the old emails in yopmails
/*
            driver.get(OpenBrowser.URL2);

            Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).clear();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).sendKeys(sheetColumnHeader.getEmailId1());
            Thread.sleep(1000);
            //takeScreenshot("enteryopemail");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL1_BUTTON_KEY))).click();
            Thread.sleep(2000);
            /////////////////////////////////////
          //  Click on  delete button for delete old emails in yopmail if they found any old emails

            driver.switchTo().frame("ifinbox");// Actually the inbox mails are showing in an iFrame so first we have to switch to that iFrame to perform the further action.
            try {
                if ((driver.findElement(By.xpath(".//*[@id='e0']")).isDisplayed())) {
                    driver.findElement(By.xpath(".//*[@id='e0']")).click();// click on the select mail icon.
                    driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();// click on the delete
                    driver.findElement(By.linkText("Empty Inbox")).click();// click on the Empty Inbox.
                }
            }catch (Exception e){

            }


            //////////////////////////////////////////
            //Run the SSPR web page for sending the otp in email id


            Thread.sleep(2000);*/
            driver.get(OpenBrowser.URL1);
            driver.navigate().refresh();
            System.out.println("**Launching Chrome Browser**");
            takeScreenshot("screen");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY))).click();

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_USERNAME_KEY))).sendKeys(sheetColumnHeader.getUsername());

            //takeScreenshot("firstname");
            //(for assertion)
           driver.findElement(By.cssSelector("div[class='Layout-module_contentWrapper_2gTmj']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_OTP_BUTTON_KEY))).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.RADIO_BUTTON))).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_VERIFICATION_CODE_KEY))).click();
           Thread.sleep(1000);
/////////////////////////////////////////////////////////////////////////////////////////////
            //for taking the otp from email

           driver.get(OpenBrowser.URL2);


            takeScreenshot("OTP");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).clear();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).sendKeys(sheetColumnHeader.getEmailId1());
            Thread.sleep(1000);
            //takeScreenshot("enteryopemail");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL1_BUTTON_KEY))).click();
            Thread.sleep(3000);
            // takeScreenshot("usernamescreen");
            driver.switchTo().frame("ifmail");
            Thread.sleep(1000);

            WebElement webElement=driver.findElement(By.cssSelector("#mailmillieu"));
            String emailText=webElement.getText();
            String regex ="[^\\d]+";
            String[] arrOTP = emailText.split(regex);
            String OTP=arrOTP[1];
            System.out.println("OTP is  "+OTP);
            driver.navigate().back();
            driver.navigate().back();
            /////////////////////////////////////////////////////////
            //now we enter the otp in otp field

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_OTP_FIELD_KEY))).sendKeys(OTP);
            Thread.sleep(1000);
            takeScreenshot("withOtp");

           WebElement webElement2= driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SUBMIT_OTP_BUTTON_KEY)));
           webElement2.click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.VERIFICATION_SCREEN_KEY))).isDisplayed();

            Thread.sleep(1000);
            //////////////////////////////////////////////////////////
            //now we take the password from email box
            driver.get(OpenBrowser.URL2);

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).clear();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).sendKeys(sheetColumnHeader.getEmailId1());
            Thread.sleep(1000);

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL1_BUTTON_KEY))).click();
            Thread.sleep(3000);
            driver.switchTo().frame("ifmail");
            Thread.sleep(1000);
            WebElement webElement1=driver.findElement(By.cssSelector("#mailmillieu"));
            String emailText1=webElement1.getText();

            String Password =emailText1.substring(135,151);

            System.out.println("Password is  "+Password);
            Thread.sleep(1000);

            driver.get(OpenBrowser.URL3);
            Thread.sleep(4000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("input[type='text'][class='inputGray']")).sendKeys(sheetColumnHeader.getUsername());
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("input[type='password'][class='inputGray']")).sendKeys(Password);
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("button[type='button']")).click();
            Thread.sleep(4000);
            driver.findElement(By.cssSelector("div[class='settings-container']")).isDisplayed();
            Thread.sleep(2000);
            /////////////////////////////////////////////////////////
            //adding the assertion for last step of end to end test cases

            WebElement web3 = driver.findElement(By.xpath("//button[contains(text(),'Sign Out')]"));
            Thread.sleep(1000);
            String ActualTitle = web3.getText();
            System.out.println(ActualTitle);
            String ExpectedTitle = "Sign Out";
            Assert.assertEquals(ActualTitle, ExpectedTitle);
            System.out.println("Assert passed");


        } catch (Exception p) {
            return false;
        }
        return true;


        //Thread.sleep(1000);
    }


    public void shutdownDriver() {

        driver.quit();

        //driver.close();
        System.out.println("I am closing Browser ");
    }

    public static DriverUtility getInstance() throws Exception {
        return new DriverUtility();
    }

    public static void takeScreenshot(String filename) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/main/java/Screenshot/" + filename + ".jpg"));
    }



}
