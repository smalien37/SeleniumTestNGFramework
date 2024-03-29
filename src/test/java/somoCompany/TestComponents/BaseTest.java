package somoCompany.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import somoCompany.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;

	static String path = System.getProperty("user.dir");

	public Properties readPropertyFile() throws IOException {

		Properties prop = new Properties();
		FileInputStream f = new FileInputStream(path + "/src/main/java/somoCompany/resources/GlobalData.properties");
		prop.load(f);
		return prop;

	}

	public WebDriver initializeDriver() throws IOException {

		Properties prop = readPropertyFile();
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		//The above one is for the passing thebrowser from maven command
		
//		String browserName = prop.getProperty("browser");
		
		
		if (browserName.equals("chromeHeadless")) {
			System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		}
		else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
			driver = new ChromeDriver();
		}
		else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "/geckodriver");
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", path + "/edgedriver");
			driver = new EdgeDriver();
		}
		else if (browserName.equals("safari")) {
//			System.setProperty("webdriver.safari.driver", path + "/safari");
			driver = new SafariDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;

	}

	public LandingPage launchApplication() throws IOException {

		Properties prop = readPropertyFile();

		WebDriver driver = initializeDriver();
		LandingPage lp = new LandingPage(driver);
		String url = prop.getProperty("url");
		lp.goTo(url);

		return lp;
	}

	public List<Map<Object, Object>> readJsonFile(String path) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

		List<Map<Object, Object>> data = mapper.readValue(jsonContent, new TypeReference<List<Map<Object, Object>>>() {
		});

		return data;
	}

	public String screenShotTC(WebDriver driver) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String k = System.getProperty("user.dir")+"/Screenshots/Evidence-" + System.currentTimeMillis() + ".png";
		File filePath = new File(k);

		try { // now copy the screenshot to desired location using copyFile method
			
			FileUtils.copyFile(src, filePath);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return k;
		
	}
	
	
//	public  ExtentReports report;
//	public  ExtentSparkReporter htmlReporter;
//
//	public  ExtentReports getReportInstance() {
//
//		report = new ExtentReports();
//		 htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/report_"+System.currentTimeMillis()+".html");	
//		 htmlReporter.config().setDocumentTitle("Dummy Test Reports");
//			htmlReporter.config().setReportName("Hackathon COURSERA Test Report");
//			htmlReporter.config().setTheme(null);
//			
//			report.setSystemInfo("Machine", "Som-mac-mini");
//			report.setSystemInfo("Environment", "DevOps");
//			report.setSystemInfo("Browser", "Chrome");
//			report.setSystemInfo("OS", "MAC-OS");
//			report.setSystemInfo("Owner", "Soumyajit");
//			report.attachReporter(htmlReporter);
//			
//		return report;
//	}

}
