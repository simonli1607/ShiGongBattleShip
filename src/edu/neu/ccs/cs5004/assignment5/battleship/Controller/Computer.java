package edu.neu.ccs.cs5004.assignment5.battleship.Controller;


import java.util.Random;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.EnemyShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Strategy.Strategy;

public class Computer extends AbstractPlayer implements Player {

  private Strategy strategy;
  Computer() {
    super();

  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void placeShips() {
    System.out.println("Generate random fleet map for Computer.");
    ifleetMap.randomPlaceShip();
  }

  public PlayerAttackResult computerTurn(Human human, Integer sunkShipNum, PlayerAttackResult previousResult) {
    System.out.println("Computer's turn:");
    Coordinate coordinate = strategy.generateAttackCoordinate(human.ifleetMap, previousResult.isHit(), previousResult.isShipSunk());
        System.out.println("Computer choose to attack " + (char)(coordinate.getColumn().ordinal() + 65) + (coordinate.getRow().ordinal
            () + 1));
        int sunkCount = human.getIfleetMap().attack(coordinate.getRow(), coordinate.getColumn(), sunkShipNum);
        this.getIbattleMap().attack(human, coordinate.getRow(), coordinate.getColumn());
        
        boolean isHit = this.getIbattleMap().getMap(coordinate.getRow(), coordinate.getColumn()) instanceof EnemyShipCell;
        boolean isSunk = sunkCount - sunkShipNum == 1;
        
        return new PlayerAttackResult(coordinate, isHit, isSunk, sunkCount);
      }
    }





