package frameworkBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
	
//	public static ExtentTest test;
	
//	public static ExtentReports report;
//	public ExtentTest logger;
//	public static String reportPath;
	
//	public Reporter Reporter;
	

	public FrameworkBase() {
		
//		Reporter.log("Setting up- properties", true);
		
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
		
//		report = new ExtentReports();
//		Reporter.log("Setting Done- Properties", true);
//		
//		Reporter.log("Setting up- Reports", true);
//		
//		reportPath = prop.getProperty("extentReportPath");
//		ExtentSparkReporter spark = new ExtentSparkReporter(prop.getProperty("extentReportPath"));
//		report=new ExtentReports();
//		report.attachReporter(spark);
//		
//		Reporter.log("Setting Done- Report", true);
	}
	
	public void initializeDriver() {
		
//		Reporter.log("Setting up- Browser", true);
		
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
		
//		Reporter.log("Browser setup done- Application ready to run :)!!", true);
		
	}
	
	public void reportTearDown() {
//		report.flush();
//
//        Reporter.log("Test Completed >>> Reports Generated", true);
//
//        Reporter.log("Report can be accessed via >>> "+reportPath,true);
	}
	
//	public String getScreenshot(String testMethodName, WebDriver driver) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		String destinationFile = System.getProperty("user.dir") + "/reports/screenshots/" + testMethodName + ".png";
//		FileUtils.copyFile(source, new File(destinationFile));
//		
//		return destinationFile;
//	}

}
