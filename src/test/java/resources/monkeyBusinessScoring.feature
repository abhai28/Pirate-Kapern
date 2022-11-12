Feature: Monkey business scoring
  Scenario: Test Row 82
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Monkey Business" 0
    And Roll is "Monkey,Monkey,Monkey,Skull,Gold,Parrot,Parrot,Parrot"
    Then Score is 1100

  Scenario: Test Row 83
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Monkey Business" 0
    And Roll is "Monkey,Sword,Sword,Monkey,Gold,Parrot,Gold,Parrot"
    And Random reroll is "1,2"
    And Reroll is "Monkey,Parrot" "1,2"
    Then Score is 1700

  Scenario: Test Row 84
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Monkey Business" 0
    And Roll is "Monkey,Monkey,Monkey,Skull,Skull,Skull,Parrot,Parrot"
    Then Score is 0