package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    
	@FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameField;
	
	@FindBy(xpath = "//div[@class='orangehrm-login-form']//div[1]//div[1]//span[1]")
	public WebElement usernameField_ErrorMessage;
    
	@FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordField;
	
	@FindBy(xpath = "//div[@class='orangehrm-login-form']//div[2]//div[1]//span[1]")
	public WebElement passwordField_ErrorMessage;
    
	@FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
    private WebElement forgotPasswordLink;
    
    public void enterUsername(String username) {
        type(usernameField, username);
    }
    
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    
    public void clickLogin() {
        click(loginButton);
    }
    
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    
    public String getUsernameFieldErrorMessage() {
        return getText(usernameField_ErrorMessage);
    }
    
    public String getPasswordFieldErrorMessage() {
        return getText(passwordField_ErrorMessage);
    }
    
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }
    
}