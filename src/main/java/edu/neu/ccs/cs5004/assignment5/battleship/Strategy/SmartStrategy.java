package edu.neu.ccs.cs5004.assignment5.battleship.Strategy;

import java.util.Random;

import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.StrategyDirection;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;

public class SmartStrategy implements Strategy {

  private boolean isRandomHitMode;

  private Coordinate previousRandomHitCoordinate;

  private StrategyDirection previousDirection;

  private boolean isPrevsiouHit;

  private boolean isGapCell(Coordinate coordinate) {
    return false;
  }
/*
*
* 1. Hit : update all variable above
* 2. IsRandom Hit mode = true:  (1)random genearte a coordinate (2)check isGapCell
* 3. ISRandomHitMode = false
* (1) if previousDirection = null or previous hit result = false; start from left -> right> up>down
* (2) if previousDirection is not null: left for example --> if previousHit =true, keep hitting left
*                                                             if previousHit = false, keep the other dirrection starting from first hit cell
*
*
*
*
* */

  @Override
  public Coordinate generateAttackCoordinate(IfleetMap enemyFleetMap) {
    Row row;
    Column col;

    Random rand = new Random();
    row = Row.values()[rand.nextInt(10)];
    col = Column.values()[rand.nextInt(10)];

      if (enemyFleetMap.getMap(row,col).attack().toString()) {
        continue;
      }
  }
}
