package driver.mobility.general;

import framework.core.mobility.MobilityBasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * The class that represents the common green header with title in all screens
 */
public class ActionHeader extends MobilityBasePage {

    /**
     * The page constructor
     * @param deviceDriver
     */
    public ActionHeader(MobileDriver<WebElement> deviceDriver) {
        super(deviceDriver);
    }

    /**
     * Gui elements in page.
     */
    private final By actionBar = MobileBy.id("com.github.fgoncalves.qa:id/action_bar");
    private final By headerTitle = MobileBy.className("android.widget.TextView");

    /**
     * To check the presence of action par in the screen.
     * @return
     */
    public boolean isActionBarDisplayed() {
        return isElementPresentInPage(actionBar);
    }

    /**
     * To check the presence of title in the screen.
     * @return
     */
    public boolean isHeaderTitleDisplayed() {
        return isElementPresentInPage(headerTitle);
    }

    /**
     * To obtain the text of title.
     * @return
     */
    public String getHeaderTitle() {
        return getTextOfElementInPage(headerTitle);
    }

}
