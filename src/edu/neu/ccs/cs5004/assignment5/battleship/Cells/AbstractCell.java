package edu.neu.ccs.cs5004.assignment5.battleship.Cells;

import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Ship;

/**
 * Represents an abstract cell.
 */

public abstract class AbstractCell implements Cell {

  protected Boolean isHit;

  public AbstractCell(Boolean isHit) {
    this.isHit = isHit;
  }


  @Override
  public void update(Ship observable) {
    return;
  }

  /**
   * Getter.
   *
   * @return true if the cell is hit, false otherwise
   */
  public Boolean getIsHit() {
    return isHit;
  }


  @Override
  public Cell attack() {
    this.isHit = true;
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

    AbstractCell that = (AbstractCell) obj;

    return isHit != null ? isHit.equals(that.isHit) : that.isHit == null;
  }

  @Override
  public int hashCode() {
    return isHit != null ? isHit.hashCode() : 0;
  }
}
