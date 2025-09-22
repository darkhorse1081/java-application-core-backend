package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

    // fields 
  private ArrayList<UserProfile> userDataBase;
  private ArrayList<String> nameVerification;
  private boolean isProfileLoaded;
  private int loadPosition = 0;

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
     this.userDataBase = new ArrayList<UserProfile>();
     this.nameVerification = new ArrayList<String>();
     this.isProfileLoaded = false;
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

  public String convertCap(String userName) {
    String converted = userName.substring(0, 1).toUpperCase() + 
         userName.substring(1).toLowerCase();
    return converted;
  }

  public void printDatabase() {

    if (userDataBase.isEmpty()) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(userDataBase.size()),"s",".");

    } else if (userDataBase.size() == 1) {
        MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(userDataBase.size()),"",":");
        MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(String.valueOf(userDataBase.size()),
          nameVerification.get(0), userDataBase.get(0).getAge());

    } else {
        MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(userDataBase.size()),"s",":");
        for (int i = 0; i < userDataBase.size(); i++) {
          if (userDataBase.get(i).getProfileLoaded()) {
            MessageCli.PRINT_DB_PROFILE_HEADER_SHORT.printMessage("***",
              String.valueOf(i+1),nameVerification.get(i),userDataBase.get(i).getAge());
          } else {
              MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(String.valueOf(i+1),
              nameVerification.get(i), userDataBase.get(i).getAge());            
          }

        }
    }
  }

  public void createNewProfile(String userName, String age) {

    if (isProfileLoaded) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(nameVerification.get(loadPosition));
    }
    if (userName.length()<3) { 
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName); // username incorrect length

    } else if (nameVerification.contains(convertCap(userName))) {
         MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(convertCap(userName)); // username is present in database

    } else if (isNumber(age) == false){ // age input is not a postive integer
        MessageCli.INVALID_AGE.printMessage(age);
    } else {
        if (Integer.parseInt(age)<=0) { // if age is negative 
          MessageCli.INVALID_AGE.printMessage(age);
        } else {
              UserProfile newEntry = new UserProfile(userName, age);
              userDataBase.add(newEntry);
              nameVerification.add(newEntry.getFirstName());
              MessageCli.PROFILE_CREATED.printMessage(newEntry.getFirstName(), 
              newEntry.getAge());
            }
    }

  }

  public void loadProfile(String userName) {

    String proccessed = convertCap(userName);
    if (userName.length()<3) { 
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(proccessed); // username incorrect length
    } 
    if (nameVerification.contains(proccessed)) {
      if (isProfileLoaded == false) { 
         isProfileLoaded = true;
         loadPosition = nameVerification.indexOf(proccessed);
         userDataBase.get(loadPosition).setProfileLoaded(true);
         MessageCli.PROFILE_LOADED.printMessage(proccessed); // success
        
      } else if (isProfileLoaded == true) { 
         userDataBase.get(loadPosition).setProfileLoaded(false); // case -> p1 already loaded -> p2 loaded 
         //-> what happens to loadt status of p1 instance
         loadPosition = nameVerification.indexOf(proccessed);
         userDataBase.get(loadPosition).setProfileLoaded(true);
         MessageCli.PROFILE_LOADED.printMessage(proccessed); 
      }

    } else {
         MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(proccessed);
    }

  }

  public void unloadProfile() {

    if (isProfileLoaded == true) { 
      isProfileLoaded = false;
      userDataBase.get(loadPosition).setProfileLoaded(false);
      MessageCli.PROFILE_UNLOADED.printMessage(userDataBase.get(loadPosition).getFirstName());
    } else {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }

}
