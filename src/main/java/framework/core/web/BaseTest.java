package framework.core.web;

import framework.configuration.RunConfiguration;
import framework.exceptions.BabbelTestException;
import framework.factory.browser.BrowserFactory;
import framework.reporting.ScreenShotCapturer;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(ScreenShotCapturer.class)
/**
 * The Base class for all the web based tests.
 */
public class BaseTest {

    /**
     * The browser driver from Selenium, may be web or mobile.
     */
    private WebDriver anyDriver;

    /**
     * To retrieve the driver object
     *
     * @return
     */
    public WebDriver getDriver() {
        return anyDriver;
    }

    /**
     * Methods that set anyDriver - may be web or mobile as needed by different runs.
     * @param driver
     */
    protected void setDriver(WebDriver driver) {
        anyDriver = driver;
    }

    /**
     * Method that opens the browser
     * @return
     */
    @Step("Open the desired browser.")
    public WebStarter openBrowser() {
        try {
            setDriver(new BrowserFactory().getDesiredBrowser());
        }
        catch (BabbelTestException exception) {
            exception.printStackTrace();
        }
        return new WebStarter(anyDriver);
    }

    /**
     * The method that quits the current execution context driver.
     */
    @AfterMethod(alwaysRun = true)
    public void closeBrowserOrDevice() {
        if (anyDriver != null) {
            //Close the device or browser
            anyDriver.quit();
        }

    }

}
