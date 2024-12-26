package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

import java.time.Duration;

//TC_RF_001
//(TS_001) Register Functionality
//Validate Registering an Account by providing only the Mandatory fields
//Newsletter subscription is not a mandatory field

public class TS_001_TC_RF_001_AccountRegistration {

    WebDriver driver;
    String url = "https://tutorialsninja.com/demo/index.php?route=common/home";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verify_user_registration() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickResister();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setTxtFirstName("Muzammil");
        registrationPage.setTxtLastName("Pathan");
        registrationPage.setTxtEmail("vobakip354@evnft.com");
        registrationPage.setTxtPhone("7765982982");
        registrationPage.setTxtPassword("bhagalpur@123");
        registrationPage.setTxtConfirmPassword("bhagalpur@123");
        registrationPage.clickChkPrivacyPolicy();
        registrationPage.clickBtnContinue();

        String confMsg = registrationPage.getConfirmationMsg();

        Assert.assertEquals(confMsg, "Your Account Has Been Created!");
        Thread.sleep(5000);
    }
}
