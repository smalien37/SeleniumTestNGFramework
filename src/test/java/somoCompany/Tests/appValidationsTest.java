package somoCompany.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import somoCompany.TestComponents.BaseTest;
import somoCompany.pageObjects.CartDetailsPage;
import somoCompany.pageObjects.LandingPage;
import somoCompany.pageObjects.OrderIdPage;
import somoCompany.pageObjects.PaymentPage;
import somoCompany.pageObjects.ProductCatalogue;

public class appValidationsTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	String productName1 = "IPHONE 13 PRO";
	String productName2 = "ZARA COAT 3";
	LandingPage llp;
	
	
	@BeforeMethod(alwaysRun = true)
	public void initiateDriver() throws IOException {
		Properties prop = readPropertyFile();

		driver = initializeDriver();
		llp = new LandingPage(driver);
		String url = prop.getProperty("url");
		llp.goTo(url);

	}

	@Test(dataProvider="getData", groups = {"purchase"})
	public void submitOrder(Map<Object, Object> m) throws IOException, InterruptedException {

//		LandingPage llp = launchApplication();
		ProductCatalogue pc = llp.loginApplication(m.get("email").toString(), m.get("password").toString());

//		List<WebElement> pl = pc.getProductList();

		pc.addToCart(m.get("productName").toString());

		CartDetailsPage cp = pc.goToCart();

		AssertJUnit.assertTrue(true);

		PaymentPage pp = cp.checkOut();

		OrderIdPage oid = pp.updatePaymentDetails("123456789", "04", "26", "123", "Somo Baby", "India");

		ArrayList<String> oID = oid.getOrderIds();
		for (String w : oID) {
			System.out.println(w);
		}

	}
	
	@Test
	public void submitOrder2() throws IOException, InterruptedException {

//		LandingPage llp = launchApplication();
		ProductCatalogue pc = llp.loginApplication("som@gmail.com", "Somosom@77");

//		List<WebElement> pl = pc.getProductList();

		pc.addToCart(productName);
		pc.addToCart(productName1);

		CartDetailsPage cp = pc.goToCart();

		AssertJUnit.assertTrue(true);

		PaymentPage pp = cp.checkOut();

		OrderIdPage oid = pp.updatePaymentDetails("123456789", "04", "26", "123", "Somo Baby", "India");

		ArrayList<String> oID = oid.getOrderIds();
		for (String w : oID) {
			System.out.println(w);
		}

	}

	
	@Test (dependsOnMethods = {"submitOrder"})
	public void orderHistoryCheck() {
		
		System.out.println("This test RAN");
		Assert.assertTrue(true);
		
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	@DataProvider
	public Map<Object, Object> getData() throws Exception {
		
//		return new Object[][] {{"som@gmail.com", "Somosom@77","ADIDAS ORIGINAL"}, 
//			{"som@gmail.com", "Somosom@77","IPHONE 13 PRO"},
//			{"som@gmail.com", "Somosom@77","ZARA COAT 3"}};
			
			return readJsonFile("/Users/som/Desktop/AI/SeleniumFrameworkDesign/src/main/java/somoCompany/resources/data.json");			
		
	}

}
