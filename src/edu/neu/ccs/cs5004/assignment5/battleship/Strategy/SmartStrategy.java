package edu.neu.ccs.cs5004.assignment5.battleship.Strategy;

import java.util.Random;

import edu.neu.ccs.cs5004.assignment5.battleship.Cells.EnemyShipCell;
import edu.neu.ccs.cs5004.assignment5.battleship.Controller.ReadConsole;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Column;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Row;
import edu.neu.ccs.cs5004.assignment5.battleship.Enums.StrategyDirection;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IbattleMap;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;

/**
 * 
 * @author Shi Gong
 * 
 * For the SmartStrategy, we define two mode
 * Mode1 : random hit
 * The program start random hit mode when (1)first hit in all game, (2)a enemy ship is sunk in previous attempt.
 * The program quite random hit mode once it hit any cells in ramdom mode.
 * When in random hit mode, it randomly hit a cell that is not a gap cell.
 * 
 * Mode2:  samrt hit
 * Once it successfully random hit a cell, the program goes to smart hit mode. It record the cell that it previous
 * random hit. Then start hit it's neighbor cells in the order of left, up, right and down. It will skip a direction
 * if it reaches the edge. Once it hit something in one direction, it will continue hit in the same direction in
 * next attempt until it reaches edge or it hit a water cell, then it change a direction for next attempt.
 *
 */
public class SmartStrategy implements Strategy {

  private boolean isPreviousModeRandomHit;

  private Coordinate previousRandomHitCoordinate;
  
  private Coordinate previousHitCoordinate;
  
  private StrategyDirection previousDirection;


  private boolean isGapCell(Coordinate coordinate) {
    return false;
  }
  
  private IbattleMap myBattleMap;
  
  public SmartStrategy(IbattleMap myBattleMap) {
	  isPreviousModeRandomHit = true;	  
	  previousRandomHitCoordinate = null;
	  previousHitCoordinate = null;
	  previousDirection = null;
	  this.myBattleMap = myBattleMap;
  }

  
  private boolean isNotEdge(int row, int col){
	  if(row<0 || row >9) {
		  return false;
	  }
	  if (col<0 || col>9) {
		  return false;
	  }
	  return true;
	  
  }
  
  private boolean isGapCell(Row row, Column col) {
	  int rowIndex = row.ordinal();
	  int colIndex =col.ordinal();

	  //check left
	  if (isNotEdge(rowIndex-1, colIndex) && 
			  this.myBattleMap.getMap(Row.values()[rowIndex-1], col) instanceof EnemyShipCell){
		  return true;
	  }	  
	  //check right
	  if (isNotEdge(rowIndex+1, colIndex) && 
			  this.myBattleMap.getMap(Row.values()[rowIndex+1], col) instanceof EnemyShipCell){
		  return true;
	  }	  
	  //check up
	  if (isNotEdge(rowIndex, colIndex-1) && 
			  this.myBattleMap.getMap(row, Column.values()[colIndex-1]) instanceof EnemyShipCell){
		  return true;
	  }	  
	  //check down
	  if (isNotEdge(rowIndex, colIndex+1) && 
			  this.myBattleMap.getMap(row, Column.values()[colIndex+1]) instanceof EnemyShipCell){
		  return true;
	  }	  
	  //check left top
	  if (isNotEdge(rowIndex-1, colIndex-1) && 
			  this.myBattleMap.getMap(Row.values()[rowIndex-1], Column.values()[colIndex-1]) instanceof EnemyShipCell){
		  return true;
	  }	  
	  //check right top
	  if (isNotEdge(rowIndex+1, colIndex-1) && 
			  this.myBattleMap.getMap(Row.values()[rowIndex+1], Column.values()[colIndex-1]) instanceof EnemyShipCell){
		  return true;
	  }
	  
	  //check left down
	  if (isNotEdge(rowIndex-1, colIndex+1) && 
			  this.myBattleMap.getMap(Row.values()[rowIndex-1], Column.values()[colIndex+1]) instanceof EnemyShipCell){
		  return true;
	  }
	  
	  //check right down
	  if (isNotEdge(rowIndex+1, colIndex+1) && 
			  this.myBattleMap.getMap(Row.values()[rowIndex+1], Column.values()[colIndex+1]) instanceof EnemyShipCell){
		  return true;
	  }
	  
	  return false;
  }
  
  private Coordinate randomHitMode(IfleetMap enemyFleetMap) {
	  boolean flag = true;
	  Row row = null;
	  Column col = null;
	  while(flag){
		  Random rand = new Random();
	      row = Row.values()[rand.nextInt(10)];
	      col = Column.values()[rand.nextInt(10)];
	      
		  /*
		   * Uncomment for testing with user input
		  System.out.println("Debug random mode: ");
		  int location[] = new ReadConsole().inputLocation();
	      row = Row.values()[location[1]];
	      col = Column.values()[location[0]];
	      */
		  
		  if (!this.isGapCell(row, col) && !enemyFleetMap.getMap(row, col).getIsHit()) {
	    	  flag = false;
	      }		  
	  }
	  return new Coordinate(col, row);
  }
  
  private Coordinate smartHitMode(IfleetMap enemyFleetMap, boolean isPreviousHit, boolean isPreviousHitSunk, boolean forseChangeDirection) {
	  
	  if (isPreviousModeRandomHit || !isPreviousHit || forseChangeDirection) {
		  // if previous hit is random hit, we starts from left cell.
		  // if previous hit is not random hit, but we pick a wrong direction and miss, we try in order of left, up, right and down.
		  boolean flag = true;
		  while(flag) {
			  StrategyDirection nextDirection = this.findNextDirection(this.previousDirection);
			  Coordinate coordinate = findNextCoordinate(this.previousRandomHitCoordinate, nextDirection);
			  this.previousDirection = nextDirection;
			  if(coordinate != null) {
				  return coordinate;
			  } 
		  }		  
	  } else {
	     // since we Hit in previous attempt, we pick the right direction, we continue with this direction
	     Coordinate coordinate = this.findNextCoordinate(this.previousHitCoordinate, this.previousDirection);
		 if (coordinate != null) {
			 return coordinate;
		 } else {
			 //If we need to continue hit in previous direction, but we hit edge, we force to change direction.
			 return this.smartHitMode(enemyFleetMap, isPreviousHit, isPreviousHitSunk, true);
		 }
	  }
	  return null;
  }
  
  private Coordinate findNextCoordinate(Coordinate curr, StrategyDirection nextDir) {
	  int rowIndex = curr.getRow().ordinal();
	  int colIndex = curr.getColumn().ordinal();
	  if (nextDir == StrategyDirection.LEFT) {
		  colIndex = curr.getColumn().ordinal()-1;
	  } else if (nextDir == StrategyDirection.RIGHT) {
		  colIndex = curr.getColumn().ordinal()+1;
	  } else if (nextDir == StrategyDirection.UP) {
		  rowIndex = curr.getRow().ordinal()-1;
	  } else {
		  rowIndex = curr.getRow().ordinal()+1;
	  }
	  if(this.isNotEdge(rowIndex, colIndex)) {
		  return new Coordinate(Column.values()[colIndex], Row.values()[rowIndex]);
	  }
	  return null;
  }
  
  private StrategyDirection findNextDirection(StrategyDirection dir) {
	  if (dir == null) return StrategyDirection.LEFT;
	  if (dir == StrategyDirection.LEFT) return StrategyDirection.UP;
	  if (dir == StrategyDirection.UP) return StrategyDirection.RIGHT;
	  if (dir == StrategyDirection.RIGHT) return StrategyDirection.DOWN;
	  return null;
  }
  
  private StrategyDirection findOppositeDirection(StrategyDirection dir) {
	  if (dir == StrategyDirection.LEFT) return StrategyDirection.RIGHT;
	  else if (dir == StrategyDirection.RIGHT) return StrategyDirection.LEFT;
	  else if (dir == StrategyDirection.UP) return StrategyDirection.DOWN;
	  else return StrategyDirection.UP;
  }
  
  
  @Override
  public Coordinate generateAttackCoordinate(IfleetMap enemyFleetMap, boolean isPreviousHit, boolean isPreviousHitSunk) {
	  boolean isCurrentModeRandomHit;
	  if (isPreviousHitSunk || (isPreviousModeRandomHit && !isPreviousHit)) {
		  isCurrentModeRandomHit = true;
		  this.previousDirection = null; //reset the direction to null;
	  } else {
		  isCurrentModeRandomHit = false;
	  }
	  
	  Coordinate result = null;
	  if(isCurrentModeRandomHit) {
		  result = this.randomHitMode(enemyFleetMap);
		  this.previousRandomHitCoordinate = result;
	  }else {
		  result = this.smartHitMode(enemyFleetMap, isPreviousHit, isPreviousHitSunk, false);
	  }
	  
	  this.isPreviousModeRandomHit = isCurrentModeRandomHit;
	  this.previousHitCoordinate = result;
	  return result;
  }
}
