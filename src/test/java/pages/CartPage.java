package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_button")
    public List<WebElement> removeButtons;

    @FindBy(css = "button[class='btn btn_secondary btn_small btn_inventory ']")
    public WebElement removeFromCartButton;

    @FindBy(css = ".cart_list .cart_item")
    public List<WebElement> products;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    //--------------------------------------------------------------------

    public void checkout() {
        checkoutButton.click();
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        if (!products.isEmpty()) {
            for (WebElement product : products) {
                productNames.add(getProductName(product));
            }
        }
        return productNames;
    }

    public List<String> getPrices() {
        List<String> productPrices = new ArrayList<>();
        if (!products.isEmpty()) {
            for (WebElement product : products) {
                productPrices.add(getProductPrice(product));
            }
        }
        return productPrices;
    }

    public WebElement getProduct(String productName) {
        for (WebElement product : products) {
            if (getProductName(product).equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public void removeAllItemsFromCart() {
        for (WebElement element : removeButtons) {
            element.click();
        }
    }
}
