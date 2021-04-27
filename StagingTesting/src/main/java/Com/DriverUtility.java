package Com;

import Global.OpenBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DriverUtility {
    static WebDriver driver;
    private HashMap<String, String> urlKeys;


    private DriverUtility() throws Exception {

       // System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver.exe");
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
        //options.addArguments("--headless");
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");


        urlKeys.put(OpenBrowser.FORGET_USERNAME_BUTTON_KEY, prop.getProperty(OpenBrowser.FORGET_USERNAME_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_EMAIL_KEY, prop.getProperty(OpenBrowser.ENTER_EMAIL_KEY));
        urlKeys.put(OpenBrowser.SEND_EMAIL_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_EMAIL_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_EMAIL1_KEY, prop.getProperty(OpenBrowser.ENTER_EMAIL1_KEY));
        urlKeys.put(OpenBrowser.SEND_EMAIL1_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_EMAIL1_BUTTON_KEY));
        //urlKeys.put(OpenBrowser.USERNAME_TEXT_KEY, prop.getProperty(OpenBrowser.USERNAME_TEXT_KEY));


      // driver = new ChromeDriver();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    boolean performTest(String parsedEmailId) throws InterruptedException, IOException {
        try {

            driver.get(OpenBrowser.URL2);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).clear();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).sendKeys(parsedEmailId);
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL1_BUTTON_KEY))).click();
            Thread.sleep(2000);
            /////////////////////////////////////
            //  For deleting old emails in yopmail
            driver.switchTo().frame("ifinbox");// Actually the inbox mails are showing in an iFrame so first we have to switch to that iFrame to perform the further action.
            try {
                if ((driver.findElement(By.xpath(".//*[@id='e0']")).isDisplayed())) {
                    driver.findElement(By.xpath(".//*[@id='e0']")).click();// click on the select mail icon.
                    driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();// click on the delete
                    driver.findElement(By.linkText("Empty Inbox")).click();// click on the Empty Inbox.
                }
            }catch (Exception e){

            }
            Thread.sleep(2000);
            //////////////////////////////////////////
            driver.get(OpenBrowser.URL1);
            driver.navigate().refresh();
            System.out.println("**Launching Chrome Browser**");
            takeScreenshot("screen");
            //WebDriverWait wait=new WebDriverWait(driver, 20);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_USERNAME_BUTTON_KEY))).click();

            Thread.sleep(1000);

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL_KEY))).sendKeys(parsedEmailId);
           // takeScreenshot("enteremail");
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("div[class='Layout-module_contentWrapper_2gTmj']")).click();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL_BUTTON_KEY))).click();

           // takeScreenshot("sentemailscreen");
            Thread.sleep(1000);
            driver.get(OpenBrowser.URL2);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).clear();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).sendKeys(parsedEmailId);
            //Thread.sleep(1000);
            //takeScreenshot("enteryopemail");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL1_BUTTON_KEY))).click();
            Thread.sleep(2000);
           // takeScreenshot("usernamescreen");
            driver.switchTo().frame("ifmail");
            driver.findElement(By.cssSelector("div[class='f16")).isDisplayed();
            Thread.sleep(2000);
            //driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.USERNAME_TEXT_KEY))).isDisplayed();
        } catch (Exception p) {
            return false;
        }
        return true;


        //Thread.sleep(1000);
    }


    void shutdownDriver() {

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
