package testSuite;

import java.io.IOException;
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
import frameworkBase.Operations;
import pageObjects.GreenCartHomePage;

public class GreenCartHomeTest extends FrameworkBase {
	public WebDriver driver;
	GreenCartHomePage greenCartHomePage;

	public Logger log = LogManager.getLogger(GreenCartHomeTest.class.getName());

	static ExtentTest test;
	static ExtentReports report;

	public Operations ops;

	// for multiple screenshots
	public String screenshotPaths = "";
	//

	@BeforeClass
	public void startTest() {
		System.out.println("Starting test class");
		log.info("Starting test class");
	}

	@AfterClass
	public void endTest() {
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
		ops = new Operations(driver, log);

		greenCartHomePage = new GreenCartHomePage(log);
		ops.openUrl(prop.getProperty("greenCartURL"), prop.getProperty("greenCartURLRedirect"));

	}

	@AfterMethod
	public void testMethodEnd() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.close();
		driver.quit();
		log.info("Driver is Closed");

		screenshotPaths = "";
	}

	@Test(enabled = true)
	public void testMetaData() {
		ops.verifyPageTitle("GreenKart - veg and fruits kart");
	}

	@Test(enabled = true)
	public void verifyPageLogo() {
		greenCartHomePage.verifyLogoText("GREENKART");
	}
	
	@Test(enabled = true)
	public void verifyPageFooterIsDisplayed() {
		screenshotPaths = captureScreenshot(screenshotPaths, "DefaultHomePage");
		ops.jsScrollTo(greenCartHomePage.footerSection);
		
	}

}
