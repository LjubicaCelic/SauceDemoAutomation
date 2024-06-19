package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage extends BaseTest {
    public FooterPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "footer")
    public WebElement footer;

    @FindBy(linkText = "Twitter")
    public WebElement x;

    @FindBy(linkText = "Facebook")
    public WebElement facebook;

    @FindBy(linkText = "LinkedIn")
    public WebElement linkedIn;

    //---------------------------------------------------------------------------------

    public void xLink() {
        x.click();
    }

    public void facebookLink() {
        facebook.click();
    }

    public void linkedInLink() {
        linkedIn.click();
    }
}
