package nz.ac.auckland;

import java.util.ArrayList;
import nz.ac.auckland.difficulty_levels.DifficultyFactory;
import nz.ac.auckland.difficulty_levels.JarvisDifficulty;
import nz.ac.auckland.difficulty_levels.Medium;
import nz.ac.auckland.difficulty_levels.strategies.StrategySystem;
import nz.ac.auckland.se281.Main.Difficulty;

public class Jarvis {

  private Difficulty difficulty;
  private int fingers;
  private int sum;
  private JarvisDifficulty selected;
  private ArrayList<Integer> playerHandInfo;
  private Integer roundNumber;

  public Jarvis(Difficulty difficulty) {
    this.difficulty = difficulty;
    this.playerHandInfo = new ArrayList<Integer>();
  }

  public void implementProcess() {

    JarvisDifficulty selected = DifficultyFactory.selectDifficulty(difficulty); // selected is the difficulty class
    if (selected instanceof Medium m) {
      m.transferToMedium(roundNumber, playerHandInfo); // send data to difficulty level medium
      selected.implementStrat();                              // specialised
    }
    StrategySystem system = selected.useSystem();             // system acts as gate from strat to difficulty
    this.fingers = system.getFingers();                       // Jarvis obtains fingers to present from the strategy system 
    this.sum = system.getSum();

  }

  public void transferDetails(int roundNumber, int playerHand){ // for one instance keeps updated

    this.roundNumber = roundNumber;
    this.playerHandInfo.add(playerHand);

  }

  public int getConsoleFingers() {
    return fingers;
  }

  public int getConsoleSum() {
    return sum;
  }

}
