package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import propUtils.PropertiesUtil;
import testBase.BaseClass;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TS_002_Login_Functionality extends BaseClass {

    PropertiesUtil prop = new PropertiesUtil();
    String baseUrl = prop.getBaseUrl();

    public TS_002_Login_Functionality() throws IOException {
    }

    @AfterMethod
    public void clearCookies() throws IOException {
        driver.manage().deleteAllCookies();
    }

    @Test(priority = 0)
    void TC_LF_001_verify_login() throws IOException {
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
    void TC_LF_002_verify_invalid_login() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail("jjjjkkk@gmail.com");
        loginPage.setTxtPassword(";dfjakdja;dkfja;dskfjad");
        loginPage.clickBtnLogin();

        Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");
        Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");

    }

    @Test(priority = 2)
    void TC_LF_003_verify_invalid_email_valid_password() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTxtEmail("jjj5656jkkdk@gmail.com");
        loginPage.setTxtPassword(prop.getLoginPassword());
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.getErrInvalidLoginMsg().contains("Warning: No match for E-Mail Address and/or Password.") || loginPage.getErrInvalidLoginMsg().contains("Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour."));
    }

    @Test(priority = 3)
    void TC_LF_004_verify_valid_email_invalid_password() throws IOException {
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
    void TC_LF_005_verify_empty_login() throws IOException {
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
    void TC_LF_006_verify_forgot_password_link() throws IOException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();
        loginPage.clickLnkForgotPassword();
        Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/forgotten");
    }

//    @Test(priority = 6)
//    void TC_LF_001_verify_login() throws IOException {
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
    void TC_LF_007_verify_email_password_placeholder() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        String mailPlaceholder = loginPage.getTxtEmailPlaceholder();
        String passPlaceholder = loginPage.getTxtPasswordPlaceholder();
        //Assert.assertEquals(loginPage.getErrInvalidLoginMsg(), "Warning: No match for E-Mail Address and/or Password.");
        Assert.assertTrue(mailPlaceholder.equals("E-Mail Address") && passPlaceholder.equals("Password"));
    }

    /*@Test(priority = 7)
    void TC_LF_008_verify_login_and_back() throws IOException {
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
    }*/

    @Test(priority = 8)
    void TC_LF_009_verify_login_logout_and_back() throws IOException {
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
        Assert.assertTrue(!homePage.getLinkLogin().isDisplayed());
    }

    //TC_LF_012 - Login Functionality" Validate the number of unsucessful login attemps
    //1. Click on 'My Account' Dropmenu
    //2. Click on 'Login' option
    //3. Enter invalid email address into the 'E-Mail Address' field - <Refer Test Data>
    //4. Enter invalid password into the 'Password' field - <Refer Test Data>
    //5. Click on 'Login' button
    //6. Repeat Step 5 for 4 more times (ER-1)

    @Test(priority = 9)
    void TC_LF_012_verify_invalid_login_attempts() throws IOException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        for (int i = 1; i <=5 ; i++) {
            loginPage.clearTxtEmail();
            loginPage.clearTxtPassword();
            loginPage.setTxtEmail("styrertert@gmail.com");
            loginPage.setTxtPassword("q234234");
            loginPage.clickBtnLogin();
        }

        Assert.assertTrue(loginPage.getErrInvalidLoginMsg().contains("Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour."));
    }

    @Test(priority = 10)
    void TC_LF_013_verify_password_visibility() throws IOException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();

        Assert.assertEquals(loginPage.getTxtPasswordAtrribute(), "password");
    }

    @Test(priority = 11)
    void TC_HyperLinkCheck() throws IOException {
        HomePage homePage = new HomePage(driver);
        String filePath = "C:/Users/devbase/Projects/opencart/logs/logs.txt";
        String result = "";
        FileWriter writer = new FileWriter(filePath);

        List<WebElement> links = homePage.getListHyperLinks();
        for(WebElement elem : links) {
            String siteurl = elem.getAttribute("href");
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

            if(siteurl!=null && !siteurl.isEmpty() && siteurl.startsWith("https://")) {
                //System.out.println("URL is " + siteurl);
                URL url = new URL(siteurl);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("HEAD");
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();

                if(responseCode>=400) {
                    result = formattedDateTime + " " + siteurl + " : Broken URL - Response - " + responseCode + "\n";
                    System.out.print(result);
                    writer.write(result);
                } else {
                    result = formattedDateTime + " " + siteurl + " : Valid URL - Response - " + responseCode + "\n";
                    System.out.print(result);
                    writer.write(result);
                }
            } else {
                System.out.println(siteurl + " : Invalid URL scheme");
            }
        }
        Assert.assertTrue(true);
        writer.close();
    }
}
