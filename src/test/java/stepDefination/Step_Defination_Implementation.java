package stepDefination;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjectmodel.Cart_Page;
import pageobjectmodel.Checkout_Page;
import pageobjectmodel.Confirmation_Page;
import pageobjectmodel.Landing_Page;
import pageobjectmodel.Product_catlogue;
import testComponents.Base_Test;

public class Step_Defination_Implementation extends Base_Test{
	public Landing_Page landing; 
	public Product_catlogue productcatlogue;
	public Confirmation_Page confirmationpage;
	
	@Given("I landed on home page")
	public void I_landed_on_home_page() throws IOException {
		
		landing = lauchApplication();
		
		}
	
	@Given("^Logged in with userName (.+) and pwd (.+)$")
	public void Logged_in_with_username_and_pwd(String username, String password) {
		
		productcatlogue = LandingPage.loginpage(username,password);

	}
	 @When("^I added a product (.+) to cart$")
	 public void I_added_a_product_to_cart(String productName) {
		 
		 productcatlogue.listproducts(productName);
			productcatlogue.products(productName);
	 }
	 @And("^checkout the product (.+) and submit the order$")
	 public void checkout_the_product_and_submit_the_order(String productName) throws InterruptedException {
		 
		 Cart_Page cartpage = productcatlogue.goTocartPage();
			
			Boolean match = cartpage.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			Checkout_Page checkoutpage = cartpage.goTocheckout();
			checkoutpage.selectcountry("india");
			confirmationpage = checkoutpage.orderbutton();
	 }
	 @Then("{string} message is displayed on Confirmation Page")
	 public void message_is_displayed_on_Confirmation_Page(String string) throws InterruptedException {
		 
		 confirmationpage.orderID();
			String ThankyouMessage = confirmationpage.thankyouMessage();
			Assert.assertTrue(ThankyouMessage.equalsIgnoreCase(string));
			driver.close();
		 
	 }
	 @Then("{string} error message is displayed")
	 public void error_message_is_displayed(String string) {
		 
		 String message = LandingPage.invalidUser();
			Assert.assertEquals(string, message);
			driver.close();
	 }

}
