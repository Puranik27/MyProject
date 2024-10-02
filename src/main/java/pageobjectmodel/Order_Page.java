package pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Reuseable_Code;

public class Order_Page extends Abstract_Reuseable_Code{
	
	WebDriver driver;
	
	public Order_Page(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath = "//td[text() = 'ZARA COAT 3']")
	List<WebElement> orderproducts;

	
	public Boolean orderdproducts(String ProductName) {
		
		Boolean match = orderproducts.stream()
				.anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
}
