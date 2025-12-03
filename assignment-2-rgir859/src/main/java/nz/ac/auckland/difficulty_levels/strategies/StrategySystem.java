package nz.ac.auckland.difficulty_levels.strategies;

import java.util.ArrayList;

public class StrategySystem {

  private Strategy strategy;
  private int obtainedFinger;
  private int obtainedSum;

  public StrategySystem(Strategy strategy){
    this.strategy = strategy;
    setTransferredValue();
  }

  public void setTransferredValue(){

    this.obtainedFinger = strategy.returnFingers();
    this.obtainedSum = strategy.returnSum();

  }

  public int getFingers() {
    return obtainedFinger;
  }

  public int getSum() {
    return obtainedSum;
  }
}
