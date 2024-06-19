package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NavigationPage extends BaseTest {

    public NavigationPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    public WebElement title;

    @FindBy(className = "app_logo")
    public WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;

    @FindBy(css = "a[class='bm-item menu-item']")
    public List<WebElement> sidebarElements;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(className = "shopping_cart_link")
    public WebElement numberOfProductsInCart;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;

    //-------------------------------------------------------------------

    public String getTitle() {
        return title.getText();
    }

    public void clickOnCart() {
        cart.click();
    }

    public void clickOnBurgerMenu() {
        burgerMenu.click();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    public void clickOnSidebarOption(String element) {
        for (WebElement webElement : sidebarElements) {
            if (webElement.getText().equalsIgnoreCase(element)) {
                webElement.click();
                break;
            }
        }
    }

    public String getNumberOfProductsInCart() {
        return numberOfProductsInCart.getText();
    }
}
