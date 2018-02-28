package edu.neu.ccs.cs5004.assignment5.battleship.Cells;

/**
 * Represents an open sea cell.
 */
public class OpenSeaCell extends AbstractWaterCell implements WaterCell {

  public OpenSeaCell(Boolean isHit) {
    super(isHit);
  }


  @Override
  public Boolean canPlacedShip() {
    return true;
  }

  @Override
  public String toString() {
    return "OpenSeaCell{" + "isHit=" + isHit
        + '}';
  }
}
