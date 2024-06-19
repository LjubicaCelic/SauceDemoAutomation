package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BaseTest {

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameInput;

    @FindBy(id = "last-name")
    public WebElement lastNameInput;

    @FindBy(id = "postal-code")
    public WebElement postalCodeInput;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;

    //---STEP-TWO---

    @FindBy(css = ".cart_list .cart_item")
    public List<WebElement> orderList;

    @FindBy(css = "div.summary_info_label[data-test='payment-info-label']")
    public WebElement paymentInformationLabel;

    @FindBy(css = "div.summary_info_label[data-test='shipping-info-value']")
    public WebElement shippingInformationLabel;

    @FindBy(css = "div.summary_info_label[data-test='total-info-label']")
    public WebElement priceTotalLabel;

    @FindBy(id = "finish")
    public WebElement finishButton;

    //--Order Confirmation

    @FindBy(className = "complete-header")
    public WebElement confirmationMessage;

    @FindBy(className = "complete-text")
    public WebElement shippingMessage;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    //------------------------------------------------------------

    public void submitCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
    }

    public void cancelOrder() {
        cancelButton.click();
    }

    public void continueOrder() {
        continueButton.click();
    }

    public String getErrorText() {
        return errorMessage.getText();
    }

    //---STEP-TWO---

    public List<String> getOrderedProducts() {
        List<String> products = new ArrayList<>();
        for (WebElement product : orderList) {
            products.add(product.getText());
        }
        return products;
    }

    public String getPaymentInformationLabel() {
        return paymentInformationLabel.getText();
    }

    public String getShippingInformationLabel() {
        return shippingInformationLabel.getText();
    }

    public String getPriceTotalLabel() {
        return priceTotalLabel.getText();
    }

    public void finishOrder() {
        finishButton.click();
    }

    //-----Complete-----

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public String getShippingMessage() {
        return shippingMessage.getText();
    }

    public void backToProducts() {
        backHomeButton.click();
    }
}
