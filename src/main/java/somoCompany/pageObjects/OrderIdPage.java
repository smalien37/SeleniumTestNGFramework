package somoCompany.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderIdPage {

	WebDriver driver;

	public OrderIdPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[@class=\"ng-star-inserted\"]")
	List<WebElement> orderId;

	public ArrayList<String> getOrderIds() {
		ArrayList<String> id = new ArrayList<String>();
		for (WebElement e : orderId) {
			id.add(e.getText());
		}

		return id;
	}

}
