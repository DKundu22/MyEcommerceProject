package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseClass {

    Action action = new Action();

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

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean isHomePageVisible() {
        return action.isDisplayed(getDriver(), homePageLogo);
    }

    public void goToLoginPage() {
        action.click(getDriver(), signupLoginLink);
    }

//    public boolean isLoginTitleVisible() {
//        return action.isDisplayed(getDriver(), loginTitle);
//    }

    public boolean isLoginTitleVisible() {
        // Explicit wait to ensure the title is visible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.visibilityOf(loginTitle));
            return true; // The title is visible
        } catch (Exception e) {
            return false; // Title not visible within the wait time
        }
    }
    
    public void doLogin(String email, String password) {
        action.type(emailInput, email);
        action.type(passwordInput, password);
        action.click(getDriver(), loginBtn);
    }

    public String getLoggedInUsername() {
        String actualText = loggedInText.getText(); // "Logged in as Dinesh Paul"
        return actualText.replace("Logged in as ", "").trim();
    }

}

