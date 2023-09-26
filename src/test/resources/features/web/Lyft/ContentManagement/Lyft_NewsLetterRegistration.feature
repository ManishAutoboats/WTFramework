#This is the newsletter feature file

Feature: BAT newsletter Registration Feature

Background: Navigate to BAT Home page
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

	@LyftRegression2
	Scenario: NewsLetter - Register and sign
		And user clicks the person icon -lyft
		And user clicks the registration button
		And populate Personal Information - first and last name with randomly generated data
		And tick signup for newsLetter tick box
		And populate DOB field with 'validDOB.key'
		And populate gender field with 'gender.key'
		And populate address fields
		And add BankID for user-Lyft
		And populate signin fields
		And user selects the create an account button
		Then assert text of 'successRegistraionMsg.key' is displayed
		And select from myAccount links NewsLetter link
		Then assert status of marketing tickbox
		And user clicks the person icon -lyft
		And proceed to delete customer account

	@LyftRegression2
	Scenario: New user subscribe to newsletter from Home page and verify in My Account page
		When create a new account with random email
		And user clicks on Lyft logo in the header
		And click on newsletter button
		When user enter newsletter details and submits the form with registered email
		Then assert text of 'subscribeSuccessText.key' is displayed
		And user clicks the person icon -lyft
		And select from myAccount links NewsLetter link
		Then assert status of marketing tickbox
		And user clicks on Lyft logo in the header
		And click on newsletter button
		When user enter newsletter details and submits the form with registered email
		Then assert text of 'emailAddressAlreadySubscribeTextMsg.key' is displayed
		And user clicks the person icon -lyft
		And select from myAccount links NewsLetter link
		And uncheck email consent checkbox
		And click subscription save button
		Then assert text of 'RemoveNewsletterSubscription.key' is displayed


  Scenario: New user subscribe to newsletter from Home page and verify in My Account page - En
	And switch to EN site
	And select allow all from cookie popup and select over 18 age confirmation option
	When create a new account with random email
	And user clicks on Lyft logo in the header
	And click on newsletter button
	When user enter newsletter details and submits the form with registered email
	Then assert text of 'subscribeSuccessTextEn.key' is displayed
	And user clicks the person icon -lyft
	And select from myAccount links NewsLetter link
	Then assert status of marketing tickbox
    And user clicks on Lyft logo in the header
    And click on newsletter button
    When user enter newsletter details and submits the form with registered email
    Then assert text of 'emailAddressAlreadySubscribeTextMsgEn.key' is displayed
    And user clicks the person icon -lyft
    And select from myAccount links NewsLetter link
    And uncheck email consent checkbox
    And click subscription save button
    Then assert text of 'RemoveNewsletterSubscriptionEn.key' is displayed