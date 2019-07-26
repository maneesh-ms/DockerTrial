package tests.web.registration;

import driver.web.overlay.EmailExistsOverlay;
import driver.web.pages.AccountDetailsPage;
import driver.web.pages.NewUserRegistrationPage;
import framework.core.web.BaseTest;
import framework.utils.FrameworkUtils;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test suite for testing the new user registration functionality.
 */
public class NewUserRegistrationTest extends BaseTest {

    @Test(priority = 1, description = "Verify User Registration Succeeds")
    @Description("Verify that a new user is able to register in the system and is presented the account details page.")
    public void verifyUserRegistrationSucceeds() {
        //////////////////////////////////////////////Notes about the test//////////////////////////////////////////////
        //This test tries to register a user who has not yet registered in check24
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String newEmailId = FrameworkUtils.getANewEmailAddress();
        String password = "Password@123";

        //Navigate till the new user registration page.
        NewUserRegistrationPage newUserPage = openBrowser()
                .launchCheckTwentyFour()
                .hoverOnMeinKonto()
                .clickNewUserLink();

        //Validate the elements of new user registration page.
        Assert.assertEquals(true, newUserPage.isEmailAddressIsDisplayedAndEnabled(), "Verify that the email address field is displayed and is enabled.");
        Assert.assertEquals(true, newUserPage.isPasswordIsDisplayedAndEnabled(), "Verify that the password field is displayed and is enabled.");
        Assert.assertEquals(true, newUserPage.isRepeatPasswordIsDisplayedAndEnabled(), "Verify that the repeat password field is displayed and is enabled.");
        Assert.assertEquals(true, newUserPage.isRegisterButtonIsDisplayedAndEnabled(), "Verify that the register button is displayed and is enabled.");

        //Enter the credentials and click on register.
        AccountDetailsPage accountDetailsPage = newUserPage.editEmailAddress(newEmailId)
                .editPassword(password)
                .editRepeatPassword(password)
                .clickOnRegister();

        //Validate the elements of account details
        Assert.assertEquals(accountDetailsPage.getUserGreeting(), "Hallo", "Verify that the greeting text shown after login matches the expected.");
        Assert.assertEquals(accountDetailsPage.getUserDetails(), "Sie sind angemeldet als " + newEmailId, "Verify that the user email address is displayed in the message.");

    }

    @Test(priority = 1, description = "Verify User Registration Fails For Existing Users")
    @Description("Verify that a  user is not able to register in the system if the emailid has already been registered earlier.")
    public void verifyUserRegistrationFailsForExistingUsers() {

        //////////////////////////////////////////////Notes about the test//////////////////////////////////////////////
        //This test tries to register a user who had already registered in check24
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String password = "Password@123";

        openBrowser()
                .launchCheckTwentyFour()
                .hoverOnMeinKonto()
                .clickNewUserLink()
                .editEmailAddress("mnsh.nair@gmail.com")
                .editPassword(password)
                .editRepeatPassword(password)
                .clickOnRegister();

        EmailExistsOverlay emailExistsOverlay = new EmailExistsOverlay(getDriver());

        //Verify the error messages
        Assert.assertEquals(true, emailExistsOverlay.isVisibleOnPage(), "Verify that email exists overlay is displayed in the page");
        Assert.assertEquals(emailExistsOverlay.getEmailExistsMessageText(), "E-Mail-Adresse wird bereits verwendet.", "Verify that the message indication email is already registered is displayed in the overlay.");

    }
}
