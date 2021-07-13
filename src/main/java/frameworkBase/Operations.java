package frameworkBase;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Operations {
	public Logger log;
	public Logger log1;
	public WebDriver driver;
	public WebDriver driver1;
	private String logMsg;

	public Operations(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// TO be deleted, Use below method
	public void verifyPageTitle(WebDriver driver, Logger log, String expectedTitle) {
		this.logMsg = "User expects page title to be : " + expectedTitle;
		log.info(this.logMsg);

		try {
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle, "Title match failed");
			log.info("Title matched: Expected : " + expectedTitle + "; Actual : " + actualTitle);
		} catch (Exception e) {
			log.info(e);
		}
	}
	
	/**
	   * Asserts that Actual title is matched with expected title. If they are not,
	   * an AssertionError, with the given message, is thrown.
	   * @param expectedTitle the expected Title as String
	   */
	public void verifyPageTitle(String expectedTitle) {
		this.logMsg = "User expects page title to be : " + expectedTitle;
		log.info(this.logMsg);

		try {
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle, "Title match failed");
			log.info("Title matched: Expected : " + expectedTitle + "; Actual : " + actualTitle);
		} catch (Exception e) {
			log.info(e);
		}
	}

	/**
	   * Asserts that Actual innerText is matched with expected innerText. If they are not,
	   * an AssertionError, with the given message, is thrown.
	   * @param expectedText the expected Title as String
	   */
	public void verifyObjectText(WebElement elem, String expectedText) {
		this.logMsg = "User expects object innerText to be : " + expectedText;
		log.info(this.logMsg);

		try {
			String actualText = elem.getText();
			Assert.assertEquals(actualText, expectedText, "InnerText match failed");
			log.info("InnerText matched: Expected : " + expectedText + "; Actual : " + actualText);
		} catch (Exception e) {
			log.info(e);
		}
	}
}
