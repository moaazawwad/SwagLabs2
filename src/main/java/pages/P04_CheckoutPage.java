package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CheckoutPage extends Page_Base {

    public P04_CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Constants
    private static final String CURRENCY_DELIMITER = "\\$";

    private double convertedPrice = 0;
    private double convertedTax = 0;
    private double convertedFinalPrice = 0;

    // Getters for prices
    public double getConvertedPrice() {
        return convertedPrice;
    }

    public double getConvertedTax() {
        return convertedTax;
    }

    public double getConvertedFinalPrice() {
        return convertedFinalPrice;
    }

    // Define locators
    private final By FIRST_NAME = By.id("first-name");
    private final By LAST_NAME = By.id("last-name");
    private final By ZIP_CODE = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.xpath("//input[@type='submit']");
    private final By ITEM_TOTAL = By.xpath("//div[@class='summary_subtotal_label']");
    private final By TAX_TOTAL = By.xpath("//div[@class='summary_tax_label']");
    private final By FINAL_PRICE = By.xpath("//div[@class='summary_total_label']");
    private final By FINISH_BUTTON = By.xpath("//a[text()='FINISH']");
    private final By CANCEL_BUTTON = By.xpath("//a[text()='CANCEL']");


    // Action methods

    public P04_CheckoutPage enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        return this;
    }

    public P04_CheckoutPage enterLastName(String lastName) {
        driver.findElement(LAST_NAME).sendKeys(lastName);
        return this;
    }

    public P04_CheckoutPage enterZipCode(String zipCode) {
        driver.findElement(ZIP_CODE).sendKeys(zipCode);
        return this;
    }

    public P04_CheckoutPage clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    public P04_CheckoutPage clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }

    public P04_CheckoutPage clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
        return this;
    }

    // Retrieve and parse item, tax, and final prices
    public P04_CheckoutPage fetchAndParsePrices() {
        String totalPriceText = driver.findElement(ITEM_TOTAL).getText().split(CURRENCY_DELIMITER)[1];
        String totalTaxText = driver.findElement(TAX_TOTAL).getText().split(CURRENCY_DELIMITER)[1];
        String totalFinalPriceText = driver.findElement(FINAL_PRICE).getText().split(CURRENCY_DELIMITER)[1];

        convertedPrice = Double.parseDouble(totalPriceText);
        convertedTax = Double.parseDouble(totalTaxText);
        convertedFinalPrice = Double.parseDouble(totalFinalPriceText);

        return this;
    }
}