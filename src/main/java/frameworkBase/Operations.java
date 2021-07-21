package frameworkBase;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Operations {
	public Logger log;
	public Logger log1;
	public WebDriver driver;
	public WebDriver driver1;
	private String logMsg;
	
//	public WebDriverWait explicitWait = new WebDriverWait(driver, 30);

	public Operations(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}
	
	/**
	   * Verify if page is loaded and used privately in ops class. If they are not,
	   * an AssertionError, with the given message, is thrown.
	   */
	private void waitForPageLoaded() {
		// Custom expected condition
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
                
        try {
        	Thread.sleep(3000); // TODO fix this wait
            
//        	explicitWait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

	/**
	   * Open URL and asset correct URL is opened or redirected to expected url (using overload). If they are not,
	   * an AssertionError, with the given message, is thrown.
	   * @param url Application url as String
	   * @param redirectUrl Application redirect url as String
	   */
	public void openUrl(String url) {
		this.logMsg = "User expects to navigate to : " + url;
		log.info(this.logMsg);

		driver.get(url);
//		this.waitForPageLoaded();
		 
		Assert.assertEquals(driver.getCurrentUrl(), url, "User successfully opened URL");
		log.info("Opened: url : " + url);
	}

	public void openUrl(String url, String redirectUrl) {
		this.logMsg = "User expects to navigate to : " + url;
		log.info(this.logMsg);

		driver.get(url);
//		this.waitForPageLoaded();

		this.logMsg = "User successfully opened " + url + " and navigated to " + redirectUrl;
		Assert.assertEquals(driver.getCurrentUrl(), redirectUrl, this.logMsg);
		log.info(this.logMsg);
	}

	// TO be deleted, Use below method
	public void verifyPageTitle(WebDriver driver, Logger log, String expectedTitle) {
		this.logMsg = "User expects page title to be : " + expectedTitle;
		log.info(this.logMsg);

		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title match failed");
		log.info("Title matched: Expected : " + expectedTitle + "; Actual : " + actualTitle);
	}

	/**
	 * Asserts that Actual title is matched with expected title. If they are not, an
	 * AssertionError, with the given message, is thrown.
	 * 
	 * @param expectedTitle the expected Title as String
	 */
	public void verifyPageTitle(String expectedTitle) {
		this.logMsg = "User expects page title to be : " + expectedTitle;
		log.info(this.logMsg);

		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title match failed");
		log.info("Title matched: Expected : " + expectedTitle + "; Actual : " + actualTitle);
	}

	/**
	 * Asserts that Actual innerText is matched with expected innerText. If they are
	 * not, an AssertionError, with the given message, is thrown.
	 * 
	 * @param expectedText the expected Title as String
	 */
	public void verifyObjectText(WebElement elem, String expectedText) {
		this.logMsg = "User expects object innerText to be : " + expectedText;
		log.info(this.logMsg);

//		explicitWait.until(ExpectedConditions.invisibilityOf(elem));
		Assert.assertTrue(elem.isDisplayed(), this.logMsg);
		
		String actualText = elem.getText();
		Assert.assertEquals(actualText, expectedText, "InnerText match failed");
		log.info("InnerText matched: Expected : " + expectedText + "; Actual : " + actualText);
	}
	
	/**
	 * Get innerText for a given element or Locator. If they are
	 * not found, an AssertionError, with the given message, is thrown.
	 * 
	 * @param expectedText the expected Title as String
	 * @param Locator xpath/CSS as String (Overload methods)
	 */
	public String getObjectText(WebElement elem) {
		String actualText;
		
		this.logMsg = "User gets object innerText if object is displayed";
		log.info(this.logMsg);
		
		this.logMsg = "Explicitly wait for element to be displayed for :" + "Seconds"; //TODO Set global wait for explicit wait from properties
		log.info(this.logMsg);
		
//		explicitWait.until(ExpectedConditions.invisibilityOf(elem));
		Assert.assertTrue(elem.isDisplayed(), this.logMsg);
		actualText = elem.getText();
		
		log.info("InnerText for elem is : " + actualText);
		
		return actualText;
	}

	/**
	 * Returns elements using xpath. If element is not found, an Error is thrown.
	 * Handles Elements not found and stale element Overload with wait time
	 * 
	 * @param xPath    the expected Title as String
	 * @param waitTime Optional wait time, default to 3 seconds if not provided
	 */
	public void getElementByXpath(String xPath) {
		this.logMsg = "User expects object to be present with Xpath: " + xPath;
		log.info(this.logMsg);
		//TODO
	}
}
