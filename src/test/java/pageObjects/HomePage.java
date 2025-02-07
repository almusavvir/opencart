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
}