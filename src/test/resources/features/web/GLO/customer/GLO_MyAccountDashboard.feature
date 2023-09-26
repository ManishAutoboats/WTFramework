#@gloAccountDashboard # @gloRegression
Feature: 13848 BAT Account management - My Account dash board tests

	Background: Navigate to BAT Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'

	@gloDeRegression
	Scenario: My Account Dashboard displays username which is also a link
		Then assert text of 'DashboardTitle.key' is displayed

	@gloPlRegression
	Scenario: Guest to enter valid email and password
		And user clicks on the links and navigates to the page
			| Title                |
			| MyAccountPage.key    |
			| MyDataPage.key       |
			| YourOrders.key       |
			| AddressBook.key      |
			| LogOut.key      	   |

	@gloDeRegression
	Scenario: My Account - Edit Account
		Then assert text of 'dashboardEditText.key' is displayed
		Then users clicks on the 'dashboardEditLinkText.key' text link
		And assert first name input is present
		And assert last name input is present
		And assert DOB input is present
		And assert save button is present
		And assert cancel button is present

	@gloDeRegression
	#Address fields has been removed as per the requirement so disabling the steps
	Scenario: My Account Dashboard page contents information expected
		Then assert text of 'myAccountDashboardText.key' is displayed
#	Then assert text of 'myAccountDashboardManageAddressText.key' is displayed 
#	Then assert text of 'myAccountDashboardEditAddressText.key' is displayed 
#	Then assert text of 'myAccountDashboardLastOrderText.key' is displayed

	@gloItLive
	Scenario: My Account - Porta un amico section without MGM Code
		Then assert text of 'PortaUnAmicoLinktext.key' is displayed
		Then users clicks on the 'PortaUnAmicoLinktext.key' text link
		And url contains 'CustomerMGMUrl.key'
		And assert title 'PortaUnAmicoLinktext.key' is displayed
		Then assert text of 'DontHaveYourCodetext.key' is displayed
		Then assert text of 'FindOutHowItWorkstext.key' is displayed
		Then user clicks on How Does it work icon
		And url contains 'PortaUnAmicoUrl.key'

	@gloItRegression
	Scenario: Refer A Friend CMS Page
	    When users clicks on the 'PortaUnAmicoLinktext.key' text link
		And  select a bring friend way if it is presence
		Then url contains 'PortaUnAmicoUrl.key'

	@gloPlRegression
	Scenario: 925606 My Account check account detail - GLO PL
		And assert My Orders Table headings are as expected
		And assert My Order table data is as expected
		And user clicks on personal data link
		And assert 'First Name' field is editable
		And assert 'Last Name' field is editable
		And click on change email checkbox
		And assert 'Email Address' field is editable
