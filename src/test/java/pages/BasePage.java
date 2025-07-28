package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;


public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions actions;
	public JavascriptExecutor js;
    
    public BasePage() {
        this.driver = DriverManager.getDriver(config.ConfigReader.getProperty("browser"));
        this.wait = DriverManager.getWait();
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    // Element interaction methods
    public void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }
    
    public void type(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    
    public String getText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }
    
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Wait methods
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void waitForElementToBePresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    // Dropdown methods
    public void selectDropdownByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    
    public void selectDropdownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }
    
    // JavaScript methods
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void clickWithJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
    
    // Page navigation
    public void navigateToUrl(String url) {
        driver.get(url);
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
}
