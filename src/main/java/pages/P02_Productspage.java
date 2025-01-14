package pages;

import Utils.DataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class P02_Productspage extends Page_Base {

    // Define constructor to set the driver
    public P02_Productspage(WebDriver driver) {
        super(driver);
    }

    // Define the "Add to Cart" button locator
    private final By ADD_TO_CART_BUTTON = By.xpath("//button[text()='ADD TO CART']");
    private final By REMOVE_FROM_CART_BUTTON = By.xpath("//button[text()='REMOVE']");


    private final By ITEM_PRICE = By.xpath("//div[@class=\"inventory_item_price\"] ");
    private final By SHOPPING_CART_ICON = By.id("shopping_cart_container");


    public double getTotalPrices() {
        return totalPrices;
    }

    private double totalPrices = 0;



    public P02_Productspage clickOnShoppingCarticon(){
        driver.findElement(SHOPPING_CART_ICON).click();
        return this;
    }

//    public P02_Productspage clickOnRemoveButton(){
//        driver.findElement(REMOVE_FROM_CART_BUTTON).click();
//        return this;
//    }

    // Method to click on random "Add to Cart" buttons dynamically based on available products

    public P02_Productspage addRandomItemsToCarts() {
        // Find all "Add to Cart" buttons
        List<WebElement> addToCartButtons = driver.findElements(ADD_TO_CART_BUTTON);
        List<WebElement> itemsPricesList = driver.findElements(ITEM_PRICE);

        // Initialize random index generator
        DataGenerator.initializeRandomIndexGenerator(addToCartButtons.size());

        // Generate a random number of items to add to the cart (between 1 and the number of products)
        int itemsToAdd = new Random().nextInt(addToCartButtons.size()) + 1;  // Ensure at least 1 item

        System.out.println("Adding " + itemsToAdd + " items to the cart.");
        // Click random buttons based on the generated count
        for (int i = 0; i < itemsToAdd; i++) {
            int randomIndex = DataGenerator.getRandomIndex();  // Get a unique random index
            addToCartButtons.get(randomIndex).click();  // Click the "Add to Cart" button at that index
         String price =   itemsPricesList.get(randomIndex).getText().split("\\$")[1];  // Click the "Add to Cart" button at that index
            double covertedPrice = Double.parseDouble(price);  // Convert to double for precision

            System.out.println("price for item number"+ i + "  = " + price );
            totalPrices+= covertedPrice;
            System.out.println("Clicked on button at index: " + randomIndex);
        }
        System.out.println("total after sum ==> " + totalPrices);

        return this;
    }

    public P02_Productspage removeRandomItemFromCart() {
        // Find all "Remove" buttons
        List<WebElement> removeButtons = driver.findElements(REMOVE_FROM_CART_BUTTON);
        List<WebElement> itemsPricesList = driver.findElements(ITEM_PRICE);

        if (removeButtons.isEmpty()) {
            System.out.println("No items in the cart to remove.");
            return this;
        }

        // Initialize random index generator
        DataGenerator.initializeRandomIndexGenerator(removeButtons.size());

        // Get a random index and remove the item
        int randomIndex = DataGenerator.getRandomIndex();
        String priceText = itemsPricesList.get(randomIndex).getText().split("\\$")[1]; // Extract the price
        double price = Double.parseDouble(priceText); // Convert the price to a double

        // Deduct the price from totalPrices
        totalPrices -= price;
        System.out.println("Removed item price: $" + price);
        System.out.println("Total price after removal: $" + totalPrices);

        // Click on the "Remove" button
        removeButtons.get(randomIndex).click();
        System.out.println("Removed item at index: " + randomIndex);

        return this;
    }

}
