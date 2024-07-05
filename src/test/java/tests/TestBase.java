package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.*;

import utilities.Helper;
import utilities.StaticThreadLocal;

import java.io.IOException;
import java.lang.reflect.Method;

import java.net.URL;
import java.util.Properties;


public class TestBase {
	public static Logger log = LogManager.getLogger();
	public static String baseURL = null;
	public static String environment = null;
	public static String userType = null;
	public static String hms;
	public static String group;
	public static String testStaus;
	//public static Properties app_prop;

    @BeforeSuite
	public void setup(){
		log = LogManager.getLogger(this.getClass());
	}
	@BeforeClass (alwaysRun = true)
	@Parameters({"deviceName", "platformVersion", "portNumber" })
	public void initiateService(String deviceName , String platformVersion, String portNumber) throws IOException {
		log.info("===== >TestBase Class ");
		log.info("===== >Start appium service ");
		startAppiumService(portNumber);
		log.info("===== >get property file ");
//		app_prop = Helper.readPropertiesFile(System.getProperty("user.dir")+"/src/test/java/resources/appOptions.properties");
//		String appPackage = app_prop.getProperty("appPackage");
//		String appActivity = app_prop.getProperty("appActivity");
//		String appPath = app_prop.getProperty("apkPath");
		String appPackage = Helper.getPropertiesFile("appOptions").getString("appPackage");
		String appActivity = Helper.getPropertiesFile("appOptions").getString("appActivity");
		String appPath = Helper.getPropertiesFile("appOptions").getString("apkPath");
		log.info("===== > Getting UiAutomator options ");
		UiAutomator2Options options = getOptions(platformVersion,deviceName,appPackage,appActivity,appPath);
		log.info("===== > launch appium driver ");
		StaticThreadLocal.setDriver(new AndroidDriver(new URL("http://127.0.0.1:"+portNumber),options));

	}
	@AfterMethod (alwaysRun = true)
	public void afterMethod(Method method){

	}
	@Parameters({"environment", "userType"})
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(@Optional("qa") String environment,  @Optional("admin") String userType){
		System.out.println("===== > before suite ");
		this.environment = environment;
		this.userType = userType;
		log.info("environment : "+this.environment);
		log.info("userType : "+this.userType);
	}
	@AfterClass(alwaysRun = true)
	public void stopDriver(){
		StaticThreadLocal.closeBrowser();
	}

	public void startAppiumService(String portNumber){
		AppiumDriverLocalService service ;
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
		serviceBuilder.withIPAddress("127.0.0.1");
		serviceBuilder.usingPort(Integer.parseInt(portNumber));
		service = AppiumDriverLocalService.buildService(serviceBuilder);
		service.start();
		log.info("Service has been started with port number "+ portNumber);
    }
    public UiAutomator2Options getOptions(String platformVersion, String deviceName, String appPackage, String appActivity, String appPath){
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setPlatformVersion(platformVersion);
		options.setAutomationName("UiAutomator2");
		options.setDeviceName(deviceName);
		options.setAppPackage(appPackage);
		options.setAppActivity(appActivity);
		options.setApp(System.getProperty("user.dir")+ appPath);
		options.setNoReset(false);
	return options;
  }
}
