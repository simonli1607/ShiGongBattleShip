package edu.neu.ccs.cs5004.assignment5.battleship.Maps;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.Cell;
import edu.neu.ccs.cs5004.assignment5.battleship.Cells.OpenSeaCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.Printer;

public abstract class AbstractMap implements Map {

  protected Cell[][] mapcell;
  protected int battleshipNum;
  protected int cruiserNum;
  protected int submarineNum;
  protected int destroyerNum;
  protected int countBattleship;
  protected int countCruiser;
  protected int countSubmarine;
  protected int countDestroyer;


  AbstractMap() {
    this.mapcell = new Cell[ROW][COLUMN];
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        mapcell[i][j] = new OpenSeaCell(false);
      }
    }
    this.battleshipNum = Map.battleshipNum;
    this.cruiserNum = Map.cruiserNum;
    this.submarineNum = Map.submarineNum;
    this.destroyerNum = Map.DestroyerNum;
    this.countBattleship = 0;
    this.countCruiser = 0;
    this.countSubmarine = 0;
    this.countDestroyer = 0;
  }

  AbstractMap(int battleshipNum, int cruiserNum, int submarineNum, int destroyerNum) {
    this.mapcell = new Cell[ROW][COLUMN];
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        mapcell[i][j] = new OpenSeaCell(false);
      }
    }
    this.battleshipNum = battleshipNum;
    this.cruiserNum = cruiserNum;
    this.submarineNum = submarineNum;
    this.destroyerNum = destroyerNum;
    this.countBattleship = 0;
    this.countCruiser = 0;
    this.countSubmarine = 0;
    this.countDestroyer = 0;
  }


  public int getBattleshipNum() {
    return battleshipNum;
  }

  public int getCruiserNum() {
    return cruiserNum;
  }


  public int getSubmarineNum() {
    return submarineNum;
  }


  public int getDestroyerNum() {
    return destroyerNum;
  }


  @Override
  public void prettyPrint(Printer printer) {
    printer.toConsole(this);
  }

  @Override
  public Cell getMap(Row row, Column col) {
    return mapcell[row.ordinal()][col.ordinal()];
  }

  @Override
  public void setMap(Row row, Column col, Cell cell) {
    mapcell[row.ordinal()][col.ordinal()] = cell;
  }


}
