package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RemoveFromCartTest extends BaseTest {
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
    public void removeAllItemsToCart() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        inventoryPage.addAllProductsToCard();
        inventoryPage.removeAllItemsFromCard();
        expectedNumberOfItemsInCart = "";
        actualNumberOfItemsInCart = inventoryPage.getNumberOfItemsInCart();
        Assert.assertTrue(actualNumberOfItemsInCart.isEmpty());
    }
}
