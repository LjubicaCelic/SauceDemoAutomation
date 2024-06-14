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
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        inventoryPage.addAllItemsToCard();
        expectedNumberOfItemsInCart = ""+inventoryPage.listOfItems.size();
        actualNumberOfItemsInCart = inventoryPage.getNumberOfItemsInCart();
        Assert.assertEquals(actualNumberOfItemsInCart, expectedNumberOfItemsInCart);
    }
}
