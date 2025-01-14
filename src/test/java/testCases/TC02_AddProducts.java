package testCases;

import Utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_Productspage;
import pages.P03_CartPage;
import pages.P04_CheckoutPage;

import static drivers.DriverHolder.getDriver;

public class TC02_AddProducts extends testBase {
    @Test(priority = 1, description = "Test login functionality using valid credentials")
    public void testLoginWithValidCredentials() {
        new P01_LoginPage(getDriver()).
                enterUsername("standard_user").
                enterPassword("secret_sauce").
                clickOnLoginButton();
        Assert.assertTrue(getDriver().getPageSource().contains("Products"), "Page source does not contain the expected text.");
    }


    @Test(priority = 2, description = "Add random items to carts")
    public void addItemsToCartAndCalculateTotal() throws InterruptedException {
        String firstName = DataGenerator.generateFirstName();
        String lastName = DataGenerator.generateLastName();

        double ecpectedSumPrices = new P02_Productspage(getDriver()).getTotalPrices();
        double actualtotalPriceText = new P04_CheckoutPage(getDriver()).getConvertedPrice();
        double totalTaxText = new P04_CheckoutPage(getDriver()).getConvertedTax();
        double actualFinalTotal = new P04_CheckoutPage(getDriver()).getConvertedFinalPrice();
        double TotalPriceAndTotalTax = actualtotalPriceText + totalTaxText;

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

        Assert.assertEquals(actualtotalPriceText, ecpectedSumPrices);
        Assert.assertEquals(TotalPriceAndTotalTax, actualFinalTotal);
        Assert.assertTrue(getDriver().getPageSource().contains("THANK YOU FOR YOUR ORDER"), "Page source does not contain the expected text.");
    }


    @Test(priority = 3, description = "Add random items to carts")
    public void addItemsToCartRemoveOneAndProceed() throws InterruptedException {
        String firstName = DataGenerator.generateFirstName();
        String lastName = DataGenerator.generateLastName();

        new P01_LoginPage(getDriver()).
                enterUsername("standard_user").
                enterPassword("secret_sauce").
                clickOnLoginButton();
        Thread.sleep(2000);

        // Go to the products page and add random items to the cart
        new P02_Productspage(getDriver()).
                addRandomItemsToCarts().
                clickOnShoppingCarticon();  // Dynamically adds a random number of items to the cart
        Thread.sleep(2000);

        new P03_CartPage(getDriver()).
                clickOnCheckoutButton();
        new P04_CheckoutPage(getDriver()).
                enterFirstName(firstName).
                enterLastName(lastName).
                enterZipCode("12345").
                clickContinueButton().
                clickCancelButton();
        Thread.sleep(2000);

        new P02_Productspage(getDriver()).removeRandomItemFromCart()
                .clickOnShoppingCarticon();
        Thread.sleep(2000);

        new P03_CartPage(getDriver()).
                clickOnCheckoutButton();
        new P04_CheckoutPage(getDriver()).
                enterFirstName(firstName).
                enterLastName(lastName).
                enterZipCode("12345").
                clickContinueButton().
                fetchAndParsePrices().
                clickFinishButton();
        Thread.sleep(2000);

        double ecpectedSumPrices = new P02_Productspage(getDriver()).getTotalPrices();
        double actualtotalPriceText = new P04_CheckoutPage(getDriver()).getConvertedPrice();
        double totalTaxText = new P04_CheckoutPage(getDriver()).getConvertedTax();
        double actualFinalTotal = new P04_CheckoutPage(getDriver()).getConvertedFinalPrice();
        double TotalPriceAndTotalTax = actualtotalPriceText + totalTaxText;
        Thread.sleep(2000);

        Assert.assertEquals(actualtotalPriceText, ecpectedSumPrices);
        Assert.assertEquals(TotalPriceAndTotalTax, actualFinalTotal);
        Assert.assertTrue(getDriver().getPageSource().contains("THANK YOU FOR YOUR ORDER"), "Page source does not contain the expected text.");

    }
}
