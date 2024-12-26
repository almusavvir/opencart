package testBase;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import propUtils.PropertiesUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class BaseClass {
    public WebDriver driver;
    PropertiesUtil prop = new PropertiesUtil();
    String baseUrl = prop.getBaseUrl();

    public BaseClass() throws IOException {
    }

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) {

        switch (browser.toLowerCase()){
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            case "safari": driver = new SafariDriver(); break;
            default: System.out.println("Invalid browser name passed as parameter"); return;
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
