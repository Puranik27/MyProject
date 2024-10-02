package pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Reuseable_Code;

public class Checkout_Page extends Abstract_Reuseable_Code{
	
	WebDriver driver;
	
	public Checkout_Page(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "(//*[@class = 'input txt text-validated'])[2]")
	WebElement searchcountry;
	
	@FindBy(xpath = "(//*[@class = 'ta-item list-group-item ng-star-inserted'])[2]")
	WebElement selectcountry;
	
	@FindBy(xpath = "//*[@class = 'btnn action__submit ng-star-inserted']")
	WebElement placeorderbutton;
	
	public void selectcountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(searchcountry,countryName).build().perform();
	}
	
	public Confirmation_Page orderbutton() throws InterruptedException {
		
		selectcountry.click();
		scrollbar();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace(); // Handle the exception
		    // Optionally, you may re-interrupt the thread:
		    Thread.currentThread().interrupt();
		}

		Thread.sleep(2000);
		placeorderbutton.click();
		Confirmation_Page confirmationpage = new Confirmation_Page(driver);
		return confirmationpage;
		
	}
}
