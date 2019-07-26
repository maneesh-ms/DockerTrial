package driver.web.overlay;

import driver.web.general.GenericOverlay;
import driver.web.pages.NewUserRegistrationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The Email Exists overlay displayed when existing user re-registers.
 */
public class EmailExistsOverlay extends GenericOverlay {

    /**
     * Page locators for Email Exists Overlay
     */
    private final By emailExistsHeader = By.xpath(getGenericOverlaySectionLocator() + "//h1[@class='dialog-box-title']");
    private final By cancelButton = By.xpath(getGenericOverlaySectionLocator() + "//button[@class='button-left button-cancel grey']");
    private final By okButton = By.xpath(getGenericOverlaySectionLocator() + "//button[@class='button-right button-ok']");

    /**
     * The page constructor
     *
     * @param driver
     */
    public EmailExistsOverlay(WebDriver driver) {
        super(driver);
    }

    /**
     * Method to get Welcome bonus message
     *
     * @return
     */
    public String getEmailExistsMessageText() {
        return getTextOfElementInPage(emailExistsHeader);
    }

    /**
     * Clicks the cancel button in the overlay
     *
     * @return
     */
    @Step("Click on the Cancel Button from email exists overlay")
    public NewUserRegistrationPage clickCancelButtonOnEmailExistsOverlay() {
        submitFromElementInPage(cancelButton);
        return new NewUserRegistrationPage(driver);
    }

    /**
     * Clicks the OK button in the overlay
     *
     * @return
     */
    @Step("Click on the OK Button from email exists overlay")
    public NewUserRegistrationPage clickOkButtonOnEmailExistsOverlay() {
        submitFromElementInPage(okButton);
        return new NewUserRegistrationPage(driver);
    }

    /**
     * To verify if overlay is prsent
     *
     * @return
     */
    public boolean isVisibleOnPage() {
        return isElementPresentInPage(emailExistsHeader)
                && isElementPresentInPage(cancelButton)
                && isElementPresentInPage(okButton);
    }
}
