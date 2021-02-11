package Com;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DriverUtility {
    static WebDriver driver;
    private HashMap<String, String> urlKeys;


    private DriverUtility() throws Exception {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        //opt.setBinary("//usr//bin//google-chrome");  //chrome binary location specified here
        //opt.addArguments("start-maximized");
        //opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //opt.setExperimentalOption("useAutomationExtension", false);
       // chrome_options.add_argument('--headless')
        opt.addArguments("--no-sandbox");
        opt.addArguments("--headless");
        opt.addArguments("--disable-dev-shm-usage");
        System.setProperty("webdriver.chrome.driver", OpenBrowser.CHROME_DRIVER_PATH);
        //chrome_options.add_argument('--disable-dev-shm-usage')
        //d = webdriver.Chrome('/home/PycharmProjects/chromedriver',chrome_options=chrome_options)
       // WebDriver driver = new ChromeDriver(opt);
       // driver.get("https://www.google.com/");
        Properties prop = new Properties();
        FileInputStream FileInputStream = new FileInputStream(
                new File("src/main/resources/object.properties"));
        prop.load(FileInputStream);
        urlKeys = new HashMap<>();

        urlKeys.put(OpenBrowser.FORGET_USERNAME_BUTTON_KEY, prop.getProperty(OpenBrowser.FORGET_USERNAME_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_EMAIL_KEY, prop.getProperty(OpenBrowser.ENTER_EMAIL_KEY));
        urlKeys.put(OpenBrowser.SEND_EMAIL_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_EMAIL_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_EMAIL1_KEY, prop.getProperty(OpenBrowser.ENTER_EMAIL1_KEY));
        urlKeys.put(OpenBrowser.SEND_EMAIL1_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_EMAIL1_BUTTON_KEY));
        //urlKeys.put(OpenBrowser.USERNAME_TEXT_KEY, prop.getProperty(OpenBrowser.USERNAME_TEXT_KEY));


        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    boolean performTest(String parsedEmailId) throws InterruptedException, IOException {
        try {


            driver.get(OpenBrowser.URL1);
            System.out.println("**Launching Chrome Browser**");
            takeScreenshot("screen");
            //WebDriverWait wait=new WebDriverWait(driver, 20);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_USERNAME_BUTTON_KEY))).click();

            //Thread.sleep(1000);

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL_KEY))).sendKeys(parsedEmailId);
            takeScreenshot("enteremail");
            //Thread.sleep(1000);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL_BUTTON_KEY))).click();
            takeScreenshot("sentemailscreen");
            //Thread.sleep(1000);
            driver.get(OpenBrowser.URL2);
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).clear();
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_EMAIL1_KEY))).sendKeys(parsedEmailId);
            //Thread.sleep(1000);
            takeScreenshot("enteryopemail");
            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.SEND_EMAIL1_BUTTON_KEY))).click();
            //Thread.sleep(2000);
            takeScreenshot("usernamescreen");
            driver.switchTo().frame("ifmail");
            driver.findElement(By.cssSelector("div[class='f16")).isDisplayed();
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
