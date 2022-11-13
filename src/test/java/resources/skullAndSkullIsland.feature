Feature: Skull Island and Skull Fortune Cards
  Scenario Outline: Test Row <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_value>
    And Roll is <dice>
    Then Score is <score>
    Examples: Test values
      | test_row | dice                                                         | score   | fortune           | fortune_value |
      | 106      | "Skull,Sword,Sword,Sword,Sword,Sword,Sword,Sword"            | 0       | "Skulls"          | 2             |
      | 107      | "Skull,Skull,Sword,Sword,Sword,Sword,Sword,Sword"            | 0       | "Skulls"          | 1             |

  Scenario: Test Row 108
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Skulls" 2
    And Roll is "Skull,Skull,Parrot,Parrot,Parrot,Monkey,Monkey,Monkey"
    And Random reroll is "2,3,4"
    And Reroll is "Skull,Skull,Sword" "2,3,4"
    And Random reroll is "4,5,6,7"
    And Reroll is "Skull,Skull,Skull,Sword" "4,5,6,7"
    And Get score deduction -900
    And Deduct score from players
    Then Check score is not negative 0
  Scenario: Test Row 110
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Captain" 0
    And Roll is "Skull,Skull,Skull,Skull,Skull,Monkey,Monkey,Monkey"
    And Random reroll is "5,6,7"
    And Reroll is "Skull,Skull,Gold" "5,6,7"
    And Get score deduction -1400
    And Deduct score from players
    Then Check score is not negative 0
  Scenario: Test Row 111
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Skulls" 2
    And Roll is "Skull,Skull,Skull,Sword,Sword,Sword,Sword,Sword"
    And Random reroll is "3,4,5,6,7"
    And Reroll is "Gold,Gold,Gold,Gold,Gold" "3,4,5,6,7"
    And Get score deduction -500
    And Deduct score from players
    Then Check score is not negative 0