package nz.ac.auckland.difficulty_levels.strategies;

import java.util.ArrayList;

import nz.ac.auckland.se281.Utils;

public class Average implements Strategy{
    
  private int randomFingers;
  private int returnSum;
  private ArrayList<Integer> playerHandInfo;
  private Integer roundNumber;

  public Average() { // raw calculation 
    this.randomFingers = Utils.getRandomNumber(1, 5);

  }

  public void upSentDetails(Integer roundNumber, ArrayList<Integer> playerHandInfo) {
    this.roundNumber = roundNumber;
    this.playerHandInfo = playerHandInfo;
  }

  public void averageSumCalculation() {

    int x = 0;

    for (int i = 0; i < playerHandInfo.size()-1; i++) {
      x += playerHandInfo.get(i);
    }

    this.returnSum = randomFingers + (int) Math.round((double)x / (playerHandInfo.size() - 1));
  }



  public int returnFingers(){
    return randomFingers;
    
  }

  public int returnSum(){
    return returnSum;
  
  }

  
}
