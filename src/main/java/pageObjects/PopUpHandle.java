package pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import frameworkBase.FrameworkBase;
import frameworkBase.Operations;

public class PopUpHandle extends FrameworkBase {
	
	public Operations ops;
	
	// Elements
	@FindBy(xpath="//input[@name='alert']")
	public WebElement alertButton;
	
	@FindBy(xpath="//input[@name='confirmation']")
	WebElement confirmationBoxButton;
	
	@FindBy(xpath="//input[@name='prompt']")
	WebElement promptButton;

	/**
	 * 
	 */
	public PopUpHandle(Logger log) {
		PageFactory.initElements(driver, this);
		ops = new Operations(driver, log);
	}
	
	public void verifyPopupAlert() {
		ops.verifyAlertText("I am alert");
	}


}
