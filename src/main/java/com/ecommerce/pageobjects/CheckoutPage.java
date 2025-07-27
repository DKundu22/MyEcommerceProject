package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BaseClass {

    private static final Logger log = LogManager.getLogger(CheckoutPage.class);
    private final Action action = new Action();

    public CheckoutPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    private WebElement cartButton;

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//u[text()='Register / Login']")
    private WebElement registerLoginBtn;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement commentTextArea;

    @FindBy(xpath = "//a[text()='Place Order']")
    private WebElement placeOrderBtn;

    @FindBy(name = "name_on_card")
    private WebElement nameOnCard;

    @FindBy(name = "card_number")
    private WebElement cardNumber;

    @FindBy(name = "cvc")
    private WebElement cvc;

    @FindBy(name = "expiry_month")
    private WebElement expiryMonth;

    @FindBy(name = "expiry_year")
    private WebElement expiryYear;

    @FindBy(id = "submit")
    private WebElement payAndConfirmOrderBtn;

    @FindBy(xpath = "//p[contains(text(),'Congratulations! Your order has been confirmed!')]")
    private WebElement successMessage;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    private WebElement deleteAccountLink;

    @FindBy(xpath = "//b[contains(text(),'Account Deleted!')]")
    private WebElement accountDeletedMsg;

    public void clickCart() {
        try {
            action.click(getDriver(), cartButton);
            log.info("Clicked on Cart button.");
        } catch (Exception e) {
            log.error("Failed to click on Cart button.", e);
            throw e;
        }
    }

    public void clickProceedToCheckout() {
        try {
            action.click(getDriver(), proceedToCheckoutBtn);
            log.info("Clicked on Proceed To Checkout button.");
        } catch (Exception e) {
            log.error("Failed to click Proceed To Checkout.", e);
            throw e;
        }
    }

    public void clickRegisterLogin() {
        try {
            action.click(getDriver(), registerLoginBtn);
            log.info("Clicked on Register/Login link.");
        } catch (Exception e) {
            log.error("Failed to click on Register/Login.", e);
            throw e;
        }
    }

    public void enterOrderComment(String comment) {
        try {
            action.type(commentTextArea, comment);
            log.info("Entered comment for order.");
        } catch (Exception e) {
            log.error("Failed to enter order comment.", e);
            throw e;
        }
    }

    public void clickPlaceOrder() {
        try {
            action.click(getDriver(), placeOrderBtn);
            log.info("Clicked on Place Order button.");
        } catch (Exception e) {
            log.error("Failed to click on Place Order.", e);
            throw e;
        }
    }

    public void enterPaymentDetails(String name, String cardNum, String cvcVal, String expMonth, String expYear) {
        try {
            action.type(nameOnCard, name);
            action.type(cardNumber, cardNum);
            action.type(cvc, cvcVal);
            action.type(expiryMonth, expMonth);
            action.type(expiryYear, expYear);
            log.info("Entered payment details.");
        } catch (Exception e) {
            log.error("Failed to enter payment details.", e);
            throw e;
        }
    }

    public void clickPayAndConfirm() {
        try {
            action.click(getDriver(), payAndConfirmOrderBtn);
            log.info("Clicked on Pay and Confirm Order.");
        } catch (Exception e) {
            log.error("Failed to click Pay and Confirm Order.", e);
            throw e;
        }
    }

    public boolean isOrderSuccessMessageVisible() {
        try {
        	  // FluentWait for WebElement
            action.waitForCondition(getDriver(),
                    ExpectedConditions.visibilityOf(successMessage),
                    15,
                    500
            );

            boolean visible = action.isDisplayed(getDriver(), successMessage);
            log.info("Order success message visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Failed to verify order success message.", e);
            return false;
        }
    }

    public void deleteAccount() {
        try {
            action.click(getDriver(), deleteAccountLink);
            log.info("Clicked Delete Account.");
        } catch (Exception e) {
            log.error("Failed to click Delete Account.", e);
            throw e;
        }
    }

    public boolean isAccountDeletedVisible() {
        try {
            boolean visible = action.isDisplayed(getDriver(), accountDeletedMsg);
            log.info("Account Deleted message visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Failed to verify account deleted message.", e);
            return false;
        }
    }
}
