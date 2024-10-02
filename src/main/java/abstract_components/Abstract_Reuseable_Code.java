package abstract_components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjectmodel.Cart_Page;
import pageobjectmodel.Order_Page;

public class Abstract_Reuseable_Code {
	
	WebDriver driver;

	public Abstract_Reuseable_Code(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);	
	}
	
	@FindBy(xpath = "(//button[@class = 'btn btn-custom'])[3]")
	WebElement cartHeader;
	
	@FindBy(xpath = "//button[@routerlink = '/dashboard/myorders']")
	WebElement orderheader;

	public void waitForElementvisible(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));	
	}
	
	public void waitForWebElementvisible(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));	
	}
	
	public void invisibleofElement(WebElement web) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(web));
	}
	
	public Cart_Page goTocartPage() {
		
		cartHeader.click();
		Cart_Page cartpage= new Cart_Page(driver);
		return cartpage;
	}
	public Order_Page OrderPage() {
		
		orderheader.click();
		Order_Page orderpage = new Order_Page(driver);
		return orderpage;
	}
	
	public  void scrollbar() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
}
