package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	public static void waitForElementVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementClickable(WebDriver driver, WebElement element, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementPresent(WebDriver driver, By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitForTextPresent(WebDriver driver, WebElement element, String text, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

}
