package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessBrowser {
    public WebDriver createHeadlessChrome(WebDriver driver) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        //options.addArguments("--window-size=1366,768");

        driver = new ChromeDriver(options);

        return driver;
    }
}
