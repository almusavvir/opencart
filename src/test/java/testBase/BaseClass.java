package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import propUtils.PropertiesUtil;
import utilities.HeadlessBrowser;

import java.io.IOException;
import java.time.Duration;

public class BaseClass {
    public WebDriver driver;
    PropertiesUtil prop = new PropertiesUtil();
    String baseUrl = prop.getBaseUrl();

    public BaseClass() throws IOException {
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {

        switch (browser.toLowerCase()){
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            case "safari": driver = new SafariDriver(); break;
            case "headlesschrome": driver = new HeadlessBrowser().createHeadlessChrome(driver); break;
            default: System.out.println(browser.toLowerCase()); return;
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
