package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

    // fields 
  private ArrayList<String> userDataBase;

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
     this.userDataBase = new ArrayList<String>();
  }

  public boolean isNumber(String input) {
    try {
      // Attempt to parse the input as an integer
        Integer.parseInt(input);
        return true;
    } catch (NumberFormatException e) {
      // If parsing fails, it's not an integer
        return false;
      }
    }

    // methods - if required

  public void printDatabase() {
    // TODO: Complete this method.
  }

  public void createNewProfile(String userName, String age) {

    if (userName.length()>3) { 
      // username incorrect length
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName); 
      // --
    } else if (userDataBase.contains(userName)) { 
      // username is present in database
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
    } else if (isNumber(age) == false){ 
      // age input is not a postive integer
      MessageCli.INVALID_AGE.printMessage(age);
    }
    else {
      if (Integer.parseInt(age) < 0) { // if age is negative 
        MessageCli.INVALID_AGE.printMessage(age);
      } else{
            String updatedName = userName.substring(0, 1).toUpperCase() + 
            userName.substring(1).toLowerCase();
            UserProfile newEntry = new UserProfile(updatedName, age);
            userDataBase.add(newEntry.getFirstName());
          }

    }

  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }

}
