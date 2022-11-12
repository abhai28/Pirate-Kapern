Feature: Multiplayer testing
  Scenario: run server
    Given Start server "Rig 1"
    And Players are connected
    And Player get msg