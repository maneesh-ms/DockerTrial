package driver.web.pages;


import framework.core.web.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The class that represents the New User Registration page where an unregistered user registers for the first time.
 */
public class NewUserRegistrationPage extends BasePage {

    /**
     * The gui elements in the page.
     */
    private final By emailIdTextBox = By.xpath("//input[@id='email']");
    private final By passwordTextBox = By.xpath("//input[@id='password']");
    private final By passwordRepeatTextBox = By.xpath("//input[@id='passwordrepetition']");
    private final By registerButton = By.xpath("//button[@name='register']");

    /**
     * The page constructor
     *
     * @param driver
     */
    public NewUserRegistrationPage(WebDriver driver) {
        super(driver);
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
     * To examine if repeat password field is displayed and enabled.
     *
     * @return
     */
    public boolean isRepeatPasswordIsDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(passwordRepeatTextBox);
    }

    /**
     * To examine if login button is displayed and enabled.
     *
     * @return
     */
    public boolean isRegisterButtonIsDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(registerButton);
    }

    /**
     * To edit the email address
     *
     * @param emailAddress
     * @return
     */
    @Step("Edit the email address field to '{emailAddress}'")
    public NewUserRegistrationPage editEmailAddress(String emailAddress) {
        editTextBoxInPage(emailIdTextBox, emailAddress);
        return this;
    }

    /**
     * To edit the password in the page.
     *
     * @param password
     * @return
     */
    @Step("Edit the password field to '{password}'")
    public NewUserRegistrationPage editPassword(String password) {
        editTextBoxInPage(passwordTextBox, password);
        return this;
    }

    /**
     * To edit the repeat password in the page.
     *
     * @param password
     * @return
     */
    @Step("Edit the Repeat Password field to '{password}'")
    public NewUserRegistrationPage editRepeatPassword(String password) {
        editTextBoxInPage(passwordRepeatTextBox, password);
        return this;
    }

    /**
     * To click on register button in the page.
     *
     * @return
     */
    public AccountDetailsPage clickOnRegister() {
        submitFromElementInPage(registerButton);
        return new AccountDetailsPage(driver);
    }

}
