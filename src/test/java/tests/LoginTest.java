package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    String invalidUsername = "standard_user123";
    String lockedOutUser = "locked_out_user";
    String invalidPassword = "Secret_sauce";
    String expectedURL = "https://www.saucedemo.com/inventory.html";
    String actualErrorMessage;
    String expectedErrorMessage;

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void userCanLoginWithValidInputs() {
        login(validUsername, validPassword);
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedURL);
        Assert.assertTrue(navigationPage.cart.isDisplayed());
        Assert.assertTrue(navigationPage.burgerMenu.isDisplayed());
        Assert.assertEquals(navigationPage.pageTitle.getText(), "Swag Labs");
    }

    @Test
    public void userCannotLoginUsingInvalidPassword() {
        login(validUsername, invalidPassword);
        actualUrl = driver.getCurrentUrl();
        actualErrorMessage = loginPage.getErrorText();
        expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void userCannotLoginUsingInvalidUsername() {
        login(invalidUsername, validPassword);
        actualUrl = driver.getCurrentUrl();
        actualErrorMessage = loginPage.getErrorText();
        expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void userCannotLoginEmptyFields() {
        login(emptyField, emptyField);
        expectedErrorMessage = "Epic sadface: Username is required";
        actualErrorMessage = loginPage.getErrorText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginEmptyUsernameField() {
        login(emptyField, validPassword);
        expectedErrorMessage = "Epic sadface: Username is required";
        actualErrorMessage = loginPage.getErrorText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginEmptyPasswordField() {
        login(validUsername, emptyField);
        expectedErrorMessage = "Epic sadface: Password is required";
        actualErrorMessage = loginPage.getErrorText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void lockedOutUserLoginErrorDisplayed() {
        login(lockedOutUser, validPassword);
        actualErrorMessage = loginPage.getErrorText();
        expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
