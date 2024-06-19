package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RemoveFromCartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(validUsername, validPassword);
        resetAppState();
    }

    @Test
    public void userCanRemoveProductFromCart(){
        addProductToCart(testData.sauceLabsBikeLight);
        addProductToCart(testData.sauceLabsBackpack);
        inventoryPage.removeProductFromCartByName(testData.sauceLabsBikeLight);
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        goToCart();
        Assert.assertTrue(cartPage.getProduct(testData.sauceLabsBackpack).isDisplayed());
        Assert.assertEquals(testData.actualNumberOfItemsInCart,"1");
        Assert.assertFalse(isElementDisplayed(cartPage.getProduct(testData.sauceLabsBikeLight)));
    }

    @Test
    public void userCanRemoveALlProductsFromCartPage() {
        addProductToCart(testData.sauceLabsFleeceJacket);
        addProductToCart(testData.sauceLabsBackpack);
        goToCart();
        cartPage.removeAllItemsFromCart();
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertFalse(isElementDisplayed(cartPage.removeFromCartButton));
        Assert.assertTrue(cartPage.continueShoppingButton.isDisplayed());
        Assert.assertTrue(cartPage.checkoutButton.isDisplayed());
        Assert.assertEquals(testData.actualNumberOfItemsInCart,emptyField);
    }

    @Test
    public void userCanRemoveProductFromTheCartByName(){
        addProductToCart(testData.sauceLabsBackpack);
        addProductToCart(testData.sauceLabsFleeceJacket);
        inventoryPage.removeProductFromCartByName(testData.sauceLabsFleeceJacket);
        goToCart();
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertEquals(testData.actualNumberOfItemsInCart,("1"));
        Assert.assertTrue(cartPage.getProduct(testData.sauceLabsBackpack).isDisplayed());
        Assert.assertFalse(isElementDisplayed(cartPage.getProduct(testData.sauceLabsFleeceJacket)));
    }

    @Test
    public void removeAllProductsInventoryPage() {
        inventoryPage.addAllProductsToCart();
        inventoryPage.removeAllItemsFromCart();
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertEquals(testData.actualNumberOfItemsInCart,emptyField);
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
