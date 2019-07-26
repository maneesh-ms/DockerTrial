package framework.configuration;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class that acts as an interface to user defined configuration.
 */
public class RunConfiguration {

    /**
     * An instance of run configuration
     */
    private static RunConfiguration currentConfiguration;

    /**
     * All properties
     */
    private Properties allProperties;

    /**
     * Constructor for the Run configuration to create singleton instance.
     */
    private RunConfiguration() {
        if (allProperties == null) {
            allProperties = new Properties();
            try {
                allProperties.load(new FileInputStream("runconfiguration.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves the current configuration.
     *
     * @return
     */
    public static RunConfiguration getCurrentConfiguration() {

        if (currentConfiguration == null) {
            currentConfiguration = new RunConfiguration();
        }
        return currentConfiguration;
    }

    /**
     * To get the application url
     *
     * @return
     */
    public String getApplicationURL() {
        return allProperties.getProperty("applicationUrl");
    }

    /**
     * To get the name of desired browser
     *
     * @return
     */
    public String getDesiredBrowser() {
        return allProperties.getProperty("browser");
    }

    /**
     * To get the path of gecko driver
     *
     * @return
     */
    public String getGeckoDriverLocation() {
        return allProperties.getProperty("geckoDriverLocation");
    }

    /**
     * To get the path of chrome driver
     *
     * @return
     */
    public String getChromeDriverLocation() {
        return allProperties.getProperty("chromeDriverLocation");
    }

    /**
     * To get the pageLoadTimeout
     *
     * @return
     */
    public int getPageLoadTimeOut() {
        return Integer.valueOf(allProperties.getProperty("pageLoadTimeOut"));
    }

    /**
     * To get the pageLoadTimeout
     *
     * @return
     */
    public int getWaitForElementTimeOut() {
        return Integer.valueOf(allProperties.getProperty("waitForElementTimeOut"));
    }

    /**
     * To get the remote run option
     *
     * @return
     */
    public boolean getRemoteRun() {
        return Boolean.valueOf(allProperties.getProperty("gridRun"));
    }

    /**
     * To get the hub url
     *
     * @return
     */
    public String getHubUrl() {
        return allProperties.getProperty("hubUrl");
    }

    /**
     * To get appium server location
     *
     * @return
     */
    public String getAppiumServer() {
        return allProperties.getProperty("appiumServer");
    }

    /**
     * To get the device platform
     *
     * @return
     */
    public String getDevicePlatform() {
        return allProperties.getProperty("platformName");
    }


    /**
     * To get the device name
     *
     * @return
     */
    public String getDeviceName() {
        return allProperties.getProperty("deviceName");
    }


    /**
     * To get the app package
     *
     * @return
     */
    public String getAppPackage() {
        return allProperties.getProperty("appPackage");
    }


    /**
     * To get the app activity
     *
     * @return
     */
    public String getAppActivity() {
        return allProperties.getProperty("appActivity");
    }

}
