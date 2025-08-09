package tests;

import base.BaseTest;
import config.ConfigReader;
import pages.LoginPage;
import pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test(priority = 1)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        
        test.info("Performing login with valid credentials");
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        loginPage.login(username, password);
        
        test.info("Verifying successful login");
        Assert.assertTrue(dashboardPage.isTopbarHeaderDisplayed(), "Dashboard");
        test.pass("Login successful");
    }
    
    @Test(priority = 2)
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage();
        
        test.info("Performing login with invalid credentials");
        loginPage.login("invalid", "wrongpassword");
        
        test.info("Verifying error message");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
        test.pass("Invalid login handled correctly");
    }
    
    @Test(priority = 3)
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage();
        
        test.info("Attempting login with empty credentials");
        loginPage.clickLogin();
        
        test.info("Verifying validation message");
        Assert.assertEquals(loginPage.getUsernameFieldErrorMessage(), "Required");
        Assert.assertEquals(loginPage.getPasswordFieldErrorMessage(), "Required");
        test.pass("Empty credentials validation works");
    }
}
