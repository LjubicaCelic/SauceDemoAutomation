package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_list .inventory_item")
    public List<WebElement> listOfProducts;

    @FindBy(css = "button[class='btn btn_primary btn_small btn_inventory ']")
    public List<WebElement> addToCartButtons;

    @FindBy(css = "button[class='btn btn_secondary btn_small btn_inventory ']")
    public List<WebElement> removeFromCartButtons;

    @FindBy(css = "button[class='btn btn_secondary btn_small btn_inventory ']")
    public WebElement removeFromCartButton;

    @FindBy(className = "product_sort_container")
    public WebElement sort;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> productPrices;

    //--------------------------------------------------------------------------

    public void addAllProductsToCart() {
        for (WebElement element : addToCartButtons) {
            element.click();
        }
    }

    public void removeAllItemsFromCart() {
        for (WebElement element : removeFromCartButtons) {
            element.click();
        }
    }

    public void clickOnProduct(WebElement product) {
        product.findElement(By.className("inventory_item_name")).click();
    }

    public void addToCartButton(WebElement product) {
        product.findElement(By.className("btn_inventory")).click();
    }

    public void removeButton(WebElement product) {
        product.findElement(By.className("btn_secondary")).click();
    }

    public void addProductToCartByName(String itemName) {
        for (WebElement product : listOfProducts) {
            if (getProductName(product).equalsIgnoreCase(itemName)) {
                addToCartButton(product);
                break;
            }
        }
    }

    public void removeProductFromCartByName(String itemName) {
        for (WebElement product : listOfProducts) {
            if (getProductName(product).equalsIgnoreCase(itemName)) {
                removeButton(product);
                break;
            }
        }
    }

    public void navigateToProductPage(String name) {
        for (WebElement product : listOfProducts) {
            if (getProductName(product).equalsIgnoreCase(name)) {
                clickOnProduct(product);
                break;
            }
        }
    }

    //Methods for sorting products

    public void clickToSort() {
        sort.click();
    }

    public void sortBy(String option) {
        Select select = new Select(sort);
        select.selectByVisibleText(option);
    }

    public boolean sortedByPriceLowToHigh() {
        boolean sorted = true;
        for (int i = 0; i < productPrices.size() - 1; i++) {
            if (Double.parseDouble(productPrices.get(i).getText().substring(1)) > (Double.parseDouble(productPrices.get(i + 1).getText().substring(1)))) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public boolean sortedByPriceLHighToLow() {
        boolean sorted = true;
        for (int i = 0; i < productPrices.size() - 1; i++) {
            if (Double.parseDouble(productPrices.get(i).getText().substring(1)) < (Double.parseDouble(productPrices.get(i + 1).getText().substring(1)))) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }


    public boolean sortedByNameAtoZ() {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : listOfProducts) {
            productNames.add(product.getText());
        }
        boolean sorted = true;
        for (int i = 0; i < productNames.size() - 1; i++) {
            if (productNames.get(i).compareToIgnoreCase(productNames.get(i + 1)) > 0) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public boolean sortedByNameZtoA() {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : listOfProducts) {
            productNames.add(product.getText());
        }
        boolean sorted = true;
        for (int i = 0; i < productNames.size() - 1; i++) {
            if (productNames.get(i).compareToIgnoreCase(productNames.get(i + 1)) < 0) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

}
