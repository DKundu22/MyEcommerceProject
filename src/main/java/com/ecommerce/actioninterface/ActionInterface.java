
package com.ecommerce.actioninterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Interface defining all reusable actions for interacting with web elements.
 */

public interface ActionInterface {

    void click(WebDriver driver, WebElement element);
    void clearText(WebElement element);
    void doubleClick(WebDriver driver, WebElement element);
    void dragAndDrop(WebDriver driver, WebElement source, WebElement target);
    void enterKeyPress(WebDriver driver);
    void explicitWaitForAlert(WebDriver driver);
    String getAttribute(WebElement element, String attribute);
    String getCssValue(WebElement element, String propertyName);
    String getPageTitle(WebDriver driver);
    String getText(WebElement element);
    boolean isAlertPresent(WebDriver driver);
    boolean isDisplayed(WebDriver driver, WebElement element);
    boolean isElementEnabled(WebElement element);
    boolean isElementSelected(WebElement element);
    boolean jsClick(WebDriver driver, WebElement element);
    void mouseHover(WebDriver driver, WebElement element);
    void moveToElementAndClick(WebDriver driver, WebElement element);
    void rightClick(WebDriver driver, WebElement element);
    String screenShot(WebDriver driver, String filename);
    boolean selectByIndex(int index, WebElement element);
    boolean selectByValue(String value, WebElement element);
    boolean selectByVisibleText(WebElement element, String visibleText);
    boolean sendKeysUsingActions(WebDriver driver, WebElement element, String value);
    boolean type(WebElement element, String text);
    void scrollByVisibilityOfElement(WebDriver driver, WebElement element);
    void scrollIntoView(WebDriver driver, WebElement element);
    void waitForElementClickable(WebDriver driver, WebElement element);
    void waitForElementVisible(WebDriver driver, WebElement element);
}
