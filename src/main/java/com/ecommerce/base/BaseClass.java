package com.ecommerce.base;

import com.ecommerce.utility.Log;
import com.ecommerce.utility.ScreenShot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public static Properties prop;
    // Thread-safe storage for WebDriver and browser name
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browserName = new ThreadLocal<>();

    /**
     * Loads configuration before the test suite starts.
     */
    @BeforeSuite(alwaysRun = true) 
    public void beforeSuite() {
        loadConfig();
        Log.startTestSuite("Automation Test Suite Started");
    }

    /**
     * Launches the browser before each test method.
     * @param browser Browser name (Chrome/Firefox/Edge/Safari)
     */
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true) 
    public void setUp(@Optional("Chrome") String browser) {
    	 try {
    	        launchApp(browser);
    	    } catch (Exception e) {
    	        Log.error("Error during setup (possibly missing browser parameter or browser driver issue):", e);
    	        throw e; // Let TestNG know this config failed
    	    }
    }

    /**
     * Closes the browser after each test method and captures screenshot if test failed.
     */
    @AfterMethod(alwaysRun = true) 
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Log.error("Test Failed: " + result.getName());
            
            if (getDriver() != null) {
                ScreenShot.captureScreenShot(getDriver(), result.getName());
            } else {
                Log.warn("WebDriver is null. Skipping screenshot for: " + result.getName());
            }
            
        }
        Log.info("Closing browser...");
        if (getDriver() != null) {
            getDriver().quit();getDriver();
            driver.remove();
            browserName.remove();
        }
    }

    /**
     * Ends the test suite.
     */
    @AfterSuite(alwaysRun = true) 
    public void afterSuite() {
        Log.endTestSuite("Automation Test Suite Finished");
    }

    /**
     * Loads the configuration properties file.
     */
    public void loadConfig() {
        try {
            prop = new Properties();
            String path = System.getProperty("user.dir") + "/Configuration/Config.properties";
            Log.info("Loading configuration properties...");
            FileInputStream ip = new FileInputStream(path);
            prop.load(ip);
            Log.info("Loaded configuration properties.");
        } catch (IOException e) {
            Log.error("Failed to load config file", e);
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    /**
     * Launches the specified browser and opens the application URL.
     * @param browser Name of the browser
     */
    public void launchApp(String browser) {
        Log.info("Launching browser: " + browser);
        browserName.set(browser);  // Correctly setting browser to ThreadLocal

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            case "safari":
                driver.set(new SafariDriver());
                break;
            default:
                Log.error("Unsupported browser: " + browser);
                throw new RuntimeException("Browser not supported: " + browser);
        }
        
        // Browser setup
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(prop.getProperty("url"));
        Log.info("Navigated to: " + prop.getProperty("url"));
    }

    /**
     * Returns the current thread-safe WebDriver instance.
     * @return WebDriver instance
     * Getter for WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Returns a WebDriverWait instance for explicit waits.
     * @return WebDriverWait instance
     * Getter for WebDriverWait
     */
    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(20));
    }

    /**
     * Returns the browser name for current thread.
     * @return Browser name
     * Getter for browser Name
     */
    public static String getBrowserName() {
        return browserName.get();
    }
}
