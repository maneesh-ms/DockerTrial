package framework.core.web;

import driver.web.pages.LandingPage;
import framework.configuration.RunConfiguration;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

/**
 * The core class that loads the url to desired browser and takes uer to landing page.
 */
public class WebStarter extends BasePage {

    /**
     * The page constructor
     *
     * @param driver
     */
    public WebStarter(WebDriver driver) {
        super(driver);
    }

    /**
     * To launch the Landing Page.
     *
     * @return
     */
    @Step("Load application url to the browser.")
    public LandingPage launchCheckTwentyFour() {
        goToUrl(RunConfiguration.getCurrentConfiguration().getApplicationURL());
        return new LandingPage(driver);
    }
}
