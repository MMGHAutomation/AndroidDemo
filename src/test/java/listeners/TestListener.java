package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import tests.TestBase;
import utilities.ExtentManager;
import utilities.Helper;
import utilities.StaticThreadLocal;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestListener extends TestBase implements ITestListener {
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;
    @Override
    public synchronized void onStart(ITestContext context) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        String dateStr = formatter.format(date);
        String reportName = context.getName() + "-"+ userType+" User -"+environment+"-"+dateStr;
        log.info("Extent Reports Version 3 Test Suite started!");
        log.info(context.getName());
        extentReport = ExtentManager.createInstance(reportName);

    }

    @SneakyThrows
    @Override
    public synchronized void onFinish(ITestContext context) {
        log.info("Extent Reports Version 3  Test Suite is ending!");
        extentReport.setSystemInfo("Environment",environment);
        extentReport.setSystemInfo("UserType",userType);
        //extentReport.setAnalysisStrategy(AnalysisStrategy.CLASS);
        extentReport.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " started!");
        Test mtest = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        String testname = mtest.testName();
        String testdesc= mtest.description();
        String testgroups = mtest.groups()[0];
        extentTest = extentReport.createTest(testname,testdesc);
        StaticThreadLocal.setExtentTest(extentTest);
        StaticThreadLocal.getExtentTest().assignCategory(testgroups);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " passed!");
        StaticThreadLocal.getExtentTest().pass("Test passed");
        setExcelData(result);
        testStaus = "Pass";
    }

    @SneakyThrows
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " failed!");
        WebDriver driver = StaticThreadLocal.getAppiumDriver();
        ExtentTest extest = StaticThreadLocal.getExtentTest();
        StaticThreadLocal.getExtentTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(
                Helper.getScreenShotAsBase64(driver,result.getName())).build());
        StaticThreadLocal.getExtentTest().fail(result.getThrowable());
        setExcelData(result);
        testStaus = "Fail";

    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " skipped!");
        StaticThreadLocal.getExtentTest().skip(result.getThrowable());
        setExcelData(result);
        testStaus = "Skip";
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
    }

    public void setExcelData(ITestResult result) {
        group = result.getMethod().getGroups()[0];
        long testCaseT = result.getEndMillis()-result.getStartMillis();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(testCaseT);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(testCaseT);
        if (testCaseT < 1000) {
            hms = String.format("%d milisecond",TimeUnit.MILLISECONDS.toMillis(testCaseT));
        }else{
            hms = String.format("%d min, %d sec",TimeUnit.MILLISECONDS.toMinutes(testCaseT),TimeUnit.MILLISECONDS.toSeconds(testCaseT));
        }
    }

}
