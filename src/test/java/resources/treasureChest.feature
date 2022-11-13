Feature: Treasure Chest
  Scenario: Test row 87
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Treasure Chest" 0
    And Roll is "Sword,Sword,Diamond,Diamond,Gold,Parrot,Parrot,Parrot"
    And Put "2,3,4" in chest
    Then Check if treasure added 3
    And Random reroll is "0,1"
    And Reroll is "Parrot,Parrot" "0,1"
    And Put "0,1,2,3,4" in chest
    And Takeout "0,1,2"
    And Random reroll is "0,1,2"
    And Reroll is "Skull,Gold,Parrot" "0,1,2"
    Then Score is 1100
  Scenario: Test row 92
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is "Treasure Chest" 0
    And Roll is "Skull,Skull,Gold,Gold,Gold,Parrot,Parrot,Parrot"
    And Put "2,3,4" in chest
    Then Check if treasure added 3
    And Random reroll is "2,3,4"
    And Reroll is "Diamond,Diamond,Gold" "2,3,4"
    And Put "4" in chest
    And Random reroll is "2,3"
    And Reroll is "Skull,Gold" "2,3"
    Then Score is 600