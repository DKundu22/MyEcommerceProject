package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test Case: Verify products listing and product detail page.
 * Steps:
 * 1. Verify homepage is visible
 * 2. Click on 'Products' button
 * 3. Verify 'All Products' page is visible
 * 4. Verify products list is visible
 * 5. Click on 'View Product' of first product
 * 6. Verify product details visibility
 */
public class ProductsPageTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(ProductsPageTest.class);
    private ProductsPage productsPage;

    @Test(groups = {"Smoke","Regression"})
    public void verifyAllProductsAndProductDetailPage() {
        log.info("===== Starting Test: verifyAllProductsAndProductDetailPage =====");

        try {
            productsPage = new ProductsPage();
            log.debug("ProductsPage object instantiated.");

            // Step 1: Verify home page visibility
            Assert.assertTrue(productsPage.isHomePageVisible(), "Home page is not visible.");
            log.info("Step 1 passed: Home page is visible.");

            // Step 2: Click on 'Products' button
            productsPage.clickOnProductsButton();
            log.info("Step 2 passed: Clicked on 'Products' button.");

            // Step 3: Verify 'All Products' page visibility
            Assert.assertTrue(productsPage.isAllProductsPageVisible(), "'All Products' page is not visible.");
            log.info("Step 3 passed: 'All Products' page is visible.");

            // Step 4: Verify products list visibility
            Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible.");
            log.info("Step 4 passed: Products list is visible.");

            // Step 5: Click on 'View Product' link of first product
            productsPage.clickFirstViewProduct();
            log.info("Step 5 passed: Clicked on 'View Product' of first product.");

            // Step 6: Verify product details visibility
            Assert.assertTrue(productsPage.isProductNameVisible(), "Product name is not visible.");
            Assert.assertTrue(productsPage.isProductCategoryVisible(), "Product category is not visible.");
            Assert.assertTrue(productsPage.isProductPriceVisible(), "Product price is not visible.");
            Assert.assertTrue(productsPage.isProductAvailabilityVisible(), "Product availability is not visible.");
            Assert.assertTrue(productsPage.isProductConditionVisible(), "Product condition is not visible.");
            Assert.assertTrue(productsPage.isProductBrandVisible(), "Product brand is not visible.");
            log.info("Step 6 passed: All product details are visible.");

            log.info("===== Test Passed: verifyAllProductsAndProductDetailPage =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
}
