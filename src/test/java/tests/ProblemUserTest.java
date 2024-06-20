package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProblemUserTest extends BaseTest {

    /*When we log in with "problemUser" username and valid password, some functionalities are not working as well as
 when we log in as standard user, which is expected.
 In this test suit I expect that there will be some mismatches because this user should have that problems!
  */
    String problemUser = "problem_user";
    String expectedAboutURL = ("https://saucelabs.com/");
    char lastCharacter = testData.lastName.charAt(testData.lastName.length() - 1);

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(problemUser, validPassword);
        resetAppState();
    }

    //When user types something in the "Last name" field, instead that typed text is shown in appropriate field
    //last character is shown in "First name" field. User cannot continue order as expected.
    @Test
    public void lastNameCharacterCopiedToFirstNameFieldOnCheckoutPageWithFailedOrder() {
        addProductToCart(testData.sauceLabsBackpack);
        goToCart();
        cartPage.checkout();
        checkoutPage.submitCheckoutInformation(testData.name, testData.lastName, testData.postalCode);
        checkoutPage.continueOrder();
        String cssValue = checkoutPage.firstNameInput.getAttribute("value");
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
        Assert.assertEquals(cssValue, String.valueOf(lastCharacter));
    }

    @Test
    public void addToCartButtonsAreNotClickable() {
        addProductToCart(testData.tShirtRed);
        addProductToCart(testData.sauceLabsBoltTShirt);
        addProductToCart(testData.sauceLabsFleeceJacket);
        goToCart();
        testData.expectedNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertEquals(testData.expectedNumberOfItemsInCart, emptyField);
    }

    @Test
    public void userCannotSortProducts() {
        sort("Name (Z to A)");
        Assert.assertFalse(inventoryPage.sortedByNameZtoA());
        sort("Price (high to low)");
        Assert.assertFalse(inventoryPage.sortedByPriceLHighToLow());
        sort("Price (low to high)");
        Assert.assertFalse(inventoryPage.sortedByPriceLowToHigh());
    }

    @Test
    public void incorrectProductLinkForSauceLabsBackpack() {
        inventoryPage.navigateToProductPage(testData.sauceLabsBackpack);
        isIncorrectProductLink(testData.sauceLabsBackpack);
    }

    @Test
    public void incorrectProductLinkForSauceLabBikeLight() {
        inventoryPage.navigateToProductPage(testData.sauceLabsBikeLight);
        isIncorrectProductLink(testData.sauceLabsBikeLight);
    }

    @Test
    public void incorrectProductLinkForSauceLabsBoltTShirt() {
        inventoryPage.navigateToProductPage(testData.sauceLabsBoltTShirt);
        isIncorrectProductLink(testData.sauceLabsBoltTShirt);
    }

    @Test
    public void incorrectProductLinkForSauceLabsFleeceJacket() {
        inventoryPage.navigateToProductPage(testData.sauceLabsFleeceJacket);
        isIncorrectProductLink(testData.sauceLabsFleeceJacket);
    }

    @Test
    public void incorrectProductLinkForSauceLabsOnesie() {
        inventoryPage.navigateToProductPage(testData.sauceLabsOnesie);
        isIncorrectProductLink(testData.sauceLabsOnesie);
    }

    @Test
    public void incorrectProductLinkFortTShirtRed() {
        inventoryPage.navigateToProductPage(testData.tShirtRed);
        isIncorrectProductLink(testData.tShirtRed);
    }

    @Test
    public void userCannotBeRedirectedToAboutPage() {
        navigationPage.clickOnBurgerMenu();
        waitForElementVisibility(navigationPage.sidebarElements.getFirst());
        navigationPage.clickOnSidebarOption("About");
        actualUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(actualUrl, expectedAboutURL);
    }

    @Test
    public void userCannotAddAllProductsToCart() {
        inventoryPage.addAllProductsToCart();
        testData.expectedNumberOfItemsInCart = "" + inventoryPage.listOfProducts.size();
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertNotEquals(testData.actualNumberOfItemsInCart, testData.expectedNumberOfItemsInCart);
    }

    @Test
    public void someRemoveButtonsAreNotClickable() {
        inventoryPage.addAllProductsToCart();
        inventoryPage.removeAllItemsFromCart();
        testData.actualNumberOfItemsInCart = navigationPage.getNumberOfProductsInCart();
        Assert.assertNotEquals(testData.actualNumberOfItemsInCart, (emptyField));
    }

    public void isIncorrectProductLink(String product) {
        Assert.assertNotEquals(productPage.getProductName(), product);
        Assert.assertNotEquals(productPage.getProductDescription(), productPage.getActualDescription(product));
        Assert.assertNotEquals(productPage.getProductPrice(), productPage.getActualPrice(product));
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
