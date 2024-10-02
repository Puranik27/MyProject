package pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Reuseable_Code;

public class Cart_Page extends Abstract_Reuseable_Code{
	
	WebDriver driver;
	
	public Cart_Page(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//*[@class = 'cartSection']/h3")
	private List<WebElement> cartProducts;
	
	
	@FindBy(xpath = "//*[@class = 'totalRow']/button")
	WebElement checkoutEle;
	
	public Boolean verifyProductDisplay(String ProductName) {
		
		Boolean match = cartProducts.stream()
				.anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	public Checkout_Page goTocheckout() {
		
		checkoutEle.click();
		Checkout_Page checkoutpage = new Checkout_Page(driver);
		return checkoutpage;
	}
}
