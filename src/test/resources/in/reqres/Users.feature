Feature: Users API Tests

  Scenario: Create a new user
    Given I have a random user data
    When I send POST request
    Then I see status code 201
    And I see user data in response body

  Scenario: Update user job with PUT request
    Given  I have created user
    When I send PUT request with updated job
    Then I see status code 200
    And I see updated job in response


  Scenario: Update user name with PATH request
    Given  I have created user
    When I send PATCH request with updated name
    Then I see status code 200
    And I see updated name in response

  Scenario: Delete user
    Given  I have created user
    When I send DELETE request
    Then I see status code 204

  Scenario: Create, update and than delete user
    #Create new user
    Given I have a random user data
    When I send POST request
    Then I see status code 201
    And I see user data in response body
    #Update job by new created user
    When I send PUT request with updated job
    Then I see status code 200
    And I see updated job in response
    #Update name with PATCH request
    When I send PATCH request with updated name
    Then I see status code 200
    And I see updated name in response
    #Delete created user
    When I send DELETE request
    Then I see status code 204
