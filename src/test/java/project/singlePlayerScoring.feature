Feature: Single player plays game and rolls dice
  @SinglePlay
  Scenario Outline: Player plays one roll and score
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