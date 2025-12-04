package nz.ac.auckland.difficulty_levels;

import nz.ac.auckland.se281.Main.Difficulty;

public class DifficultyFactory {

    public static JarvisDifficulty selectDifficulty(Difficulty difficulty){
      
        switch(difficulty){

              case EASY:
                  return new Easy();
            
              case MEDIUM:
                  return new Medium();
            
              case HARD:
                  return new Hard();

              case MASTER:
                  return new Master();
        } 

        return null;     
    }

}
