package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.Jarvis;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private int round;
  private String globalPlayerName;
  private Difficulty difficulty;
  private Jarvis robot;
  private Boolean gamestart = false;
  private int playerPoints;
  private int JarvisPoints;
  private int pointsToWin;


  public Morra() {
    this.round = 1;
  }

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {

    // input check already implemented
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    this.globalPlayerName = options[0];
    this.difficulty = difficulty;

    this.robot = new Jarvis(difficulty);
    round = 1;

    this.gamestart = true;
    this.playerPoints = 0;
    this.JarvisPoints = 0;
    this.pointsToWin = pointsToWin;

  }

  public void play() {

    if (!gamestart) {
      MessageCli.GAME_NOT_STARTED.printMessage();

    } else{

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

        robot.transferDetails(round, Integer.parseInt(fingers));         // passing on player hand and round info details
        robot.implementProcess();
        MessageCli.PRINT_INFO_HAND.printMessage(globalPlayerName, fingers, sum); 
        int botFinger = robot.getConsoleFingers();
        int botSum = robot.getConsoleSum();
        MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", String.valueOf(botFinger), String.valueOf(botSum));
        round += 1;

        if (Integer.parseInt(sum) == Integer.parseInt(fingers)+botFinger 
          && botSum != Integer.parseInt(fingers)+botFinger) { // player sum = player fingers + jarvis fingers
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
          playerPoints += 1;
          if (playerPoints == pointsToWin) {
            MessageCli.END_GAME.printMessage(globalPlayerName, String.valueOf(round-1));
            gamestart = false;
          }
        } else if (botSum == Integer.parseInt(fingers)+botFinger 
          && Integer.parseInt(sum) != Integer.parseInt(fingers)+botFinger) { // // jarvis sum = player fingers + jarvis fingers
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
          JarvisPoints += 1;
          if (JarvisPoints == pointsToWin) {
            MessageCli.END_GAME.printMessage("Jarvis", String.valueOf(round-1));
            gamestart = false;
          }
        } else {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
        }

    }
    
  }

  public void showStats() {

    if (!gamestart) {
        MessageCli.GAME_NOT_STARTED.printMessage();
    } else {
      MessageCli.PRINT_PLAYER_WINS.printMessage
      (globalPlayerName, String.valueOf(playerPoints), String.valueOf(pointsToWin - playerPoints));
      MessageCli.PRINT_PLAYER_WINS.printMessage
      ("Jarvis", String.valueOf(JarvisPoints), String.valueOf(pointsToWin - JarvisPoints));
    }
  }
}
