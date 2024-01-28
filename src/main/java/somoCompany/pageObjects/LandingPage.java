package somoCompany.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import somoCompany.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // IN order to run the @FindBy class we need to use the
												// PAGEFACTORY.initElements
	}

	@FindBy(id = "userEmail")
	WebElement userEmail; // WebElement userEmail = driver.findElement(By.id("/id"));

	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(css= "[class*='flyInOut']")
	WebElement errMsg;

	public ProductCatalogue loginApplication(String email, String password) {

		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		waitForElementToBeClickable(loginButton);
//		loginButton.click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", loginButton);
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForElementToAppear2(errMsg);
		return errMsg.getText();
	}

	public void goTo(String url) {

		driver.get(url);
	}

}
