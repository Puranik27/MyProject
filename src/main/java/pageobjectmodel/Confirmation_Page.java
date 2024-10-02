package pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Reuseable_Code;

public class Confirmation_Page extends Abstract_Reuseable_Code{
	
	WebDriver driver;
	
	public Confirmation_Page(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "(//*[@class = 'em-spacer-1'])[4]")
	WebElement orderid;
	
	@FindBy(css = ".hero-primary")
	WebElement thanksMessage;
	
	public void orderID() throws InterruptedException{
		
		String OrderID = orderid.getText();
		System.out.println("Order ID : " + OrderID);
	}

	public String thankyouMessage() {
		
	String message = thanksMessage.getText();
	return message;
	}
}
