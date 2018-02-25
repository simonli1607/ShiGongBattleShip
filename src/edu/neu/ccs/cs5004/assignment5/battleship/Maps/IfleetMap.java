package edu.neu.ccs.cs5004.assignment5.battleship.Maps;

import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Direction;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Ship;

public interface IfleetMap extends Map {

  /**
   *
   * @return
   */
  public static IfleetMap generateEmptyMap() {
    return new FleetMap();
  }


  /**
   * Place a ship in given location and direction.
   */
  void placeShip(Ship ship, Row row, Column col, Direction direction);

  boolean canPlaceShip(Ship ship, Row row, Column col, Direction direction);
  /**
   * Random generate a complete fleet map.
   */
  void randomPlaceShip();


  int getCountBattleship();

  int getCountCruiser();

  int getCountSubmarine();

  int getCountDestroyer();

  Integer attack(Row row, Column col, Integer sunkShipNum);
}
