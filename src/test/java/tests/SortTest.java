package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(validUsername, validPassword);
    }

    @Test
    public void userCanSortProductsByPriceLowToHigh() {
        sort("Price (low to high)");
        Assert.assertTrue(inventoryPage.sortedByPriceLowToHigh());
    }

    @Test
    public void userCanSortProductsByPriceHighToLow() {
        sort("Price (high to low)");
        Assert.assertTrue(inventoryPage.sortedByPriceLHighToLow());
    }

    @Test
    public void userCanSortProductsByNameAToZ() {
        sort("Name (A to Z)");
        Assert.assertTrue(inventoryPage.sortedByNameAtoZ());
    }

    @Test
    public void userCanSortProductsByNameZToA() {
        sort("Name (Z to A)");
        Assert.assertTrue(inventoryPage.sortedByNameZtoA());
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
