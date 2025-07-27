package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.CartPage;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CartTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(CartTest.class);
    private CartPage cartPage;
    
    /**
     * Test Case 12: Verify adding multiple products to cart and validating their details.
     * Steps:
     * 1. Verify home page is loaded
     * 2. Navigate to Products page
     * 3. Add first product to cart
     * 4. Click 'Continue Shopping'
     * 5. Add second product to cart
     * 6. Click 'View Cart'
     * 7. Verify both products are added
     * 8. Verify prices, quantity, and total price for each product
     */

    @Test(groups = {"Smoke", "Regression"})
    public void addMultipleProductsToCartTest() {
        log.info("===== Starting Test: addMultipleProductsToCartTest =====");

        try {
            // Step 1: Verify home page is loaded
            String pageTitle = getDriver().getTitle();
            log.debug("Page title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("Automation Exercise"), "Home page not loaded.");
            log.info("Step 1 passed: Home page loaded successfully.");

            // Step 2: Navigate to Products page
            cartPage = new CartPage();
            log.debug("AddToCartPage object instantiated.");

            cartPage.clickProductsButton();
            log.info("Clicked on Products button.");

            String currentUrl = getDriver().getCurrentUrl();
            log.debug("Current URL: " + currentUrl);
            Assert.assertTrue(currentUrl.contains("products"), "Not navigated to Products page.");
            log.info("Step 2 passed: Navigated to Products page successfully.");

            // Step 3: Add first product to cart
            cartPage.addFirstProductToCart();
            log.info("Step 3 passed: First product added to cart.");

            // Step 4: Click 'Continue Shopping'
            cartPage.clickContinueShopping();
            log.info("Step 4 passed: Clicked Continue Shopping.");

            // Step 5: Add second product to cart
            cartPage.addSecondProductToCart();
            log.info("Step 5 passed: Second product added to cart.");

            // Step 6: Click 'View Cart'
            cartPage.clickViewCart();
            log.info("Step 6 passed: Clicked View Cart.");

            // Step 7: Verify both products are added
            List<WebElement> products = cartPage.getCartProducts();
            log.debug("Number of products in cart: " + products.size());
            Assert.assertEquals(products.size(), 2, "Both products not added to cart.");
            log.info("Step 7 passed: Verified both products are added to cart.");

            // Step 8: Verify prices, quantity, and total price
            List<WebElement> prices = cartPage.getProductPrices();
            List<WebElement> quantities = cartPage.getProductQuantities();
            List<WebElement> totals = cartPage.getProductTotalPrices();

            for (int i = 0; i < products.size(); i++) {
                String priceText = prices.get(i).getText().replaceAll("[^\\d]", "");
                String quantityText = quantities.get(i).getText().replaceAll("[^\\d]", "");
                String totalText = totals.get(i).getText().replaceAll("[^\\d]", "");

                int price = Integer.parseInt(priceText);
                int quantity = Integer.parseInt(quantityText);
                int expectedTotal = price * quantity;
                int actualTotal = Integer.parseInt(totalText);

                log.debug("Product " + (i + 1) + ": Price = " + price + ", Quantity = " + quantity + ", Total = " + actualTotal);
                Assert.assertEquals(actualTotal, expectedTotal,
                        "Mismatch in total price for product " + (i + 1));
            }

            log.info("Step 8 passed: Verified prices, quantities, and total prices for all products.");
            log.info("===== Test Passed: addMultipleProductsToCartTest =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
    
    
    /**
     * Test Case 13: Verify product quantity in Cart
     * Steps:
     * 1. Navigate to home page
     * 2. Click 'View Product'
     * 3. Set quantity to 4
     * 4. Add to cart
     * 5. View cart
     * 6. Verify quantity in cart is 4
     */
    @Test(groups = {"Regression"})
    public void verifyProductQuantityInCartTest() {
        log.info("===== Starting Test: verifyProductQuantityInCartTest =====");

        try {
            // Step 1: Verify home page is loaded
            String title = getDriver().getTitle();
            log.debug("Page title: " + title);
            Assert.assertTrue(title.contains("Automation Exercise"), "Home page is not visible.");
            log.info("Step 1 passed: Home page loaded successfully.");

            // Step 2: Instantiate CartPage and click 'View Product'
            cartPage = new CartPage();
            cartPage.clickViewProduct();
            log.info("Step 2 passed: Clicked on View Product.");

            // Step 3: Set quantity to 4
            cartPage.setProductQuantity("4");
            log.info("Step 3 passed: Set product quantity to 4.");

            // Step 4: Click 'Add to Cart'
            cartPage.clickAddToCart();
            log.info("Step 4 passed: Clicked Add to Cart.");

            // Step 5: Click 'View Cart'
            cartPage.clickViewCartFromDetailPage();
            log.info("Step 5 passed: Clicked View Cart.");

            // Step 6: Verify quantity in cart is exactly 4
            String actualQty = cartPage.getCartQuantity();
            log.debug("Actual quantity in cart: " + actualQty);
            Assert.assertEquals(actualQty, "4", "Product quantity in cart is incorrect.");
            log.info("Step 6 passed: Verified product quantity is exactly 4.");

            log.info("===== Test Passed: verifyProductQuantityInCartTest =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }


    /**
     * Test Case 14: Remove Product from Cart
     * Steps:
     * 1. Launch browser
     * 2. Navigate to url 'http://automationexercise.com'
     * 3. Verify that home page is visible successfully
     * 4. Add product to cart
     * 5. Click 'Cart' button
     * 6. Verify that cart page is displayed
     * 7. Click 'X' button corresponding to particular product
     * 8. Verify that product is removed from the cart
     */
    @Test(groups = { "Smoke", "Regression" })
    public void removeProductFromCartTest() {
        log.info("===== Starting Test: removeProductFromCartTest =====");

        try {
            // Step 1 & 2: Browser launch and URL navigation is handled by BaseClass setup

            // Step 3: Verify that home page is visible successfully
            String pageTitle = getDriver().getTitle();
            log.debug("Page title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("Automation Exercise"), "Home page is not visible.");
            log.info("Step 3 passed: Home page loaded successfully.");

            // Step 4: Add product to cart
            cartPage = new CartPage();
            cartPage.clickProductsButton();
            cartPage.addFirstProductToCart();
            cartPage.clickViewCart();
            log.info("Steps 4-5 passed: Product added and navigated to cart page.");

            // Step 6: Verify cart page is displayed
            String cartPageTitle = getDriver().getTitle();
            log.debug("Cart Page title: " + cartPageTitle);
            Assert.assertTrue(cartPageTitle.contains("Automation Exercise"), "Cart page not displayed.");
            Assert.assertTrue(cartPage.getCartProducts().size() > 0, "No products found in cart.");
            log.info("Step 6 passed: Cart page displayed and product present.");

            // Step 7: Click 'X' to delete product
            cartPage.removeProductFromCart();
            log.info("Step 7 passed: Clicked remove icon for product.");

            // Step 8: Verify product is removed
            int remainingProducts = cartPage.getCartProducts().size();
            Assert.assertEquals(remainingProducts, 0, "Product not removed from cart.");
            log.info("Step 8 passed: Product successfully removed from cart.");
            log.info("===== Test Passed: removeProductFromCartTest =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
    
}
