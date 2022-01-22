@All_Scenario
Feature: Adding Products to cart


 Background: Below are common steps for every scenario
	Given User Launch Chrome browser 

@scenario1
Scenario Outline: Adding a product to cart 
	When User opens URL as <Url>  
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "<title>"
	And Add the product "<product>" to cart 
	Then Click on Cart icon and Validate the product "<product>" from Cart 
#	And Click on Checkout
#	Then Enter user First name as "<fName>" Second name as "<sName>" and Postal code as "<zip>" 
#	And Click on Continue
#	Then Click on Finish
#	When User click on Log out link 
#	Then Page Title should be "Your store. Login" 
	And close browser 
	
	Examples: 
		|email        |password     |Url|title    |product            |fName  |sName|zip   |
		|standard_user|secret_sauce |Url|Swag Labs|Sauce Labs Backpack|Shubham|Kumar|814133|
			 


@scenario2
Scenario Outline: Logining in and verify product is present or not
	When User opens URL as <Url>  
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "<title>"
	And verify the product "<product>" is displayed or not
	And close browser
		
	Examples: 
		|email        |password     |Url|title    |product            |
		|standard_users|secret_sauce |Url|Swag Labs|Sauce Labs Backpack|

@scenario3
Scenario Outline: Logining in and verify product price is present or not
	When User opens URL as <Url>  
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "<title>"
	And verify the product "<product>" is listed with the given price
	And close browser
		
	Examples: 
		|email        |password     |Url|title    |product            |
		|standard_user|secret_sauce |Url|Swag Labs|Sauce Labs Backpack|	

@scenario4
Scenario Outline: Logining in with wrong credentials
	When User opens URL as <Url>  
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Validate error msg if credentials are Invalid
	And close browser 
	
	Examples: 
		|email        |password     |Url|
		|wrong_user   |wrong_sauce  |Url|
	