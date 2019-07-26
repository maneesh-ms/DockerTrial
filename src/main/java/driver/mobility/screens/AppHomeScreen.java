package driver.mobility.screens;

import driver.mobility.general.ActionHeader;
import framework.core.mobility.MobilityBasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * The class that represents the mobility home screen post successful login
 */
public class AppHomeScreen extends ActionHeader {
    /**
     * Gui elements in the page
     */
    private By homeImage = MobileBy.className("android.widget.ImageView");
    private By loadingSymbol = MobileBy.id("com.github.fgoncalves.qa:id/login_progress");

    /**
     * The page constructor
     * @param deviceDriver
     */
    public AppHomeScreen(MobileDriver<WebElement> deviceDriver) {
        super(deviceDriver);
    }

    /**
     * wait until the page is ready and loaded
     * @return
     */
    @Step("Wait until the Home screen loads completely.")
    public AppHomeScreen waitUntilHomeScreenLoads() {
        waitUntilElementIsNotVisible(loadingSymbol);
        return this;
    }

    /**
     * To get the presence of the home image.
     * @return
     */
    public boolean isHomeImageDisplayed() {
        return isElementDisplayedAndEnabledInPage(homeImage);
    }
}
