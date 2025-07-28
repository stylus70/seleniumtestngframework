package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    
   
    @FindBy(linkText = "Logout")
    public WebElement logoutLink;
	
	@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	public WebElement TopBar_Header_Dashboard;
	
	@FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	public WebElement userMenu;
    
    public boolean isTopbarHeaderDisplayed() {
        return isElementDisplayed(TopBar_Header_Dashboard);
    }
    
    public void clickUserMenu() {
        click(userMenu);
        waitForElementToBeVisible(logoutLink);
    }
    
    public void logout() {
        clickUserMenu();
        click(logoutLink);
    }
}