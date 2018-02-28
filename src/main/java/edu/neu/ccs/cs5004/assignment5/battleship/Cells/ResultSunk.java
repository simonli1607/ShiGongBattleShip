package edu.neu.ccs.cs5004.assignment5.battleship.Cells;

/**
 * Represents the sunk result of an attack.
 */

public class ResultSunk implements AttackResult {

  @Override
  public String attackResult() {
    return "Sunk";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return 23;
  }

  @Override
  public String toString() {
    return "Sunk";
  }
}
