package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginPage;

import static drivers.DriverHolder.getDriver;

public class TC01_Login extends testBase {

    @Test(priority = 1, description = "Login with valid email and password")
    public void loginWithValidCredintial_P() {

         new P01_LoginPage(getDriver())
                 .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickOnLoginButton();

        // Add an assertion to verify successful login
        String expectedUrl = "https://www.saucedemo.com/v1/inventory.html"; // Replace with the URL after successful login
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedUrl, "Login was not successful.");
    }
}