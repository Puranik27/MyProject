package pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Reuseable_Code;

public class Product_catlogue extends Abstract_Reuseable_Code{
	
	WebDriver driver;
	
	public Product_catlogue(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@class = 'col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By waitEle = By.xpath("//div[@class = 'col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']");
	By waiting = By.cssSelector("b");
	By prodnames = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.id("toast-container");
 	
	public List<WebElement> waitElements() {
		
		waitForElementvisible(waitEle);
		return products;
	}
	
	public WebElement listproducts(String ProductName) {
		
		WebElement prod = products.stream()
				.filter(product -> product.findElement(waiting).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
	}
	public void products(String product) {
		
		WebElement prod = listproducts(product);
		prod.findElement(prodnames).click();
		waitForElementvisible(toastmessage);
		invisibleofElement(spinner);
	}
}
