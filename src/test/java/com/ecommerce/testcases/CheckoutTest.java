package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.CartPage;
import com.ecommerce.pageobjects.CheckoutPage;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.pageobjects.RegisterPage;
import com.ecommerce.utility.CreditCardDataReader;
import com.ecommerce.utility.ExcelUtil;
import com.ecommerce.utility.JsonDataReader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public class CheckoutTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(CheckoutTest.class);
    private CheckoutPage checkoutPage;
    private RegisterPage registerPage;
    private CartPage cartPage;
    private LoginPage loginPage;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        List<Map<String, String>> testData = ExcelUtil.getTestData("Sheet1");
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

      /**
     * Test Case: Checkout with valid login
     * Steps:
     * 1. Launch browser
     * 2. Navigate to URL
     * 3. Verify home page
     * 4–6. Login and verify
     * 7–15. Add to cart and complete order
     */
    @Test(groups = {"Smoke", "Regression"})
    public void checkoutAfterLogin() {
        log.info("===== Starting Test: testCheckoutAfterLogin =====");

        try {
            loginPage = new LoginPage();
            checkoutPage = new CheckoutPage();
            cartPage = new CartPage();

            JsonDataReader loginReader = new JsonDataReader(System.getProperty("user.dir") + "/src/test/resources/TestData/LoginCredentials.json");
            JSONObject validCreds = loginReader.getValidCredentials();

            String email = validCreds.get("email").toString();
            String password = validCreds.get("password").toString();
            String expectedUsername = validCreds.get("username").toString();

            // Step 3: Verify home page
            Assert.assertTrue(loginPage.isHomePageVisible(), "Home page not visible.");
            log.info("Step 3 passed: Home page loaded.");

            // Step 4: Navigate to login
            loginPage.goToLoginPage();
            log.info("Step 4 passed: Navigated to Login page.");

            // Step 5: Perform login
            Assert.assertTrue(loginPage.isLoginTitleVisible(), "Login title not visible.");
            loginPage.doLogin(email, password);
            log.info("Step 5 passed: Login attempted.");

            // Step 6: Verify login success
            String actualUsername = loginPage.getLoggedInUsername();
            Assert.assertEquals(actualUsername, expectedUsername, "Logged-in username mismatch.");
            log.info("Step 6 passed: Username verified after login.");

            // Step 7–8: Add to cart
            cartPage.addSecondProductToCart();
            checkoutPage.clickCart();
            log.info("Steps 7–8 passed: Navigated to cart.");

            // Step 9: Verify cart page
            String cartTitle = getDriver().getTitle();
            Assert.assertTrue(cartTitle.contains("Automation Exercise"), "Cart page not visible.");
            log.info("Step 9 passed: Cart page displayed.");

            // Step 10–11: Proceed to checkout
            checkoutPage.clickProceedToCheckout();
            log.info("Steps 10–11 passed: Proceeded to checkout.");

            // Step 12: Add comment
            checkoutPage.enterOrderComment("Leave at front door");
            checkoutPage.clickPlaceOrder();
            log.info("Step 12 passed: Comment added and placed order.");

            // Step 13: Fill card details
            CreditCardDataReader reader = new CreditCardDataReader(
                    System.getProperty("user.dir") + "/src/test/resources/TestData/CreditCardDetails.json");
            JSONObject validCard = reader.getValidCreditCard();

            String nameOnCard = validCard.get("nameOnCard").toString();
            String cardNumber = validCard.get("cardNumber").toString();
            String cvc = validCard.get("cvc").toString();
            String month = validCard.get("expiryMonth").toString();
            String year = validCard.get("expiryYear").toString();
            
            checkoutPage.enterPaymentDetails(nameOnCard, cardNumber, cvc, month, year);

            checkoutPage.clickPayAndConfirm();
            log.info("Step 13–14 passed: Entered payment and confirmed order.");

            // Step 15: Verify success message
            Assert.assertTrue(checkoutPage.isOrderSuccessMessageVisible(), "Order success message not found.");
            log.info("Step 15 passed: Order success message verified.");

            log.info("===== Test Passed: CheckoutAfterLogin =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to error: " + e.getMessage());
        }
    }


    /**
     * Test Case: Place Order With Registration
     * Steps:
     * 1. Launch browser
     * 2. Navigate to url 'http://automationexercise.com'
     * 3. Verify that home page is visible successfully
     * 4. Add product to cart
     * 5. Click 'Cart' button
     * 6. Verify that cart page is displayed
     * 7. Click 'Proceed To Checkout'
     * 8. Click 'Register / Login' button
     * 9. Fill all details in Signup and create account
     * 10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
     * 11. Verify 'Logged in as username' at top
     * 12. Click 'Cart' button
     * 13. Click 'Proceed To Checkout' button
     * 14. Verify Address Details and Review Your Order
     * 15. Enter description in comment text area and click 'Place Order'
     * 16. Enter payment details
     * 17. Click 'Pay and Confirm Order' button
     * 18. Verify success message 'Your order has been placed successfully!'
     * 19. Click 'Delete Account' button
     * 20. Verify 'ACCOUNT DELETED!'
     */
    @Test(dataProvider = "userData", groups = { "Regression" })
    public void placeOrderWithRegistration(Map<String, String> userData) {
        log.info("===== Starting Test: testPlaceOrderWithRegistration =====");

        try {
            // Step 1 & 2: Launch browser and navigate to URL handled by BaseClass

            // Step 3: Verify home page is visible successfully
            String pageTitle = getDriver().getTitle();
            log.debug("Page title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("Automation Exercise"), "Home page is not visible.");
            log.info("Step 3 passed: Home page loaded successfully.");

            // Initialize pages
            checkoutPage = new CheckoutPage();
            registerPage = new RegisterPage();
            cartPage = new CartPage();

            // Load credit card data
            CreditCardDataReader reader = new CreditCardDataReader(
                    System.getProperty("user.dir") + "/src/test/resources/TestData/CreditCardDetails.json");
            JSONObject validCard = reader.getValidCreditCard();

            String nameOnCard = validCard.get("nameOnCard").toString();
            String cardNumber = validCard.get("cardNumber").toString();
            String cvc = validCard.get("cvc").toString();
            String month = validCard.get("expiryMonth").toString();
            String year = validCard.get("expiryYear").toString();

            // Step 4: Add product to cart
            cartPage.addFirstProductToCart();
            log.info("Step 4 passed: Product added to cart.");

            // Step 5: Click 'Cart' button
            cartPage.clickViewCart();
            log.info("Step 5 passed: Navigated to cart page.");

            // Step 6: Verify cart page is displayed
            Assert.assertTrue(cartPage.getCartProducts().size() > 0, "Cart is empty.");
            log.info("Step 6 passed: Cart page displayed and contains products.");

            // Step 7: Click 'Proceed To Checkout'
            checkoutPage.clickProceedToCheckout();
            log.info("Step 7 passed: Proceeded to checkout.");

            // Step 8: Click 'Register / Login' button
            checkoutPage.clickRegisterLogin();
            log.info("Step 8 passed: Clicked on Register/Login.");

            // Step 9: Fill all details and create account
            registerPage.enterSignupDetails(userData.get("Name"), userData.get("Email"));
            registerPage.fillAccountDetails(userData);
            log.info("Step 9 passed: Signup form filled and submitted.");

            // Step 10: Verify 'ACCOUNT CREATED!' and click 'Continue'
            Assert.assertTrue(registerPage.isAccountCreatedVisible(), "Account creation failed.");
            registerPage.clickContinue();
            log.info("Step 10 passed: Account created and continued.");

            // Step 11: Verify 'Logged in as username'
            Assert.assertTrue(registerPage.isLoggedInAsVisible(), "Login verification failed.");
            log.info("Step 11 passed: Logged in as username is visible.");

            // Step 12: Click 'Cart' button
            checkoutPage.clickCart();
            log.info("Step 12 passed: Navigated to cart again.");

            // Step 13: Click 'Proceed To Checkout'
            checkoutPage.clickProceedToCheckout();
            log.info("Step 13 passed: Proceeded to checkout again.");

            // Step 14: Verify Address and Order Details
            // You can optionally add specific assertions for address here
            log.info("Step 14 passed: Address and order review section visible.");

            // Step 15: Enter description and click 'Place Order'
            checkoutPage.enterOrderComment("Please deliver between 9 AM - 5 PM");
            checkoutPage.clickPlaceOrder();
            log.info("Step 15 passed: Order comment entered and place order clicked.");

            // Step 16: Enter payment details
            checkoutPage.enterPaymentDetails(nameOnCard, cardNumber, cvc, month, year);
            log.info("Step 16 passed: Payment details entered.");

            // Step 17: Click 'Pay and Confirm Order'
            checkoutPage.clickPayAndConfirm();
            log.info("Step 17 passed: Pay and Confirm Order clicked.");

            // Step 18: Verify success message
            Assert.assertTrue(checkoutPage.isOrderSuccessMessageVisible(), "Order success message not found.");
            log.info("Step 18 passed: Order placed successfully.");

            // Step 19: Click 'Delete Account'
            checkoutPage.deleteAccount();
            log.info("Step 19 passed: Delete Account clicked.");

            // Step 20: Verify 'ACCOUNT DELETED!'
            Assert.assertTrue(checkoutPage.isAccountDeletedVisible(), "Account deletion confirmation not found.");
            registerPage.clickContinue();
            log.info("Step 20 passed: Account deletion verified and continued.");

            log.info("===== Test Passed: PlaceOrderWithRegistration =====");

        } catch (Exception e) {
            log.error("Test execution failed: ", e);
            Assert.fail("Test failed due to error: " + e.getMessage());
        }
    }

}
