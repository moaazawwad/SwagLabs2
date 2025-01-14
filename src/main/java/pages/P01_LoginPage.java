package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage extends Page_Base {

    //define constructor to set the driver
    public P01_LoginPage(WebDriver driver) {
        super(driver);
    }

    //define locators
    private final By USERNAME_TEXT = By.id("user-name");
    private final By PASSWORD_TEXT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");


    //define action methods
    public P01_LoginPage enterUsername(String userName) {
        driver.findElement(USERNAME_TEXT).sendKeys(userName);
        return this;
    }

    public P01_LoginPage enterPassword(String password) {
        driver.findElement(PASSWORD_TEXT).sendKeys(password);
        return this;
    }

    public P01_LoginPage clickOnLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }
}
