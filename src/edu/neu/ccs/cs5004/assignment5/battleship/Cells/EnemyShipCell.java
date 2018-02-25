package edu.neu.ccs.cs5004.assignment5.battleship.Cells;

import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Ship;
import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.Printer;

/**
 * Represents an abstract ship cell.
 */
public class EnemyShipCell extends AbstractShipCell implements IenemyShipCell {
  private Boolean sunkShip;//an attribute that indicates if it belongs to a sunk ship or not.

  public EnemyShipCell(Boolean isHit, MarkSunk markSunk, Boolean sunkShip) {
    super(isHit, markSunk);
    this.sunkShip = sunkShip;
  }


  /**
   * Getter.
   *
   * @return true if the cell is belong to a sunk ship, false otherwise
   */
  public Boolean getSunkShip() {
    return sunkShip;
  }


  @Override
  public void update(Ship observable) {
    this.sunkShip = observable.isSunk();
  }

  @Override
  public void prettyPrint(Printer printer) {
    printer.toConsole(this);
  }

  @Override
  public AttackResult attackResult() {
    return new Miss();
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
    EnemyShipCell that = (EnemyShipCell) obj;
    return sunkShip != null ? sunkShip.equals(that.sunkShip) : that.sunkShip == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (sunkShip != null ? sunkShip.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "EnemyShipCell{" + "sunkShip=" + sunkShip
        + ", markSunk=" + markSunk + ", isHit=" + isHit + '}';
  }
}
