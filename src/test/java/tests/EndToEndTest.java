package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class EndToEndTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void endToEndPurchaseFlow(){
        login(validUsername,validPassword);
        addProductToCart(testData.sauceLabsBackpack);
        addProductToCart(testData.sauceLabsOnesie);
        addProductToCart(testData.sauceLabsFleeceJacket);
        goToCart();
        cartPage.checkout();
        checkoutPage.submitCheckoutInformation(testData.name,testData.lastName,testData.postalCode);
        checkoutPage.continueOrder();
        checkoutPage.finishOrder();
        checkoutPage.backToProducts();
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnLogoutButton();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,loginPageUrl);
        Assert.assertTrue(isElementDisplayed(loginPage.usernameField));
        Assert.assertTrue(isElementDisplayed(loginPage.passwordField));
        Assert.assertTrue(isElementDisplayed(loginPage.loginButton));
    }
}
