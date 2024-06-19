package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class CheckoutTest extends BaseTest {

    String emptyFieldMessage;
    String checkoutPageMessage = "Checkout: Your Information";
    String overviewPageMessage = "Checkout: Overview";
    String completePageMessage = "Checkout: Complete!";
    String orderConfirmationMessage = "Thank you for your order!";
    String orderShippingMessage = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
    String checkoutURL = "https://www.saucedemo.com/checkout-step-one.html";
    String overviewURL = "https://www.saucedemo.com/checkout-step-two.html";
    String checkoutCompleteURL = "https://www.saucedemo.com/checkout-complete.html";


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
        Assert.assertEquals(actualUrl, checkoutURL);
        Assert.assertEquals(navigationPage.getTitle(), checkoutPageMessage);
        Assert.assertTrue(checkoutPage.cancelButton.isDisplayed());
        Assert.assertTrue(checkoutPage.continueButton.isDisplayed());
    }

    @Test
    public void afterFillingMandatoryUserIsRedirectedToCheckoutOverview() {
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, overviewURL);
        Assert.assertEquals(navigationPage.getTitle(), overviewPageMessage);
        Assert.assertTrue(checkoutPage.paymentInformationLabel.isDisplayed());
        Assert.assertTrue(checkoutPage.priceTotalLabel.isDisplayed());
    }

    @Test
    public void byFinishingOrderUserIsRedirectedToCheckoutCompletePage() {
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        checkoutPage.finishOrder();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(navigationPage.getTitle(), completePageMessage);
        Assert.assertEquals(actualUrl, checkoutCompleteURL);
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), orderConfirmationMessage);
        Assert.assertEquals(checkoutPage.getShippingMessage(), orderShippingMessage);
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
        emptyFieldMessage = "Error: First Name is required";
        checkoutPage.submitCheckoutInformation(emptyField, emptyField, "");
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), emptyFieldMessage);
    }

    @Test
    public void userCannotFinishOrderWithEmptyFirstNameField() {
        emptyFieldMessage = "Error: First Name is required";
        checkoutPage.submitCheckoutInformation(emptyField, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), emptyFieldMessage);
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test
    public void userCannotFinishOrderWithEmptyLastNameField() {
        emptyFieldMessage = "Error: Last Name is required";
        checkoutPage.submitCheckoutInformation(testData.name, emptyField, testData.postalCode);
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), emptyFieldMessage);
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test
    public void userCannotFinishOrderWithEmptyPostalCodeField() {
        emptyFieldMessage = "Error: Postal Code is required";
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, emptyField);
        checkoutPage.continueOrder();
        Assert.assertEquals(checkoutPage.getErrorText(), emptyFieldMessage);
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    public void addProducts() {
        addProductToCart(testData.sauceLabsBoltTShirt);
        addProductToCart(testData.tShirtRed);
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
    }
}
