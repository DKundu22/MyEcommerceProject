package com.ecommerce.actiondriver;

import com.ecommerce.actioninterface.ActionInterface;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class Action implements ActionInterface {

    private static final int TIMEOUT = 30;

    public void click(WebDriver driver, WebElement element) {
        try {
            waitForElementVisible(driver, element);
            Actions act = new Actions(driver);
            act.moveToElement(element).click().build().perform();
        } catch (Exception e) {
            element.click();
        }
    }

    public boolean type(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayed(WebDriver driver, WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean jsClick(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void mouseHover(WebDriver driver, WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean selectByVisibleText(WebElement element, String visibleText) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectByValue(String value, WebElement element) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectByIndex(int index, WebElement element) {
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollByVisibilityOfElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String screenShot(WebDriver driver, String filename) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String directory = System.getProperty("user.dir") + "/Screenshots/";
        
        // Create directory if not exist
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdir();
        }
        
        String destination = directory + filename + "_" + timestamp + ".png";
        
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }


    private void waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}

