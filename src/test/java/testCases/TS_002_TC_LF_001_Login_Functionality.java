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

        Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");

    }
}
