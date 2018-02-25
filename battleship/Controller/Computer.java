package edu.neu.ccs.cs5004.assignment5.battleship.Controller;


import java.util.Random;

import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;

public class Computer extends AbstractPlayer implements Player {

  Computer() {
    super();

  }


  @Override
  public void placeShips() {
    System.out.println("Generate random fleet map for Computer.");
    ifleetMap.randomPlaceShip();
  }

  public Integer computerTurn(Human human, Integer sunkShipNum) {
    System.out.println("Computer's turn:");

    Row row;
    Column col;
    while (true) {
      Random rand = new Random();
      row = Row.values()[rand.nextInt(10)];
      col = Column.values()[rand.nextInt(10)];

      if (this.ibattleMap.getMap(row, col).getIsHit()) {
        continue;
      } else {
        System.out.println("Computer choose to attack " + (char)(col.ordinal() + 65) + (row.ordinal
            () + 1));
        int sunkCount = human.getIfleetMap().attack(row, col, sunkShipNum);
        this.getIbattleMap().attack(human, row, col);
        return sunkCount;
      }
    }

  }


}
