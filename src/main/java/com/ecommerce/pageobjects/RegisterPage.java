package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Page Object class representing the Register Page.
 * Handles user registration and related verifications.
 */
public class RegisterPage extends BaseClass {

    private static final Logger log = LogManager.getLogger(RegisterPage.class);
    private final Action action = new Action();

    // Page elements
    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    private WebElement signupLoginLink;

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Signup')]")
    private WebElement signupBtn;

    @FindBy(id = "id_gender1")
    private WebElement mrTitleRadio;

    @FindBy(id = "id_gender2")
    private WebElement mrsTitleRadio;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement offersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "address2")
    private WebElement address2;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "zipcode")
    private WebElement zipcode;

    @FindBy(id = "mobile_number")
    private WebElement mobile;

    @FindBy(xpath = "//button[contains(text(),'Create Account')]")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//b[contains(text(),'Account Created!')]")
    private WebElement accountCreatedMsg;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAsUsername;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    private WebElement deleteAccountLink;

    @FindBy(xpath = "//b[contains(text(),'Account Deleted!')]")
    private WebElement accountDeletedMsg;

    // Constructor
    public RegisterPage() {
        PageFactory.initElements(getDriver(), this);
    }
    
 // Helper method to remove decimal from dropdown values
    private String cleanDropdownValue(String value) {
        if (value == null) return "";
        return value.replace(".0", "").trim();
    }

    /**
     * Navigates to the Signup/Login page.
     */
    public void navigateToRegister() {
        try {
            action.click(getDriver(), signupLoginLink);
            log.info("Clicked on Signup/Login link.");
        } catch (Exception e) {
            log.error("Failed to click on Signup/Login link.", e);
            throw e;
        }
    }

    /**
     * Enters user name and email for initial signup.
     */
    public void enterSignupDetails(String name, String email) {
        try {
            log.debug("Entering signup name: " + name);
            action.type(nameField, name);

            log.debug("Entering signup email: " + email);
            action.type(emailField, email);

            action.click(getDriver(), signupBtn);
            log.info("Signup details submitted.");
        } catch (Exception e) {
            log.error("Failed to enter signup details.", e);
            throw e;
        }
    }

    /**
     * Selects user title (Mr or Mrs).
     */
    public void selectTitle(String title) {
        try {
            if (title.equalsIgnoreCase("Mr")) {
                action.click(getDriver(), mrTitleRadio);
                log.debug("Selected title: Mr");
            } else if (title.equalsIgnoreCase("Mrs")) {
                action.click(getDriver(), mrsTitleRadio);
                log.debug("Selected title: Mrs");
            } else {
                throw new IllegalArgumentException("Invalid title: " + title);
            }
        } catch (Exception e) {
            log.error("Failed to select title.", e);
            throw e;
        }
    }

    /**
     * Fills all account details using provided data.
     */
    public void fillAccountDetails(Map<String, String> data) {
        try {
            selectTitle(data.get("Title"));
            action.type(passwordField, data.get("Password"));
            
            // Clean dropdown values to avoid decimal issues like 12.0
            String day = cleanDropdownValue(data.get("Day"));
            String month = cleanDropdownValue(data.get("Month"));
            String year = cleanDropdownValue(data.get("Year"));

            action.selectByVisibleText(dayDropdown, day);
            action.selectByVisibleText(monthDropdown, month);
            action.selectByVisibleText(yearDropdown, year);
            log.debug("Selecting DOB: " + day + "-" + month + "-" + year);


            if (Boolean.parseBoolean(data.get("Newsletter"))) {
                action.click(getDriver(), newsletterCheckbox);
                log.debug("Subscribed to newsletter.");
            }

            if (Boolean.parseBoolean(data.get("Offers"))) {
                action.click(getDriver(), offersCheckbox);
                log.debug("Subscribed to special offers.");
            }

            action.type(firstName, data.get("FirstName"));
            action.type(lastName, data.get("LastName"));
            action.type(company, data.get("Company"));
            action.type(address1, data.get("Address"));
            action.type(address2, data.get("Address2"));
            action.selectByVisibleText(country, data.get("Country"));
            action.type(state, data.get("State"));
            action.type(city, data.get("City"));
            action.type(zipcode, data.get("Zip"));
            action.type(mobile, data.get("Mobile"));

            action.click(getDriver(), createAccountBtn);
            log.info("Account details submitted for creation.");
        } catch (Exception e) {
            log.error("Failed to fill account details.", e);
            throw new RuntimeException("Failed to fill account details: " + e.getMessage(), e);
        }
    }

    /**
     * Verifies if the 'Account Created!' message is displayed.
     */
    public boolean isAccountCreatedVisible() {
        try {
            boolean visible = action.isDisplayed(getDriver(), accountCreatedMsg);
            log.info("'Account Created!' message visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Failed to verify account creation message.", e);
            return false;
        }
    }

    /**
     * Clicks the 'Continue' button post account creation.
     */
    public void clickContinue() {
        try {
            action.click(getDriver(), continueButton);
            log.info("Clicked 'Continue' button after account creation.");
        } catch (Exception e) {
            log.error("Failed to click on 'Continue' button.", e);
            throw e;
        }
    }

    /**
     * Verifies if 'Logged in as [username]' is displayed.
     */
    public boolean isLoggedInAsVisible() {
        try {
            boolean visible = action.isDisplayed(getDriver(), loggedInAsUsername);
            log.info("'Logged in as' text visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Failed to verify 'Logged in as' text.", e);
            return false;
        }
    }

    /**
     * Deletes the current account.
     */
    public void deleteAccount() {
        try {
            action.click(getDriver(), deleteAccountLink);
            log.info("Clicked 'Delete Account' link.");
        } catch (Exception e) {
            log.error("Failed to click 'Delete Account'.", e);
            throw e;
        }
    }

    /**
     * Verifies if the 'Account Deleted!' message is visible.
     */
    public boolean isAccountDeletedVisible() {
        try {
            boolean visible = action.isDisplayed(getDriver(), accountDeletedMsg);
            log.info("'Account Deleted!' message visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Failed to verify account deletion message.", e);
            return false;
        }
    }
}
