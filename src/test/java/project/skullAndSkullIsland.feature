Feature: Skull Island and Skull Fortune Card
  Scenario Outline: Test Row <test_row>
    Given Play a turn
    When Get fortune card
    And Roll dice
    And Fortune card is <fortune> <fortune_value>
    And Roll is <dice>
    Then Score is <score>
    Examples: Test values
      | test_row | dice                                                         | score   | fortune           | fortune_value |
      | 106      | "Skull,Sword,Sword,Sword,Sword,Sword,Sword,Sword"            | 0       | "Skulls"           | 2             |
      | 107      | "Skull,Skull,Sword,Sword,Sword,Sword,Sword,Sword"            | 0       | "Skulls"           | 1             |