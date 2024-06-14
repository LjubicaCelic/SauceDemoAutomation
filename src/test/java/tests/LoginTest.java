package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    String validUsername = "standard_user";
    String invalidUsername = "standard_user123";
    String lockedOutUser = "locked_out_user";
    String problemUser = "problem_user";
    String performanceGlitchUser = "performance_glitch_user";
    String errorUser = "error_user";
    String visualUser = "visual_user";

    String validPassword = "secret_sauce";
    String invalidPassword = "Secret_sauce";
    String expectedURL = "https://www.saucedemo.com/inventory.html";
    String actualURL;
    String actualErrorMessage = "";
    String invalidUsernameOrPasswordMessage = "Epic sadface: Username and password do not match any user in this service";

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void standardUserCanLogin() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertTrue(inventoryPage.cart.isDisplayed());
        Assert.assertTrue(inventoryPage.burgerMenu.isDisplayed());
        Assert.assertEquals(inventoryPage.pageTitle.getText(), "Swag Labs");
    }

    @Test
    public void userCannotLoginUsingInvalidPassword() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickLoginButton();
        actualURL = driver.getCurrentUrl();
        actualErrorMessage = loginPage.getErrorText();
        System.out.println(loginPage.getErrorText());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(actualErrorMessage, invalidUsernameOrPasswordMessage);
    }

    @Test
    public void userCannotLoginUsingInvalidUsername() {
        loginPage.enterUsername(invalidUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        actualURL = driver.getCurrentUrl();
        actualErrorMessage = loginPage.getErrorText();
        System.out.println(loginPage.getErrorText());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(actualErrorMessage, invalidUsernameOrPasswordMessage);
    }

}
