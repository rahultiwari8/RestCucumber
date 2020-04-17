Feature: This feature is to check GET API

Scenario Outline: Check if user is able to submit GET API request

   Given I want to execute service <serviceName>

    When I submit the GET request as per the data in Excel Worksheet <EWorksheet>

    And I validate status code

    And I validate mandatory tag in response from Excel Worksheet <EWorksheet>

    And I validate response content

    And I validate tag values in response from Excel Worksheet <EWorksheet>

    And I validate header parameter in response

Examples:

| serviceName          | EWorksheet |
| "getClientDetails"|  "first"|
