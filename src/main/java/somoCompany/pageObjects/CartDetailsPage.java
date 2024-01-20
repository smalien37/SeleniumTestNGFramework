package somoCompany.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import somoCompany.AbstractComponents.AbstractComponent;

public class CartDetailsPage extends AbstractComponent{

	WebDriver driver;

	public CartDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement checkOutButton;

	public PaymentPage checkOut() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkOutButton);
		waitForElementToAppear2(checkOutButton);
		waitForElementToBeClickable(checkOutButton);
		Thread.sleep(2000);
		checkOutButton.click();
		return new PaymentPage(driver);
	}

}
