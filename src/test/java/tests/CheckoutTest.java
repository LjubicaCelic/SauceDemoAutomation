package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest extends BaseTest {


    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(validUsername, validPassword);
        resetAppState();
        addProducts();
        goToCart();
        cartPage.checkout();
    }

    @Test
    public void userIsOnCheckoutPage() {
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, testData.checkoutURL);
        Assert.assertEquals(navigationPage.getTitle(), testData.checkoutPageMessage);
        Assert.assertTrue(checkoutPage.cancelButton.isDisplayed());
        Assert.assertTrue(checkoutPage.continueButton.isDisplayed());
    }

    @Test
    public void afterFillingMandatoryUserIsRedirectedToCheckoutOverview() {
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, testData.overviewURL);
        Assert.assertEquals(navigationPage.getTitle(), testData.overviewPageMessage);
        Assert.assertTrue(checkoutPage.paymentInformationLabel.isDisplayed());
        Assert.assertTrue(checkoutPage.priceTotalLabel.isDisplayed());
    }

    @Test
    public void userIsRedirectedToCheckoutCompletePageAfterOrderCompletion() {
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        checkoutPage.finishOrder();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(navigationPage.getTitle(), testData.completePageMessage);
        Assert.assertEquals(actualUrl, testData.checkoutCompleteURL);
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), testData.orderConfirmationMessage);
        Assert.assertEquals(checkoutPage.getShippingMessage(), testData.orderShippingMessage);
    }

    @Test
    public void afterSuccessfulOrderUserIsRedirectedToInventoryPage() {
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        checkoutPage.finishOrder();
        checkoutPage.backToProducts();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, inventoryURL);
        Assert.assertEquals(navigationPage.getTitle(), "Products");
    }

    @Test
    public void userCannotFinishOrderWithEmptyFields() {
        testData.emptyFieldMessage = "Error: First Name is required";
        checkoutPage.submitCheckoutInformation(emptyField, emptyField, "");
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), testData.emptyFieldMessage);
    }

    @Test
    public void userCannotFinishOrderWithEmptyFirstNameField() {
        testData.emptyFieldMessage = "Error: First Name is required";
        checkoutPage.submitCheckoutInformation(emptyField, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), testData.emptyFieldMessage);
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test
    public void userCannotFinishOrderWithEmptyLastNameField() {
        testData.emptyFieldMessage = "Error: Last Name is required";
        checkoutPage.submitCheckoutInformation(testData.name, emptyField, testData.postalCode);
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), testData.emptyFieldMessage);
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test
    public void userCannotFinishOrderWithEmptyPostalCodeField() {
        testData.emptyFieldMessage = "Error: Postal Code is required";
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, emptyField);
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), testData.emptyFieldMessage);
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    public void addProducts() {
        addProductToCart(testData.sauceLabsBoltTShirt);
        addProductToCart(testData.tShirtRed);
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
