package driver.mobility.screens;

import driver.mobility.general.ActionHeader;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
/**
 * The class that represents the login screen of the mobility app.
 */
public class AppLoginScreen extends ActionHeader {

    /**
     * The gui elements in the page.
     */
    private final By usernameTextField = MobileBy.id("com.github.fgoncalves.qa:id/email");
    private final By passwordTextField = MobileBy.id("com.github.fgoncalves.qa:id/password");
    private final By signInOrRegisterButton = MobileBy.id("com.github.fgoncalves.qa:id/email_sign_in_button");

    /**
     * The page constructor
     * @param deviceDriver
     */
    public AppLoginScreen(MobileDriver<WebElement> deviceDriver) {
        super(deviceDriver);
    }

    /**
     * To edit the user name field
     * @param userName
     * @return
     */
    @Step("Edit the user name field to '{userName}'")
    public AppLoginScreen editUserNameField(String userName) {
        editTextBoxInPage(usernameTextField, userName);
        return this;
    }

    /**
     * To edit the password field
     * @param password
     * @return
     */
    @Step("Edit the password field to '{password}'")
    public AppLoginScreen editPasswordField(String password) {
        editTextBoxInPage(passwordTextField, password);
        return this;
    }

    /**
     * To click on the login button
     * @return
     */
    @Step("Click on the Sign In Or Register Button ")
    public AppHomeScreen clickSignInOrRegisterButton() {
        clickOnElementInPage(signInOrRegisterButton);
        return new AppHomeScreen(getMobilityDriver()).waitUntilHomeScreenLoads();
    }

    /**
     * To get the presence of the user name field in the login screen.
     * @return
     */
    public boolean isUserNameFieldDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(usernameTextField);
    }


    /**
     * To get the presence of the password field in the login screen.
     * @return
     */
    public boolean isPasswordFieldDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(passwordTextField);
    }


    /**
     * To get the presence of the SignIn or Register button in the login screen.
     * @return
     */
    public boolean isSignInOrRegisterButtonDisplayedAndEnabled() {
        return isElementDisplayedAndEnabledInPage(signInOrRegisterButton);
    }


}
