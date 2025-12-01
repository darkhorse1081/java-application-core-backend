package nz.ac.auckland.se281;

import nz.ac.auckland.difficulty_levels.DifficultyFactory;
import nz.ac.auckland.difficulty_levels.strategies.Strategy;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private int round;
  private String globalPlayerName;

  public Morra() {
    this.round = 1;
  }

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {

    // input check already implemented
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    this.globalPlayerName = options[0];

  }

  public void play() {

      MessageCli.START_ROUND.printMessage(String.valueOf(round));
      MessageCli.ASK_INPUT.printMessage();
      String line = Utils.scanner.nextLine();
      String[] parts = line.trim().split("\\s+");
      String fingers = parts[0]; // contains "1"
      String sum = parts[1]; // contains "2"

      while (((parts.length>2) || 
      ((Integer.parseInt(fingers) < 1 || Integer.parseInt(fingers) > 5)) || 
      ((Integer.parseInt(sum) < 1 || Integer.parseInt(sum) > 10)))) { // condition not met

        MessageCli.INVALID_INPUT.printMessage();
        MessageCli.ASK_INPUT.printMessage();
        String check = Utils.scanner.nextLine();
        parts = check.trim().split("\\s+");
        fingers = parts[0];
        sum = parts[1]; 
      }

      MessageCli.PRINT_INFO_HAND.printMessage(globalPlayerName, fingers, sum);
      round += 1;

  }

  public void showStats() {}
}
