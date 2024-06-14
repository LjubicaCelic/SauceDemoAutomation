package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest extends BaseTest {
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    String expectedNumberOfItemsInCart;
    String actualNumberOfItemsInCart;

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void addAllItemsToCart() {
        login();
        inventoryPage.addAllProductsToCard();
        expectedNumberOfItemsInCart = ""+inventoryPage.listOfProducts.size();
        actualNumberOfItemsInCart = inventoryPage.getNumberOfItemsInCart();
        Assert.assertEquals(actualNumberOfItemsInCart, expectedNumberOfItemsInCart);
    }
    @Test
    public void addItemsToCartById() {
        login();
        inventoryPage.addItemByIdToCard(1);
        actualNumberOfItemsInCart = inventoryPage.getNumberOfItemsInCart();
    }
    @Test
    public void addItemsToCartByName() {
        login();
        inventoryPage.addProductToCartByName("Sauce Labs Bike Light");
        actualNumberOfItemsInCart = inventoryPage.getNumberOfItemsInCart();
    }

}
