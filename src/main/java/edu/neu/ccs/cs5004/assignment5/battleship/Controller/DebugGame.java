package edu.neu.ccs.cs5004.assignment5.battleship.Controller;


import edu.neu.ccs.cs5004.assignment5.battleship.Maps.BattleMap;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.FleetMap;

public class DebugGame extends AbstractGame implements Game {


  public DebugGame() {
    System.out.println("-------------------------------");
    System.out.println("Debug Mode for Battleship Game.");
    debugMapConfig();
  }

  void debugMapConfig() {
    ReadConsole reader = new ReadConsole();

    System.out.println("Please input the number of battleship you want in your fleet map. ");
    int battleshipNum = reader.inputNum(0,3);

    System.out.println("Please input the number of cruiser you want in your fleet map. ");
    int cruiserNum = reader.inputNum(0,3);

    System.out.println("Please input the number of submarine you want in your fleet map. ");
    int submarineNum = reader.inputNum(0,3);

    System.out.println("Please input the number of destroyer you want in your fleet map. ");
    int destroyerNum = reader.inputNum(0,3);

    computer.ifleetMap = new FleetMap(battleshipNum, cruiserNum, submarineNum, destroyerNum);
    computer.ibattleMap = new BattleMap(battleshipNum, cruiserNum, submarineNum, destroyerNum);
    human.ifleetMap = new FleetMap(battleshipNum, cruiserNum, submarineNum, destroyerNum);
    human.ibattleMap = new BattleMap(battleshipNum, cruiserNum, submarineNum, destroyerNum);

  }


}
