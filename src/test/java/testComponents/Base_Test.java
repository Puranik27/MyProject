package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjectmodel.Landing_Page;

public class Base_Test {
	
	public WebDriver driver;
	public  Landing_Page LandingPage;
	
	public WebDriver intializedriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		// prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
		ChromeOptions options = new ChromeOptions();
		
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
			options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			 driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			
		 driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("chrome://settings/clearBrowserData");
		//driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		return driver;	
	}
	
	@BeforeMethod(alwaysRun=true)
	public Landing_Page lauchApplication() throws IOException {
		
		 driver = intializedriver();
		 LandingPage = new Landing_Page(driver);
		 LandingPage.url();
		 return LandingPage;
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty(("user.dir")+"//reports//" + testcaseName + ".png");
		
	}
	
	public List<HashMap<String, String>> dataReader(String filepath) throws IOException {
		
		// read json to string
		String jsoncontent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//converting string to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		}

	@AfterMethod(alwaysRun=true)
	public void exitProject() {
		
		driver.quit();
	}
}
