package somoCompany.Tests;

import java.time.Duration;
import somoCompany.pageObjects.CartDetailsPage;
import somoCompany.pageObjects.LandingPage;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class StandAloneTest {

	static String path = System.getProperty("user.dir");

	public static void main(String[] args) throws InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		
	//		 WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", path + "/chromedriver");

		WebDriver driver = new ChromeDriver();
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys("som@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Somosom@77");

		driver.findElement(By.id("login")).click();
		
//		LandingPage lp = new LandingPage(driver);
		
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='products']/div[1]/div[2]/div")));

		List<WebElement> items = driver.findElements(By.xpath("//*[@id='products']/div[1]/div[2]/div"));

		System.out.println(items.size());

//		 Iterator<WebElement> it = items.iterator();
//		 
//		 while(it.hasNext()) {
//			 
//			 if (it.next() ) 
//			 
//		 }
		
		
		WebElement prod = items.stream()
				.filter(item -> item.findElement(By.tagName("b")).getText().equals("ADIDAS ORIGINAL")).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		WebElement prod1 = items.stream()
				.filter(item2 -> item2.findElement(By.tagName("b")).getText().equals("IPHONE 13 PRO")).findFirst()
				.orElse(null);
		prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")));
		driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-sidebar/nav/ul/li[4]/button")).click();
		
		

		List<WebElement> products = driver.findElements(By.xpath("//div[@class='cart']/descendant::h3"));
		System.out.println(products.size());

		boolean prod2 = products.stream().anyMatch(prd -> prd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(prod2);
		
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		
		//PAYMENT FORM
		
		driver.findElement(By.xpath("//div[contains(text(),'Credit Card Number ')]/following-sibling::input")).sendKeys("5742997197922797");
		
		Select expMonth = new Select(driver.findElement(By.xpath("//div[contains(text(),'Expiry Date ')]/following-sibling::select[1]")));
		Select expYear = new Select(driver.findElement(By.xpath("//div[contains(text(),'Expiry Date ')]/following-sibling::select[2]")));
		
		expMonth.selectByVisibleText("08");
		expYear.selectByVisibleText("28");
		
		driver.findElement(By.xpath("//div[contains(text(),'CVV Code ')]/following-sibling::input")).sendKeys("123");
		driver.findElement(By.xpath("//div[contains(text(),'Name on Card ')]/following-sibling::input")).sendKeys("Som Roy");
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("I");
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']/following-sibling::section")));
		
		List<WebElement> countries = driver.findElements(By.xpath("//input[@placeholder='Select Country']/following-sibling::section/button"));
		
		for (WebElement e: countries) {
			System.out.println(e.findElement(By.tagName("span")).getText());
			if(e.findElement(By.tagName("span")).getText().equalsIgnoreCase("India")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);;
					w.until(ExpectedConditions.elementToBeClickable(e));
					Thread.sleep(2000);
					e.click();
					break;
				}
		}
//		myCountry.click();
//		 driver.close();

	}

}
