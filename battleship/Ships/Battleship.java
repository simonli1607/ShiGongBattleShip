package edu.neu.ccs.cs5004.assignment5.battleship.Ships;

/**
 * Represent a battleship for the game program.
 */

public class Battleship extends AbstractShip {

  public Battleship(Integer size, Integer numberHitCells) {
    super(size, numberHitCells);
    this.size = 4;
  }


  @Override
  public String toString() {
    return "Battleship{" + "size=" + size
        + ", numberHitCells=" + numberHitCells + '}';
  }


}
