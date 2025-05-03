package com.ecommerce.utility;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import com.ecommerce.utility.ExtentManager;
import com.ecommerce.utility.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseClass implements ITestListener {

	@Override
    public void onTestStart(ITestResult result) {
        String browser = BaseClass.getBrowserName();  // ✅ Fetch the current browser from ThreadLocal
        String testName = result.getMethod().getMethodName() + " on " + browser;

        ExtentTestManager.startTest(testName, result.getMethod().getDescription());
        ExtentTestManager.getTest().assignCategory(browser);  // ✅ Assign browser as category for grouping
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
        try {
            Action action = new Action();
            String screenshotPath = action.screenShot(getDriver(), result.getMethod().getMethodName());
            ExtentTestManager.getTest().fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "⚠️ Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}

