package edu.neu.ccs.cs5004.assignment5.battleship.Viewer;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.EnemyShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.SpecificShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.WaterCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.Map;

/**
 * Represents a console printer.
 */

public class ConsolePrinter implements Printer {

  public void toConsole(Map map) {
    prettyPrint(map);
  }

  public void toConsole(Cell cell) {
    cell.prettyPrint(new ConsolePrinter());
  }

  public void toConsole(WaterCell waterCell) {

    if (waterCell.getIsHit() == true) {
      System.out.print(" ~ ");
    } else {
      System.out.print("   ");
    }
  }

  public void toConsole(EnemyShipCell enemyShipCell) {
    if (enemyShipCell.getSunkShip() == true) {
      System.out.print(" / ");
    } else {
      System.out.print(" X ");
    }
  }

  public void toConsole(SpecificShipCell specificShipCell) {
    if (specificShipCell.getShip().isSunk()) {
      System.out.print(" / ");
    } else if (specificShipCell.getIsHit()) {
      System.out.print(" X ");
    } else {
      System.out.print(" O ");
    }
  }

  /**
   * Print the map.
   *
   * @param map the map to be printed
   */
  void prettyPrint(Map map) {

    System.out.println();
    System.out.println("    A   B   C   D   E   F   G   H   I   J"); //alphabetic notation
    System.out.println(" ###########################################");
    for (int i = 0; i < Map.ROW; i++) { //rows
      System.out.print(" #|");
      for (int j = 0; j < Map.COLUMN; j++) { //columns

        this.toConsole(map.getMap(Row.values()[i], Column.values()[j]));
        System.out.print("|");
      }
      System.out.println("# " + (i + 1)); //next row
      if (i != Map.ROW - 1) {
        System.out.println(" #|---------------------------------------|#"); //linebreak between rows
      } else {
        System.out.println(" ###########################################");
      }
    }

    System.out.println("    A   B   C   D   E   F   G   H   I   J");//alphabetic notation
    System.out.println();

  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "ConsolePrinter{}";
  }
}
