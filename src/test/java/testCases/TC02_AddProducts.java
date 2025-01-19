package testCases;

import Utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_Productspage;
import pages.P03_CartPage;
import pages.P04_CheckoutPage;

import static drivers.DriverHolder.getDriver;
import static pages.Page_Base.captureScreenshot;

public class TC02_AddProducts extends testBase {
    @Test(priority = 1, description = "Add Random Products Successfully")
    public void testLoginWithValidCredentials() {
        new P01_LoginPage(getDriver()).
                enterUsername("standard_user").
                enterPassword("secret_sauce").
                clickOnLoginButton();
    }


    @Test(priority = 1, description = "Add random products to the cart and complete checkout process")
    public void addItemsToCartAndCalculateTotal() throws InterruptedException {
        String firstName = DataGenerator.generateFirstName();
        String lastName = DataGenerator.generateLastName();

        new P01_LoginPage(getDriver()).
                enterUsername("standard_user").
                enterPassword("secret_sauce").
                clickOnLoginButton();

        // Go to the products page and add random items to the cart
        new P02_Productspage(getDriver()).
                addRandomItemsToCarts().
                clickOnShoppingCarticon();  // Dynamically adds a random number of items to the cart

        new P03_CartPage(getDriver()).
                clickOnCheckoutButton();
        new P04_CheckoutPage(getDriver()).
                enterFirstName(firstName).
                enterLastName(lastName).
                enterZipCode("12345").
                clickContinueButton().
                fetchAndParsePrices().
                clickFinishButton();

        double ecpectedSumPrices = new P02_Productspage(getDriver()).getTotalPrices();
        double actualtotalPriceText = new P04_CheckoutPage(getDriver()).getConvertedPrice();
        double totalTaxText = new P04_CheckoutPage(getDriver()).getConvertedTax();
        double actualFinalTotal = new P04_CheckoutPage(getDriver()).getConvertedFinalPrice();

        double TotalPriceAndTotalTax = actualtotalPriceText + totalTaxText;
        captureScreenshot(getDriver(), "completeCheckoutProcess");

        Assert.assertEquals(actualtotalPriceText, ecpectedSumPrices);
        Assert.assertEquals(TotalPriceAndTotalTax, actualFinalTotal);
        Assert.assertTrue(getDriver().getPageSource().contains("THANK YOU FOR YOUR ORDER"), "Page source does not contain the expected text.");
    }


    @Test(priority = 2, description = "Add random products, remove one, and complete checkout process")
    public void addItemsToCartRemoveOneAndProceed() throws InterruptedException {
        String firstName = DataGenerator.generateFirstName();
        String lastName = DataGenerator.generateLastName();

        new P01_LoginPage(getDriver()).
                enterUsername("standard_user").
                enterPassword("secret_sauce").
                clickOnLoginButton();

        // Go to the products page and add random items to the cart
        new P02_Productspage(getDriver()).
                addRandomItemsToCarts().
                clickOnShoppingCarticon();  // Dynamically adds a random number of items to the cart

        new P03_CartPage(getDriver()).
                clickOnCheckoutButton();
        new P04_CheckoutPage(getDriver()).
                enterFirstName(firstName).
                enterLastName(lastName).
                enterZipCode("12345").
                clickContinueButton().
                clickCancelButton();

        new P02_Productspage(getDriver()).removeRandomItemFromCart()
                .clickOnShoppingCarticon();

        new P03_CartPage(getDriver()).
                clickOnCheckoutButton();
        new P04_CheckoutPage(getDriver()).
                enterFirstName(firstName).
                enterLastName(lastName).
                enterZipCode("12345").
                clickContinueButton().
                fetchAndParsePrices().
                clickFinishButton();

        double ecpectedSumPrices = new P02_Productspage(getDriver()).getTotalPrices();
        double actualtotalPriceText = new P04_CheckoutPage(getDriver()).getConvertedPrice();
        double totalTaxText = new P04_CheckoutPage(getDriver()).getConvertedTax();
        double actualFinalTotal = new P04_CheckoutPage(getDriver()).getConvertedFinalPrice();
        double TotalPriceAndTotalTax = actualtotalPriceText + totalTaxText;
        captureScreenshot(getDriver(), "completeProcessWithBackStep");

        Assert.assertEquals(actualtotalPriceText, ecpectedSumPrices);
        Assert.assertEquals(TotalPriceAndTotalTax, actualFinalTotal);
        Assert.assertTrue(getDriver().getPageSource().contains("THANK YOU FOR YOUR ORDER"), "Page source does not contain the expected text.");
    }
}
