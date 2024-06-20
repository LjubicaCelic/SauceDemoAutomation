package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;

import java.time.Duration;
import java.util.List;

public class BaseTest {

    public static WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public NavigationPage navigationPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public FooterPage footerPage;
    public ProductPage productPage;
    public TestData testData = new TestData();

    public String emptyField = "";
    public String validUsername = "standard_user";
    public String validPassword = "secret_sauce";
    public String loginPageUrl = "https://www.saucedemo.com/";
    public String inventoryURL = "https://www.saucedemo.com/inventory.html";
    public String actualUrl;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage();
        navigationPage = new NavigationPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        footerPage = new FooterPage();
        productPage = new ProductPage();

    }


    //---------------------HelperMethods------------------------------------------


    public void login(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    public String getProductName(WebElement product) {
        return product.findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductPrice(WebElement product) {
        return product.findElement(By.className("inventory_item_price")).getText();
    }

    public void addProductToCart(String nameOfProduct) {
        inventoryPage.addProductToCartByName(nameOfProduct);
    }

    public boolean isProductInCart(String productName) {
        return cartPage.getProductNames().contains(productName);
    }

    public boolean isPriceCorrect(String productPrice, int index) {
        return cartPage.getPrices().get(index).equals(productPrice);
    }

    public void goToCart() {
        navigationPage.clickOnCart();
    }

    public void resetAppState() {
        navigationPage.clickOnBurgerMenu();
        waitForElementVisibility(navigationPage.sidebarElements.getFirst());
        navigationPage.clickOnSidebarOption("Reset App State");
        driver.navigate().refresh();
    }

    public void sort(String option) {
        inventoryPage.clickToSort();
        inventoryPage.sortBy(option);
    }

    public void scrollIntoElementView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForElementVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAllElementsVisibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void switchToNewWindow() {
        String mainWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        boolean elementIsDisplayed = false;
        try {
            elementIsDisplayed = element.isDisplayed();
        } catch (Exception ignored) {
        }
        return elementIsDisplayed;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
