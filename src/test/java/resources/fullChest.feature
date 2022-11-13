Feature: Full Chest
  Scenario Outline: Test row: <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_value>
    And Roll is <dice>
    Then Score is <score>
    Examples: Test values
      | test_row | dice                                                         | score   | fortune           | fortune_value |
      | 97       | "Monkey,Monkey,Monkey,Diamond,Parrot,Sword,Sword,Sword"      | 400     | "Gold"            | 0             |
      | 98       | "Monkey,Monkey,Monkey,Gold,Gold,Sword,Sword,Sword"           | 1800    | "Captain"         | 0             |
      | 99       | "Monkey,Monkey,Monkey,Diamond,Sword,Sword,Sword,Sword"       | 1000    | "Gold"            | 0             |
      | 103      | "Monkey,Monkey,Parrot,Diamond,Diamond,Diamond,Gold,Gold"     | 1200    | "Monkey Business" | 0             |

  Scenario Outline: Test Row <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_values>
    And Roll is <dice>
    And Random reroll is <dice_index>
    And Reroll is <reroll_dice> <dice_index>
    And check sea battle <battle_score>
    Then Score is <score>
    Examples: Test Values
      | test_row | fortune      | fortune_values | score | dice                                                      | dice_index     | reroll_dice   | battle_score |
      | 100      | "Sea Battle" | 2              | 1200  | "Monkey,Monkey,Monkey,Monkey,Sword,Parrot,Parrot,Gold"    | "5,6"          | "Gold,Sword"  | 300          |
