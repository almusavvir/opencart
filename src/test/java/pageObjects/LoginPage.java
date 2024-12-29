package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement btnLogout;
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement lblMyAccount;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement errInvalidLogin;
    @FindBy(xpath = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
    WebElement linkForgotPassword;
    @FindBy(xpath = "//a[normalize-space()='Qafox.com']")
    WebElement linkHomePage;

    //methods
    public void setTxtEmail(String email){
        txtEmail.sendKeys(email);
    }
    public String getTxtEmailPlaceholder(){
        return txtEmail.getAttribute("placeholder");
    }
    public String getTxtPasswordPlaceholder(){
        return txtPassword.getAttribute("placeholder");
    }
    public void setTxtPassword(String password){
        txtPassword.sendKeys(password);
    }
    public void clickBtnLogin(){
        btnLogin.click();
    }
    public void clickBtnLogout(){
        btnLogout.click();
    }
    public void clickLnkForgotPassword(){
        linkForgotPassword.click();
    }
    public void clickLnkHomePage(){
        linkHomePage.click();
    }

    public String getConfirmationMsg(){
        try {
            return (lblMyAccount.getText());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getErrInvalidLoginMsg(){
        try {
            return (errInvalidLogin.getText());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getLinkForgotPassword(){
        try {
            return (linkForgotPassword.getText());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
