package utilities;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;

public class StaticThreadLocal {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();

    public static  void setDriver(AppiumDriver appiumDriver) {
        driver.set(appiumDriver);

    }

    public static AppiumDriver getAppiumDriver(){
        return driver.get();
    }

    public static void closeBrowser() {
        //getAppiumDriver().close();
        getAppiumDriver().quit();
        driver.remove();
    }

    public static void setExtentTest(ExtentTest extentTest) {
        threadLocalExtentTest.set(extentTest);
    }

    public static ExtentTest getExtentTest(){
        return threadLocalExtentTest.get();
    }
    public static void setExtentTestInfo(String message) {
        getExtentTest().info(message);
    }
}
