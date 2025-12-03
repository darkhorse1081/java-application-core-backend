package nz.ac.auckland.difficulty_levels;

import java.util.ArrayList;

import nz.ac.auckland.difficulty_levels.strategies.Average;
import nz.ac.auckland.difficulty_levels.strategies.RandomStrategy;
import nz.ac.auckland.difficulty_levels.strategies.StrategySystem;

public class Medium implements JarvisDifficulty{

  private StrategySystem system;
  private ArrayList<Integer> playerHandInfo;
  private Integer roundNumber;
  private Boolean implementFlag = false;

  public Medium() {           // constructor for medium difficulty
      this.roundNumber = 0;
  }

  public void implementStrat(){    // here goes the logic for switching stratgeies based on difficulty 
    
                                                                   
    if (roundNumber<4) {
      RandomStrategy random = new RandomStrategy();
      this.system = new StrategySystem(random);
    } else {
      Average average = new Average();
      average.upSentDetails(roundNumber, playerHandInfo); // updated values
      average.averageSumCalculation();
      this.system = new StrategySystem(average);
    }

      // difficulty implements logic of revolving strategy around strategy system selector
  }

  public void transferToMedium(int roundNumber, ArrayList<Integer> playerHandInfo) {
    
    this.roundNumber = roundNumber;
    this.playerHandInfo = playerHandInfo;
    this.implementFlag = true;
  }

  public StrategySystem useSystem() {
    return system;
  }

}
