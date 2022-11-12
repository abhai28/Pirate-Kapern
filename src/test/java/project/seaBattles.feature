Feature: Sea Battle single player scoring
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
