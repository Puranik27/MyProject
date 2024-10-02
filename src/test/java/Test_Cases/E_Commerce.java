package Test_Cases;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjectmodel.Cart_Page;
import pageobjectmodel.Checkout_Page;
import pageobjectmodel.Confirmation_Page;
import pageobjectmodel.Landing_Page;
import pageobjectmodel.Order_Page;
import pageobjectmodel.Product_catlogue;
import testComponents.Base_Test;

public class E_Commerce extends Base_Test{
	
	String ProductName = "ZARA COAT 3";
	
@Test(dataProvider="getdata",groups= {"Purchase"})
public void eCoomerceapp(HashMap<String,String> Input) throws IOException, InterruptedException
{
	
		Product_catlogue productcatlogue = LandingPage.loginpage(Input.get("email"),Input.get("pwd"));
		
		productcatlogue.listproducts(Input.get("productName"));
		productcatlogue.products(Input.get("productName"));
		Cart_Page cartpage = productcatlogue.goTocartPage();
		
		Boolean match = cartpage.verifyProductDisplay(Input.get("productName"));
		Assert.assertTrue(match);
		Checkout_Page checkoutpage = cartpage.goTocheckout();
		checkoutpage.selectcountry("india");
		Confirmation_Page confirmationpage = checkoutpage.orderbutton();
		
		confirmationpage.orderID();
		String ThankyouMessage = confirmationpage.thankyouMessage();
		Assert.assertTrue(ThankyouMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	@Test(dependsOnMethods = {"eCoomerceapp"})
	public void OrderPlaced() {
		
		Product_catlogue productcatlogue = LandingPage.loginpage("pvp@gmail.com", "Abcd@123");
		Order_Page orderpage = productcatlogue.OrderPage();
		Assert.assertTrue(orderpage.orderdproducts(ProductName));
		
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException {
		
		List<HashMap<String, String>> data = dataReader(System.getProperty("user.dir")+ "//src//test//java//data_Reader_files//credentials.json");
		
	/*	HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","pvp@gmail.com");
		map.put("pwd", "Abcd@123");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email","puranik@gmail.com");
		map1.put("pwd", "Abcde@123");
		map1.put("productName","IPHONE 13 PRO");
		
		HashMap<String,String> map2 = new HashMap<String,String>();
		map2.put("email","puranik@gmail.com");
		map2.put("pwd", "Abcde@123");
		map2.put("productName","IPHONE 13 PRO");*/
		
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
		
		//return new Object[][] {{"pvp@gmail.com","Abcd@123","ZARA COAT 3"},{"puranik@gmail.com","Abcde@123","IPHONE 13 PRO"}};
	}
}
