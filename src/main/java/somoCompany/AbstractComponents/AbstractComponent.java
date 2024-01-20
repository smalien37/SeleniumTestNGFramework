package somoCompany.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver; 

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear2(WebElement findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToBeClickable(WebElement findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(findBy));
	}

}
