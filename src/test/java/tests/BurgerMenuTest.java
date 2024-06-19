package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BurgerMenuTest extends BaseTest {

    String actualUrl;
    String expectedAboutURL = ("https://saucelabs.com/");

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(validUsername, validPassword);
    }

    @Test
    public void userIsRedirectedToAllProductsPage() {
        inventoryPage.navigateToProductPage(testData.sauceLabsBackpack);
        navigationPage.clickOnBurgerMenu();
        waitForElementVisibility(navigationPage.sidebarElements.getFirst());
        navigationPage.clickOnSidebarOption("All Items");
        actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(inventoryPage.listOfProducts.getFirst().isDisplayed());
        Assert.assertEquals(actualUrl, inventoryURL);
    }

    @Test
    public void userIsRedirectedToAboutPage() {
        navigationPage.clickOnBurgerMenu();
        waitForElementVisibility(navigationPage.sidebarElements.getFirst());
        navigationPage.clickOnSidebarOption("About");
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedAboutURL);
    }

    @Test
    public void userIsLoggedOut() {
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnLogoutButton();
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void resetAppStateClearsCartItems() {
        inventoryPage.addAllProductsToCart();
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        testData.expectedNumberOfItemsInCart = "" + inventoryPage.listOfProducts.size();
        Assert.assertEquals(testData.actualNumberOfItemsInCart, testData.expectedNumberOfItemsInCart);
        navigationPage.clickOnBurgerMenu();
        waitForElementVisibility(navigationPage.sidebarElements.getFirst());
        navigationPage.clickOnSidebarOption("Reset App State");
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertEquals(testData.actualNumberOfItemsInCart, emptyField);
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
