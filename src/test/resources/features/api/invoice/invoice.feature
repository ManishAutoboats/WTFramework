@api
Feature: Create Invoice for a given order and email the invoice to customer

  Background: Create sales order
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details

#  email is being sent but taking around 4 mins to reach the mail.
  Scenario: Create Invoice for a given order and email
    When create invoice for the order via api
    Then assert response status code is 200
    When invoice is emailed to the customer
    Then assert response status code is 200
