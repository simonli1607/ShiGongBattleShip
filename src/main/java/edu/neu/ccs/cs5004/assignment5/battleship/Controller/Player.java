package edu.neu.ccs.cs5004.assignment5.battleship.Controller;

import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IbattleMap;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;

public interface Player {

  void placeShips();

  IbattleMap getIbattleMap();

  IfleetMap getIfleetMap();


}
