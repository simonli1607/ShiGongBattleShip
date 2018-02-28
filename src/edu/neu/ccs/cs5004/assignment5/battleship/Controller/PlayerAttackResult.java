package edu.neu.ccs.cs5004.assignment5.battleship.Controller;

import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Coordinate;

public class PlayerAttackResult {
	  private Coordinate coordinate;
	  private boolean isHit;
	  private boolean isShipSunk;
	  private int sunkCount;

	  public PlayerAttackResult(Coordinate coordinate, boolean isHit, boolean isShipSunk, int sunkCount){
		  this.coordinate = coordinate;
		  this.isHit = isHit;
		  this.isShipSunk = isShipSunk;
		  this.sunkCount = sunkCount;		  
	  }

	  public Coordinate getCoordinate() {
		  return coordinate;
	  }

	  public boolean isHit() {
		  return isHit;
	  }

	  public boolean isShipSunk() {
		  return isShipSunk;
	  }

	  public int getSunkCount() {
		  return sunkCount;
	  }	  
}