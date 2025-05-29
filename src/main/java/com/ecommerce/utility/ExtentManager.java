package com.ecommerce.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport/ExtentReport.html");
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Functional Test Suite");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Environment", ConfigReader.get("environment"));
        extent.setSystemInfo("Tester", ConfigReader.get("tester.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java", System.getProperty("java.version"));
        return extent;
    }
}