package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.io.IOException;

public class TS_008_Cart extends BaseClass {

    public TS_008_Cart() throws IOException {
    }

    @AfterMethod
    public void clearCookies() throws IOException {
        driver.manage().deleteAllCookies();
    }

    @Test(priority = 0)
    public void TC008_addtocart_from_pdp () {
        HomePage homePage = new HomePage(driver);
        homePage.clearSearchBox();
        homePage.typeInSearchBox("iMac");
        homePage.clickSearchButton();


    }
}
