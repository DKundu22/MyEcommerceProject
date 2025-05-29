package com.ecommerce.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to handle screenshot capture functionality.
 * Captures full-page screenshot and saves to the /Screenshots directory
 * with a timestamp in the filename.
 *
 * Example Usage:
 *     ScreenShot.captureScreenShot(driver, "LoginFailure");
 */
public class ScreenShot {
    public static String captureScreenShot(WebDriver driver, String screenshotName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + "_" + timeStamp + ".png";
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File finalDestination = new File(path);
            FileUtils.copyFile(source, finalDestination);
            Log.info("Screenshot captured: " + path);
        } catch (IOException e) {
            Log.error("Screenshot capture failed!", e);
        }
        return path;
    }
}