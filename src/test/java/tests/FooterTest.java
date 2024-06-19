package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FooterTest extends BaseTest {

    String originalWindow;

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        login(validUsername, validPassword);
        //scrollIntoElementView(footerPage.footer);
        originalWindow = driver.getWindowHandle();
    }

    @Test
    public void userIsRedirectedToTwitter() {
        footerPage.xLink();
        switchToNewWindow();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://x.com/saucelabs");
        closeNewWindowAndSwitchBack();
    }

    @Test
    public void userIsRedirectedToFacebook() {
        footerPage.facebookLink();
        switchToNewWindow();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://www.facebook.com/saucelabs");
        closeNewWindowAndSwitchBack();

    }

    @Test
    public void userIsRedirectedToLinkedIn() {
        footerPage.linkedInLink();
        switchToNewWindow();
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://www.linkedin.com/company/sauce-labs/");
        closeNewWindowAndSwitchBack();

    }

    private void closeNewWindowAndSwitchBack() {
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @AfterMethod
    public void removeAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
