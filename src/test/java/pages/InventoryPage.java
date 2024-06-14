package pages;

import base.BaseTest;
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

    @FindBy(className = "bm-item-list")
    public List<WebElement> sidebarElements;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(className = "inventory_item")
    public List<WebElement> listOfItems;

    @FindBy(css = "button[class='btn btn_primary btn_small btn_inventory ']")
    public List<WebElement> addToCartButttons;

    @FindBy(css = "button[class='btn btn_secondary btn_small btn_inventory ']")
    public List<WebElement> removeFromCartButtons;

    @FindBy(className = "shopping_cart_link")
    public WebElement numberOfItemsInCart;

    //-------------------------------------------------------------------

    public void addAllItemsToCard(){
        for (WebElement element: addToCartButttons){
            element.click();
        }
    }

    public void addItemToCard(){
        for (WebElement element: addToCartButttons){
            element.click();
        }
    }

    public void removeAllItemsFromCard(){
        for (WebElement element: removeFromCartButtons){
            element.click();
        }
    }

    public boolean listOfItemsIsNotEmpty(){
        return !listOfItems.isEmpty();
    }

    public void goToCart() {
        cart.click();
    }

    public void clickOnBurgerMenu() {
        burgerMenu.click();
    }

    public boolean logoutButtonIsDisplayed() {
        return logoutButton.isDisplayed();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }
    public void goTo(String element) {
        for (WebElement webElement : sidebarElements) {
            if (webElement.getText().equalsIgnoreCase(element)) ;
            webElement.click();
        }
    }

    public String getNumberOfItemsInCart(){
        return numberOfItemsInCart.getText();
    }
}
