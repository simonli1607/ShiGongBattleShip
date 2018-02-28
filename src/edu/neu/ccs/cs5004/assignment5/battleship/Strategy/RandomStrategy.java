package edu.neu.ccs.cs5004.assignment5.battleship.Strategy;

import java.util.Random;

import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IbattleMap;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;

public class RandomStrategy implements Strategy {
	
  private IbattleMap myBattleMap;
  
  public RandomStrategy(IbattleMap myBattleMap) {
	this.myBattleMap = myBattleMap;  
  }

  @Override
  public Coordinate generateAttackCoordinate(boolean isPreviousHit, boolean isPreviousHitSunk) {
    Row row;
    Column col;
    while (true) {
      Random rand = new Random();
      row = Row.values()[rand.nextInt(10)];
      col = Column.values()[rand.nextInt(10)];

      if (myBattleMap.getMap(row, col).getIsHit()) {
        continue;
      }

      return new Coordinate(col, row);

    }
  }
}
