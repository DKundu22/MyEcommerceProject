package com.ecommerce.actiondriver;

import com.ecommerce.actioninterface.ActionInterface;
import com.ecommerce.utility.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * Implements reusable WebDriver actions like click, type, wait, select, etc.
 */

public class Action implements ActionInterface {

    private static final int TIMEOUT = 30;
    
    /**
     * Clicks on a WebElement after waiting until it is clickable.
     */
    public void click(WebDriver driver, WebElement element) {
        try {
            waitForElementClickable(driver, element);
            element.click();
            Log.info("Clicked on element: " + element);
        } catch (Exception e) {
            Log.error("Click failed on element: " + element, e);
            throw new RuntimeException("Failed to click element", e);
        }
    }
    
    /**
     * Clears and types text into a WebElement input field.
     */
    public boolean type(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            Log.info("Typed text: " + text);
            return true;
        } catch (Exception e) {
            Log.error("Typing failed for element: " + element, e);
            return false;
        }
    }

    /**
     * Checks if an element is displayed.
     */
    public boolean isDisplayed(WebDriver driver, WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            Log.warn("Element not displayed: " + element);
            return false;
        }
    }

    /**
     * Performs JavaScript-based click on a WebElement.
     */
    public boolean jsClick(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            Log.info("JS Clicked element");
            return true;
        } catch (Exception e) {
            Log.error("JS Click failed", e);
            return false;
        }
    }

   
    /**
     * Performs mouse hover on a WebElement.
     */
    public void mouseHover(WebDriver driver, WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
            Log.info("Mouse hovered on element");
        } catch (Exception e) {
            Log.error("Mouse hover failed", e);
        }
    }

    /**
     * Selects an option from a dropdown by visible text.
     */
    public boolean selectByVisibleText(WebElement element, String visibleText) {
        try {
            new Select(element).selectByVisibleText(visibleText);
            return true;
        } catch (Exception e) {
            Log.error("Select by visible text failed", e);
            return false;
        }
    }

    /**
     * Selects an option from a dropdown by value.
     */
    public boolean selectByValue(String value, WebElement element) {
        try {
            new Select(element).selectByValue(value);
            return true;
        } catch (Exception e) {
            Log.error("Select by value failed", e);
            return false;
        }
    }

    /**
     * Selects an option from a dropdown by index.
     */
    public boolean selectByIndex(int index, WebElement element) {
        try {
            new Select(element).selectByIndex(index);
            return true;
        } catch (Exception e) {
            Log.error("Select by index failed", e);
            return false;
        }
    }

    /**
     * Scrolls to a WebElement using JavaScript.
     */
    public void scrollByVisibilityOfElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Log.error("Scroll by visibility failed", e);
        }
    }

    /**
     * Brings an element into view.
     */
    public void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Log.error("Scroll into view failed", e);
        }
    }
    
    /**
     * Waits for an element to be visible.
     */
    public void waitForElementVisible(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            Log.error("Element not visible after timeout", e);
        }
    }

    /**
     * Waits for an element to be clickable.
     */
    public void waitForElementClickable(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            Log.error("Element not clickable after timeout", e);
        }
    }
    
    /**
     * Returns text from an element.
     */
    public String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            Log.error("Failed to get text", e);
            return null;
        }
    }

    /**
     * Gets attribute value from an element.
     */
    public String getAttribute(WebElement element, String attribute) {
        try {
            return element.getAttribute(attribute);
        } catch (Exception e) {
            Log.error("Failed to get attribute: " + attribute, e);
            return null;
        }
    }

    /**
     * Performs double-click on an element.
     */
    public void doubleClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
        } catch (Exception e) {
            Log.error("Double click failed", e);
        }
    }

    /**
     * Performs right-click on an element.
     */
    public void rightClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
        } catch (Exception e) {
            Log.error("Right click failed", e);
        }
    }

    /**
     * Checks if an element is enabled.
     */
    public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            Log.error("Failed to check if element is enabled", e);
            return false;
        }
    }

    /**
     * Checks if an element is selected.
     */
    public boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            Log.error("Failed to check if element is selected", e);
            return false;
        }
    }

    /**
     * Captures screenshot and returns file path.
     
    public String screenShot(WebDriver driver, String filename) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String directory = System.getProperty("user.dir") + "/Screenshots/";
        File folder = new File(directory);
        if (!folder.exists()) folder.mkdir();

        String fullPath = directory + filename + "_" + timestamp + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));
            Log.info("Screenshot captured at: " + fullPath);
        } catch (IOException e) {
            Log.error("Screenshot capture failed", e);
        }
        return fullPath;
    } */
}
