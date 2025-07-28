package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardPageTest extends BaseTest {
    
    @BeforeMethod
    public void loginToApp() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("Admin", "admin123");
    }
    
    @Test (priority = 1)
    public void testDashboardPageDisplay() {
        DashboardPage dashboardPage = new DashboardPage();
        
        test.info("Verifying home page elements");
        Assert.assertTrue(dashboardPage.isTopbarHeaderDisplayed(), "Dashboard");
        test.pass("Dashboard page displayed correctly");
    }
    
    @Test (priority = 2)
    public void testLogout() throws InterruptedException {
    	DashboardPage dashboardPage = new DashboardPage();
        
    	dashboardPage.waitForElementToBeVisible(dashboardPage.userMenu);
        test.info("Performing logout");
        dashboardPage.logout();
        
        test.info("Verifying logout successful");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Should be redirected to login page");
        test.pass("Logout successful");
    }
}
