package tests.mobility.login;

import driver.mobility.screens.AppHomeScreen;
import driver.mobility.screens.AppLoginScreen;
import framework.core.mobility.MobilityBaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test suite for testing the login functionality in the mobile application.
 */
public class ExistingUserLoginTest extends MobilityBaseTest {
    @Test(priority = 0, description = "Verify App Login-Page Presentation")
    @Description("Verify that the right elements are displayed in the login page.")
    public void verifyAppLoginPagePresentation() {

        //////////////////////////////////////////////Notes about the test//////////////////////////////////////////////
        //This test validates the login page of the mobile application.
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Navigation to the login screen.
        AppLoginScreen appLoginScreen = pickDeviceAndLaunchQaApplication();

        //Validation
        Assert.assertTrue(appLoginScreen.isActionBarDisplayed(),"Verify that the common action bar is displayed in the app login screen.");
        Assert.assertEquals(appLoginScreen.getHeaderTitle(), "QA" ,"Verify that the header title is displayed as expected in app login screen.");
        Assert.assertTrue(appLoginScreen.isUserNameFieldDisplayedAndEnabled() ,"Verify that the username field is displayed in the app login screen.");
        Assert.assertTrue(appLoginScreen.isPasswordFieldDisplayedAndEnabled(),"Verify that the password field is displayed in the app login screen.");
        Assert.assertEquals(appLoginScreen.isSignInOrRegisterButtonDisplayedAndEnabled(), true ,"Verify that the signin or register is displayed in the app login screen.");
    }

    /**
     * Data driven test sample
     * @return
     */
    @DataProvider(name = "users-credentials")
    public Object[][] dataProviderValidRegisteredUsers() {

        return new Object[][]{
                //Data-set 1 (This set is supposed to pass, they are valid data)
                {"arthur@gmail.com", "98765"},
                //Data-set 2 (This is actually invalid credentials to demonstrate a failed result in mobility.
                {"unauthorised@gmail.com", "wrongPassword"}
        };
    }
    @Test(priority = 0, description = "Verify App User Login With Valid Credentials", dataProvider = "users-credentials")
    @Description("Verify that a user with valid credentials can login to the system.")
    public void verifyAppUserLoginWithValidCredentials(String userName, String password) {

        //////////////////////////////////////////////Notes about the test//////////////////////////////////////////////
        //This test ensures that the home screen is displayed once an authenticated user logs in.
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Navigation
        AppHomeScreen appHomePage = pickDeviceAndLaunchQaApplication()
                .editUserNameField(userName)
                .editPasswordField(password)
                .clickSignInOrRegisterButton();

        //Validation
        Assert.assertTrue(appHomePage.isHomeImageDisplayed(), "Verify that the home image is displayed in the app home page.");
        Assert.assertTrue(appHomePage.isActionBarDisplayed(), "Verify that the common action bar is displayed in the app home page.");
        Assert.assertEquals(appHomePage.getHeaderTitle(), "QA", "Verify that the header title is displayed as expected in app home page.");
    }
}
