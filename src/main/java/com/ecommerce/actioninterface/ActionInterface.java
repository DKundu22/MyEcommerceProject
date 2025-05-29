package com.ecommerce.actioninterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Interface defining all reusable actions for interacting with web elements.
 */

public interface ActionInterface {
    void click(WebDriver driver, WebElement element);
    boolean type(WebElement element, String text);
    boolean isDisplayed(WebDriver driver, WebElement element);
    boolean jsClick(WebDriver driver, WebElement element);
    void mouseHover(WebDriver driver, WebElement element);
    boolean selectByVisibleText(WebElement element, String visibleText);
    boolean selectByValue(String value, WebElement element);
    boolean selectByIndex(int index, WebElement element);
    void scrollByVisibilityOfElement(WebDriver driver, WebElement element);
    void scrollIntoView(WebDriver driver, WebElement element);
    void waitForElementVisible(WebDriver driver, WebElement element);
    void waitForElementClickable(WebDriver driver, WebElement element);
    String getText(WebElement element);
    String getAttribute(WebElement element, String attribute);
    void doubleClick(WebDriver driver, WebElement element);
    void rightClick(WebDriver driver, WebElement element);
    boolean isElementEnabled(WebElement element);
    boolean isElementSelected(WebElement element);
    //String screenShot(WebDriver driver, String filename);
}
