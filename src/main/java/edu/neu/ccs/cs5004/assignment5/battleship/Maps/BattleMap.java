package edu.neu.ccs.cs5004.assignment5.battleship.Maps;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.EnemyShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.NotSunk;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.OpenSeaCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.SpecificShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Sunk;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Player;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;

public class BattleMap extends AbstractMap implements IbattleMap {


  BattleMap() {
    super();

  }

  public BattleMap(int battleshipNum, int cruiserNum, int submarineNum, int destroyerNum) {
    super(battleshipNum, cruiserNum, submarineNum, destroyerNum);

  }

  public void attack(Player player, Row row, Column col) {
    Cell cell = player.getIfleetMap().getMap(row, col);
    Cell battleMapCell;

    switch (cell.attackResult().toString()) {
      case "Hit":
        battleMapCell = new EnemyShipCell(true, new NotSunk(), false);
        setMap(row, col, battleMapCell);
        ((SpecificShipCell) cell).getShip().registerObserver(getMap(row, col));
        break;
      case "Sunk":
        battleMapCell = new EnemyShipCell(true, new Sunk(), true);
        setMap(row, col, battleMapCell);
        ((SpecificShipCell) cell).getShip().notifyObservers();
        break;
      default:
        battleMapCell = new OpenSeaCell(true);

    }
    setMap(row, col, battleMapCell);
  }


}
