package nz.ac.auckland.difficulty_levels.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nz.ac.auckland.se281.Utils;

public class Top implements Strategy{

  private int randomFingers;
  private int returnSum;
  private ArrayList<Integer> playerHandInfo;
  private Integer roundNumber;

  public Top() { // raw calculation 
    this.randomFingers = Utils.getRandomNumber(1, 5);

  }

  public void upSentDetails(Integer roundNumber, ArrayList<Integer> playerHandInfo) {
    this.roundNumber = roundNumber;
    this.playerHandInfo = playerHandInfo;
  }

  public void findCommon() {
        if (playerHandInfo.isEmpty()) {
          this.returnSum = -1;} // Handle empty list error

    // 1. Create a Map to store <Number, Count>
    HashMap<Integer, Integer> frequencyMap = new HashMap<>();

    // 2. Count occurrences
    for (Integer num : playerHandInfo) {
        // getOrDefault checks if 'num' is in map. If yes, get count. If no, get 0.
        // Then we add 1 to it.
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    // 3. Find the entry with the highest count
    int mostCommonElement = playerHandInfo.get(0);
    int maxCount = 0;

    for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
        if (entry.getValue() > maxCount) {
            mostCommonElement = entry.getKey();
            maxCount = entry.getValue();
        }
    }

    this.returnSum = randomFingers + mostCommonElement;
}
  

  public int returnFingers(){
    return randomFingers;
    
  }

  public int returnSum(){
    return returnSum;
  
  }
}
