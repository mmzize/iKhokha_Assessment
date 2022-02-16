package Setup;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Setup_Class {
    public static String dir = System.getProperty("user.dir");

    private static final String Chromedriver = dir + "./src/main/java/Drivers/chromedriver.exe";
    private static final String Firefox = dir + "/src/main/java/Test_Drivers/geckodriver.exe";
    private static WebDriver driver;
    public Setup_Class() {
    }

    public static WebDriver startBrowserOfChoice(String URL, String BrowserChoice) {
        if (BrowserChoice.equalsIgnoreCase("chroME")) {
            System.setProperty("webdriver.chrome.driver", Chromedriver);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            driver = new ChromeDriver(options);
            driver.manage().window().setPosition(new Point(-800,10));
            driver.manage().window().maximize();
        } else if (BrowserChoice.equalsIgnoreCase("FireFox")) {
            System.setProperty("webdriver.gecko.driver", Firefox);
            //  driver = new FirefoxDriver();
        }
        driver.get(URL);
        return driver;
    }
}
