Feature: BAT checkout Loyalty Points
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account via api and log in with the account

  @VuseFRReg3
  Scenario: Verify awarded Loyalty Points and Price after redemption.
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And assert if user is able to input any value '4' in mini basket Qty field
    And assert if user is able to input any value '5' in mini basket Qty field
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    And grab Order Number and assert Order Status on View Order Details page
    And assert status of the order is "processing" via api
    When create invoice for the order via api
    Then assert Loyalty points awarded for the order
    And search and checkout product "searchTerm.key"
    Then Payment page details confirmed
    Then user should be able to see the option to redeem the loyalty points
    And assert Total Charges are updated after redeeming the loyalty points

  @VuseFRReg3
  Scenario: VuseFR - RAF send invitation email and get loyalty points
    Then users clicks on the 'referFriendLink.key' link
    And click on the send invitations link
    And enter the email address of the friend
    When click on submit invitation button
    Then assert success message of 'rafInvitationSuccess.key' presented to user
    When the friend click on the RAF link received in mailinator
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account with RAF link
    Then users clicks on the 'yourAddressText.key' text link
    And populate all address fields except for first and last name
    Then click the save address button
    And Customer makes a home delivery purchase with "mastercard"
    And grab Order Number and assert Order Status on View Order Details page
    And assert status of the order is "processing" via api
    When create invoice for the order via api
    And users clicks on the 'logoutText.key' text link
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user login with credentials registered via api
    And user goes to my account page
    And click on loyalty points link
    Then verify loyalty points awarded for referring the friend