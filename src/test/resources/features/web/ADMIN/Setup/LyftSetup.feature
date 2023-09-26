## Navigation to registration page
## invalid registration details and validation msg's
## Valid registration
## Registration validation errors

@smokeLyftAdminFast @LyftAdminSmoke
Feature:Lyft Setup prerequirement for magento admin cases - Lyft

	Background: Navigate to BAT Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup
	 And user selects over 18 age confirmation option
And user clicks the person icon -lyft

	Scenario: Registration of new user for Lyft brand
		When user clicks the registration button
		And populate Personal Information - first and last name with randomly generated data
		And tick signup for newsLetter tick box
		And populate DOB field with 'validDOB.key'
		And populate gender field with 'gender.key'
		And populate address fields
		And add BankID for user-Lyft
		And populate signin fields
		And user selects the create an account button
		Then assert text of 'successRegistraionMsg.key' is displayed

	Scenario: Register user adds item to card and goes to checkout
		And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
		And user click on search icon and submits the following search term 'searchTermdi.key'
		And click first result
		And select product strength or colour
		And click add to cart button
		And click on basket icon
		And user clicks on checkout button
		Then Payment page details confirmed

	Scenario: Order Status tests - Debit Card Payment - Pending Payment section -for Lyft
		And enter select package shop details
		And click on shipping method
		And Credit Card - VISA order option and press next
		And enter visa card details
		And tick agree to terms and conditions
		And select place order
		And user clicks on Next button to move head and place the order
		Then assert print receipt link is present
		Then assert text of 'ThankForPurchase.key' is displayed
		Then assert text of 'yourOrderNumberText.key' is displayed
		And grab and output Order number

	Scenario:Admin-Lyft-Customer search result is displayed
		When user logins Magento Admin home page successfully
		And user navigates to all customers page
		And user searches customer by search keyword
		Then search result is displayed correctly


