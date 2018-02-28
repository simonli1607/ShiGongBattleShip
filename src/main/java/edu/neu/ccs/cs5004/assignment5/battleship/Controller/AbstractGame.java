package edu.neu.ccs.cs5004.assignment5.battleship.Controller;

public abstract class AbstractGame implements Game {

  protected Computer computer;
  protected Human human;

  public AbstractGame() {
    computer = new Computer();
    human = new Human();
  }

  public Computer getComputer() {
    return computer;
  }

  public Human getHuman() {
    return human;
  }

}
