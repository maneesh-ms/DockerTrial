package driver.web.pages;


import driver.web.general.CommonPageSection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The class that represents the Login page where an existing user login to the application.
 */
public class LoginPage extends CommonPageSection {

    /**
     * Gui Elements in page
     */
    private final By emailIdTextBox = By.xpath("//input[@id='email']");
    private final By passwordTextBox = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//button[@name='login']");
    private final By errorMessageText = By.xpath("//div[@class='error-desc']");

    /**
     * The page constructor
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * To edit the email address in page.
     *
     * @param emailAddress
     * @return
     */
    @Step("Edit the email address field to '{emailAddress}'")
    public LoginPage editEmailAddress(String emailAddress) {
        editTextBoxInPage(emailIdTextBox, emailAddress);
        return this;
    }

    /**
     * To edit the password in page.
     *
     * @param password
     * @return
     */
    @Step("Edit the password field to '{password}'")
    public LoginPage editPassword(String password) {
        editTextBoxInPage(passwordTextBox, password);
        return this;
    }

    /**
     * To click on the login button
     *
     * @return
     */
    @Step("Click on the Login button")
    public AccountDetailsPage clickOnLogin() {
        submitFromElementInPage(loginButton);
        return new AccountDetailsPage(driver);
    }

    /**
     * To wait until the page loads successfully.
     */
    @Step("Wait until the Login page loads completely.")
    public LoginPage waitUntilThePageLoads() {
        waitUntilElementIsVisible(emailIdTextBox);
        return this;
    }

    /**
     * To examine if email address field is displayed and enabled.
     *
     * @return
     */
    public boolean isEmailAddressIsDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(emailIdTextBox);
    }

    /**
     * To examine if password field is displayed and enabled.
     *
     * @return
     */
    public boolean isPasswordIsDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(passwordTextBox);
    }

    /**
     * To examine if login button is displayed and enabled.
     *
     * @return
     */
    public boolean isLoginButtonIsDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(loginButton);
    }

    /**
     * To get the error message in the page.
     *
     * @return
     */
    public String getErrorMessageInPage() {
        if (isElementPresentInPage(errorMessageText)) {
            return getTextOfElementInPage(errorMessageText);
        } else {
            return null;
        }
    }
}