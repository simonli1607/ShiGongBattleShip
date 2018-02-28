package edu.neu.ccs.cs5004.assignment5.battleship.Controller;


import edu.neu.ccs.cs5004.assignment5.battleship.Cells.EnemyShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Direction;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Battleship;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Cruiser;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Destoryer;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Ship;
import edu.neu.ccs.cs5004.assignment5.battleship.Ships.Submarine;
import edu.neu.ccs.cs5004.assignment5.battleship.Strategy.Strategy;
import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.ConsolePrinter;


public class Human extends AbstractPlayer implements Player {

  private Strategy strategy;

  public Human() {
    super();
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void placeShips() {
    System.out.println("==== Setting up fleet map for Player  ====");
    System.out.println("Please choose the ways to place the ships");
    System.out.print("1. RANDOM placement\n2. USER placement\n");

    ReadConsole reader = new ReadConsole();

    int num = reader.inputNum(1,2);
    if (num == 1) {
      System.out.println("Generate random fleet map for Player now.");
      ifleetMap.randomPlaceShip();
    } else {
    	userPlaceAllShips(reader);
    }
  }

  public void userPlaceShip(ReadConsole reader) {

    // get ship type
  Ship ship = chooseShipType(reader);

  // get row and column
    while (true) {

      System.out.println("Please input location you want to placed ship: \n");
      //reader.main(args);
      int location[] = reader.inputLocation();
      Row row = Row.values()[location[1]];
      Column col = Column.values()[location[0]];


      //get direction
      System.out.println("Please input direction you want to placed ship: ");
      System.out.print("1. Horizontal\n2. Vertical\n");

      int direction = reader.inputNum(1, 2);
      Direction dir;

      if (direction == 1) {
        dir = Direction.HORIZONTAL;
      } else {
        dir = Direction.VERTICAL;
      }

      if (ifleetMap.canPlaceShip(ship, row, col, dir)) {

        ifleetMap.placeShip(ship, row, col, dir);
        ifleetMap.prettyPrint(new ConsolePrinter());
        return;
      }else {
        System.out.println(RealGame.ANSI_RED + "Can't placed ship -- Wrong coordinate. Please " +
            "input again."
            + RealGame.ANSI_RESET);
      }
    }
}

Ship chooseShipType(ReadConsole reader){
  System.out.println("Please input ship type you want to placed: ");
  System.out.print("1. Battleship\n2. Cruiser\n3. Submarine\n4. Destroyer\n");

  int num = reader.inputNum(1,4);
  Ship ship;

  switch (num) {
    case 1:
      ship = new Battleship(4, 0);
      if (ifleetMap.getCountBattleship() == ifleetMap.getBattleshipNum()) {
        System.out.println(RealGame.ANSI_RED + "Map already has enough Battleships. Please input again."
            + RealGame.ANSI_RESET);
        return chooseShipType(reader);
      }
      break;
    case 2:
      ship = new Cruiser(3, 0);
      if (ifleetMap.getCountCruiser() == ifleetMap.getCruiserNum()) {
        System.out.println(RealGame.ANSI_RED + "Map already has enough Cruisers. Please input" +
            " again."
            + RealGame.ANSI_RESET);
        return chooseShipType(reader);
      }
      break;
    case 3:
      ship = new Submarine(2, 0);
      if (ifleetMap.getCountSubmarine() == ifleetMap.getSubmarineNum()) {
        System.out.println(RealGame.ANSI_RED + "Map already has enough Submarines. Please " +
            "input again."
            + RealGame.ANSI_RESET);
        return chooseShipType(reader);
      }
      break;
    default:
      ship = new Destoryer(1, 0);
      if (ifleetMap.getCountDestroyer() == ifleetMap.getDestroyerNum()) {
        System.out.println(RealGame.ANSI_RED + "Map already has enough Destroyers. Please input" +
            " again."
            + RealGame.ANSI_RESET);
        return chooseShipType(reader);
      }
  }
  return ship;
}

  void userPlaceAllShips(ReadConsole reader) {

    while (ifleetMap.getCountBattleship() != ifleetMap.getBattleshipNum()
        || ifleetMap.getCountCruiser() != ifleetMap.getCruiserNum()
        || ifleetMap.getCountSubmarine() != ifleetMap.getSubmarineNum()
        || ifleetMap.getCountDestroyer() != ifleetMap.getDestroyerNum()) {

      userPlaceShip(reader);
    }
    System.out.println("Finish set up fleet map.");
    return;
  }


  public PlayerAttackResult humanTurn(ReadConsole reader, Computer computer, Integer sunkShipNum, PlayerAttackResult previousResult) {
    System.out.println("Player's turn:");

    Coordinate coordinate = strategy.generateAttackCoordinate(previousResult.isHit(), previousResult.isShipSunk());
    System.out.println("Player choose to attack " + (char)(coordinate.getColumn().ordinal() + 65) + (coordinate.getRow().ordinal
            () + 1));
    int sunkCount = computer.getIfleetMap().attack(coordinate.getRow(), coordinate.getColumn(), sunkShipNum);
    this.getIbattleMap().attack(computer, coordinate.getRow(), coordinate.getColumn());
    
    boolean isHit = this.getIbattleMap().getMap(coordinate.getRow(), coordinate.getColumn()) instanceof EnemyShipCell;
    boolean isSunk = sunkCount - sunkShipNum == 1;
    
    return new PlayerAttackResult(coordinate, isHit, isSunk, sunkCount);

  }

  public class CellHasBeenHitByUserException extends Exception{
  }
  
}
