package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import propUtils.PropertiesUtil;
import testBase.BaseClass;

import java.io.IOException;

public class TS_005_Search_Functionality extends BaseClass {

    PropertiesUtil prop = new PropertiesUtil();

    public TS_005_Search_Functionality() throws IOException {
    }

    @AfterMethod
    public void clearCookies() throws IOException {
        driver.manage().deleteAllCookies();
    }

    @Test(priority = 0)
    void TC_SF_001_validate_existing_product() throws IOException, InterruptedException {
        Thread.sleep(5000);
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchBox();
        homePage.typeInSearchBox("iMac");
        homePage.clickSearchButton();

        Assert.assertEquals(homePage.getFirstSearchCardText(), "imac");
        //Thread.sleep(3000);
    }

    @Test(priority = 1)
    void TC_SF_002_validate_nonexisting_product() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchBox();
        homePage.typeInSearchBox("FitBit");
        homePage.clickSearchButton();

        Assert.assertEquals(homePage.getLblNoProductText(), "There is no product that matches the search criteria.");
        //Thread.sleep(3000);
    }

    @Test(priority = 2)
    void TC_SF_003_validate_blank_search() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchBox();
        homePage.clickSearchButton();

        Assert.assertEquals(homePage.getLblNoProductText(), "There is no product that matches the search criteria.");
        //Thread.sleep(3000);
    }

    @Test(priority = 3)
    void TC_SF_004_validate_existing_product_after_login() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();
        homePage.clickSearchBox();
        homePage.typeInSearchBox("iMac");
        homePage.clickSearchButton();

        Assert.assertEquals(homePage.getFirstSearchCardText(), "imac");
        //Thread.sleep(3000);
    }

    @Test(priority = 4)
    void TC_SF_005_validate_search_criteria() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickLogin();
        homePage.clickSearchBox();
        homePage.typeInSearchBox("mac");
        homePage.clickSearchButton();

        Assert.assertEquals(homePage.getFirstSearchCardText(), "imac");
        Assert.assertEquals(homePage.getSecondSearchCardText(), "macbook");
        //Thread.sleep(3000);
    }

    @Test(priority = 5)
    void TC_SF_006_validate_search_placeholder() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clearSearchBox();
        homePage.getSearchBoxPlaceholder();

        Assert.assertEquals(homePage.getSearchBoxPlaceholder(), "Search");
        //Assert.assertEquals(homePage.getSecondSearchCardText(), "macbook");
        //Thread.sleep(3000);
    }

    @Test(priority = 6)
    void TC_SF_007_validate_search_criteria() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        //homePage.clickSearchBox();
        homePage.clickSearchButton();
        homePage.clickSearchCriteriaBox();
        homePage.typeInSearchCriteriaBox("mac");
        homePage.clickSearchCriteriaButton();

        Assert.assertEquals(homePage.getFirstSearchCardText(), "imac");
        Assert.assertEquals(homePage.getSecondSearchCardText(), "macbook");
    }

    @Test(priority = 7)
    void TC_SF_008_validate_search_product_description() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        //homePage.clickSearchBox();
        homePage.clickSearchButton();
        homePage.clickSearchCriteriaBox();
        homePage.clearSearchCriteriaBox();
        homePage.typeInSearchCriteriaBox("iLife");
        homePage.checkProductCategoryCheckbox();
        homePage.clickSearchCriteriaButton();

        Assert.assertEquals(homePage.getFirstSearchCardText(), "imac");
    }

    @Test(priority = 8)
    void TC_SF_009_validate_search_by_category() {
//        1. Don't enter anything into the 'Search' text box field
//        2. Click on the button having search icon
//        3. Enter any Product Name into the 'Search Criteria' text box field - <Refer Test Data>
//        4. Select the correct category of the given Product Name into 'Category' dropdown field - <Refer Test Data>
//        5. Click on 'Search' button (Validate ER-1)
//        6. Select a wrong category in tthe 'Category' dropdown field - - <Refer Test Data>
//        7. Click on 'Search' button (Validate ER-2)

        HomePage homePage = new HomePage(driver);
        homePage.clickSearchButton();
        homePage.clickSearchCriteriaBox();
        homePage.clearSearchCriteriaBox();
        homePage.typeInSearchCriteriaBox("iMac");
        homePage.clickSearchCriteriaButton();

        WebElement dropdownElement = driver.findElement(By.name("category_id"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("27");

        homePage.clickSearchButton();
        Assert.assertEquals(homePage.getFirstSearchCardText(), "imac");

    }


}
