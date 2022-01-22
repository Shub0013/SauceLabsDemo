Feature: Logging
	
@scenario
Scenario Outline: Logining into the site 
	Given User Launch Chrome browser 
	When User opens URL as <Url>  
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "<title>"
	
#	When User click on Log out link 
#	Then Page Title should be "Your store. Login" 
	And close browser 
	
	Examples: 
		|email        |password     |Url|title    |product            |
		|standard_user|secret_sauce |Url|Swag Labs|Sauce Labs Backpack|
