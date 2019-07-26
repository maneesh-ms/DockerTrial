package framework.core.mobility;

import framework.core.web.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

/**
 * The parent page of all page object classes for the mobile screens inherited from BasePage to leverage its features
 */
public class MobilityBasePage extends BasePage {
    /**
     * Some components need for mobility interactions
     */
    private TouchAction screenTouchAction;

    /**
     * The page constructor
     *
     * @param driver
     */
    public MobilityBasePage(MobileDriver<WebElement> driver) {
        super(driver);
        this.screenTouchAction = new TouchAction(driver);
    }

    /**
     * To retrieve the driver web or mobile.
     * @return
     */
    public MobileDriver getMobilityDriver() {
        return ((MobileDriver)getDriver());
    }

    /**
     * To tap an element present in the mobile screen.
     *
     * @param byLocatorStrategy
     */
    public void tapAnElementInScreen(By byLocatorStrategy) {
        screenTouchAction.tap(tapOptions().withElement(element(getElement(byLocatorStrategy))))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }

    //////////////////////////////////////////////Notes////////////////////////////////////////////////////////////////
    //Below are some of the important mobile specific actions(not used until now). Might be useful in future test flows.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * To long press a particular element in the mobile screen.
     *
     * @param byLocatorStrategy
     */
    public void longPressElementInScreen(MobileBy byLocatorStrategy) {
        screenTouchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(getElement(byLocatorStrategy)))
                .withDuration(Duration.ofMillis(10000)))
                .release()
                .perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * To get the root element in the screen.
     *
     * @return
     * @throws InterruptedException
     */
    private WebElement getRootElement() {

        WebElement rootElement = null;
        By xpathLocator = By.xpath("//*");
        try {
            rootElement = getDriver().findElement(xpathLocator);
        } catch (StaleElementReferenceException e) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            rootElement = getDriver().findElement(xpathLocator);
        }
        return rootElement;
    }

    /**
     * To perform the swipe in any direction in the mobile screen
     *
     * @param direction
     * @throws InterruptedException
     */
    public void swipeOnMobileScreen(SwipeDirection direction) {

        WebElement rootElement = getRootElement();
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

        if (direction.equals(SwipeDirection.DOWN)) {
            x1 = rootElement.getSize().width / 2;
            y1 = rootElement.getSize().height / 2;
            x2 = rootElement.getSize().width / 2;
            y2 = (rootElement.getSize().height * 9) / 10;
        } else if (direction.equals(SwipeDirection.UP)) {
            x1 = rootElement.getSize().width / 2;
            y1 = (rootElement.getSize().height * 8) / 10;
            x2 = rootElement.getSize().width / 2;
            y2 = rootElement.getSize().height / 2;
        } else if (direction.equals(SwipeDirection.RIGHT)) {
            x1 = rootElement.getSize().width / 10;
            y1 = rootElement.getSize().height / 2;
            x2 = (rootElement.getSize().width * 9) / 10;
            y2 = rootElement.getSize().height / 2;
        } else if (direction.equals(SwipeDirection.LEFT)) {
            x1 = (rootElement.getSize().width * 9) / 10;
            y1 = rootElement.getSize().height / 2;
            x2 = rootElement.getSize().width / 10;
            y2 = rootElement.getSize().height / 2;
        }
        screenTouchAction
                .press(ElementOption.element(rootElement, x2, y2))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10)))
                .moveTo(ElementOption.element(rootElement, x1, y1))
                .release()
                .perform();
    }

    /**
     * The possible swipe directions.
     */
    public enum SwipeDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /**
     * To drag and drop an element in the mobile screen.
     * @param fromElementLocatorStrategy
     * @param toElementLocatorStrategy
     */
    public void dragAndDrop(By fromElementLocatorStrategy,By toElementLocatorStrategy) {
        screenTouchAction.longPress(element(getElement(fromElementLocatorStrategy)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(element(getElement(toElementLocatorStrategy)))
                .perform()
                .release();
    }

}
