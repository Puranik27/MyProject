#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the product from Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on home page

  @Regression
  Scenario Outline: Functional Testing of purchasing the product
  
    Given Logged in with userName <username> and pwd <password> 
    When I added a product <productName> to cart
    And checkout the product <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page 

    Examples: 
      | username        |  password   |  productName  |
      | pvp@gmail.com   |  Abcd@123   |  ZARA COAT 3   |           
          
