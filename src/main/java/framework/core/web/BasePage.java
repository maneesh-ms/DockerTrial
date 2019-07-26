package framework.core.web;

import framework.configuration.RunConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The page parent page of all page object classes
 */
public class BasePage {

    /**
     * The page elements WebDriver, Actions and JavascriptExecutor
     */
    protected WebDriver driver;
    private Actions pageAction;
    private JavascriptExecutor jsExecutor;

    /**
     * The constructor initialising WebDriver, Actions, Javascript Executor objects
     *
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.pageAction = new Actions(driver);
        if (driver instanceof JavascriptExecutor) {
            this.jsExecutor = (JavascriptExecutor) driver;
        }
    }

    /**
     * To retrieve the driver web or mobile.
     * @return
     */
    public WebDriver getDriver() {
       return driver;
    }

    /**
     * To navigate to a specific url
     */
    public void goToUrl(String url) {
        driver.get(url);
    }

    /**
     * To retrieve a web element in page using xpath.
     */
    public WebElement getElement(By byLocatorStrategy) {

        return driver.findElement(byLocatorStrategy);
    }

    /**
     * To wait for a specified time until element appears in screen or a specified time out.
     *
     * @param byLocatorStrategy
     * @param timeOutSeconds
     */
    public void waitUntilElementIsVisible(By byLocatorStrategy, int timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLocatorStrategy));
    }

    /**
     * To wait until an element appears in screen or a configured timeout.
     *
     * @param byLocatorStrategy
     */
    public void waitUntilElementIsVisible(By byLocatorStrategy) {
        waitUntilElementIsVisible(byLocatorStrategy, RunConfiguration.getCurrentConfiguration().getPageLoadTimeOut());
    }

    /**
     * To wait until an element disappears from page or a specified time out
     *
     * @param byLocatorStrategy
     * @param timeOutSeconds
     */
    public void waitUntilElementIsNotVisible(By byLocatorStrategy, int timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocatorStrategy));
    }

    /**
     * To wait until an elment disappears from the page or a configured timeout.
     *
     * @param byLocatorStrategy
     */
    public void waitUntilElementIsNotVisible(By byLocatorStrategy) {
        waitUntilElementIsNotVisible(byLocatorStrategy, RunConfiguration.getCurrentConfiguration().getPageLoadTimeOut());
    }

    /**
     * To hover on an element in page.
     */
    public void hoverOnElementInPage(By byLocatorStrategy) {
        pageAction.moveToElement(getElement(byLocatorStrategy)).perform();
    }

    /**
     * To click on an element in the page.
     */
    public void clickOnElementInPage(By byLocatorStrategy) {
        try {
            getElement(byLocatorStrategy).click();
        } catch (WebDriverException exception) {
            try {
                pageAction.moveToElement(getElement(byLocatorStrategy)).click().perform();
            } catch (WebDriverException exceptionAgain) {
                exceptionAgain.printStackTrace();
            }
        }
    }

    /**
     * To click on an element in the page.
     */
    public void clickOnElementInPageUsingJsAndId(By byLocatorStrategy) {
        clickOnElementInPageUsingJsAndId(getElement(byLocatorStrategy));
    }

    /**
     * To click on an webelement in the page using JS when it has ID.
     */
    public void clickOnElementInPageUsingJsAndId(WebElement element) {
        try {
            String elementId = element.getAttribute("id");
            jsExecutor.executeScript("document.getElementById('" + elementId + "').click()");
        } catch (WebDriverException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * To edit the text box in page.
     *
     * @param byLocatorStrategy
     * @param value
     */
    public void editTextBoxInPage(By byLocatorStrategy, String value) {

        WebElement elementToEdit = getElement(byLocatorStrategy);

        try {
            elementToEdit.sendKeys(value);
        }
        catch(Exception e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(elementToEdit);
            actions.click();
            actions.sendKeys(value);
            actions.build().perform();
        }
    }

    public void editTextBoxInPageUsingJsAndId(By byLocatorStrategy, String value) {
        try {
            String elementId = getElement(byLocatorStrategy).getAttribute("id");
            jsExecutor.executeScript("document.getElementById('" + elementId + "').value= '" +value + "'");
        } catch (WebDriverException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * To edit the textbox using key actions
     *
     * @param byLocatorStrategy
     * @param key
     */
    public void editTextBoxInPage(By byLocatorStrategy, Keys key) {
        getElement(byLocatorStrategy).sendKeys(Keys.TAB);
    }

    /**
     * To verify if the element is present in the page.
     *
     * @param byLocatorStrategy
     * @return
     */
    public boolean isElementPresentInPage(By byLocatorStrategy) {
        try{
            WebElement element = driver.findElement(byLocatorStrategy);
            return element.isDisplayed();
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * To verify if the element is enabled in the page.
     *
     * @param byLocatorStrategy
     * @return
     */
    public boolean isElementEnabledInPage(By byLocatorStrategy) {
        try{
            WebElement element = driver.findElement(byLocatorStrategy);
            return element.isEnabled();
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * To verify if the element is displayed and enabled in the page.
     *
     * @param byLocatorStrategy
     * @return
     */
    public boolean isElementDisplayedAndEnabledInPage(By byLocatorStrategy) {
        return isElementPresentInPage(byLocatorStrategy) && isElementEnabledInPage(byLocatorStrategy);
    }

    /**
     * To get the text of an element in the page.
     *
     * @param byLocatorStrategy
     * @return
     */
    public String getTextOfElementInPage(By byLocatorStrategy) {
        return getElement(byLocatorStrategy).getText();
    }

    /**
     * To perform submit action from the page for an element.
     *
     * @param byLocatorStrategy
     */
    public void submitFromElementInPage(By byLocatorStrategy) {
        getElement(byLocatorStrategy).submit();
    }

}
