package edu.neu.ccs.cs5004.assignment5.battleship.Strategy;

import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Computer;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.ReadConsole;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IbattleMap;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;

public class UserStrategy implements Strategy {

  private ReadConsole reader;
  private IbattleMap myBattleMap;

  public UserStrategy(ReadConsole reader, IbattleMap myBattleMap) {
    this.reader = reader;
    this.myBattleMap = myBattleMap;
  }

  public class CellHasBeenHitByUserException extends Exception {
  }

  @Override
  public Coordinate generateAttackCoordinate(boolean isPreviousHit, boolean isPreviousHitSunk) {
    System.out.println("Please enter the location you want to attack:");
    boolean flag = true;
    Row row = null;
    Column col = null;
    while (flag) {
      try {
        int location[] = reader.inputLocation();
        row = Row.values()[location[1]];
        col = Column.values()[location[0]];
        boolean isCellHitBefore = myBattleMap.getMap(row, col).getIsHit();
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
