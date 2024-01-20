package somoCompany.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import somoCompany.TestComponents.BaseTest;
import somoCompany.pageObjects.CartDetailsPage;
import somoCompany.pageObjects.LandingPage;
import somoCompany.pageObjects.OrderIdPage;
import somoCompany.pageObjects.PaymentPage;
import somoCompany.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
	
	LandingPage llp;
	String productName = "ADIDAS ORIGINAL";
	String productName1 = "IPHONE 13 PRO";
	String productName2 = "ZARA COAT 3";
//	ExtentReports report;
	
	@BeforeMethod(alwaysRun = true)
	public void initiateDriver() throws IOException {
		Properties prop = readPropertyFile();

		driver = initializeDriver();
		llp = new LandingPage(driver);
		String url = prop.getProperty("url");
		llp.goTo(url);
		report = getReportInstance();

	}	
	
	@Test(groups = {"errorHandling"})
	public void invalidLoginTest() {
		
//		report.createTest("Invalid Login test");
		
		
		llp.loginApplication("som6768gjj78@gmail.com", "Som987689@77");
		screenShotTC();

		
		AssertJUnit.assertEquals("Incorrect email or password.", llp.getErrorMessage());
//		tt.addScreenCaptureFromPath(System.getProperty("user.dir"));
		
	}

	//For each java class categorize the test cases
	//Say for login functionality check we need to create 5 TCs & they are in one java class called LoginFunctionalityCheck
	//Similarly create all the TCs for Order completion or something like that 
	//We can go & create JAVA classes with the name of SPRINTS
	
	@Test
	public void submitOrder3() throws IOException, InterruptedException {

//		LandingPage llp = launchApplication();
		ProductCatalogue pc = llp.loginApplication("som@gmail.com", "Somosom@77");

//		List<WebElement> pl = pc.getProductList();

		pc.addToCart(productName);
		pc.addToCart(productName1);
		pc.addToCart(productName2);

		CartDetailsPage cp = pc.goToCart();

		AssertJUnit.assertTrue(true);

		PaymentPage pp = cp.checkOut();

		OrderIdPage oid = pp.updatePaymentDetails("123456789", "04", "26", "123", "Somo Baby", "India");

		ArrayList<String> oID = oid.getOrderIds();
		for (String w : oID) {
			System.out.println(w);
		}
		
//		Assert.assertFalse(false);

	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		
		driver.close();
//		report.flush();
		
		
	}

	
}
