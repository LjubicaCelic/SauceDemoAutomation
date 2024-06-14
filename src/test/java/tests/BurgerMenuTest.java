package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BurgerMenuTest extends BaseTest {
    String expectedInventoryURL = ("https://www.saucedemo.com/inventory.html");
    String actualInventoryURL;
    String expectedAboutURL = ("https://saucelabs.com/");
    String actualAboutURL;
    String expectedNumberOfItemsInCart;
    String actualNumberOfItemsInCart;

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void allItems() throws InterruptedException {
        login();
        inventoryPage.goToProduct("Sauce Labs Backpack");
        inventoryPage.clickOnBurgerMenu();
        waitForElementVisibility(inventoryPage.sidebarElements.getFirst());
        inventoryPage.goTo("All Items");
        actualInventoryURL = driver.getCurrentUrl();
        Assert.assertTrue(inventoryPage.listOfProducts.getFirst().isDisplayed());
        Assert.assertEquals(actualInventoryURL, expectedInventoryURL);
    }

    @Test
    public void about() {
        login();
        inventoryPage.clickOnBurgerMenu();
        waitForElementVisibility(inventoryPage.sidebarElements.getFirst());
        inventoryPage.goTo("About");
        actualAboutURL = driver.getCurrentUrl();
        Assert.assertEquals(actualAboutURL, expectedAboutURL);
    }

    @Test
    public void resetAppState() {
        login();
        inventoryPage.addAllProductsToCard();
        actualNumberOfItemsInCart = inventoryPage.getNumberOfItemsInCart();
        expectedNumberOfItemsInCart = "" + inventoryPage.listOfProducts.size();
        Assert.assertEquals(actualNumberOfItemsInCart,expectedNumberOfItemsInCart);
        inventoryPage.clickOnBurgerMenu();
        waitForElementVisibility(inventoryPage.sidebarElements.getFirst());
        inventoryPage.goTo("Reset App State");
        expectedNumberOfItemsInCart = "";
        actualNumberOfItemsInCart = inventoryPage.getNumberOfItemsInCart();
        Assert.assertEquals(actualNumberOfItemsInCart, expectedNumberOfItemsInCart);
    }

}
