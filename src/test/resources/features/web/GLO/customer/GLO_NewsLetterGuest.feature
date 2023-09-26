Feature: BAT newsletter Guest Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloItSmoke @gloItRegression2 #@gloItLive @gloItRegression
  Scenario: Glo IT Newsletter Guest - enter valid e-mail address and assert success message
    And ensure glo newsletter element is present on page
    And ensure glo newsletter sign up button present on page
    And click on glo newsletter button
    And Newsletter-glo user enters DOB
    And enter glo newsletter details and random email and submit
    And assert newsletter success validation message of 'Grazie per la tua iscrizione.'

  @gloItLive @gloItRegression2
  Scenario: Glo IT Newsletter Guest - enter invalid e-mail address and assert Error
    And ensure glo newsletter element is present on page
    And ensure glo newsletter sign up button present on page
    And click on glo newsletter button
    And Newsletter-glo user enters DOB
    And enter glo invalid newsletter details for glo and submit
    And assert newsletter error validation message of 'Inserisci un indirizzo email valido (Esempio: johndoe@dominio.it).'

  @gloKzSmoke #@gloKzLive #@gloKzRegression blocked by 527504- N/A
  Scenario:Glo KZ Newsletter Guest - enter valid e-mail address and assert success message
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And Newsletter-glo user enters DOB
    And enter glo newsletter details and random email and submit
    And  validation success message displayed

	#@gloKzRegression blocked by 527504- N/A
  Scenario: Glo KZ Newsletter Guest - enter invalid e-mail address and assert Error
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And Newsletter-glo user enters DOB
    And enter glo invalid newsletter details for glo and submit
    And NewsLetter invalid email message displayed

	#@gloKzRegression blocked by 527504- N/A
  Scenario:Newsletter Guest - enter existing e-mail address and expected message
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    When user enter newsletter details and submits the form with loginSubscribedEmail.key
    And click on glo newsletter button
    When user enter newsletter details and submits the form with loginSubscribedEmail.key
    Then assert text of 'emailAddressAlreadySubscribeTextMsg.key' is displayed

#Scenario:
#21885 Newsletter Guest - enter existing e-mail address and expected message 
#	And first and last name and email input boxes present as guest user 
#	And enter first and last name 
#	And enter e-mail address already used and assert 'emailAddressAlreadySubscribeTextMsg.key' message is presented

  #@gloKzRegression blocked by 527504- N/A
#	USER STORY 217646
  Scenario: Minimum age 21 years - DOB field validation newsletter page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    When user enters newsletter details and submits the form with age as in table then assert expected is displayed
      | age      | expected                         |
      | 20-years | newsletterMinimumAgeErrorMsg.key |
      | 21-years | subscribeSuccessText.key         |

  @gloItRegression2
  Scenario: Newsletter Guest - SMS Marketing options are displayed
    Given ensure glo newsletter element is present on page
    And ensure glo newsletter sign up button present on page
    And click on glo newsletter button
    Then verify the email and sms consent option is present