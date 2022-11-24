Feature: pltsci-sdet-assignment

  Scenario: pltsci-sdet-assignment
    Given A room size of 5 by 5
    * robot initial coordinates of 1 and 2
    * dirt patches at
      | X | Y |
      | 1 | 0 |
      | 2 | 2 |
      | 2 | 3 |
    * movement instructions of "NNESEESWNWW"
    When user calls "AddPlaceAPI" with "POST" http request