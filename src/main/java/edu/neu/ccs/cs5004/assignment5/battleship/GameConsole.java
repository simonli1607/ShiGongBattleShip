package edu.neu.ccs.cs5004.assignment5.battleship;

import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Computer;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.DebugGame;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Game;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.Human;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.ReadConsole;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.RealGame;
import edu.neu.ccs.cs5004.assignment5.battleship.Strategy.RandomStrategy;
import edu.neu.ccs.cs5004.assignment5.battleship.Strategy.SmartStrategy;
import edu.neu.ccs.cs5004.assignment5.battleship.Strategy.Strategy;
import edu.neu.ccs.cs5004.assignment5.battleship.Strategy.UserStrategy;
import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.ConsolePrinter;


public class GameConsole {

  public static void main(String[] args) {
    System.out.println("-----------------------------------------");
    System.out.println("Welcome to Battleship Game.");
    System.out.println("Enter q anytime if you want to exit the Game.");
    System.out.println("-----------------------------------------");

    GameConsole gameConsole = new GameConsole();
    ReadConsole reader = new ReadConsole();

    //choose game mode
    Game game = gameConsole.chooseGameMode(reader);

    //choose fleet map generate ways(by user/random)
    game.getHuman().placeShips();
    game.getComputer().placeShips();

    //print map by mode
    gameConsole.printMaps(game);

    //play game function:

    System.out.println("Finish fleet maps set up. Let's start!");
    System.out.println("----------------------------------------");

    gameConsole.chooseAttachStrategy(reader, game.getHuman(), game.getComputer());

    int sunkShipNum1 = 0;
    int sunkShipNum2 = 0;
    int shipNum = game.getComputer().getIbattleMap().getBattleshipNum()
            + game.getComputer().getIbattleMap().getCruiserNum()
            + game.getComputer().getIbattleMap().getSubmarineNum()
            + game.getComputer().getIbattleMap().getDestroyerNum();

    while (true) {
      //human turn to attack;
      sunkShipNum1 = game.getHuman().humanTurn(reader, game.getComputer(), sunkShipNum1);
      System.out.println("Enemy Sunk Ship number is " + sunkShipNum1);
      if (sunkShipNum1 == shipNum) {
        gameConsole.printMaps(game);
        System.out.println("Game over, the winner is the Player.");
        System.exit(1);
      }

      //computer turn to attack
      sunkShipNum2 = game.getComputer().computerTurn(game.getHuman(), sunkShipNum2);
      if (sunkShipNum2 == shipNum) {
        gameConsole.printMaps(game);
        System.out.println("Game over, the winner is the Computer.");
        System.exit(1);
      }
      gameConsole.printMaps(game);
    }

  }

  Game chooseGameMode(ReadConsole reader) {
    System.out.println("Which mode do you want to play?");
    System.out.println("1. Game Mode\n2. Debug Mode");

    int num = reader.inputNum(1, 2);
    if (num == 1) {
      return new RealGame();
    } else {
      return new DebugGame();
    }
  }

  void chooseAttachStrategy(ReadConsole reader, Human human, Computer computer) {
    System.out.println("Please choose attack strategy for human player.");
    System.out.println("1.User Attack\n2. Random Attack\n3. Smart Strategy");

    int num = reader.inputNum(1, 3);
    if (num == 1) {
      human.setStrategy(new UserStrategy(reader));
    } else if (num == 2) {
         human.setStrategy(new RandomStrategy());
    } else {
      //   human.setStrategy(new SmartStrategy(reader));
    }

    System.out.println("Please choose attack strategy for computer player.");
    System.out.println("1. Random Attack\n2. Smart Strategy");

    num = reader.inputNum(1, 2);
    if (num == 1) {
         computer.setStrategy(new RandomStrategy());
    } else if (num == 2) {
      //   human.setStrategy(new RandomStrategy(reader));}

    }
  }

  void printMaps(Game game) {
    ConsolePrinter printer = new ConsolePrinter();
    System.out.println("---------------Player's Map------------------");
    game.getHuman().getIfleetMap().prettyPrint(printer);
    System.out.println("------------Player's Battle Map---------------");
    game.getHuman().getIbattleMap().prettyPrint(printer);

    if (game.getClass().equals(DebugGame.class)) {
      System.out.println("---------------Computer's Map-----------------");
      game.getComputer().getIfleetMap().prettyPrint(printer);
      //print computer's battle map here
      System.out.println("------------Computer's Battle Map-------------");
      game.getComputer().getIbattleMap().prettyPrint(printer);
    }

  }


}
