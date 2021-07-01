package testSuite;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static org.testng.Assert.assertTrue;

import frameworkBase.FrameworkBase;
import pageObjects.GreenCartHomePage;

public class GreenCartHomeTest extends FrameworkBase {
	GreenCartHomePage greenCartHomePage;
	private static Logger log = LogManager.getLogger(GreenCartHomeTest.class.getName());
	
	static ExtentTest test;
	static ExtentReports report;
	
	public WebDriver driver;
	

	@BeforeClass
	public void startTest() {
		System.out.println("Starting test class");
		log.info("Starting test class");
		
		
	}
	
	@AfterClass
	public void endTest()
	{
		System.out.println("Ending test class");
		
		
	}

	public GreenCartHomeTest() {
		super();
	}

	@BeforeTest
	public void testSetup() {

	}

	@BeforeMethod
	public void testCaseSetup() {
		
		driver = initializeDriver();

		greenCartHomePage = new GreenCartHomePage();
		driver.get(prop.getProperty("greenCartURL"));
		log.info("Driver is Closed");
	}

	@AfterMethod
	public void testMethodEnd() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.close();
		driver.quit();
		log.info("Driver is Closed");
		
	}

	@Test(enabled = true)
	public void testMetaData() {
		
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertEquals(pageTitle, "GreenKart - veg and fruits kart1");
		log.info("Meta data is validated");
		
	}
	
	@Test(enabled = true)
	public void verifyPageLogo() {
		
		System.out.println("Second test");
		System.out.println(greenCartHomePage.verifyLogoText());
		Assert.assertEquals(greenCartHomePage.verifyLogoExists(), true);
		
	}

}
