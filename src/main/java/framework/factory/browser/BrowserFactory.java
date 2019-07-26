package framework.factory.browser;

import framework.exceptions.BabbelTestException;
import framework.configuration.RunConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * The Factory that brings the desired browser based on user preferences listed in configuration file.
 */
public class BrowserFactory {

    /**
     * The browser driver from selenium.
     */
    private WebDriver desiredBrowser;

    /**
     * The factory constructor that initializes implementations of Configuration for different strategies.
     */
    public BrowserFactory() throws BabbelTestException {

        if (RunConfiguration.getCurrentConfiguration().getRemoteRun()) {
            try{
                DesiredCapabilities desiredCapab = null;

                MutableCapabilities capabilities = null;

                if (RunConfiguration.getCurrentConfiguration().getDesiredBrowser().equals("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.setAcceptInsecureCerts(true);
                    options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                    options.addArguments("--proxy-server=http://148.87.19.20:80");
                    capabilities = options;

                } else if (RunConfiguration.getCurrentConfiguration().getDesiredBrowser().equals("firefox")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--start-maximized");
                    options.setAcceptInsecureCerts(true);
                    options.addArguments("--proxy-server=http://148.87.19.20:80");
                    options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                    capabilities = options;
                }

                desiredBrowser = new RemoteWebDriver(new URL(RunConfiguration.getCurrentConfiguration().getHubUrl()), capabilities);

            }
            catch(Exception babbelTestException) {
                throw new BabbelTestException("Could not create remote driver from grid : " + babbelTestException.getMessage());
            }

        } else {
            //Conditions to generate desired browser based on configuration.
            try {
                if (RunConfiguration.getCurrentConfiguration().getDesiredBrowser().equals("chrome")) {
                    System.setProperty("webdriver.chrome.driver", RunConfiguration.getCurrentConfiguration().getChromeDriverLocation());
                    desiredBrowser = new ChromeDriver();
                } else if (RunConfiguration.getCurrentConfiguration().getDesiredBrowser().equals("firefox")) {
                    System.setProperty("webdriver.firefox.marionette", RunConfiguration.getCurrentConfiguration().getGeckoDriverLocation());
                    desiredBrowser = new FirefoxDriver();
                }
            } catch (Exception babbelTestException) {
                throw new BabbelTestException("Could not create desired local browser : " + babbelTestException.getMessage());
            }
        }
        //Sets the page load time out.
        desiredBrowser.manage().timeouts().pageLoadTimeout(RunConfiguration.getCurrentConfiguration().getPageLoadTimeOut(), TimeUnit.SECONDS);
    }

    /**
     * To get the current desired browser
     *
     * @return
     */
    public WebDriver getDesiredBrowser() {
        return desiredBrowser;
    }
}
