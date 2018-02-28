package edu.neu.ccs.cs5004.assignment5.battleship.Enums;

public class Coordinate {
  private Column column;
  private Row row;
  public Coordinate(Column column, Row row) {
    this.column = column;
    this.row =row;
  }

  public Coordinate(int columnInt, int rowInt) {
    this.column = Column.values()[columnInt];
    this.row = Row.values()[rowInt];
  }

  public Column getColumn() {
    return column;
  }

  public Row getRow() {
    return row;
  }
}
