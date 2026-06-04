package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ProductDisplayPage extends BasePage {


    public ProductDisplayPage(WebDriver driver) {
        super(driver);
    }

    //locators

    @FindBy (xpath = "//button[@id='button-cart']")
    @CacheLookup
    WebElement addToCartButton;

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

}
