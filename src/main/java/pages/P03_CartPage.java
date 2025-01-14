package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_CartPage extends Page_Base {
    // Define constructor to set the driver
    public P03_CartPage(WebDriver driver) {
        super(driver);
    }

    private final By CHECKOUT_BUTTON = By.xpath("//a[text()=\"CHECKOUT\"]");

    public P03_CartPage clickOnCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return this;
    }
}
