package edu.neu.ccs.cs5004.assignment5.battleship.Strategy;

import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Computer;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.ReadConsole;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;

public class UserStrategy implements Strategy {

  private ReadConsole reader;

  public UserStrategy(ReadConsole reader) {
    this.reader = reader;
  }

  public class CellHasBeenHitByUserException extends Exception {
  }

  @Override
  public Coordinate generateAttackCoordinate(IfleetMap enemyFleetMap, boolean isPreviousHit, boolean isPreviousHitSunk) {
    System.out.println("Please enter the location you want to attack:");
    boolean flag = true;
    Row row = null;
    Column col = null;
    while (flag) {
      try {
        int location[] = reader.inputLocation();
        row = Row.values()[location[1]];
        col = Column.values()[location[0]];
        boolean isCellHitBefore = enemyFleetMap.getMap(row, col).getIsHit();
        if (isCellHitBefore) {
          throw new CellHasBeenHitByUserException();
        }
        flag = false;
      } catch (CellHasBeenHitByUserException e) {
        System.out.println("You attacked this cell before, please choose a different one");
      }

    }
    return new Coordinate(col, row);
  }
}
