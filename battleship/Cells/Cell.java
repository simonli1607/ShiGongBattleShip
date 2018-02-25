package edu.neu.ccs.cs5004.assignment5.battleship.Cells;

import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Ship;
import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.Printer;

/**
 * Represents a cell.
 */
public interface Cell {
  /**
   * Get the attack result for a cell.
   *
   * @return a attack result
   */
  AttackResult attackResult();

  /**
   * Indicate a ship can be placed on this cell or not.
   *
   * @return true if a ship can place on this cell, false otherwise
   */
  Boolean canPlacedShip();

  /**
   * Attack the cell.
   *
   * @return the updated cell after the attack
   */
  Cell attack();

  /**
   * Print the cell.
   */
  void prettyPrint(Printer printer);


  Boolean getIsHit();

  void update(Ship observable);


}
