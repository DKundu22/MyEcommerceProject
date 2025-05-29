package com.ecommerce.utility;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ecommerce.base.BaseClass;
import com.ecommerce.utility.ExtentManager;
import com.ecommerce.utility.ExtentTestManager;
import com.ecommerce.utility.ScreenShot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseClass implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String browser = BaseClass.getBrowserName();  // Fetch browser from ThreadLocal
        String testName = result.getMethod().getMethodName() + " on " + browser;

        ExtentTestManager.startTest(testName, result.getMethod().getDescription());
        ExtentTestManager.getTest().assignCategory(browser);  // Assign category for grouping
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());

        try {
            String screenshotPath = ScreenShot.captureScreenShot(getDriver(), result.getMethod().getMethodName());
            ExtentTestManager.getTest()
                .fail("📸 Screenshot of failure:",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "⚠️ Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush(); // Final flush to write report
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
