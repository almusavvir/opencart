package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends BasePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
    }
    //locators
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement linkLogin;
    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement linkLogout;
    @FindBy(xpath = "//a[normalize-space()='Qafox.com']")
    WebElement linkHomePage;
    @FindBy(xpath = "//a")
    List<WebElement> listHyperlinks;
    @FindBy(xpath ="//div[@id='search']/input[@class='form-control input-lg']")
    WebElement searchBox;
    @FindBy(xpath = "//div[@id='search']/span[@class='input-group-btn']/button[@class='btn btn-default btn-lg']")
    WebElement searchButton;
    @FindBy(xpath = "//div[@id='content']/div[@class='row'][3]/div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']/div[@class='product-thumb']/div[2]/div[@class='caption']/h4/a")
    WebElement firstSearchCardTitle;
    @FindBy(xpath = "//div[@id='content']/div[@class='row'][3]/div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12'][2]/div[@class='product-thumb']/div[2]/div[@class='caption']/h4/a")
    WebElement secondSearchCardTitle;
    @FindBy(xpath = "//div[@id='content']/p[2]")
    WebElement lblNoProduct;

    //methods
    public void clickMyAccount(){
        lnkMyAccount.click();
    }
    public void clickResister(){
        lnkRegister.click();
    }
    public void clickLogin(){
        linkLogin.click();
    }
    public WebElement getLinkLogout(){
        return linkLogout;
    }
    public WebElement getLinkLogin(){
        return linkLogin;
    }
    public void clickLnkHomePage(){
        linkHomePage.click();
    }
    public List<WebElement> getListHyperLinks(){
        return listHyperlinks;
    }
    public void clickSearchBox() {
        searchBox.click();
    }
    public void typeInSearchBox(String keyword) {
        searchBox.sendKeys(keyword);
    }
    public void clearSearchBox() {
        searchBox.clear();
    }
    public void clickSearchButton(){
        searchButton.click();
    }
    public String getFirstSearchCardText(){
        return firstSearchCardTitle.getText().toLowerCase();
    }
    public String getSecondSearchCardText(){
        return secondSearchCardTitle.getText().toLowerCase();
    }
    public String getLblNoProductText(){
        return lblNoProduct.getText();
    }
    public String getSearchBoxPlaceholder(){
        return searchBox.getAttribute("placeholder");
    }
}