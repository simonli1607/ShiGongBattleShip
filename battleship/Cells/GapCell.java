package edu.neu.ccs.cs5004.assignment5.battleship.Cells;

/**
 * Represents a gap cell.
 */

public class GapCell extends AbstractWaterCell implements WaterCell {

  public GapCell(Boolean isHit) {
    super(isHit);
  }

  @Override
  public Boolean canPlacedShip() {
    return false;
  }


  @Override
  public String toString() {
    return "GapCell{" + "isHit=" + isHit + '}';
  }
}
