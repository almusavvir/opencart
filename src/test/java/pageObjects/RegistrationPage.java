package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtPhone;
    @FindBy(xpath ="//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath ="//input[@id='input-confirm']")
    WebElement txtConfirmPassword;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPrivacyPolicy;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    public WebElement msgUserAlreadyExists;

    //methods
    public void setTxtFirstName(String firstName) {
        txtFirstName.sendKeys(firstName);
    }
    public void setTxtLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }
    public void setTxtEmail(String email) {
        txtEmail.sendKeys(email);
    }
    public void setTxtPhone(String phone) {
        txtPhone.sendKeys(phone);
    }
    public void setTxtPassword(String password) {
        txtPassword.sendKeys(password);
    }
    public void setTxtConfirmPassword(String password) {
        txtConfirmPassword.sendKeys(password);
    }
    public void clickChkPrivacyPolicy() {
        chkPrivacyPolicy.click();
    }
    public void clickBtnContinue() {
        btnContinue.click();
    }
    public String getConfirmationMsg(){
        try {
            return (msgConfirmation.getText());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getUserAlreadyExistsMsg(){
        try {
            return (msgUserAlreadyExists.getText());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
