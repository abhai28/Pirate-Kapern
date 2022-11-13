Feature: Single Player Scoring
  @SinglePlay
  Scenario Outline: Test row: <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_value>
    And Roll is <dice>
    Then Score is <score>
    Examples: Test values
      | test_row | dice                                                         | score   | fortune           | fortune_value |
      | 45       | "Skull,Skull,Skull,Sword,Sword,Sword,Sword,Sword"            | 0       | "Gold"            | 0             |
      | 52       | "Monkey,Monkey,Parrot,Parrot,Diamond,Diamond,Gold,Gold"      | 800     | "Captain"         | 0             |
      | 54       | "Skull,Skull,Monkey,Monkey,Monkey,Sword,Sword,Sword"         | 300     | "Gold"            | 0             |
      | 55       | "Skull,Skull,Monkey,Diamond,Diamond,Diamond,Sword,Parrot"    | 500     | "Gold"            | 0             |
      | 56       | "Skull,Skull,Gold,Gold,Gold,Gold,Sword,Sword"                | 700     | "Diamond"         | 0             |
      | 57       | "Skull,Parrot,Parrot,Parrot,Parrot,Sword,Sword,Sword"        | 400     | "Gold"            | 0             |
      | 62       | "Skull,Skull,Monkey,Monkey,Monkey,Monkey,Monkey,Monkey"      | 1100    | "Gold"            | 0             |
      | 63       | "Skull,Parrot,Parrot,Parrot,Parrot,Parrot,Parrot,Parrot"     | 2100    | "Gold"            | 0             |
      | 64       | "Gold,Gold,Gold,Gold,Gold,Gold,Gold,Gold"                    | 5400    | "Gold"            | 0             |
      | 65       | "Gold,Gold,Gold,Gold,Gold,Gold,Gold,Gold"                    | 5400    | "Diamond"         | 0             |
      | 66       | "Sword,Sword,Sword,Sword,Sword,Sword,Sword,Sword"            | 9000    | "Captain"         | 0             |
      | 72       | "Skull,Skull,Gold,Gold,Monkey,Monkey,Monkey,Monkey"          | 600     | "Gold"            | 0             |
  @SingleReroll
  Scenario Outline: Test row: <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_values>
    And Roll is <dice>
    And Random reroll is <dice_index>
    And Reroll is <reroll_dice> <dice_index>
    Then Score is <score>
    Examples: Test Values
      | test_row |fortune      | fortune_values | score | dice                                                      | dice_index     | reroll_dice            |
      | 46       | "Gold"      | 0              | 0     | "Skull,Parrot,Parrot,Parrot,Parrot,Sword,Sword,Sword"     | "5,6,7"        | "Skull,Skull,Sword"    |
      | 47       | "Gold"      | 0              | 0     | "Skull,Parrot,Parrot,Parrot,Parrot,Skull,Sword,Sword"     | "6,7"          | "Skull,Sword"          |
      | 53       | "Gold"      | 0              | 300   | "Skull,Parrot,Parrot,Monkey,Monkey,Skull,Sword,Sword"     | "1,2"          | "Monkey,Sword"         |
      | 58       | "Gold"      | 0              | 800   | "Skull,Parrot,Parrot,Gold,Gold,Sword,Sword,Sword"         | "1,2"          | "Gold,Sword"           |
      | 59       | "Captain"   | 0              | 1200  | "Skull,Parrot,Parrot,Gold,Gold,Sword,Sword,Sword"         | "1,2"          | "Gold,Sword"           |
      | 67       | "Gold"      | 0              | 4600  | "Monkey,Monkey,Monkey,Monkey,Monkey,Monkey,Sword,Sword"   | "6,7"          | "Monkey,Monkey"        |
      | 68       | "Diamond"   | 0              | 400   | "Skull,Monkey,Monkey,Skull,Parrot,Sword,Parrot,Sword"     | "4,6"          | "Diamond,Diamond"      |
      | 69       | "Gold"      | 0              | 500   | "Skull,Monkey,Monkey,Skull,Parrot,Sword,Diamond,Sword"    | "1,2"          | "Diamond,Diamond"      |
      | 70       | "Gold"      | 0              | 600   | "Skull,Monkey,Sword,Gold,Parrot,Gold,Sword,Sword"         | "2,6,7"        | "Gold,Monkey,Parrot"   |
      | 71       | "Diamond"   | 0              | 500   | "Skull,Monkey,Sword,Gold,Parrot,Gold,Sword,Sword"         | "2,6,7"        | "Gold,Monkey,Parrot"   |
  @DoubleReroll
  Scenario Outline: Test row: <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_values>
    And Roll is <dice>
    And Random reroll is <dice_index_1>
    And Reroll is <reroll_dice_1> <dice_index_1>
    And Random reroll is <dice_index_2>
    And Reroll is <reroll_dice_2> <dice_index_2>
    Then Score is <score>
    Examples: Test Values
      | test_row |fortune      | fortune_values | score | dice                                                      | dice_index_1     | reroll_dice_1            | dice_index_2    | reroll_dice_2      |
      | 48       | "Gold"      | 0              | 0     | "Skull,Parrot,Parrot,Parrot,Parrot,Sword,Sword,Sword"     | "5,6,7"          | "Skull,Monkey,Monkey"    | "6,7"           | "Skull,Monkey"     |
      | 50       | "Gold"      | 0              | 4800  | "Skull,Parrot,Gold,Gold,Parrot,Sword,Sword,Sword"         | "1,4"            | "Gold,Gold"              | "5,6,7"         | "Gold,Gold,Gold"   |
      | 60       | "Gold"      | 0              | 600   | "Skull,Parrot,Parrot,Monkey,Monkey,Sword,Sword,Sword"     | "3,4"            | "Skull,Sword"            | "1,2"           | "Sword, Monkey"    |
