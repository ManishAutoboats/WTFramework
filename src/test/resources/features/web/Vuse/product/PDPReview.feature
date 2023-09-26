
 Feature: 13842 BAT PDP Theme feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

   @VuseITAnonReg3
  Scenario: Vuse IT guest user Reviews /Ratings on PDP
   And user click on search icon and submits the following search term 'reviewapprovedProduct.key'
   And user closes the alert if present
   And click on the first result with a review
   And assert review rating is present
   When user click on add rating CTA
   Then verify an error message is displayed
   When guest user click on sign in link from error message
   Then user should be redirected to loginPageTitle.key page

  @VuseITReg
  Scenario: logged-in user Reviews /Ratings on PDP and PLP
   And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
   And user click on search icon and submits the following search term 'reviewapprovedProduct.key'
   And user closes the alert if present
   And click on the first result with a review
   And assert review rating is present
   When user click on add rating CTA
   And user fill the rating form and submit
   Then add review success message is displayed
   And the newly added item is not in the review list

  #@VuseUKReg Going live in disabled mode in RT 87 release
  Scenario: Bazar Voice PDP page review rating
   And create a new account
   When users clicks on the 'ShopDevices.key' text link
   And user closes the alert if present
   And verify the first product is reviewed or non reviewed and navigae to PDP
   Then ensure PDP elements are displayed as expected
   And assert review rating is present
   Then assert review button is present for reviewed product
   When user click on add rating CTA
   Then assert rating modal window is opened
   And user fill the rating form and submit
   And assert form is submitted successfully
   When user close the review submit confirmation popup
   Then assert the review description is same as submitted by user

  #@VuseUKReg Going live in disabled mode in RT 87 release
  Scenario: Bazar Voice PLP page review rating
   And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
   When users clicks on the 'ShopDevices.key' text link
   And user closes the alert if present
   And assert review rating is present for all the product on PLP
   When users clicks on the 'ShopFlavours.key' text link
   And assert review rating is present for all the product on PLP

  @VuseZAReg
  Scenario: DEFECT 874041 logged-in user Reviews /Ratings on PDP
   And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
   And user click on search icon and submits the following search term 'reviewapprovedProduct.key'
   And user closes the alert if present
   And click on first result
   And assert review rating is present
   When user click on add rating CTA
   And user fill the rating form and submit

  @VuseUKReg
  Scenario: 950306, 964772 Artist CMS block for Skins on PDP
   And user navigates to BAT "Epod 2 skin" page via URL
   And assert 'artist-block-container' section is display on PDP
   And assert 'artist-block-image' section is display on PDP
   And assert 'artist-block-artist-name' section is display on PDP
   And assert 'artist-block-artist-desc' section is display on PDP
   And assert 'artist-block-artist-collection-title' section is display on PDP
   And assert 'artist-block-artist-collection-detail' section is display on PDP

   #automate bug 923131
   @VuseUKReg
   Scenario: Validate notice me when in stock
    When user click on search icon and submits the following search term 'OutOfStockItem.key'
    And assert text of 'outOfStockProduct.key' is displayed
    Then click strength "12" on pdp page
    And assert CTA notice me when in stock is display correctly

  @VuseITReg
  Scenario: logged-in user add Reviews /Ratings without filling fields
   Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
   And user click on search icon and submits the following search term 'reviewapprovedProduct.key'
   And user closes the alert if present
   When click on the first result with a review
   Then assert review rating is present
   When user click on add rating CTA
   And user clicks on submit button without filling the rating form
   Then assert the error messages on the rating form