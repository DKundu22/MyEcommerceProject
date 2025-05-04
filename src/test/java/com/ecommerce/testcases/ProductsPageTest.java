package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.ProductsPage;
import com.ecommerce.utility.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsPageTest extends BaseClass {

    ProductsPage productsPage;

    @Test
    public void verifyAllProductsAndProductDetailPage() {
        Log.startTestCase("verifyAllProductsAndProductDetailPage");

        productsPage = new ProductsPage();
        Log.info("ProductsPage object initialized.");

        // Step 3: Verify home page
        Log.info("Verifying home page visibility.");
        Assert.assertTrue(productsPage.isHomePageVisible(), "Home page is not visible.");

        // Step 4: Click on 'Products'
        Log.info("Clicking on 'Products' button.");
        productsPage.clickOnProductsButton();

        // Step 5: Verify All Products page
        Log.info("Verifying All Products page visibility.");
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "All Products page is not visible.");

        // Step 6: Verify products list
        Log.info("Verifying products list visibility.");
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible.");

        // Step 7: Click on 'View Product' of first product
        Log.info("Clicking on 'View Product' of first product.");
        productsPage.clickFirstViewProduct();

        // Step 8-9: Verify product detail page and details
        Log.info("Verifying product details are visible.");
        Assert.assertTrue(productsPage.isProductNameVisible(), "Product name is not visible.");
        Assert.assertTrue(productsPage.isProductCategoryVisible(), "Product category is not visible.");
        Assert.assertTrue(productsPage.isProductPriceVisible(), "Product price is not visible.");
        Assert.assertTrue(productsPage.isProductAvailabilityVisible(), "Product availability is not visible.");
        Assert.assertTrue(productsPage.isProductConditionVisible(), "Product condition is not visible.");
        Assert.assertTrue(productsPage.isProductBrandVisible(), "Product brand is not visible.");

        Log.endTestCase("verifyAllProductsAndProductDetailPage");
    }
}
