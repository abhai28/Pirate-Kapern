Feature: Miscellaneous Fortune Cards and Full Chest bonus
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