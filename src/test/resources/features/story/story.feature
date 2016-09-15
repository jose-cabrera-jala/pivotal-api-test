Feature: Test for GET Story in Pivotal

  Background: create a Project
    Given I have a crated /projects with the table
      | name | Project 004-50 |
    And  stored as project


  Scenario: GET method for Story API
    When I send a /projects/[project.id]/stories GET request
      And I expect Status code 200
      And I send a /projects/[project.id] DELETE request
    Then I expect Status code 204