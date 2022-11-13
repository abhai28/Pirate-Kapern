Feature: Sorceress
  @Sorceress
  Scenario: Test Row 77
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Sorceress" 0
    And Roll is "Diamond,Diamond,Sword,Monkey,Gold,Parrot,Parrot,Parrot"
    And Random reroll is "5,6,7"
    And Reroll is "Skull,Monkey,Monkey" "5,6,7"
    And Sorceress card is used
    And Fixed sorceress reroll "Monkey" 5
    Then Score is 500

  Scenario: Test Row 78
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Sorceress" 0
    And Roll is "Skull,Skull,Skull,Parrot,Parrot,Parrot,Sword,Sword"
    And Sorceress card is used
    And Fixed sorceress reroll "Parrot" 0
    And Random reroll is "6,7"
    And Reroll is "Parrot,Parrot" "6,7"
    Then Score is 1000
  Scenario: Test Row 79
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Sorceress" 0
    And Roll is "Skull,Monkey,Parrot,Parrot,Parrot,Parrot,Monkey,Monkey"
    And Random reroll is "1,6,7"
    And Reroll is "Skull,Parrot,Parrot" "1,6,7"
    And Sorceress card is used
    And Fixed sorceress reroll "Parrot" 0
    Then Score is 2000