package nz.ac.auckland.difficulty_levels;

import java.util.ArrayList;

import nz.ac.auckland.difficulty_levels.strategies.RandomStrategy;
import nz.ac.auckland.difficulty_levels.strategies.StrategySystem;
import nz.ac.auckland.difficulty_levels.strategies.Top;

public class Hard implements JarvisDifficulty{
  
  private StrategySystem system;
  private ArrayList<Integer> playerHandInfo;
  private Integer roundNumber;

  public Hard() {           // constructor for medium difficulty
      this.roundNumber = 0;
  }

  public void implementStrat() {

    if (roundNumber<4) {
      RandomStrategy random = new RandomStrategy();
      this.system = new StrategySystem(random);
    } else {
      Top top = new Top();
      top.upSentDetails(roundNumber, playerHandInfo); // updated values
      top.findCommon();
      this.system = new StrategySystem(top);
    }
  }

  public void transferToHard(int roundNumber, ArrayList<Integer> playerHandInfo) {
    
    this.roundNumber = roundNumber;
    this.playerHandInfo = playerHandInfo;

  }


  public StrategySystem useSystem() {
    return system;
  }


  
}
