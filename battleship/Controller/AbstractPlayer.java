package edu.neu.ccs.cs5004.assignment5.battleship.Controller;


import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IbattleMap;
import edu.neu.ccs.cs5004.assignment5.battleship.Maps.IfleetMap;


public abstract class AbstractPlayer implements Player {

  protected IfleetMap ifleetMap;
  protected IbattleMap ibattleMap;


  public AbstractPlayer() {

    this.ifleetMap = IfleetMap.generateEmptyMap();
    this.ibattleMap = IbattleMap.generateEmptyMap();

  }

  @Override
  public IfleetMap getIfleetMap() {
    return ifleetMap;
  }

  @Override
  public IbattleMap getIbattleMap() {
    return ibattleMap;
  }
}
  /*

  @Override
  public AttackResult attack(Row row, Column col) {
    return null;
  }


}
*/
