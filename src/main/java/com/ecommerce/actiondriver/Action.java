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
     * Clar text from a WebElement.
     */
    public void clearText(WebElement element) {
        try {
            element.clear();
            Log.info("Cleared text from element.");
        } catch (Exception e) {
            Log.error("Failed to clear text.", e);
        }
    }

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
     * Performs drag and drop from source to target element.
     */
    public void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        try {
            Actions actions = new Actions(driver);
            actions.dragAndDrop(source, target).perform();
            Log.info("Dragged element from source to target.");
        } catch (Exception e) {
            Log.error("Drag-and-drop failed.", e);
            throw e;
        }
    }

    /**
     * Custom exception for element not found.
     */
    public static class ElementNotFoundException extends RuntimeException {
        public ElementNotFoundException(String message) {
            super(message);
        }
    }

     /**
     * Presses the Enter key using Actions class.
     */
    public void enterKeyPress(WebDriver driver) {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ENTER).perform();
            Log.info("Enter key pressed.");
        } catch (Exception e) {
            Log.error("Enter key press failed.", e);
        }
    }

    /**
     * Waits for an alert to be present.
     */
    public void explicitWaitForAlert(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.alertIsPresent());
            Log.info("Alert is present.");
        } catch (Exception e) {
            Log.error("Alert wait failed.", e);
        }
    }

    /**
     * Returns attribute value from a WebElement.
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
     * Checks if an element is enabled.
     */
    public String getCssValue(WebElement element, String propertyName) {
        try {
            return element.getCssValue(propertyName);
        } catch (Exception e) {
            Log.error("Failed to get CSS value.", e);
            return null;
        }
    }

    /**
     * Returns text from a WebElement.
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
     * Get the page title from any web page.
     */
    public String getPageTitle(WebDriver driver) {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            Log.error("Failed to get page title.", e);
            return null;
        }
    }

    
    /**
     * Highlights element for debugging.
     */
    public void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            Log.info("Highlighted element: " + element);
        } catch (Exception e) {
            Log.error("Failed to highlight element.", e);
        }
    }

    /**
     * Checks if an alert is present.
     */
    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
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
     * Checks if a WebElement is enabled.
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
     * Checks if a WebElement is selected.
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
     * Move to an element and click it.
     */
    public void moveToElementAndClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            Log.info("Moved to element and clicked.");
        } catch (Exception e) {
            Log.error("Move and click failed.", e);
        }
    }


    /**
     * Performs right-click on a WebElement.
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
     * Retries clicking on a WebElement up to a maximum number of attempts.
     */
    public void retryClick(WebDriver driver, WebElement element, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                waitForElementClickable(driver, element);
                element.click();
                Log.info("Clicked on element: " + element);
                return;
            } catch (Exception e) {
                attempts++;
                Log.warn("Retrying click on element: " + element + " (Attempt " + attempts + ")");
            }
        }
        Log.error("Failed to click on element after " + maxRetries + " attempts.");
        throw new RuntimeException("Failed to click element: " + element);
    }

    /**
     * Retries JavaScript click on a WebElement.
     */
    public boolean retryJsClick(WebDriver driver, WebElement element, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                Log.info("JS Clicked element.");
                return true;
            } catch (Exception e) {
                attempts++;
                Log.warn("Retrying JS click on element: " + element + " (Attempt " + attempts + ")");
            }
        }
        Log.error("Failed to JS click element after " + maxRetries + " attempts.");
        return false;
    }

    /**
     * Retries selecting dropdown option by visible text.
     */
    public boolean retrySelectByVisibleText(WebElement element, String visibleText, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                new Select(element).selectByVisibleText(visibleText);
                Log.info("Selected option by visible text: " + visibleText);
                return true;
            } catch (Exception e) {
                attempts++;
                Log.warn("Retrying select by visible text: " + visibleText + " (Attempt " + attempts + ")");
            }
        }
        Log.error("Failed to select by visible text after " + maxRetries + " attempts.");
        return false;
    }

    /**
     * Captures screenshot and returns file path.
     */
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
            Log.error("Screenshot capture failed.", e);
        }
        return fullPath;
    }

    /**
     * Scrolls element into view using JavaScript.
     */
    public void scrollByVisibilityOfElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Log.error("Scroll by visibility failed", e);
        }
    }

    /**
     * Scrolls the page to bring the element into view.
     */
    public void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            Log.info("Scrolled element into view.");
        } catch (Exception e) {
            Log.error("Scroll into view failed.", e);
        }
    }

    /**
     * Scrolls the entire page to the bottom.
     */
    public void scrollToBottom(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Log.info("Scrolled to the bottom of the page.");
        } catch (Exception e) {
            Log.error("Failed to scroll to the bottom of the page.", e);
            throw e;
        }
    }

    /**
     * Selects dropdown option by index.
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
     * Selects dropdown option by value.
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
     * Selects dropdown option by visible text.
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
     * Sends keys to an element using Actions class.
     */
    public boolean sendKeysUsingActions(WebDriver driver, WebElement element, String value) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().sendKeys(value).build().perform();
            Log.info("Sent keys using Actions.");
            return true;
        } catch (Exception e) {
            Log.error("Failed to send keys using Actions.", e);
            return false;
        }
    }

    /**
     * Types into an input field after clearing it.
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
     * Waits for a custom expected condition using FluentWait.
     */
    public void waitForCondition(WebDriver driver, ExpectedCondition<?> condition, int timeout, int pollingTime) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(pollingTime))
                    .ignoring(NoSuchElementException.class);
            wait.until(condition);
            Log.info("Condition met: " + condition);
        } catch (TimeoutException e) {
            Log.error("Condition not met within timeout: " + condition, e);
            throw e;
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
     * Waits for page to load completely.
     */
    public void waitForPageLoad(WebDriver driver) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Log.info("Page loaded completely.");
        } catch (TimeoutException e) {
            Log.error("Page did not load completely within timeout.", e);
            throw e;
        }
    }
}
