package driver.web.overlay;

import driver.web.pages.LoginPage;
import driver.web.pages.NewUserRegistrationPage;
import framework.core.web.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The Customer Layer that appears on the page right side when user hovers Mein Konto
 */
public class CustomerLayerOverlay extends BasePage {

    /**
     * Gui Elements in page
     */
    private final String customerLayerLocator = "//div[@class='c24-customer-layer c24-header-hover-layer' and contains(@style,'visible')]";
    private final By loginButton = By.xpath(customerLayerLocator + "//a[@id='c24-meinkonto-anmelden']");
    private final By newUserLink = By.xpath(customerLayerLocator + "//div[@class='c24-customer-salutation-text']//a[contains(@class,'c24-meinkonto-reflink')]");

    /**
     * The page constructor
     *
     * @param driver
     */
    public CustomerLayerOverlay(WebDriver driver) {
        super(driver);
    }

    /**
     * To wait until the overlay is displayed
     */
    public CustomerLayerOverlay waitUntilTheOverlayIsShown() {
        waitUntilElementIsVisible(loginButton);
        return this;
    }


    /**
     * To click on the Login button
     *
     * @return
     */
    @Step("Click on the Login button from Customer Layer Overlay")
    public LoginPage clickLoginButton() {
        clickOnElementInPage(loginButton);
        return new LoginPage(driver)
                .waitUntilThePageLoads();
    }

    /**
     * To click on the New User link
     *
     * @return
     */
    @Step("Click on the New User Link from Customer Layer Overlay")
    public NewUserRegistrationPage clickNewUserLink() {
        clickOnElementInPage(newUserLink);
        return new NewUserRegistrationPage(driver);
    }


}
