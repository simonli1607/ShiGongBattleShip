package edu.neu.ccs.cs5004.assignment5.battleship.Ships;


import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;

/**
 * Represetns a ship of the game.
 */
public interface Ship {

  /**
   * Indicate if the ship is hit or not.
   *
   * @return the updated ship after it was hit
   */
  Ship hitShip();

  /**
   * Indicates a ship is sunk or not.
   *
   * @return true if the ship is sunk, false otherwise
   */
  Boolean isSunk();

  Integer size();

  /**
   * add observer to a list of observers
   *
   * @param observer to register to the observable
   */
  void registerObserver(Cell observer);

  /**
   * remove observer from a list of observers
   *
   * @param observer to remove
   */
  void removeObserver(Cell observer);

  /**
   * notify observer if a model was changed
   */
  void notifyObservers();

}
