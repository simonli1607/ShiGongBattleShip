package edu.neu.ccs.cs5004.assignment5.battleship.Viewer;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.EnemyShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.SpecificShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.WaterCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.Map;

/**
 * Represents a printer.
 */

public interface Printer {

  /**
   * Print the cell map.
   *
   * @param map the map to be printed.
   */
  void toConsole(Map map);
  //void toConsole(IfleetMap map);

  void toConsole(Cell cell);

  void toConsole(WaterCell waterCell);

  void toConsole(EnemyShipCell enemyShipCell);

  void toConsole(SpecificShipCell specificShipCell);

}
