package base;

import config.ConfigReader;
import driver.DriverManager;
import utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {
	
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected ExtentTest test;
    
    @BeforeClass
    public void setupClass() {
        logger.info("Starting test class: " + this.getClass().getSimpleName());
    }
    
    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Starting test: " + method.getName());
        
        // Initialize driver
        String browser = ConfigReader.getProperty("browser");
        driver = DriverManager.getDriver(browser);
        
        // Create extent test
        test = ExtentReportManager.createTest(method.getName());
        
        // Navigate to base URL
        String baseUrl = ConfigReader.getProperty("base.url");
        driver.get(baseUrl);
        
        test.info("Navigated to: " + baseUrl);
        logger.info("Test setup completed for: " + method.getName());
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, result.getMethod().getMethodName());
            test.fail("Test failed").addScreenCaptureFromPath(screenshotPath);
            logger.error("Test failed: " + result.getMethod().getMethodName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
            logger.info("Test passed: " + result.getMethod().getMethodName());
        }
        
        if (driver != null) {
            DriverManager.quitDriver();
        }
    }
    
    @AfterClass
    public void tearDownClass() {
        logger.info("Completed test class: " + this.getClass().getSimpleName());
    }
    
    @BeforeSuite
    public void setupSuite() {
        ExtentReportManager.initializeReport();
        logger.info("Test suite started");
    }
    
    @AfterSuite
    public void tearDownSuite() {
        ExtentReportManager.flushReport();
        logger.info("Test suite completed");
    }
}