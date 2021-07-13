package frameworkBase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends FrameworkBase implements ITestListener {
	
	static ExtentTest test;
	ExtentReports report = ExtentReportNG.getExtentReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testMethodName = result.getMethod().getMethodName();
		test = report.createTest(testMethodName);
		extentTest.set(test);
		
		extentTest.get().log(Status.INFO, "Starting Test : " + testMethodName);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		WebDriver driver=null;
		String testMethodName = result.getMethod().getMethodName();
		
		extentTest.get().log(Status.PASS, "Test : " + testMethodName + " Passed");
//		extentTest.get().pass(result.getThrowable()); // TODO remove later if not needed
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass()
					.getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Take Screenshots
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName, driver), testMethodName);
//			getScreenshot(testMethodName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver=null;
		String testMethodName = result.getMethod().getMethodName();
		
		extentTest.get().log(Status.FAIL, "Test : " + testMethodName + " Failed");
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass()
					.getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Take Screenshots
		try {
			String screenshotPath = getScreenshot(testMethodName, driver);
//			extentTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName, driver), result.getMethod().getMethodName());
			extentTest.get().addScreenCaptureFromPath(screenshotPath, testMethodName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.SKIP, "Going to skip this test");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.INFO, "Finishing Test");
		report.flush();
		
	}

}
