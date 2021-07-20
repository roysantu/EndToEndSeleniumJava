package frameworkBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class FrameworkBase {
	
	public static WebDriver driver;
	public Properties prop;
	
	public FrameworkBase() {
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("/Users/santuroy/Documents/newWorkspace/EndToEndJavaSeleniumFramework/src/main/java/resources/data.properties");
			prop.load(fis); // Read data from Property file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeDriver() {
		
		String browserName = prop.getProperty("browserName");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
			
		} else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("geckoDriverPath"));
			driver = new FirefoxDriver();
			
		} else if(browserName.equals("safari")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("safariDriverPath"));
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	
	public String getScreenshot(String testMethodName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destinationFile = System.getProperty("user.dir") + "/reports/screenshots/" + testMethodName + "_"+ timeStamp + ".png";
		
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

}
