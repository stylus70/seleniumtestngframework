package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ExtentReportManager {
 
	private static ExtentReports extent;
    private static final String SCREENSHOT_PATH = ".\\reports\\screenshots\\";   
    private static String repName;
    
    public static void initializeReport() {
        if (extent == null) {
        	
        	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        	repName = "Test-Report-" + timeStamp + ".html";
        	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        	
            //ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("UI Test Report");
            sparkReporter.config().setReportName("Automation Test Results");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
    }
    
    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }
    
    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_PATH + fileName;
        
        try {
            File screenshotDir = new File(SCREENSHOT_PATH);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);
            
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}