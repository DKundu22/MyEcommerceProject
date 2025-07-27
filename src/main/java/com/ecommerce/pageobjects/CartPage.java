package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object class representing the Add to Cart functionality.
 * Handles product addition to cart and validates cart contents.
 */
public class CartPage extends BaseClass {

    private static final Logger log = LogManager.getLogger(CartPage.class);
    private final Action action = new Action();

    // Page elements
    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsBtn;

    @FindBy(xpath = "(//div[@class='product-overlay']//a[text()='Add to cart'])[1]")
    private WebElement firstProductAddToCartBtn;

    @FindBy(xpath = "(//div[@class='product-overlay']//a[text()='Add to cart'])[2]")
    private WebElement secondProductAddToCartBtn;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement viewCartBtn;

    @FindBy(xpath = "//tr[contains(@id,'product')]")
    private List<WebElement> cartProducts;

    @FindBy(xpath = "//tr[contains(@id,'product')]/td[@class='cart_price']/p")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//tr[contains(@id,'product')]/td[@class='cart_quantity']/button")
    private List<WebElement> productQuantities;

    @FindBy(xpath = "//tr[contains(@id,'product')]/td[@class='cart_total']/p")
    private List<WebElement> productTotalPrices;   

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    private WebElement removeProductBtn;

    // Constructor to initialize WebElements
    public CartPage() {
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
     * Hovers over the first product and clicks 'Add to cart'.
     */
    public void addFirstProductToCart() {
        try {
            WebElement firstProduct = getDriver().findElements(By.className("productinfo")).get(0);
            action.mouseHover(getDriver(), firstProduct);
            action.jsClick(getDriver(), firstProductAddToCartBtn);
            log.info("First product added to cart.");
        } catch (Exception e) {
            log.error("Failed to add first product to cart.", e);
            throw e;
        }
    }

    /**
     * Hovers over the second product and clicks 'Add to cart'.
     */
    public void addSecondProductToCart() {
        try {
            WebElement secondProduct = getDriver().findElements(By.className("productinfo")).get(1);
            action.mouseHover(getDriver(), secondProduct);
            action.jsClick(getDriver(), secondProductAddToCartBtn);
            log.info("Second product added to cart.");
        } catch (Exception e) {
            log.error("Failed to add second product to cart.", e);
            throw e;
        }
    }

    /**
     * Clicks on the 'Continue Shopping' button.
     */
    public void clickContinueShopping() {
        try {
            action.click(getDriver(), continueShoppingBtn);
            log.info("Clicked on Continue Shopping button.");
        } catch (Exception e) {
            log.error("Failed to click on Continue Shopping button.", e);
            throw e;
        }
    }

    /**
     * Clicks on the 'View Cart' button.
     */
    public void clickViewCart() {
        try {
            action.click(getDriver(), viewCartBtn);
            log.info("Clicked on View Cart button.");
        } catch (Exception e) {
            log.error("Failed to click on View Cart button.", e);
            throw e;
        }
    }

    /**
     * Returns the list of products added to the cart.
     *
     * @return list of cart product elements
     */
    public List<WebElement> getCartProducts() {
        try {
            log.info("Retrieved list of cart products. Count: " + cartProducts.size());
            return cartProducts;
        } catch (Exception e) {
            log.error("Failed to retrieve cart product list.", e);
            throw e;
        }
    }

    /**
     * Returns the prices of products in the cart.
     *
     * @return list of product prices
     */
    public List<WebElement> getProductPrices() {
        try {
            log.info("Retrieved product prices from cart.");
            return productPrices;
        } catch (Exception e) {
            log.error("Failed to retrieve product prices.", e);
            throw e;
        }
    }

    /**
     * Returns the quantities of products in the cart.
     *
     * @return list of product quantities
     */
    public List<WebElement> getProductQuantities() {
        try {
            log.info("Retrieved product quantities from cart.");
            return productQuantities;
        } catch (Exception e) {
            log.error("Failed to retrieve product quantities.", e);
            throw e;
        }
    }

    /**
     * Returns the total prices of products in the cart.
     *
     * @return list of total prices
     */
    public List<WebElement> getProductTotalPrices() {
        try {
            log.info("Retrieved total prices from cart.");
            return productTotalPrices;
        } catch (Exception e) {
            log.error("Failed to retrieve total prices.", e);
            throw e;
        }
    }
    
    
    
    // Page elements
    @FindBy(xpath = "//a[@href='/product_details/1']")
    private WebElement viewProductBtn;

    @FindBy(xpath = "//input[@id='quantity']")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[normalize-space()='Add to cart']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    private WebElement viewCartButton;

    @FindBy(xpath = "//table[@id='cart_info_table']//tr[1]/td[4]/button")
    private WebElement quantityInCart;

    

    /**
     * Clicks the 'View Product' button for the first product on the homepage.
     */
    public void clickViewProduct() {
        try {
            action.jsClick(getDriver(), viewProductBtn);
            log.info("Clicked on 'View Product' for the first product.");
        } catch (Exception e) {
            log.error("Failed to click on 'View Product' button.", e);
            throw e;
        }
    }

    /**
     * Sets the desired product quantity in the input box.
     *
     * @param quantity The quantity to set
     */
    public void setProductQuantity(String quantity) {
        try {
            action.type(quantityInput, quantity);
            log.info("Set product quantity to: " + quantity);
        } catch (Exception e) {
            log.error("Failed to set product quantity.", e);
            throw e;
        }
    }

    /**
     * Clicks the 'Add to cart' button.
     */
    public void clickAddToCart() {
        try {
            action.click(getDriver(), addToCartBtn);
            log.info("Clicked on 'Add to cart' button.");
        } catch (Exception e) {
            log.error("Failed to click on 'Add to cart' button.", e);
            throw e;
        }
    }

    /**
     * Clicks the 'View Cart' button.
     */
    public void clickViewCartFromDetailPage() {
        try {
            action.click(getDriver(), viewCartButton);
            log.info("Clicked on 'View Cart' button.");
        } catch (Exception e) {
            log.error("Failed to click on 'View Cart' button.", e);
            throw e;
        }
    }

    /**
     * Gets the quantity displayed in the cart for the product.
     *
     * @return quantity as a String
     */
    public String getCartQuantity() {
        try {
            String quantity = quantityInCart.getText().trim();
            log.info("Retrieved product quantity from cart: " + quantity);
            return quantity;
        } catch (Exception e) {
            log.error("Failed to retrieve quantity from cart.", e);
            throw e;
        }
    }

    /**
     * Clicks the 'X' button to remove product from cart.
     */
    public void removeProductFromCart() {
        try {
            action.click(getDriver(), removeProductBtn);
            log.info("Clicked on remove (X) button for product in cart.");
            // Wait for the product to be removed from the cart
            action.waitForCondition(getDriver(), driver -> getCartProducts().isEmpty(), 10, 500);
        } catch (Exception e) {
            log.error("Failed to click on remove button.", e);
            throw e;
        }
    }

}

