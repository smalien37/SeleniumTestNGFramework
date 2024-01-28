package somoCompany.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import junit.framework.Assert;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
		driver=new ChromeDriver(options);
		driver.get("https://www.google.co.in/");
		System.out.println("Title is: " +driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Google");
		driver.quit();

	}

}
