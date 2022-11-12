Feature: Full Chest Test
  Scenario Outline: Test Row <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_value>
    And Roll is <dice>
    Then Score is <score>
    Examples: Test values
      | test_row | dice                                                         | score   | fortune           | fortune_value |
      | 97       | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Diamond,Parrot"      | 400     | "Gold"            | 0             |
      | 98       | "Monkey,Monkey,Monkey,Sword,Sword,Sword,Gold,Gold"           | 1800    | "Captain"         | 0             |
      | 99       | "Diamond,Sword,Monkey,Monkey,Monkey,Sword,Sword,Sword"       | 1000    | "Gold"            | 0             |
      | 103      | "Diamond,Diamond,Monkey,Monkey,Parrot,Diamond,Gold,Gold"     | 1200    | "Monkey Business" | 0             |

  Scenario: Test Row 100
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Sea Battle" 2
    And Roll is "Monkey,Monkey,Monkey,Monkey,Sword,Parrot,Parrot,Gold"
    And Random reroll is "5,6"
    And Reroll is "Gold,Sword" "5,6"
    Then Score is 1200
