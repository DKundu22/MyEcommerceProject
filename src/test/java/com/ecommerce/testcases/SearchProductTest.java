package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.SearchProductsPage;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Test Case: Verify search functionality for multiple products.
 * Steps:
 * 1. Verify home page is loaded
 * 2. Navigate to Products page
 * 3. Search for multiple products and verify search results
 */
public class SearchProductTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(SearchProductTest.class);
    private SearchProductsPage searchPage;

    @Test(groups = {"Smoke", "Regression"})
    public void searchMultipleProductsTest() {
        log.info("===== Starting Test: searchMultipleProductsTest =====");

        try {
            // Step 1: Verify home page is loaded
            String pageTitle = getDriver().getTitle();
            log.debug("Page title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("Automation Exercise"), "Home page not loaded.");
            log.info("Step 1 passed: Home page loaded successfully.");

            // Step 2: Navigate to Products page
            searchPage = new SearchProductsPage();
            log.debug("SearchProductsPage object instantiated.");

            searchPage.clickProductsButton();
            log.info("Clicked on Products button.");

            String currentUrl = getDriver().getCurrentUrl();
            log.debug("Current URL: " + currentUrl);
            Assert.assertTrue(currentUrl.contains("products"), "Not navigated to Products page.");
            log.info("Step 2 passed: Navigated to Products page successfully.");

            // Step 3: Search for multiple products and verify results
            String[] products = {"Top", "T-shirt", "Jeans"};

            for (String product : products) {
                log.info("Searching for product: " + product);
                searchPage.searchForProduct(product);

                Assert.assertTrue(searchPage.isSearchedProductsTitleVisible(),
                        "'SEARCHED PRODUCTS' title not visible for product: " + product);
                log.info("'SEARCHED PRODUCTS' title is visible.");

                List<?> searchResults = searchPage.getSearchedProducts();
                log.debug("Number of search results for " + product + ": " + searchResults.size());
                Assert.assertTrue(searchResults.size() > 0, "No products found for: " + product);
                log.info("Search results verified for product: " + product);
            }

            log.info("===== Test Passed: searchMultipleProductsTest =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
}
