package frameworkBase;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Operations extends FrameworkBase {
	public Logger log;
	public WebDriver driver;
	
	public Operations(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}
	
	public void verifyPageTitle(WebDriver driver, Logger log, String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title match failed");

		log.info("Meta data is validated");
		
	}

	/**
	 * @param log
	 */


}
