Feature: Identify Courses
  @Smoke @Regression
  Scenario: Search for Web Development Courses
    Given Navigate to the coursera home page
    When the user clicks on the inputBox
    And the user selects the checkBox
    Then verify the name of the first course
    Then verify the rating of the first course
    Then verify the duration of the first course
    Then verify the name of the second course
    Then verify the rating of the second course
    Then verify the duration of the second course
  @Smoke @Regression
  Scenario: Display the languages and levels
    Given navigate to the learning page
    Then get the languages and their count
    And get the levels and their count
  @Smoke @Regression
  Scenario: Fill the form
    Given navigate to the Coursera home page
    When the user clicks on For Enterprises
    Then enter the invalid details of email in the form
    And verify the first invalid error message
    Then enter the invalid details of phone in the form
    And verify the second invalid error message
    Then enter the valid details in the form
    And verify the third valid message
