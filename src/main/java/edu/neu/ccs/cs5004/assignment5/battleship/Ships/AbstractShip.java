package edu.neu.ccs.cs5004.assignment5.battleship.Ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;

/**
 * Represents an abstract ship.
 */
public abstract class AbstractShip implements Ship {

  protected Integer size;
  protected Integer numberHitCells;
  private List<Cell> observers;

  public AbstractShip(Integer size, Integer numberHitCells) {
    this.size = size;
    this.numberHitCells = numberHitCells;
    observers = new ArrayList<>();
  }

  @Override
  public void registerObserver(Cell observer) {
    observers.add(observer);
    observer.update(this);
  }


  @Override
  public void notifyObservers() {
    for (Cell obs : observers) {
      obs.update(this);
    }
  }

  @Override
  public void removeObserver(Cell observer) {
    observers.remove(observer);
  }


  @Override
  public Integer size() {
    return this.getSize();
  }

  /**
   * Getter.
   *
   * @return value of size.
   */
  public Integer getSize() {
    return size;
  }

  /**
   * Getter.
   *
   * @return the number of cells hit.
   */
  public Integer getNumberHitCells() {
    return numberHitCells;
  }

  @Override
  public Boolean isSunk() {
    return this.numberHitCells.equals(this.size);
  }


  @Override
  public Ship hitShip() {
    this.numberHitCells++;
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
    AbstractShip that = (AbstractShip) obj;
    return Objects.equals(size, that.size)
        && Objects.equals(numberHitCells, that.numberHitCells);
  }

  @Override
  public int hashCode() {

    return Objects.hash(size, numberHitCells);
  }
}
