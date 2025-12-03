package nz.ac.auckland.difficulty_levels;

import nz.ac.auckland.difficulty_levels.strategies.RandomStrategy;
import nz.ac.auckland.difficulty_levels.strategies.StrategySystem;

public class Easy implements JarvisDifficulty{

  private StrategySystem system;

  public Easy() {
    implementStrat();
  }

  public void implementStrat(){

    RandomStrategy random = new RandomStrategy(); // here goes the logic for switching stratgeies based on difficulty
    this.system = new StrategySystem(random);

  }

  public StrategySystem useSystem() {
    return system;
  }

}
