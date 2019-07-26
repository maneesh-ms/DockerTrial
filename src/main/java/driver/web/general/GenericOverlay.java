package driver.web.general;

import framework.core.web.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The class that represents the generic overlay, variants of which might appear in different pages.
 */
public class GenericOverlay extends BasePage {

    /**
     * Page locators for Welcome Bonus Overlay
     */
    private final String overlaySection = "//div[@class='c24-dialog-box']";
    private final By closeButton = By.xpath(overlaySection + "//*[@class='dialog-box-close button-cancel close']");

    /**
     * The page constructor
     *
     * @param driver
     */
    public GenericOverlay(WebDriver driver) {
        super(driver);
    }

    /**
     * To click on the page right top close button.
     */
    @Step("Click on the close button fro the overlay")
    public void clickCloseButtonRightTop() {
        clickOnElementInPage(closeButton);
    }

    /**
     * To get the locator of the overlay section.
     * @return
     */
    public String getGenericOverlaySectionLocator() {
        return overlaySection;
    }

}
