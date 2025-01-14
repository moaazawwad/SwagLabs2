package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CheckoutPage extends Page_Base {

    public P04_CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public double getCovertedPrice() {
        return covertedPrice;
    }

    private double covertedPrice = 0;

    public double getCovertedFinalPrice() {
        return covertedFinalPrice;
    }

    private double covertedFinalPrice = 0;


    public double getCovertedTax() {
        return covertedTax;
    }

    private double covertedTax = 0;

    // Define locators
    private final By FIRST_NAME = By.id("first-name");
    private final By LAST_NAME = By.id("last-name");
    private final By ZIP_CODE = By.id("postal-code");
    private final By CONTINUE = By.xpath("//input[@type='submit']");
    private final By ITEM_TOTAL = By.xpath("//div[@class=\"summary_subtotal_label\"]");
    private final By TAX_TOTAL = By.xpath("//div[@class=\"summary_tax_label\"]");
    private final By FINAL_PRICE = By.xpath("//div[@class=\"summary_total_label\"]");
    private final By FINISH_BUTTON = By.xpath("//a[text()=\"FINISH\"]");

    private final By CANCEL_BUTTON = By.xpath("//a[text()=\"CANCEL\"]");

    // Action method to enter the first name
    public P04_CheckoutPage enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        return this;
    }

    // Action method to enter the last name
    public P04_CheckoutPage enterLastName(String lastName) {
        driver.findElement(LAST_NAME).sendKeys(lastName);
        return this;
    }

    // Action method to enter the zip code
    public P04_CheckoutPage enterZipCode(String zipCode) {
        driver.findElement(ZIP_CODE).sendKeys(zipCode);
        return this;
    }

    // Action method to click the continue button
    public P04_CheckoutPage clickContinue() {
        driver.findElement(CONTINUE).click();
        return this;
    }

    public P04_CheckoutPage clickOnFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }
    public P04_CheckoutPage clickOnCancelhButton() {
        driver.findElement(CANCEL_BUTTON).click();
        return this;
    }

    // Action method to click the continue button
    public P04_CheckoutPage getItemsAndTaxAndFinalPrice() {
      String totalPrice =   driver.findElement(ITEM_TOTAL).getText().split("\\$")[1];
        String totalTax =   driver.findElement(TAX_TOTAL).getText().split("\\$")[1];
        String totalFinalPrice =   driver.findElement(FINAL_PRICE).getText().split("\\$")[1];

        System.out.println("final totalPrice text " + totalPrice);
        System.out.println("final totalTax text " + totalTax);
        System.out.println("final totalFinalPrice text " + totalFinalPrice);

        covertedPrice = Double.parseDouble(totalPrice);
        covertedTax = Double.parseDouble(totalTax);
        covertedFinalPrice = Double.parseDouble(totalFinalPrice);

        System.out.println("finalllll covertedPrice" + covertedPrice);
        System.out.println("finalllll covertedTax" + covertedTax);
        System.out.println("finalllll covertedFinalPrice" + covertedFinalPrice);

        return this;
    }
}