package framework.factory.device;

import framework.configuration.RunConfiguration;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DeviceFactory {
    /**
     * The device driver from appium
     */
    private DesiredCapabilities desiredDeviceCapabilities;

    public DeviceFactory(){

        desiredDeviceCapabilities = new DesiredCapabilities();;
        RunConfiguration currentConfiguration = RunConfiguration.getCurrentConfiguration();

        if(currentConfiguration.getDevicePlatform().equals("android")){
            //Set device name and platform
            desiredDeviceCapabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, currentConfiguration.getDevicePlatform());
            desiredDeviceCapabilities.setCapability("deviceName", currentConfiguration.getDeviceName());
        }
        else if(RunConfiguration.getCurrentConfiguration().getDevicePlatform().equals("ios")){
            //TODO for ios
        }
        else {
            //TODO throw custom exception as none of the platforms are selected.
        }
    }

    /**
     * To pick device capabilities
     *
     * @return
     */
    public DesiredCapabilities getDevice() {
        return desiredDeviceCapabilities;
    }
}
