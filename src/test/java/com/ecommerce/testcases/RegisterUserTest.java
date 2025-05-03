package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.RegisterPage;
import com.ecommerce.utility.ExcelUtil;
import com.ecommerce.utility.Log;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class RegisterUserTest extends BaseClass {
    Logger log = LogManager.getLogger(RegisterUserTest.class);

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        List<Map<String, String>> testData = ExcelUtil.getTestData("Sheet1");
        Object[][] data = new Object[testData.size()][1];

        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "userData")
    public void registerUser(Map<String, String> userData) {
    	
        Log.startTestCase("resiterUser");

        RegisterPage registerPage = new RegisterPage();
        log.info("Navigating to Signup/Login Page...");
        registerPage.navigateToRegister();

        log.info("Entering signup details with name: " + userData.get("Name") + " and email: " + userData.get("Email"));
        registerPage.enterSignupDetails(userData.get("Name"), userData.get("Email"));

        log.info("Filling user registration details...");
        registerPage.fillAccountDetails(userData);

        log.info("Verifying account creation...");
        Assert.assertTrue(registerPage.isAccountCreatedVisible(), "Account creation failed.");
        log.info("Account created successfully.");
        
        // Click Continue
        registerPage.clickContinue();

        // Verify 'Logged in as username' is visible
        Assert.assertTrue(registerPage.isLoggedInAsVisible(), "'Logged in as username' not visible.");


        log.info("Attempting to delete the created account...");
        registerPage.deleteAccount();
        Assert.assertTrue(registerPage.isAccountDeletedVisible(), "Account deletion failed.");
        log.info("Account deleted successfully.");
        
     // Click Continue after deletion
        registerPage.clickContinue();
        
        Log.endTestCase("resiterUser");


    }
}

