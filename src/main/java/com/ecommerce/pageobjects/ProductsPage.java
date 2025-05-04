package com.ecommerce.pageobjects;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductsPage extends BaseClass {

    Action action = new Action();

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

    public ProductsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean isHomePageVisible() {
        return action.isDisplayed(getDriver(), homePageLogo);
    }

    public void clickOnProductsButton() {
        action.click(getDriver(), productsButton);
    }

    public boolean isAllProductsPageVisible() {
        return action.isDisplayed(getDriver(), allProductsTitle);
    }

    public boolean isProductsListVisible() {
        return productsList.size() > 0;
    }

//    public void clickOnFirstViewProduct() {
//        action.click(getDriver(), firstViewProductLink);
//    }
    
    public void clickFirstViewProduct() {
        action.scrollIntoView(getDriver(), firstViewProductLink);
        action.click(getDriver(), firstViewProductLink);
    }


    public boolean isProductNameVisible() {
        return action.isDisplayed(getDriver(), productName);
    }

    public boolean isProductCategoryVisible() {
        return action.isDisplayed(getDriver(), productCategory);
    }

    public boolean isProductPriceVisible() {
        return action.isDisplayed(getDriver(), productPrice);
    }

    public boolean isProductAvailabilityVisible() {
        return action.isDisplayed(getDriver(), productAvailability);
    }

    public boolean isProductConditionVisible() {
        return action.isDisplayed(getDriver(), productCondition);
    }

    public boolean isProductBrandVisible() {
        return action.isDisplayed(getDriver(), productBrand);
    }
}
