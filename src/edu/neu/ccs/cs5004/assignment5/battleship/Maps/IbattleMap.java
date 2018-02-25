package edu.neu.ccs.cs5004.assignment5.battleship.Maps;

import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Player;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;

public interface IbattleMap extends Map {

  /**
   *
   * @return
   */
  public static IbattleMap generateEmptyMap() {
    return new BattleMap();
  }

  void attack(Player player, Row row, Column col);
}
