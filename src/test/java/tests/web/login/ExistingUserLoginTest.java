package tests.web.login;

import driver.web.pages.AccountDetailsPage;
import driver.web.pages.LoginPage;
import framework.core.web.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExistingUserLoginTest extends BaseTest {

    /**
     * Data driven test sample
     * @return
     */
    @DataProvider(name = "users-credentials")
    public Object[][] dataProviderValidRegisteredUsers() {

        return new Object[][]{
        //Data-set 1
            {"mnsh.nair@gmail.com", "test@123"},
            //Data-set 2 (This is actually invalid credentials to demonstrate a failed result.
            {"vishnuchandar07@gmail.com", "canarabank"}
        };
    }

    @Test(priority = 0, description = "Verify User Login With Valid Credentials", dataProvider = "users-credentials")
    @Description("Verify that a user with valid credentials can successfully login to the system.")
    public void verifyUserLoginWithValidCredentials(String userName, String password) {

        //////////////////////////////////////////////Notes about the test//////////////////////////////////////////////
        //This test assumes the users are already present in the system and tries to login.
        //This could be achieved by way of loading present data dumps into AUT before test in formal test environments.
        //If there is no way to present dumps, we have to user creation to @Before class, I have skipped it for now.
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Navigate to login page.
        LoginPage loginPage = openBrowser()
                .launchCheckTwentyFour()
                .hoverOnMeinKonto()
                .clickLoginButton();

        //Validate the elements of user login  page.
        Assert.assertEquals(true, loginPage.isEmailAddressIsDisplayedAndEnabled(), "Verify that the email address field is displayed and is enabled.");
        Assert.assertEquals(true, loginPage.isPasswordIsDisplayedAndEnabled(), "Verify that the password field is displayed and is enabled.");
        Assert.assertEquals(true, loginPage.isLoginButtonIsDisplayedAndEnabled(), "Verify that the login button is displayed and is enabled.");

        //Enter details and navigate to account details page.
        AccountDetailsPage accountDetailsPage = loginPage
                .editEmailAddress(userName)
                .editPassword(password)
                .clickOnLogin();

        //Validate the elements of account details page.
        Assert.assertTrue(accountDetailsPage.isUserGreetingDisplayed(), "Ensure that the user greeting is displayed in the page");
        Assert.assertEquals(accountDetailsPage.getUserGreeting(), "Hallo", "Verify that the greeting text shown after login matches the expected.");
        Assert.assertEquals(accountDetailsPage.getUserDetails(), "Sie sind angemeldet als " + userName, "Verify that the user email address is displayed in the message.");

    }

    @Test(priority = 1, description = "Verify User Login With Invalid Credentials")
    @Description("Verify that a user with invalid credentials cannot login to the system and error messages are presented.")
    public void verifyUserLoginWithInvalidCredentials() {
        //////////////////////////////////////////////Notes about the test//////////////////////////////////////////////
        //This test tries to login as an unauthenticated user into the system and examine the result
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Navigate till the login page
        LoginPage loginPage = openBrowser()
                .launchCheckTwentyFour()
                .hoverOnMeinKonto()
                .clickLoginButton();

        //Enter invalid credentials in the page and click on login.
        loginPage.editEmailAddress("ievgenii.serhiev@gmail.com")
                .editPassword("year2017@45300")
                .clickOnLogin();

        //Validate the error message
            Assert.assertEquals(loginPage.getErrorMessageInPage(), "E-Mail-Adresse oder Passwort ist nicht korrekt.", "Verify that the right error message is displayed in the screen for unauthorized users.");

    }
}
