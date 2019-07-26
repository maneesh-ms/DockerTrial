package framework.core.mobility;

import driver.mobility.screens.AppLoginScreen;
import framework.configuration.RunConfiguration;
import framework.core.web.BaseTest;
import framework.factory.device.DeviceFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The parent class of all mobile tests extending from Base Test
 */
public class MobilityBaseTest extends BaseTest{

    //The capabilities property
    private DesiredCapabilities entireCapabilities;

    //The mobility device driver
    private MobileDriver<WebElement> deviceDriver;

    /**
     * Pick the right device for test as per configuration and load the app mentioned in configuration
     * @return
     */
    @Step("Pick the device and open the QA Mobile App.")
    public AppLoginScreen pickDeviceAndLaunchQaApplication() {

        entireCapabilities = new DeviceFactory().getDevice();

        RunConfiguration currentConfiguration = RunConfiguration.getCurrentConfiguration();

        if (currentConfiguration.getDevicePlatform().equals("android")) {
            entireCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, currentConfiguration.getAppPackage());
            entireCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, currentConfiguration.getAppActivity());

            try {
                deviceDriver = new AndroidDriver<WebElement>(new URL(currentConfiguration.getAppiumServer()), entireCapabilities);
                setDriver(deviceDriver);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if (RunConfiguration.getCurrentConfiguration().getDevicePlatform().equals("ios")) {
            //TODO for ios
        }
        else {
            //TODO throw custom exception as none of the platforms are selected.
        }
        return new AppLoginScreen(deviceDriver);
    }

}
