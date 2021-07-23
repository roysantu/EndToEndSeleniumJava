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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameworkBase {

	public static WebDriver driver;
	public Properties prop;

	public FrameworkBase() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/resources/data.properties");
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

		if (browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + prop.getProperty("chromeDriverPath"));

			// From Maven
			WebDriverManager.chromedriver().version(prop.getProperty("chromeDriverVersion")).setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-gpu");
			driver = new ChromeDriver(options);

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().version(prop.getProperty("FirefoxDriverVersion")).setup();
//			FirefoxOptions options = new FirefoxOptions() //TODO implement options for firefox driver

			driver = new FirefoxDriver();

		} else if (browserName.equals("safari")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + prop.getProperty("safariDriverPath"));
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}

	public String getScreenshot(String testMethodName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destinationFile = System.getProperty("user.dir") + "/reports/screenshots/" + testMethodName + "_"
				+ timeStamp + ".png";

		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

	public String captureScreenshot(String screenshotPaths, String testMethodName) {

		try {
			if (screenshotPaths != null && !screenshotPaths.isEmpty()) {
				screenshotPaths = screenshotPaths + ";" + getScreenshot(testMethodName, driver);
			} else {
				screenshotPaths = getScreenshot(testMethodName, driver);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return screenshotPaths;
	}

}
