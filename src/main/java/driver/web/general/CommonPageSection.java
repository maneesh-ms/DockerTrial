package driver.web.general;

import driver.web.overlay.CustomerLayerOverlay;
import framework.core.web.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the common page section which includes the headers and navigation elements.
 */
public class CommonPageSection extends BasePage {

    /**
     * Gui Elements in page
     */
    private final String pageHeader = "//div[@id='c24-header-top']";
    private final By meinKontoHover = By.xpath(pageHeader + "//*[@class='c24-customer-hover c24-header-hover']");

    /**
     * Page constructor
     *
     * @param driver
     */
    public CommonPageSection(WebDriver driver) {
        super(driver);
    }

    /**
     * Hover at Mein Konto and get the Customer Layer Overlay
     *
     * @return
     */
    @Step("Hover on the Mein Konto Link from top Navigation")
    public CustomerLayerOverlay hoverOnMeinKonto() {
        hoverOnElementInPage(meinKontoHover);
        return new CustomerLayerOverlay(driver).waitUntilTheOverlayIsShown();
    }

}