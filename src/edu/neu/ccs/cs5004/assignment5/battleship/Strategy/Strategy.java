package edu.neu.ccs.cs5004.assignment5.battleship.Strategy;

import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;

public interface Strategy {

  Coordinate generateAttackCoordinate(IfleetMap enemyFleetMap, boolean isPreviousHit, boolean isPreviousHitSunk);

}
