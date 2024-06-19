package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BaseTest {

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.inventory_details_name.large_size[data-test='inventory-item-name']")
    public WebElement productName;

    @FindBy(css = "div.inventory_details_desc.large_size[data-test='inventory-item-desc']")
    public WebElement productDescription;

    @FindBy(className = "inventory_details_price")
    public WebElement productPrice;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    //----------------------------------------------------------------------------------------------------------

    public String getProductName() {
        return productName.getText();
    }

    public String getProductDescription() {
        return productDescription.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void clickToGoBackToProducts(){
        backToProductsButton.click();
    }

    public String getActualDescription(String productName) {
        String description = "";
        switch (productName) {
            case "Sauce Labs Backpack":
                description = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
                break;
            case "Sauce Labs Bike Light":
                description = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";
                break;
            case "Sauce Labs Bolt T-Shirt":
                description = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
                break;
            case "Sauce Labs Fleece Jacket":
                description = "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.";
                break;
            case "Sauce Labs Onesie":
                description = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.";
                break;
            case "Test.allTheThings() T-Shirt (Red)":
                description = "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.";
                break;
            default:
                System.out.println(productName + "is unknown!");
        }
        return description;
    }

    public String getActualPrice(String productName) {
        String price = "";
        switch (productName) {
            case "Sauce Labs Backpack":
                price = "$29.99";
                break;
            case "Sauce Labs Bike Light":
                price = "$9.99";
                break;
            case "Sauce Labs Bolt T-Shirt":
                price = "$15.99";
                break;
            case "Sauce Labs Fleece Jacket":
                price = "$49.99";
                break;
            case "Sauce Labs Onesie":
                price = "$7.99";
                break;
            case "Test.allTheThings() T-Shirt (Red)":
                price = "$15.99";
                break;
            default:
                System.out.println(productName + "is unknown!");
        }
        return price;
    }
}


