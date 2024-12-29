package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import propUtils.PropertiesUtil;
import testBase.BaseClass;

import java.io.IOException;
import java.time.Duration;

public class TS_002_TC_LF_001_Login_Functionality extends BaseClass {

    PropertiesUtil prop = new PropertiesUtil();
    String baseUrl = prop.getBaseUrl();

    public TS_002_TC_LF_001_Login_Functionality() throws IOException {
    }

    @AfterMethod
    public void clearCookies() throws IOException {
        driver.manage().deleteAllCookies();
    }

    @Test(priority = 0)
    void verify_login() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail(prop.getLoginEmail());
        loginPage.setTxtPassword(prop.getLoginPassword());
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.getConfirmationMsg(), "My Account");
        homePage.clickMyAccount();
        loginPage.clickBtnLogout();
    }
    @Test(priority = 1)
    void verify_invalid_login() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail("jjjjkkk@gmail.com");
        loginPage.setTxtPassword(";dfjakdja;dkfja;dskfjad");
        loginPage.clickBtnLogin();

        //Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");
        Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");

    }

    @Test(priority = 2)
    void verify_invalid_email_valid_password() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail("jjj5656jkkdk@gmail.com");
        loginPage.setTxtPassword(prop.getLoginPassword());
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");

    }

    @Test(priority = 3)
    void verify_valid_email_invalid_password() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail(prop.getLoginEmail());
        loginPage.setTxtPassword("asdfafadf");
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");

    }

    @Test(priority = 4)
    void verify_empty_login() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail("");
        loginPage.setTxtPassword("");
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");

    }

    @Test(priority = 5)
    void verify_forgot_password_link() throws IOException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();
        loginPage.clickLnkForgotPassword();
        Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/forgotten");
    }

//    @Test(priority = 6)
//    void verify_login() throws IOException {
//        HomePage homePage = new HomePage(driver);
//        homePage.clickMyAccount();
//        homePage.clickLogin();
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.setTxtEmail(prop.getLoginEmail());
//
//        loginPage.setTxtPassword(prop.getLoginPassword());
//        loginPage.clickBtnLogin();
//
//        Assert.assertEquals(loginPage.getConfirmationMsg(), "My Account");
//        homePage.clickMyAccount();
//        loginPage.clickBtnLogout();
//    }

    @Test(priority = 6)
    void verify_email_password_placeholder() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        String mailPlaceholder = loginPage.getTxtEmailPlaceholder();
        String passPlaceholder = loginPage.getTxtPasswordPlaceholder();
        //Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");
        Assert.assertTrue(mailPlaceholder.equals("E-Mail Address") && passPlaceholder.equals("Password"));
    }

    @Test(priority = 7)
    void verify_login_and_back() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail(prop.getLoginEmail());
        loginPage.setTxtPassword(prop.getLoginPassword());
        loginPage.clickBtnLogin();
        driver.navigate().back();
        homePage.clickLnkHomePage();
        homePage.clickMyAccount();
        Assert.assertTrue(homePage.getLinkLogout().isDisplayed());
    }

    @Test(priority = 8)
    void verify_login_logout_and_back() throws IOException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        loginPage.setTxtEmail(prop.getLoginEmail());
        loginPage.setTxtPassword(prop.getLoginPassword());
        loginPage.clickBtnLogin();

        driver.navigate().back();
        homePage.clickLnkHomePage();
        homePage.clickMyAccount();
        loginPage.clickBtnLogout();
        Assert.assertFalse(homePage.getLinkLogout().isDisplayed());
    }
}
