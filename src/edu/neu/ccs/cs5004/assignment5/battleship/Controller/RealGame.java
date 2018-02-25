package edu.neu.ccs.cs5004.assignment5.battleship.Controller;


public class RealGame extends AbstractGame implements Game {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  public RealGame() {
    super();
    System.out.println("-------------------------------");
    System.out.println("Game Mode for Battleship Game.");
  }

}
