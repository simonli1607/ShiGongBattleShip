package edu.neu.ccs.cs5004.assignment5.battleship.Cells;

import java.util.List;

import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Ship;
import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.Printer;

/**
 * Represents a specific ship cell.
 */

public class SpecificShipCell extends AbstractShipCell implements IspecificShipCell {
  private Ship ship;
  private List<Cell> observers;

  public SpecificShipCell(Boolean isHit, MarkSunk markSunk, Ship ship) {
    super(isHit, markSunk);
    this.ship = ship;
  }

  @Override
  public MarkSunk getMarkSunk() {
    if (ship.isSunk()) {
      return new Sunk();
    }
    return new NotSunk();
  }

  @Override
  public void prettyPrint(Printer printer) {
    printer.toConsole(this);
  }

  /**
   * Getter.
   *
   * @return the ship which placed on the cell
   */
  public Ship getShip() {
    return ship;
  }

  @Override
  public AttackResult attackResult() {
    if (this.ship.isSunk()) {
      return new ResultSunk();
    } else {
      return new Hit();
    }
  }

  @Override
  public Cell attack() {
    this.isHit = true;
    this.ship = ship.hitShip();
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }

    SpecificShipCell that = (SpecificShipCell) obj;

    return ship != null ? ship.equals(that.ship) : that.ship == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (ship != null ? ship.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SpecificShipCell{"
        + "ship=" + ship
        + ", markSunk=" + markSunk
        + ", isHit=" + isHit + '}';
  }
}
