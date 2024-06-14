package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortTest extends BaseTest {
        String validUsername = "standard_user";
        String validPassword = "secret_sauce";

        @BeforeMethod
        public void pageSetUp() {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.saucedemo.com/");
        }

        @Test
        public void sort(){
            login();
            inventoryPage.clickToSort();
        }


}
