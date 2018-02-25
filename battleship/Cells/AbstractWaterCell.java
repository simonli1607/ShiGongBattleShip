package edu.neu.ccs.cs5004.assignment5.battleship.Cells;


import edu.neu.ccs.cs5004.assignment5.battleship.Viewer.Printer;

/**
 * Represent an abstract water cell.
 */
public abstract class AbstractWaterCell extends AbstractCell implements WaterCell {

  public AbstractWaterCell(Boolean isHit) {
    super(isHit);
  }


  @Override
  public AttackResult attackResult() {
    return new Miss();
  }

  @Override
  public void prettyPrint(Printer printer) {
    printer.toConsole(this);
  }


}
