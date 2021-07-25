package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import org.apache.logging.log4j.Logger;

import frameworkBase.FrameworkBase;
import frameworkBase.Operations;

public class GreenCartHomePage extends FrameworkBase {
	
	public Operations ops;
	
	// Elements
	// Header Elements
	@FindBy(xpath = ".//div/header")
	WebElement headerSection;
	
	@FindBy(partialLinkText = "Limited offer")
	WebElement offerLink;
	
	@FindBy(css = "div.greenLogo")
	WebElement pageNameLogo;
	
	@FindBy(linkText = "Top Deals")
	WebElement topDeals;
	
	@FindBy(css = ".cartinfo")
	WebElement smallCartTable;
	
	@FindBy(css = "tr:nth-child(1) td:nth-child(3)")
	WebElement smallCartItemCount;
	
	@FindBy(xpath = ".//div[@class='cart-info']/table/tbody/tr[2]/td[3]")
	WebElement smallCartItemPrice;
	
	@FindBy(xpath = "//a[@class='cart-icon']")
	static WebElement cartPreviewIcon;
	
	@FindBy(css = "div.cart-preview.active")
	WebElement cartPreview;
	
	@FindAll({
		@FindBy(css = "div.cart-preview.active li")
	})
	public List<WebElement> cartPreviewItemLists;
	
	// Product list
	@FindAll({
		@FindBy(className = "product") 
	})
	List<WebElement> allProducts;
	
	@FindBy(className = "products")
	WebElement productContainer;
	
	@FindAll({
		@FindBy(css = "div.product-image img") 
	})
	public List<WebElement> allProductImages;
	
	@FindBy(xpath = "//footer")
	public WebElement footerSection;
	
	
	// Constructor
	public GreenCartHomePage(Logger log) {
		PageFactory.initElements(driver, this);
		ops = new Operations(driver, log);
	}
	
	// Methods for Header section
	public boolean verifyLogoExists() { 
		return pageNameLogo.isDisplayed();
	}
	
	public void verifyLogoText(String elemText) { 
		ops.verifyObjectText(pageNameLogo, elemText);
	}
	
	public void scrollTo(String elemText) { 
		ops.verifyObjectText(pageNameLogo, elemText);
	}

	public ArrayList<String> verifyHeaderSectionStyle() {

		ArrayList<String> styleList = new ArrayList<String>(3);
		
		styleList.add(headerSection.getAttribute("style"));
		styleList.add(headerSection.getCssValue("margin-left"));
		styleList.add(headerSection.getCssValue("animation"));
		
		return styleList;
	}
	
	public void verifySmallCartInitialState() {
		Assert.assertEquals(smallCartItemCount.getText(), "0", "Default Item Count is 0");
		Assert.assertEquals(smallCartItemPrice.getText(), "0", "Default Item Price is 0");
		
	}
	
	// Methods for Product lists
	public int verifyProductCount() {
		int actualProductCount;
		actualProductCount= allProducts.size();
		return actualProductCount;
	}
	
	public WebElement getProductByIndex(int index) {		
		return allProducts.get(index);
	}
	
	public WebElement getProductCardByName(String name) {		
		// Get Element with that name under products list 
		// Get parent element card that contains the product name
		WebElement cardElem = productContainer.findElement(By.xpath("//h4[contains(text(),'" + name + "')]/parent::div[@class='product']"));
		return cardElem;
	}
	
//	public ProductCard getProductCardObj(WebElement productCardElement) {
//		String imgSrc = productCardElement.findElement(By.cssSelector("img")).getAttribute("src"); 
//		String imgAlt = productCardElement.findElement(By.cssSelector("img")).getAttribute("alt");
//		String productName = productCardElement.findElement(By.className("product-name")).getText();
//		String productPrice = productCardElement.findElement(By.className("product-price")).getText();
//		String quantity = productCardElement.findElement(By.className("quantity")).getText();
//		WebElement addToCart = productCardElement.findElement(By.tagName("button"));
//		
//		ProductCard productCard = new ProductCard(imgSrc, imgAlt, productName, productPrice, quantity, addToCart);
//		return productCard;
//	}
//	
//	public String getPseudoStyleOfIndex(int index) {
//		// Pseudo Element ::before
//		String script = 
//		"return window.getComputedStyle(document.querySelectorAll('p.product-price')[" + 
//		String.valueOf(index) + 
//		"],':before').getPropertyValue('content')";
//		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String productCurrency = (String) js.executeScript(script);
//		return productCurrency;
//	}
//	
//	public String getProductDetails(WebElement elem, String productProperty) {
//		ProductCard newProductCard = this.getProductCardObj(elem);
//		if(productProperty.equals("src")) {
//			return newProductCard.imgSrc;
//		} else if (productProperty.equals("alt")) {
//			return newProductCard.imgAlt;
//		} else if (productProperty.equals("product-name")) {
//			return newProductCard.productName;
//		} else if (productProperty.equals("product-price")) {
//			return newProductCard.productPrice;
//		} else if (productProperty.equals("quantity")) {
//			return newProductCard.quantity;
//		} else if (productProperty.equals("buttonText")) {
//			// TODO How to add different Return Data type
//			return newProductCard.addToCart.getText();
//		} else {
//			return "Not a valid Property Name. Use : src, alt, product-name, product-price, quantity, buttonText";
//		}
//	}
//	
//	private WebElement getProductAddButton(WebElement elem) {
//		ProductCard newProductCard = this.getProductCardObj(elem);
//		if (elem.isDisplayed()) {
//			return newProductCard.addToCart;
//		} else {
//			return null;
//		}
//	}
//	
//	public void addProductToCartByIndex(int index) {
//		WebElement productCard = this.getProductByIndex(index);
//		WebElement ProductAddToCartButton = getProductAddButton(productCard);
//		ProductAddToCartButton.click();
//	}
//	
//	public void verifyCartPreviewForItemAdded(WebElement elem) {
//		ProductCard newProductCard = this.getProductCardObj(elem);
//		verifyElementIsDisplayed(cartPreviewIcon);
//		cartPreviewIcon.click();
//		// Explicit Wait 
//		WebDriverWait wait = new WebDriverWait(driver, 1000);
//		wait.until(ExpectedConditions.visibilityOf(this.cartPreview)); 
//		
//		WebElement lastProductAdded = this.cartPreviewItemLists.get(this.cartPreviewItemLists.size()-1);
//		// Verify Image Link is valid for the Item
//		String imageUrl = lastProductAdded.findElement(By.cssSelector("img.product-image")).getAttribute("src");
//		try {
//			this.verifyUrl(imageUrl);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// Verify product details Section
//		String productDetails = lastProductAdded.findElement(By.cssSelector("div.product-info p")).getText();
//		assertTrue(productDetails.equals(newProductCard.productName), "Product Description matched");
//		// Verify product Price
//		String productPrice = lastProductAdded.findElement(By.cssSelector("div.product-info p.product-price")).getText();
//		Assert.assertEquals(productPrice, newProductCard.productPrice, "Product price matched");
//		// TODO Add other validations for last product added
//	}
//	
//	// Generic Methods // TODO move to utilities when it feels safe
//	public void verifyIfImageIfBroken(List<WebElement> elems) throws IOException {
//		for(int i=0; i<elems.size(); i++) {
//			this.verifyUrl(elems.get(i).getAttribute("src"));
//		}
//	}
//	
//	public void verifyIfLinkIfBroken(List<WebElement> elems) throws IOException {
//		for(int i=0; i<elems.size(); i++) {
//			this.verifyUrl(elems.get(i).getAttribute("href")); // TODO Not Implemented in test but should work smooth
//		}
//	}
//	
//	private void verifyUrl(String url) throws IOException {
//		// TODO update Code using HTTPRequest
//		URL obj = new URL(url);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("GET");
//		
//		if (String.valueOf(con.getResponseCode()).equals("200")){
//			
//		}
//		else {
//			System.out.println("Image @ url " + url + " does not exists at server");
//		}
//	
//	}
//	
//	private static void verifyElementIsDisplayed(WebElement elem) {
//		
//		elem.isDisplayed();
//	}

}
