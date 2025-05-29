package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import com.ecommerce.utility.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Page Object class for Products Page and Product Details Page.
 */
public class ProductsPage extends BaseClass {

    private static final Logger log = LogManager.getLogger(ProductsPage.class);
    private final Action action = new Action();

    // WebElements
    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    private WebElement homePageLogo;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsButton;

    @FindBy(xpath = "//h2[contains(text(),'All Products')]")
    private WebElement allProductsTitle;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='product-image-wrapper']")
    private List<WebElement> productsList;

    @FindBy(xpath = "(//a[contains(text(),'View Product')])[1]")
    private WebElement firstViewProductLink;

    @FindBy(xpath = "//div[@class='product-information']//h2")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='product-information']//p[contains(text(),'Category')]")
    private WebElement productCategory;

    @FindBy(xpath = "//div[@class='product-information']//span/span")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Availability')]")
    private WebElement productAvailability;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Condition')]")
    private WebElement productCondition;

    @FindBy(xpath = "//div[@class='product-information']//b[contains(text(),'Brand')]")
    private WebElement productBrand;

    /**
     * Constructor to initialize web elements.
     */
    public ProductsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Verify if home page logo is displayed.
     */
    public boolean isHomePageVisible() {
        try {
            log.info("Checking if home page logo is displayed...");
            return action.isDisplayed(getDriver(), homePageLogo);
        } catch (Exception e) {
            log.error("Exception in isHomePageVisible: ", e);
            return false;
        }
    }

    /**
     * Click on 'Products' button to navigate to the products page.
     */
    public void clickOnProductsButton() {
        try {
            log.info("Clicking on 'Products' button...");
            action.click(getDriver(), productsButton);
        } catch (Exception e) {
            log.error("Exception in clickOnProductsButton: ", e);
        }
    }

    /**
     * Verify if 'All Products' page title is displayed.
     */
    public boolean isAllProductsPageVisible() {
        try {
            log.info("Checking if 'All Products' title is visible...");
            return action.isDisplayed(getDriver(), allProductsTitle);
        } catch (Exception e) {
            log.error("Exception in isAllProductsPageVisible: ", e);
            return false;
        }
    }

    /**
     * Verify if the list of products is displayed.
     */
    public boolean isProductsListVisible() {
        try {
            log.info("Checking if product list is displayed...");
            return productsList != null && !productsList.isEmpty();
        } catch (Exception e) {
            log.error("Exception in isProductsListVisible: ", e);
            return false;
        }
    }

    /**
     * Click on 'View Product' link of the first listed product.
     */
    public void clickFirstViewProduct() {
        try {
            log.info("Clicking on first 'View Product' link...");
            action.scrollIntoView(getDriver(), firstViewProductLink);
            action.click(getDriver(), firstViewProductLink);
        } catch (Exception e) {
            log.error("Exception in clickFirstViewProduct: ", e);
        }
    }

    /**
     * Verify if product name is displayed on the product detail page.
     */
    public boolean isProductNameVisible() {
        try {
            log.info("Checking if product name is visible...");
            return action.isDisplayed(getDriver(), productName);
        } catch (Exception e) {
            log.error("Exception in isProductNameVisible: ", e);
            return false;
        }
    }

    /**
     * Verify if product category is displayed on the product detail page.
     */
    public boolean isProductCategoryVisible() {
        try {
            log.info("Checking if product category is visible...");
            return action.isDisplayed(getDriver(), productCategory);
        } catch (Exception e) {
            log.error("Exception in isProductCategoryVisible: ", e);
            return false;
        }
    }

    /**
     * Verify if product price is displayed on the product detail page.
     */
    public boolean isProductPriceVisible() {
        try {
            log.info("Checking if product price is visible...");
            return action.isDisplayed(getDriver(), productPrice);
        } catch (Exception e) {
            log.error("Exception in isProductPriceVisible: ", e);
            return false;
        }
    }

    /**
     * Verify if product availability is displayed on the product detail page.
     */
    public boolean isProductAvailabilityVisible() {
        try {
            log.info("Checking if product availability is visible...");
            return action.isDisplayed(getDriver(), productAvailability);
        } catch (Exception e) {
            log.error("Exception in isProductAvailabilityVisible: ", e);
            return false;
        }
    }

    /**
     * Verify if product condition is displayed on the product detail page.
     */
    public boolean isProductConditionVisible() {
        try {
            log.info("Checking if product condition is visible...");
            return action.isDisplayed(getDriver(), productCondition);
        } catch (Exception e) {
            log.error("Exception in isProductConditionVisible: ", e);
            return false;
        }
    }

    /**
     * Verify if product brand is displayed on the product detail page.
     */
    public boolean isProductBrandVisible() {
        try {
            log.info("Checking if product brand is visible...");
            return action.isDisplayed(getDriver(), productBrand);
        } catch (Exception e) {
            log.error("Exception in isProductBrandVisible: ", e);
            return false;
        }
    }
}
