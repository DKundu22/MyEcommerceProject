package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object class representing the Search Products Page.
 * Handles product search interactions and logs steps with appropriate log levels.
 */
public class SearchProductsPage extends BaseClass {

    private static final Logger log = LogManager.getLogger(SearchProductsPage.class);
    private final Action action = new Action();

    // Page elements
    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsBtn;

    @FindBy(id = "search_product")
    private WebElement searchInput;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[text()='Searched Products']")
    private WebElement searchedProductsTitle;

    @FindBy(xpath = "//div[@class='features_items']/div")
    private List<WebElement> productList;

    // Constructor to initialize WebElements
    public SearchProductsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Clicks the 'Products' button to navigate to the products page.
     */
    public void clickProductsButton() {
        try {
            action.click(getDriver(), productsBtn);
            log.info("Clicked on Products button.");
        } catch (Exception e) {
            log.error("Failed to click on Products button.", e);
            throw e;
        }
    }

    /**
     * Searches for a product by name.
     *
     * @param productName the name of the product to search for
     */
    public void searchForProduct(String productName) {
        try {
            action.type(searchInput, productName);
            log.info("Entered product name in search input: " + productName);
            action.click(getDriver(), searchButton);
            log.info("Clicked on Search button.");
        } catch (Exception e) {
            log.error("Product search failed for: " + productName, e);
            throw e;
        }
    }

    /**
     * Checks if the 'Searched Products' title is visible.
     *
     * @return true if visible, false otherwise
     */
    public boolean isSearchedProductsTitleVisible() {
        try {
            boolean visible = action.isDisplayed(getDriver(), searchedProductsTitle);
            log.info("'Searched Products' title visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("'Searched Products' title not visible.", e);
            return false;
        }
    }

    /**
     * Returns the list of searched product WebElements.
     *
     * @return list of products
     */
    public List<WebElement> getSearchedProducts() {
        try {
            log.info("Retrieved list of searched products. Count: " + productList.size());
            return productList;
        } catch (Exception e) {
            log.error("Failed to retrieve searched products list.", e);
            throw e;
        }
    }
}
