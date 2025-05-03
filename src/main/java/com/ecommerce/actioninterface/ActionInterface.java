
package com.ecommerce.actioninterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    String screenShot(WebDriver driver, String filename);
}

