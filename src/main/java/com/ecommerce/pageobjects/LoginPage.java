package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object class representing the Login Page.
 * Handles interactions and logs steps with appropriate log levels.
 */
public class LoginPage extends BaseClass {

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final Action action = new Action();

    // Page elements
    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    private WebElement homePageLogo;

    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    private WebElement loginTitle;

    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInText;

    // Constructor to initialize WebElements
    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Verifies that the homepage logo is visible.
     */
    public boolean isHomePageVisible() {
        try {
            boolean displayed = action.isDisplayed(getDriver(), homePageLogo);
            log.info("Homepage logo visibility check passed.");
            log.debug("Logo displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            log.error("Home page logo is not visible.", e);
            return false;
        }
    }

    /**
     * Clicks the 'Signup/Login' link.
     */
    public void goToLoginPage() {
        try {
            action.click(getDriver(), signupLoginLink);
            log.info("Clicked on Signup/Login link.");
        } catch (Exception e) {
            log.error("Unable to click on Signup/Login link.", e);
            throw e;
        }
    }

    /**
     * Waits for and verifies that the login form title is visible.
     */
    public boolean isLoginTitleVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(loginTitle));
            log.info("Login form title is visible.");
            return true;
        } catch (Exception e) {
            log.error("Login form title not visible.", e);
            return false;
        }
    }

    /**
     * Performs login with given credentials.
     *
     * @param email    the user's email
     * @param password the user's password
     */
    public void doLogin(String email, String password) {
        try {
            log.debug("Attempting to enter email: " + email);
            action.type(emailInput, email);

            log.debug("Attempting to enter password.");
            action.type(passwordInput, password);

            log.info("Clicking Login button.");
            action.click(getDriver(), loginBtn);

            log.info("Login submitted successfully.");
        } catch (Exception e) {
            log.error("Login action failed.", e);
            throw e;
        }
    }

    /**
     * Retrieves the logged-in user's name after successful login.
     *
     * @return the username or null if retrieval fails
     */
    public String getLoggedInUsername() {
        try {
            String actualText = loggedInText.getText(); // e.g., "Logged in as John"
            String username = actualText.replace("Logged in as ", "").trim();
            log.info("Retrieved logged-in username.");
            log.debug("Username extracted: " + username);
            return username;
        } catch (Exception e) {
            log.error("Unable to fetch logged-in username.", e);
            return null;
        }
    }
}
