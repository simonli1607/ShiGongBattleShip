package edu.neu.ccs.cs5004.assignment5.battleship.Cells;


/**
 * Represents an abstract ship cell.
 */
public abstract class AbstractShipCell extends AbstractCell implements ShipCell {
  protected MarkSunk markSunk;

  /**
   * Constructor of abstract ship cell.
   *
   * @param isHit    if the cell is hit
   * @param markSunk if the cell is belong to a sunk ship or not.
   */
  public AbstractShipCell(Boolean isHit, MarkSunk markSunk) {
    super(isHit);
    this.markSunk = markSunk;

  }

  /**
   * Getter.
   *
   * @return the markSunk value
   */
  public MarkSunk getMarkSunk() {
    return markSunk;
  }


  @Override
  public Boolean canPlacedShip() {
    return false;
  }


  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }

    AbstractShipCell that = (AbstractShipCell) obj;

    return markSunk != null ? markSunk.equals(that.markSunk) : that.markSunk == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (markSunk != null ? markSunk.hashCode() : 0);
    return result;
  }
}
