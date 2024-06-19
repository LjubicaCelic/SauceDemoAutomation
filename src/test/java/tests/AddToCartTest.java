package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(validUsername, validPassword);
        resetAppState();
    }

    @Test
    public void userCanAddProductToCartByName() {
        addProductToCart(testData.sauceLabsOnesie);
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        testData.expectedNumberOfItemsInCart = "1";
        Assert.assertEquals(testData.actualNumberOfItemsInCart, testData.expectedNumberOfItemsInCart);
        Assert.assertTrue(inventoryPage.removeFromCartButton.isDisplayed());
    }

    @Test
    public void userCanAddAllProductsToCart() {
        inventoryPage.addAllProductsToCart();
        testData.expectedNumberOfItemsInCart = "" + inventoryPage.listOfProducts.size();
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertEquals(testData.actualNumberOfItemsInCart, testData.expectedNumberOfItemsInCart);
    }

    @Test
    public void addedProductsAreInCart() {
        addProductToCart(testData.sauceLabsBikeLight);
        addProductToCart(testData.sauceLabsBackpack);
        addProductToCart(testData.sauceLabsFleeceJacket);
        goToCart();
        Assert.assertTrue(isProductInCart(testData.sauceLabsBikeLight)&&isPriceCorrect(productPage.getActualPrice(testData.sauceLabsBikeLight),0));
        Assert.assertTrue(isProductInCart(testData.sauceLabsBackpack)&&isPriceCorrect(productPage.getActualPrice(testData.sauceLabsBackpack),1));
        Assert.assertTrue(isProductInCart(testData.sauceLabsFleeceJacket)&&isPriceCorrect(productPage.getActualPrice(testData.sauceLabsFleeceJacket),2));
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
