package framework.reporting;

import framework.core.web.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.util.UUID;

/**
 * The class that is responsible to capture screenshot and provide to the allure reporting engine.
 */
public class ScreenShotCapturer implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    /**
     * To grab the screenshot on test failures
     *
     * @param iTestResult
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {

        BaseTest baseTest = (BaseTest) (iTestResult.getInstance());
        WebDriver driver = baseTest.getDriver();

        if (driver == null) {
            Reporter.log("An error occurred while prepared the screenshot. Looks like driver is not initialized properly.");
        } else {
            try {
                //Grab allure screenshots
                attachTextMessageToReports(iTestResult);
                attachScreenshotToReports(driver);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    /**
     * Function for allure to capture screenshots and attach to reports
     *
     * @param driver
     * @return
     */
    @Attachment(value = "Page screenshot at the moment of failure.", type = "image/png")
    public byte[] attachScreenshotToReports(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Function for allure to capture text message and attach to reports
     *
     * @param iTestResult
     * @return
     */
    @Attachment(value = "Note : ", type = "text/plain")
    public String attachTextMessageToReports(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getConstructorOrMethod().getName();
        return "The test '" + methodName + "' has failed. Please examine the below screenshots.";
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
