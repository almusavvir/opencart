package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import propUtils.PropertiesUtil;
import testBase.BaseClass;

import java.io.IOException;

//TC_RF_001
//(TS_001) Register Functionality
//Validate Registering an Account by providing only the Mandatory fields
//Newsletter subscription is not a mandatory field

public class TS_001_TC_RF_001_AccountRegistration extends BaseClass {

    PropertiesUtil prop = new PropertiesUtil();
    String baseUrl = prop.getBaseUrl();

    public TS_001_TC_RF_001_AccountRegistration() throws IOException {
    }

    @Test
    public void verify_user_registration() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickResister();

        RegistrationPage registerPage = new RegistrationPage(driver);
        registerPage.setTxtFirstName("Muzammil");
        registerPage.setTxtLastName("Pathan");
        registerPage.setTxtEmail("vobakip354@evnft.com");
        registerPage.setTxtPhone("7765982982");
        registerPage.setTxtPassword("bhagalpur@123");
        registerPage.setTxtConfirmPassword("bhagalpur@123");
        registerPage.clickChkPrivacyPolicy();
        registerPage.clickBtnContinue();

        String confMsg = registerPage.getConfirmationMsg();

        Assert.assertEquals(confMsg, "Your Account Has Been Created!");
        Thread.sleep(5000);
    }
}
