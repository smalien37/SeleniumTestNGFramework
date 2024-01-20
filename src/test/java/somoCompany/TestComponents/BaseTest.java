package somoCompany.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
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
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
			driver = new ChromeDriver();
		}
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "/geckodriver");
			driver = new FirefoxDriver();
		}
		if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", path + "/edgedriver");
			driver = new EdgeDriver();
		}
		if(browserName.equals("safari")) {
			System.setProperty("webdriver.safari.driver", path + "/safari");
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
	
	public Map<Object, Object> readJsonFile(String path) throws Exception {
		
		 ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(new File(path), new TypeReference<Map<Object, Object>>() {});
		
	}
	
	
	/* 	
	{
		"email": "som@gmail.com",
		"password": "Somosom@77",
		"productName": "IPHONE 13 PRO"
	},
	
	{
		"email": "som@gmail.com",
		"password": "Somosom@77",
		"productName": "ZARA COAT 3"
	}
] */
	
	
}
