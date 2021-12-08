Feature: Users API Tests

  Scenario: Create a new user
    Given I have a random user
    When I send POST request to 'api/users/' endpoint
    Then I see status code 201
    And I see user data in response body