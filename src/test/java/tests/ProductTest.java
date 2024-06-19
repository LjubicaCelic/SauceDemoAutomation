package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(validUsername, validPassword);
    }

    @Test
    public void isValidDescriptionAndPriceForSauceLabsBackpack() {
        isValidProduct(testData.sauceLabsBackpack);
    }

    @Test
    public void isValidDescriptionAndPriceForSauceLabsBikeLight() {
        isValidProduct(testData.sauceLabsBikeLight);
    }

    @Test
    public void isValidDescriptionAndPriceForSauceLabsBoltTShirt() {
        isValidProduct(testData.sauceLabsBoltTShirt);
    }

    @Test
    public void isValidDescriptionAndPriceForSauceLabsFleeceJacket() {
        isValidProduct(testData.sauceLabsFleeceJacket);
    }

    @Test
    public void isValidDescriptionAndPriceForSauceLabsOnesiet() {
        isValidProduct(testData.sauceLabsOnesie);
    }

    @Test
    public void isValidDescriptionAndPriceForTShirtRed() {
        isValidProduct(testData.tShirtRed);
    }
    public void isValidProduct(String product) {
        inventoryPage.navigateToProductPage(product);
        Assert.assertEquals(productPage.getProductName(), product);
        Assert.assertEquals(productPage.getProductDescription(), productPage.getActualDescription(product));
        Assert.assertEquals(productPage.getProductPrice(), productPage.getActualPrice(product));
    }
}
