package pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Reuseable_Code;

public class Landing_Page extends Abstract_Reuseable_Code{
	
	WebDriver driver;
	
	public Landing_Page(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(id = "userEmail")
	WebElement usrnm;
	
	@FindBy(id = "userPassword")
	WebElement pwd;
	
	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement toastMessage;
	
	By toast = By.xpath("//div[@aria-label='Incorrect email or password.']");
	
	
	public Product_catlogue loginpage(String username,String password) {
		
		usrnm.sendKeys(username);
		pwd.sendKeys(password);
		login.click();
		Product_catlogue productcatlogue = new Product_catlogue(driver);
		return productcatlogue;
	}
	
	public void url() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String invalidUser() {
		
		waitForElementvisible(toast);
		return toastMessage.getText();
		
	}
	
}
