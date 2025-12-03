package nz.ac.auckland.difficulty_levels.strategies;

import nz.ac.auckland.se281.Utils;

public class RandomStrategy implements Strategy{

  private int randomFingers;
  private int returnSum;

  public RandomStrategy(){
    this.randomFingers = Utils.getRandomNumber(1, 5);
    this.returnSum = Utils.getRandomNumber(randomFingers+1,randomFingers+5);
  }

  public int returnFingers(){
    return randomFingers;
    
  }

  public int returnSum(){
    return returnSum;
  
  }

}