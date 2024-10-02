package Test_Cases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjectmodel.Cart_Page;
import pageobjectmodel.Checkout_Page;
import pageobjectmodel.Confirmation_Page;
import pageobjectmodel.Landing_Page;
import pageobjectmodel.Product_catlogue;
import testComponents.Base_Test;
import testComponents.Retry;

public class Error_Validation extends Base_Test{
	
@Test(groups = {"ErrorValidation"}, retryAnalyzer = Retry.class)
public void LoginValidtion() throws IOException
{

		String ProductName = "ZARA COAT 3";
		Product_catlogue productcatlogue = LandingPage.loginpage("pvp@gmail.com", "Abcde@1234");
		String message = LandingPage.invalidUser();
		Assert.assertEquals("Incorrect email or password.", message);

	}

@Test
public void productCheck() throws IOException
{

	String ProductName = "ZARA COAT 3";
	Product_catlogue productcatlogue = LandingPage.loginpage("puranik@gmail.com","Abcde@123");
	
	productcatlogue.listproducts(ProductName);
	productcatlogue.products(ProductName);
	Cart_Page cartpage = productcatlogue.goTocartPage();
	
	Boolean match = cartpage.verifyProductDisplay("ZARA COAT 3");
	Assert.assertTrue(match);
	}
}
