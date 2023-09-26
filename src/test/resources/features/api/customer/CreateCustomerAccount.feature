@api
Feature: Create Customer account

  Scenario: Create customer account
    When I create a customer account
    Then Customer account response status code is 200
    And Verify customer account is created with the given details

  @bug118579
  Scenario: Create customer account with subscription
    When I create a customer account with subscription
    Then Customer account response status code is 200
    And Verify customer account is created with the given details

  Scenario Outline: Create customer account with invalid details
    When I create a customer account with invalid <field>
    Then Customer account response status code is <status>
    And Customer account error message is <error_msg>
    Examples:
      | field      | status | error_msg                                                                       |
      | email      | 400    | The customer email is missing. Enter and try again.                             |
      | password   | 400    | The password needs at least %1 characters. Create a new password and try again. |

  Scenario: Create customer account with the existing email
    When I create a customer account
    Then Customer account response status code is 200
    And Verify customer account is created with the given details
    When I create a customer account with existing user email
    Then Customer account response status code is 400
    And Customer account error message is A customer with the same email address already exists in an associated website.

  Scenario: Create test account1
    When I create test account for different site
      | baseURL                                                            |firstname |lastname  | dob           | gender|create_address|company|telephone      |country_id|region   |city          |postcode    |street[]                               | default_billing|default_shipping|email                  |password|password_confirmation|persistent_remember_me|custom-terms|mgm_promo_code|codice_fiscale  |region_id|referrer|birth_city|consent_store_my_data|neo_ext|citizen_card_number|bank_id     |
      | https://www-eu-uat1-global-vype.non-prod.marketing.bat.net/gb/en/  |Auto      |Test      | 08/05/1977    | 1     |1             |       |0987654321234  |GB        |         |London        |SW1 2AA     |10 Downing Street                      |1              |1               |battest11@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-glo.non-prod.marketing.bat.net/it/it    |Auto      |Test      | 08/05/1977    | 1     |1             |       |+391234567890  |IT        |         |SnztEK        |UB10 9DW    |12 The Cloisters                       |1              |1               |battest8@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |12345680A0212345|         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-glo.non-prod.marketing.bat.net/kz/ru    |Auto      |Test      | 08/05/1977    | 1     |1             |       |+71234567890   |KZ        |         |SnztEK        |160800      |10 Downing Street                      |1              |1               |battest11@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-vype.non-prod.marketing.bat.net/nl/nl   |Auto      |Test      | 08/05/1977    | 1     |1             |       |013561346113   |NL        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-vype.non-prod.marketing.bat.net/fr/fr   |Auto      |Test      | 08/05/1977    | 1     |1             |       |019458770659   |FR        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-vype.non-prod.marketing.bat.net/dk/da   |Auto      |Test      | 08/05/1977    | 1     |1             |       |019458770659   |DK        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-am-uat1-global-vype.non-prod.marketing.bat.net/mx/es   |Auto      |Test      | 08/05/1977    | 1     |1             |       |+528065012269  |MX        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |592      |        |          |                     |434567 |                   |            |
      | https://www-am-uat1-global-vype.non-prod.marketing.bat.net/co/es   |Auto      |Test      | 08/05/1977    | 1     |1             |       |+573447596217  |CO        |         |Belén         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |706      |        |          |                     |       |  12345            |            |
      | https://www-eu-uat1-global-vype.non-prod.marketing.bat.net/ie/en   |Auto      |Test      | 08/05/1977    | 1     |1             |       |019458770659   |IE        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-lyft.non-prod.marketing.bat.net/se/sv   |Auto      |Test      | 1977-05-08   | 1     |1             |       |016156721262   |SE        |         |Stockholm     |11164       |Nils Ericsons plan 4, Stockholms Stad  |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |197506032916|
      | https://www-eu-uat1-global-lyft.non-prod.marketing.bat.net/dk/da   |Auto      |Test      | 08/05/1977    | 1     |1             |       |019458770659   |DK        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-vype.non-prod.marketing.bat.net/it/it   |Auto      |Test      | 08/05/1977    | 1     |1             |       |+391234567890  |IT        |         |Rosasco       |35040       |12 The Cloisters                       |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |MGM-53553     |12345680A0212345|593      |        |Test City |1                    |       |                   |            |
      | https://www-eu-uat1-global-vype.non-prod.marketing.bat.net/de/de   |Auto      |Test      | 08/05/1977    | 1     |1             |       |013561346113   |DE        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |
      | https://www-eu-uat1-global-glo.non-prod.marketing.bat.net/de/de    |Auto      |Test      | 08/05/1977    | 1     |1             |       |013561346113   |DE        |         |BdPtY         |UB10 9DW    |10 Downing Street                      |1              |1               |battest4@mailinator.com|Pa55word|Pa55word             |on                    |on          |              |                |         |        |          |                     |       |                   |            |

    Then Customer account response status code is 302
    And Redirect location is correct


  Scenario Outline: Create test account
    When I create the test account battest@mailinator.com for <site>
    Then Customer account response status code is 302
    #And Redirect location is correct
    And Confirm the user is created with <code> in DB
    And Confirm the created user with <code> if needed
    Examples:
      | site           | code        |
      | eu-vuse-gb/en  |vuse_uk_en_gb|
      | eu-glo-it/it   |glo_it_it_it|
      | eu-glo-kz/ru   |glo_kz_ru_kz|
      | eu-vype-nl/nl  |vype_nl_nl_nl|
      | eu-vype-fr/fr  |vuse_fr_fr_fr|
      | eu-vuse-dk/da  |vuse_dk_da_dk|
      | am-vuse-mx/es  |vuse_mx_es_es|
      | am-vype-co/es  |vuse_co_es_es|
      | eu-vype-ie/en  |vype_ie_en_ie|
      | eu-lyft-se/sv  |lyft_se_sv_se|
      | eu-lyft-dk/da  |lyft_dk_da_dk|
      | eu-vuse-it/it  |vuse_it_it_it|
      | eu-vype-de/de  |vype_de_de_de|
      | eu-glo-de/de   |glo_de_de_de|
      | eu-glo-pl/pl   |glo_pl_pl_pl|

  @createAutoTestAccount
  Scenario Outline: Create test account via api for all sites
    When create the test account battest@mailinator.com for <site> and <code>
    Then Customer account response status code is 200
    And Confirm the user is created with <code> in DB
    And Confirm the created user with <code> if needed
    Examples:
      | site           | code        |
      | eu-vuse-gb/en  |vuse_uk_en_gb|
      | eu-vype-nl/nl  |vype_nl_nl_nl|
      | eu-vuse-fr/fr  |vuse_fr_fr_fr|
      | am-vuse-mx/es  |vuse_mx_es_es|
      | am-vuse-co/es  |vuse_co_es_es|
      | eu-vuse-it/it  |vuse_it_it_it|
      | eu-vuse-de/de  |vuse_de_de_de|
      | eu-glo-de/de   |glo_de_de_de|
      | eu-glo-it/it   | glo_it_it_it|
      | eu-glo-kz/ru   |glo_kz_ru_kz|
      | eu-glo-pl/pl   |glo_pl_pl_pl|
      | eu-lyft-se/sv  |lyft_se_sv_se|
      | eu-vuse-za/en  |vuse_za_en_za|
      | eu-velo-za/en  |velo_za_en_za|
      | eu-velo-pl/pl  |velo_eu_pl_pl|
      | eu-velo-be/be  |velo_eu_be_en|

  @createAutoTestAccount
  Scenario Outline: Create another test account via api for parallel run
    When create the test account battestsubs@mailinator.com for <site> and <code>
    Then Customer account response status code is 200
    And Confirm the user is created with <code> in DB
    And Confirm the created user with <code> if needed
    Examples:
      | site           | code        |
      | eu-vype-fr/fr  |vuse_fr_fr_fr|
      | eu-lyft-se/sv  |lyft_se_sv_se|
      | eu-vuse-de/de  |vuse_de_de_de|
      | eu-vuse-it/it  |vuse_it_it_it|

  @createAutoTestAccountToken
  Scenario Outline: Create test account token via api for all sites
    When create token for the test account battest@mailinator.com for <site> and <code>
    Then Customer token response status code is 200
    Examples:
      | site           | code        |
      | eu-vuse-gb/en  |vuse_uk_en_gb|
#      | eu-vype-nl/nl  |vype_nl_nl_nl|
      | eu-vuse-fr/fr  |vuse_fr_fr_fr|
#      | eu-vuse-dk/da  |vuse_dk_da_dk|
#      | am-vuse-mx/es  |vuse_mx_es_es|
      | am-vype-co/es  |vuse_co_es_es|
#      | eu-vype-ie/en  |vype_ie_en_ie|
      | eu-vuse-it/it  |vuse_it_it_it|
      | eu-vuse-de/de  |vuse_de_de_de|
      | eu-glo-de/de   |glo_de_de_de|
      | eu-glo-it/it   | glo_it_it_it|
      | eu-glo-kz/ru   |glo_kz_ru_kz|
      | eu-glo-pl/pl   |glo_pl_pl_pl|
      | eu-lyft-se/sv  |lyft_se_sv_se|
#      | eu-lyft-dk/da  |lyft_dk_da_dk|
#      | eu-epok-de/de  | epok_de_de_de|
      | eu-vuse-za/en  |vuse_za_en_za|
#      | eu-velo-de/de  |velo_eu_de_de|