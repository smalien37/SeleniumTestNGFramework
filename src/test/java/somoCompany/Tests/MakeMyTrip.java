package somoCompany.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {

	static String path = System.getProperty("user.dir");
	static WebDriver driver;

	public static void fromAirport(WebDriver driver, String airport) throws InterruptedException {

		driver.findElement(By.id("fromCity")).sendKeys(airport);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		Thread.sleep(2000);

		List<WebElement> suggestedCities = driver
				.findElements(By.xpath("//li[contains(@id, \"react-autowhatever-1\")]/descendant::p"));
		w.until(ExpectedConditions.visibilityOfAllElements(suggestedCities));

		for (WebElement e : suggestedCities) {
			if (e.getText().contains(airport)) {
				Thread.sleep(2000);
				e.click();
				break;
			}
		}
	}

	public static void goToAirport(WebDriver driver, String airport) throws InterruptedException {

		driver.findElement(By.id("toCity")).sendKeys(airport);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		Thread.sleep(2000);

		List<WebElement> suggestedCities = driver
				.findElements(By.xpath("//li[contains(@id, \"react-autowhatever-1\")]/descendant::p"));
		w.until(ExpectedConditions.visibilityOfAllElements(suggestedCities));

		for (WebElement e : suggestedCities) {
			if (e.getText().contains(airport)) {
				Thread.sleep(2000);
				e.click();
				break;
			}
		}
	}

	public static void departureDate(WebDriver driver, String month, String day) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".datePickerContainer")));

		List<WebElement> m = driver.findElements(By.cssSelector(".DayPicker-Month"));
		for (WebElement e : m) {
			if (e.findElement(By.cssSelector("div.DayPicker-Caption > :first-child")).getText().contains(month)) {
				List<WebElement> date = e.findElements(By.xpath(".//div[@class ='DayPicker-Day']"));
				for (WebElement d : date) {
					if (d.findElement(By.xpath("./descendant::p")).getText().equals(day)) {
						d.click();
						break;
					}
				}
				break;
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
		driver = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://www.makemytrip.com/");

		driver.switchTo().frame(driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame")));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wewidgeticon.we_close")));
		driver.findElement(By.cssSelector(".wewidgeticon.we_close")).click();
		driver.switchTo().defaultContent();

		fromAirport(driver, "Kolk");
		goToAirport(driver, "Banga");
		departureDate(driver, "Jan", "22");
		
		driver.findElement(By.xpath("//a[contains(text(), 'Search')]")).click();
		
		driver.close();
	}

}
