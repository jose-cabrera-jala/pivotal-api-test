Feature: Test for PUT Comments in Pivotal

  Background: create a Project, Story and Comments
    Given  I have a created /projects with the table
      | name | Project 005-49 |
    And I expect Status code 200
    And stored as project
    And I have a created /projects/[project.id]/stories with the table
      | name | Story 005-50 |
    And I expect Status code 200
    And stored as story
    And I have a created /projects/[project.id]/stories/[story.id]/comments with the table
      | text | Comments 005-50 |
    And I expect Status code 200
    And stored as comment

  Scenario: PUT method for Comments API with table
    When  I send a /projects/[project.id]/stories/[story.id]/comments/[comment.id] PUT request with table
      | text | Comments 005-01 |

    Then I expect Status code 200
    And I send a /projects/[project.id] DELETE request
    And I expect Status code 204

  Scenario: PUT method for Story API with json
    When  I send a /projects/[project.id]/stories/[story.id]/comments/[comment.id] PUT request with json
      """
      {
        "text":"Comments 005-01"
      }
      """
    Then I expect Status code 200
    And I send a /projects/[project.id] DELETE request
    And I expect Status code 204