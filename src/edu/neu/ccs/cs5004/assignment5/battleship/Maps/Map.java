package edu.neu.ccs.cs5004.assignment5.battleship.Maps;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.Printer;

public interface Map {

  int ROW = 10;
  int COLUMN = 10;
  int battleshipNum = 1;
  int cruiserNum = 2;
  int submarineNum = 3;
  int DestroyerNum = 4;


  /**
   *
   * @return
   */
  static FleetMap generateEmptyFleetMap() {
    return new FleetMap();
  }


  /**
   * Print the map.
   *
   * @param printer the printer who can print the map
   */
  void prettyPrint(Printer printer);


  /**
   * Getter for map.
   *
   * @param row the row of the cell
   * @param col the column of th cell
   * @return the cell in the specific position
   */
  Cell getMap(Row row, Column col);

  /**
   * Setter for map.
   *
   * @param row  the row of the cell
   * @param col  the column of the cell
   * @param cell the value to be set
   */
  void setMap(Row row, Column col, Cell cell);


  int getBattleshipNum();

  int getCruiserNum();

  int getSubmarineNum();

  int getDestroyerNum();


}
