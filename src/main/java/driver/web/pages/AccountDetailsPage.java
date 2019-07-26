package driver.web.pages;

import driver.web.general.CommonPageSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The account details page after login or registration.
 */
public class AccountDetailsPage extends CommonPageSection {

    /**
     * Gui elements in the page.
     */
    private final String pageHeader_xPath = "//div[@class='page-header-inner']";
    private final By userGreetingText = By.xpath(pageHeader_xPath + "//h1");
    private final By userDetailsText = By.xpath(pageHeader_xPath + "/p");

    /**
     * The page constructor
     *
     * @param driver
     */
    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * To examine if the use greeting is displayed in the page.
     * @return
     */
    public boolean isUserGreetingDisplayed() {
        return isElementPresentInPage(userGreetingText);
    }

    /**
     * To retrieve the user greeting text.
     *
     * @return
     */
    public String getUserGreeting() {
        return getTextOfElementInPage(userGreetingText);
    }

    /**
     * To retrieve the displayed user details.
     *
     * @return
     */
    public String getUserDetails() {
        return getTextOfElementInPage(userDetailsText);
    }

}
