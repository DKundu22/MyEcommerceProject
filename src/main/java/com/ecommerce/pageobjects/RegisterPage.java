package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

import java.util.Map;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BaseClass {
    Action action = new Action();

    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    private WebElement signupLoginLink;

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Signup')]")
    private WebElement signupBtn;

    @FindBy(xpath = "//input[@id='id_gender1']")
    private WebElement mrTitleRadio;

    @FindBy(xpath = "//input[@id='id_gender2']")
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


    public RegisterPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void navigateToRegister() {
        action.click(getDriver(), signupLoginLink);
    }

    public void enterSignupDetails(String name, String email) {
        action.type(nameField, name);
        action.type(emailField, email);
        action.click(getDriver(), signupBtn);
    }

    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            action.click(getDriver(), mrTitleRadio);
        } else if (title.equalsIgnoreCase("Mrs")) {
            action.click(getDriver(), mrsTitleRadio);
        } else {
            throw new IllegalArgumentException("Invalid title: " + title + ". Use 'Mr' or 'Mrs'.");
        }
    }

    public void fillAccountDetails(Map<String, String> data) {
       // action.click(getDriver(), mrTitleRadio);
    	
    	selectTitle(data.get("Title"));

        action.type(passwordField, data.get("Password"));

//        action.selectByVisibleText(dayDropdown, "1");
//        action.selectByVisibleText(monthDropdown, "January");
//        action.selectByVisibleText(yearDropdown, "1990");

        action.selectByVisibleText(dayDropdown, data.get("Day"));
        action.selectByVisibleText(monthDropdown, data.get("Month"));
        action.selectByVisibleText(yearDropdown, data.get("Year"));

        if (Boolean.parseBoolean(data.get("Newsletter")))
            action.click(getDriver(), newsletterCheckbox);

        if (Boolean.parseBoolean(data.get("Offers")))
            action.click(getDriver(), offersCheckbox);

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
    }

    public boolean isAccountCreatedVisible() {
        return action.isDisplayed(getDriver(), accountCreatedMsg);
    }
    
    public void clickContinue() {
        action.click(getDriver(), continueButton);
    }

    public boolean isLoggedInAsVisible() {
        return action.isDisplayed(getDriver(), loggedInAsUsername);
    }

    public void deleteAccount() {
        action.click(getDriver(), deleteAccountLink);
    }

    public boolean isAccountDeletedVisible() {
        return action.isDisplayed(getDriver(), accountDeletedMsg);
    }
}
