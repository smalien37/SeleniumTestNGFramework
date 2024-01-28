package somoCompany.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import somoCompany.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // IN order to run the @FindBy class we need to use the
												// PAGEFACTORY.initElements
	}

	@FindBy(xpath = "//*[@id='products']/div[1]/div[2]/div")
	List<WebElement> productList;

	@FindBy(css = "#toast-container")
	WebElement toast;

	@FindBy(xpath = "//button[@routerlink=\"/dashboard/cart\"]")
	WebElement cartButton;

	@FindBy(css = ".ng-animating")
	WebElement loadingAnimation;

	By prdList1 = By.xpath("//*[@id='products']/div[1]/div[2]/div");
	By toastContr = By.cssSelector("#toast-container");
	By productCard = By.cssSelector(".card-body button:last-of-type");
	By loadAnime = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() {

		waitForElementToAppear(prdList1);
//		waitForElementToAppear(toastContr);
		waitForElementToDisappear(toastContr);

		List<WebElement> prdList = productList;
		return prdList;

	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductList().stream()
				.filter(item -> item.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addToCart(String productName) {

		WebElement e = getProductByName(productName).findElement(productCard);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", e);
		waitForElementToAppear(toastContr);
		waitForElementToDisappear(toastContr);
		waitForElementToDisappear(loadAnime);		
		

	}

	public CartDetailsPage goToCart() {
		waitForElementToBeClickable(cartButton);
		cartButton.click();
		return new CartDetailsPage(driver);
	}

}
