package somoCompany.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import somoCompany.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {
	
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[contains(text(),'Credit Card Number ')]/following-sibling::input")
	WebElement cardNumber;	
	
	@FindBy(xpath = "//div[contains(text(),'Expiry Date ')]/following-sibling::select[1]")
	WebElement expMonth;	
	
	@FindBy(xpath = "//div[contains(text(),'Expiry Date ')]/following-sibling::select[2]")
	WebElement expYear;	
	
	@FindBy(xpath = "//div[contains(text(),'CVV Code ')]/following-sibling::input")
	WebElement CVV;	
	
	@FindBy(xpath = "//div[contains(text(),'Name on Card ')]/following-sibling::input")
	WebElement nameCard;	
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement Country;	
	
	@FindBy(xpath = "//input[@placeholder='Select Country']/following-sibling::section")
	WebElement autoSuggest;	
	
	@FindBy(xpath = "//input[@placeholder='Select Country']/following-sibling::section/button")
	List<WebElement> suggestionList;	
	
	@FindBy(xpath = "//a[contains(text(), 'Place Order ')]")
	WebElement orderButton;	
	
	
	
	By autoSug = By.xpath("//input[@placeholder='Select Country']/following-sibling::section");
	
	
	public List<WebElement> getCountryList() {

		waitForElementToAppear(autoSug);

		List<WebElement> countryList = suggestionList;
		return countryList;

	}
	
	
	public OrderIdPage updatePaymentDetails(String cardNo, String month, String year, String cvv, String name, String country) throws InterruptedException {
		
		cardNumber.sendKeys(cardNo);
		Select s1 = new Select(expMonth);
		Select s2 = new Select(expYear);
		
		s1.selectByVisibleText(month);
		s2.selectByVisibleText(year);
		CVV.sendKeys(cvv);
		nameCard.sendKeys(name);
		Country.sendKeys(country);
		
		for (WebElement e: getCountryList()) {
			if(e.findElement(By.tagName("span")).getText().equalsIgnoreCase(country)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);;
					waitForElementToBeClickable(e);
					Thread.sleep(2000);
					e.click();
					break;
				}
		}
		
		orderButton.click();
		
		return new OrderIdPage(driver);
		
	}
	
	
	
	
	
	
	

}
