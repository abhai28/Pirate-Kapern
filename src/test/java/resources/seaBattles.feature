Feature: Sea Battles
  Scenario Outline: Test Row <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_value>
    And Roll is <dice>
    And check sea battle <battle_score>
    Then Score is <score>
    Examples: Test values
      | test_row | dice                                                         | score | fortune      | fortune_value | battle_score |
      | 114      | "Monkey,Monkey,Monkey,Monkey,Skull,Skull,Skull,Sword"        | 0     | "Sea Battle" | 2             | -300         |
      | 116      | "Monkey,Monkey,Skull,Skull,Skull,Sword,Sword,Sword"          | 0     | "Sea Battle" | 4             | -1000        |
      | 117      | "Monkey,Monkey,Gold,Parrot,Parrot,Monkey,Sword,Sword"        | 500   | "Sea Battle" | 2             | 300          |
      | 120      | "Monkey,Monkey,Sword,Sword,Skull,Monkey,Sword,Sword"         | 800   | "Sea Battle" | 3             | 500          |
      | 123      | "Monkey,Monkey,Sword,Sword,Skull,Monkey,Sword,Sword"         | 1300  | "Sea Battle" | 4             | 1000         |

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
      | test_row | fortune      | fortune_values | score | dice                                                      | dice_index     | reroll_dice                | battle_score |
      | 115      | "Sea Battle" | 3              | 0     | "Skull,Skull,Parrot,Parrot,Parrot,Parrot,Sword,Sword"     | "2,3,4,5"      | "Skull,Skull,Skull,Skull"  | -500         |
      | 118      | "Sea Battle" | 2              | 500   | "Monkey,Skull,Monkey,Monkey,Parrot,Parrot,Monkey,Sword"   | "4,5"          | "Skull,Sword"              | 300          |
      | 121      | "Sea Battle" | 3              | 0     | "Monkey,Skull,Monkey,Monkey,Skull,Sword,Monkey,Sword"     | "1,2,3,6"      | "Skull,Skull,Sword,Sword"  | -500         |

  Scenario: Test Row 124
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Sea Battle" 4
    And Roll is "Monkey,Monkey,Monkey,Sword,Skull,Diamond,Parrot,Parrot"
    And Random reroll is "6,7"
    And Reroll is "Sword,Sword" "6,7"
    And Random reroll is "0,1,2"
    And Reroll is "Sword,Parrot,Parrot" "0,1,2"
    And check sea battle 1000
    Then Score is 1300