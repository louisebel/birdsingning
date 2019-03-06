Feature: Administration of a level

  Scenario: activation of a level Ok if all previous level activated
    Given The level 4 exists with quizes :
      | question            | choixA      | choixB | choixC | choixAnswer |
      | cock-a-doodle-doo   | rooster     | hen    | eagle  | rooster     |

    And they are already 3 activated levels
    When an admin try to update the level 4
    Then activation of the level 4 should have been a success

  Scenario Outline: impossible to activate a level because no quiz or all the previous are not activate
    Given The level has an "<id>" and "<numberOfQuizes>" and will be activate
    And  the "<numberOfPreviousLevelActivate>"
    When an admin try to patch the level "<id>"
    Then there is an exception with "<exceptionMessage>"

    Examples:
      | id | numberOfPreviousLevelActivate | numberOfQuizes | exceptionMessage                                                                 |
      | 4  | 2                             | 1              | Impossible to activate the level because all the previous level are not activate |
      | 4  | 3                             | 0              | Impossible to activate the level because it doesn't have quiz                    |
