package edu.neu.ccs.cs5004.assignment5.battleship.Maps;

import java.util.Arrays;
import java.util.Random;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.GapCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.NotSunk;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.OpenSeaCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.SpecificShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Human;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Player;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.ReadConsole;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.RealGame;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Direction;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Battleship;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Cruiser;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Destoryer;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Ship;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Submarine;


/**
 * Represents a fleet map.
 */
public class FleetMap extends AbstractMap implements IfleetMap {
  static final int ROW = 10;
  static final int COLUMN = 10;


  /**
   * Constructor of fleet map.
   */
  public FleetMap() {
    super();
  }

  public FleetMap(int battleshipNum, int cruiserNum, int submarineNum, int destroyerNum) {
    super(battleshipNum, cruiserNum, submarineNum, destroyerNum);

  }

  public int getCountBattleship() {
    return countBattleship;
  }

  public int getCountCruiser() {
    return countCruiser;
  }

  public int getCountSubmarine() {
    return countSubmarine;
  }

  public int getCountDestroyer() {
    return countDestroyer;
  }

  /**
   * Indicate if this ship can be placed in given location.
   */
  public boolean canPlaceShip(Ship ship, Row row, Column col, Direction direction) {
    for (int i = 0; i < ship.size(); i++) {
      if (direction.equals(Direction.VERTICAL)) {
        if (row.ordinal() + i >= ROW
            || !getMap(Row.values()[row.ordinal() + i], col).getClass().equals(OpenSeaCell.class)) {
          return false;
        }
      } else { //if(direction.equals(Direction.HORIZONTAL))
        if (col.ordinal() + i >= COLUMN || !getMap(row,
            Column.values()[col.ordinal() + i]).getClass().equals(OpenSeaCell.class)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Set the cells surrounding by the given ship to GapCell.
   */

  void setGapCell(Ship ship, Row row, Column col, Direction direction) {

    if (direction.equals(Direction.VERTICAL)) {
      for (int i = 0; i < ship.size(); i++) {
        if (col.ordinal() != 0) {
          setMap(Row.values()[row.ordinal() + i], Column.values()[col.ordinal() - 1], new GapCell(false));
        }
        if (col.ordinal() != 9) {
          setMap(Row.values()[row.ordinal() + i], Column.values()[col.ordinal() + 1], new GapCell(false));
        }
      }
      if (row.ordinal() != 0) {
        setMap(Row.values()[row.ordinal() - 1], col, new GapCell(false));
      }
      if (row.ordinal() != 0 && col.ordinal() != 0) {
        setMap(Row.values()[row.ordinal() - 1], Column.values()[col.ordinal() - 1], new GapCell(false));
      }
      if (row.ordinal() != 0 && col.ordinal() != 9) {
        setMap(Row.values()[row.ordinal() - 1], Column.values()[col.ordinal() + 1], new GapCell(false));
      }
      if (row.ordinal() + ship.size() - 1 != 9) {
        setMap(Row.values()[row.ordinal() + ship.size()], col, new GapCell(false));
      }
      if (row.ordinal() + ship.size() - 1 != 9 && col.ordinal() != 0) {
        setMap(Row.values()[row.ordinal() + ship.size()], Column.values()[col.ordinal() - 1],
            new GapCell(false));
      }
      if (row.ordinal() + ship.size() - 1 != 9 && col.ordinal() != 9) {
        setMap(Row.values()[row.ordinal() + ship.size()], Column.values()[col.ordinal() + 1],
            new GapCell(false));
      }

    } else {
      for (int i = 0; i < ship.size(); i++) {
        if (row.ordinal() != 0) {
          setMap(Row.values()[row.ordinal() - 1], Column.values()[col.ordinal() + i], new GapCell(false));
        }
        if (row.ordinal() != 9) {
          setMap(Row.values()[row.ordinal() + 1], Column.values()[col.ordinal() + i], new GapCell(false));
        }
      }
      if (row.ordinal() != 0 && col.ordinal() != 0) {
        setMap(Row.values()[row.ordinal() - 1], Column.values()[col.ordinal() - 1], new GapCell(false));
      }
      if (row.ordinal() != 9 && col.ordinal() != 0) {
        setMap(Row.values()[row.ordinal() + 1], Column.values()[col.ordinal() - 1], new GapCell(false));
      }
      if (col.ordinal() != 0) {
        setMap(row, Column.values()[col.ordinal() - 1], new GapCell(false));
      }
      if (col.ordinal() + ship.size() - 1 != 9) {
        setMap(row, Column.values()[col.ordinal() + ship.size()], new GapCell(false));
      }
      if (col.ordinal() + ship.size() - 1 != 9 && row.ordinal() != 0) {
        setMap(Row.values()[row.ordinal() - 1], Column.values()[col.ordinal() + ship.size()],
            new GapCell(false));
      }
      if (col.ordinal() + ship.size() - 1 != 9 && row.ordinal() != 9) {
        setMap(Row.values()[row.ordinal() + 1], Column.values()[col.ordinal() + ship.size()],
            new GapCell(false));
      }
    }

  }

  @Override
  public void placeShip(Ship ship, Row row, Column col, Direction direction) {

    //set ship cell one by one
    if (direction.equals(Direction.VERTICAL)) {
      for (int i = 0; i < ship.size(); i++) {
        SpecificShipCell cell = new SpecificShipCell(false, new NotSunk(), ship);
        this.setMap(Row.values()[row.ordinal() + i], col, cell);

      }
    } else {
      for (int i = 0; i < ship.size(); i++) {
        SpecificShipCell cell = new SpecificShipCell(false, new NotSunk(), ship);
        this.setMap(row, Column.values()[col.ordinal() + i], cell);

      }
    }

    //set the surrounding cells to gap cell.
    setGapCell(ship, row, col, direction);

    // num of current ship type increase by 1
    if (ship.getClass().equals(Battleship.class)) {
      this.countBattleship++;
    } else if (ship.getClass().equals(Cruiser.class)) {
      this.countCruiser++;
    } else if (ship.getClass().equals(Submarine.class)) {
      this.countSubmarine++;
    } else {
      this.countDestroyer++;
    }

  }

  @Override
  public void randomPlaceShip() {

    Ship ship;
    Row row;
    Column col;
    Direction dir;
    Random rand = new Random();

    int i = 0;
    int shipNum = getBattleshipNum() + getCruiserNum() + getSubmarineNum() + getDestroyerNum();

    while (i < shipNum) {
      if (countBattleship < getBattleshipNum()) {
        ship = new Battleship(4, 0);
      } else if (countCruiser < getCruiserNum()) {
        ship = new Cruiser(3, 0);
      } else if (countSubmarine < getSubmarineNum()) {
        ship = new Submarine(2, 0);
      } else {
        ship = new Destoryer(1, 0);
      }

      row = Row.values()[rand.nextInt(10)];
      col = Column.values()[rand.nextInt(10)];
      dir = rand.nextInt(2) == 0 ? Direction.VERTICAL : Direction.HORIZONTAL;

      if (!canPlaceShip(ship, row, col, dir)) {
        continue;
      } else {
        placeShip(ship, row, col, dir);
        i++;
      }

    }

    System.out.print("Finish generate random fleet map.\n\n");

  }

  // new add code
  public Integer attack(Row row, Column col, Integer sunkShipNum) {
    Cell attackCell = getMap(row, col).attack();
    System.out.println("Attack result is " + attackCell.attackResult().toString());

    if (attackCell.attackResult().toString().equals("Sunk")) {
      sunkShipNum++;
    }
    return sunkShipNum;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    FleetMap FleetMap1 = (FleetMap) obj;
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        if (FleetMap1.mapcell[i][j].getClass() != mapcell[i][j].getClass()) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {

        if (mapcell[i][j].getClass() == OpenSeaCell.class) {
          hash += i + 31 * j;
        } else if (mapcell[i][j].getClass() == GapCell.class) {
          hash += (i + 31 * j) * 31;
        } else if (mapcell[i][j].getClass() == SpecificShipCell.class) {
          hash += (i + 31 * j) * 31 * 31;
        } else {
          hash += (i + 31 * j) * 31 * 31 * 31;
        }

      }
    }
    return hash;
  }

  @Override
  public String toString() {
    return "FleetMap{" + "map=" + Arrays.toString(mapcell) + '}';
  }
}
