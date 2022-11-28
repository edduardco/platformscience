@pltsci-sdet-assignment
Feature: pltsci-sdet-assignment

  @cleaning-sessions
  Scenario: cleaning service
    Given A room size of 5 by 5
    * robot initial coordinates of 1 and 2
    * dirt patches at
      | X | Y |
      | 1 | 0 |
      | 2 | 2 |
      | 2 | 3 |
    * movement instructions of "NNESEESWNWW"
    When robot calls cleaning service
    Then cleaning calls responds with status code 200
    * verify robot final coordinates are 1 and 3
    * verify cleaned patches is 1
