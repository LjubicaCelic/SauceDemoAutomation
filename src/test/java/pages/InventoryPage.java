package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    public WebElement pageTitle;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;

    @FindBy(css = "a[class='bm-item menu-item']")
    public List<WebElement> sidebarElements;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> listOfProducts;

    @FindBy(css = "button[class='btn btn_primary btn_small btn_inventory ']")
    public List<WebElement> addToCartButttons;

    @FindBy(css = "button[class='btn btn_secondary btn_small btn_inventory ']")
    public List<WebElement> removeFromCartButtons;

    @FindBy(className = "shopping_cart_link")
    public WebElement numberOfItemsInCart;

    @FindBy(className = "product_sort_container")
    public WebElement sort;

    @FindBy(className = "inventory_item_name")
    public WebElement productName;

    //-------------------------------------------------------------------

    public void addAllProductsToCard() {
        for (WebElement element : addToCartButttons) {
            element.click();
        }
    }

    public void addItemByIdToCard(int id) {
        for (int i = 0; i < addToCartButttons.size(); i++) {
            if (i == id) {
                addToCartButttons.get(i).click();
            }
        }
    }

    public void removeAllItemsFromCard() {
        for (WebElement element : removeFromCartButtons) {
            element.click();
        }
    }

    public void goToCart() {
        cart.click();
    }

    public void clickOnBurgerMenu() {
        burgerMenu.click();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    public void goTo(String element) {
        for (WebElement webElement : sidebarElements) {
            if (webElement.getText().equalsIgnoreCase(element)) {
                webElement.click();
                break;
            }
        }
    }

    public void addProductToCartByName(String itemName) {
        for (WebElement product : listOfProducts) {
            if (productName.getText().equalsIgnoreCase(itemName)) {
                WebElement addToCartButton = product.findElement(By.cssSelector("button[class='btn btn_primary btn_small btn_inventory ']"));
                addToCartButton.click();
                break;
            }
        }
    }

    public void goToProduct(String name) {
        for (WebElement product : listOfProducts) {
            if (product.getText().equalsIgnoreCase(name)) {
                product.click();
                break;
            }
        }
    }

        public String getNumberOfItemsInCart () {
            return numberOfItemsInCart.getText();
        }

        public void clickToSort () {
            sort.click();
        }
    }
