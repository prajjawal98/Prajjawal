package Password.ForgotPasswordScreen;

import Global.OpenBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Drivers {
    static WebDriver driver;
    private HashMap<String, String> urlKeys;

    private Drivers() throws Exception {
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

        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");


        // urlKeys.put(OpenBrowser.CLICK_BUTTON_KEY, prop.getProperty(OpenBrowser.CLICK_BUTTON_KEY));
        urlKeys.put(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY, prop.getProperty(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY));
        urlKeys.put(OpenBrowser.ENTER_USERNAME_KEY, prop.getProperty(OpenBrowser.ENTER_USERNAME_KEY));
        urlKeys.put(OpenBrowser.SEND_OTP_BUTTON_KEY, prop.getProperty(OpenBrowser.SEND_OTP_BUTTON_KEY));
        urlKeys.put(OpenBrowser.TEXT_DISPLAYED_KEY, prop.getProperty(OpenBrowser.TEXT_DISPLAYED_KEY));

      //  driver = new ChromeDriver();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public boolean performTest(SheetColumnHeader2 sheetColumnHeader) throws InterruptedException, IOException {
        try {




            driver.get(OpenBrowser.URL1);

            System.out.println("**Launching Chrome Browser**");
            // takeScreenshot("screen");

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.FORGET_PASSWORD_BUTTON_KEY))).click();

           Thread.sleep(500);

            driver.findElement(By.cssSelector(urlKeys.get(OpenBrowser.ENTER_USERNAME_KEY))).sendKeys(sheetColumnHeader.getUsername1());

            // takeScreenshot("firstname");
            //(for assertion)
            driver.findElement(By.cssSelector("div[class='Layout-module_contentWrapper_2gTmj']")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.SEND_OTP_BUTTON_KEY))).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(urlKeys.get(OpenBrowser.TEXT_DISPLAYED_KEY))).isDisplayed();


            Thread.sleep(1000);

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

    public static Drivers getInstance() throws Exception {
        return new Drivers();
    }
}