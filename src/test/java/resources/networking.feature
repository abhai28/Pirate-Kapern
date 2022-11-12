Feature: Multiplayer testing
  Scenario: Test Row 130
    Given Start server "Rig 1"
    And Players are connected
    And Player get message "Game Has Begun"
    And Player get message "Score" and "Score: 0"
    And Player 1 gets message "Fortune" and "Fortune Card: Captain"
    And Player 2 gets message "Fortune" and "Fortune Card: Skulls 1"
    And Player 3 gets message "Fortune" and "Fortune Card: Gold"
    And Player 1 gets message "Dice" and "Your turn has started!"
    And Player 1 gets message "Dice" and "1: Sword 2: Sword 3: Sword 4: Skull 5: Sword 6: Sword 7: Sword 8: Sword "
    And Player 1 gets message "Reroll" and "Would you like to reroll the dices? Yes or No"
    And Player 1 writes back "No"
    And Player 1 gets message "Score" and "Score: 4000"
    And Player 1 gets message "Dice" and "Your turn has ended!"
    And Player 2 gets message "Dice" and "Your turn has started!"
    And Player 2 gets message "Dice" and "1: Sword 2: Sword 3: Sword 4: Skull 5: Sword 6: Sword 7: Sword 8: Sword "
    And Player 2 gets message "Reroll" and "Would you like to reroll the dices? Yes or No"
    And Player 2 writes back "No"
    And Player 2 gets message "Score" and "Score: 2000"
    And Player 2 gets message "Dice" and "Your turn has ended!"
    And Player 3 gets message "Dice" and "Your turn has started!"
    And Player 3 gets message "Dice" and "1: Monkey 2: Skull 3: Skull 4: Skull 5: Monkey 6: Monkey 7: Monkey 8: Monkey "
    And Player 3 gets message "Dice" and "You have rolled 3 which has disqualified you."
    And Player 3 gets message "Score" and "Score: 0"
    And Player 3 gets message "Dice" and "Your turn has ended!"
    And Player 1 gets message "Dice" and "The winner is player 1 with score 4000."
    And Player 2 gets message "Dice" and "The winner is player 1 with score 4000."
    And Player 3 gets message "Dice" and "The winner is player 1 with score 4000."
    Then Close server


