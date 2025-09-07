package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<ClientProfile> clientDatabase;
  private ClientProfile loadedProfile;

  public InsuranceSystem() { // constructor for insurance system class

    clientDatabase = new ArrayList<>();
    loadedProfile = null;
  }

  public String convertTitleCase(
      String inputString) { // ensures character entered is convereted to title case

    String resultString;
    resultString = inputString.toLowerCase();
    char titleChar = resultString.charAt(0); // converts first character to title case
    resultString = String.valueOf(titleChar).toUpperCase() + resultString.substring(1);

    return resultString;
  }

  public void printDatabase() {

    if (clientDatabase.size() == 0) { // checks for initial number of profiles in data base
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
      return;
    }

    if (clientDatabase.size() == 1) { // prints output with one profile
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
    } else { // prints output with two or more profiles
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(
          String.valueOf(clientDatabase.size()), "s", ":");
    }

    for (int i = 0;
        i < clientDatabase.size();
        i++) { // print client with star if loaded " %s%s: %s, %s"

      if (loadedProfile != null) { // prints loaded profile with star
        if (clientDatabase.get(i).getName().equals(loadedProfile.getName())) {
          int loadedCount = loadedProfile.getPolicyCount();
          if (loadedCount == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                String.valueOf(i + 1),
                clientDatabase.get(i).getName(),
                clientDatabase.get(i).getAge(),
                String.valueOf(loadedCount),
                "y",
                loadedProfile.getTotalPremium());
          } else { // prints loaded profile with star and plural policies
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                String.valueOf(i + 1),
                clientDatabase.get(i).getName(),
                clientDatabase.get(i).getAge(),
                String.valueOf(loadedCount),
                "ies",
                loadedProfile.getTotalPremium());
          }
        } else { // prints all other profiles
          int currentCount = clientDatabase.get(i).getPolicyCount();
          if (currentCount == 1) { // prints all other profiles with singular policies
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                String.valueOf(i + 1),
                clientDatabase.get(i).getName(),
                clientDatabase.get(i).getAge(),
                String.valueOf(currentCount),
                "y",
                clientDatabase.get(i).getTotalPremium());
          } else { // prints all other profiles with plural policies
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                String.valueOf(i + 1),
                clientDatabase.get(i).getName(),
                clientDatabase.get(i).getAge(),
                String.valueOf(currentCount),
                "ies",
                clientDatabase.get(i).getTotalPremium());
          }
        }
      } else { // prints all other profiles with no loaded profile
        int currentCount = clientDatabase.get(i).getPolicyCount();
        if (currentCount == 1) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              String.valueOf(i + 1),
              clientDatabase.get(i).getName(),
              clientDatabase.get(i).getAge(),
              String.valueOf(currentCount),
              "y",
              clientDatabase.get(i).getTotalPremium());
        } else { // prints all other profiles with plural policies and no loaded profile
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              String.valueOf(i + 1),
              clientDatabase.get(i).getName(),
              clientDatabase.get(i).getAge(),
              String.valueOf(currentCount),
              "ies",
              clientDatabase.get(i).getTotalPremium());
        }
      }
      clientDatabase.get(i).printPolicyList();
    }
  }

  public void createNewProfile(String userName, String age) {
    String newUser =
        convertTitleCase(
            userName); // sets username titlecase when valid name entered and instatiates new
    // profile

    if (loadedProfile != null) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedProfile.getName());
      return;
    }
    if (newUser.length() < 3) { // checks valditiy for the length of the username entered
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(newUser);
      return;
    }
    if (age.contains(".")) { // checks if age contains decimals
      MessageCli.INVALID_AGE.printMessage(age);
      return;
    }
    int ageInt = Integer.parseInt(age); // checks if age is negative
    if (ageInt < 0) {
      MessageCli.INVALID_AGE.printMessage(age, newUser);
      return;
    }
    for (ClientProfile cp :
        clientDatabase) { // checks the uniquness of the username entered, compares to existing
      // profile names
      if (cp.getName().equals(newUser)) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(newUser);
        return;
      }
    }

    ClientProfile newCp =
        new ClientProfile(newUser, age); // creates new profile with valid username
    boolean result = clientDatabase.add(newCp);
    if (result) {
      MessageCli.PROFILE_CREATED.printMessage(newUser, age);
    } else {
      System.out.println("Error in creating profile");
    }
  }

  public void loadProfile(String userName) { // loads profile with valid username
    String titleUser = convertTitleCase(userName);
    boolean found = false; // checks if profile is found
    for (ClientProfile temp : clientDatabase) {
      if (temp.getName().equals(titleUser)) {
        loadedProfile = temp;
        found = true;
        break;
      }
    }
    if (found) { // prints message if profile is loaded
      MessageCli.PROFILE_LOADED.printMessage(titleUser);
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(titleUser);
    }
  }

  public void unloadProfile() { // unloads profile if one is loaded
    if (loadedProfile == null) { // checks if profile is loaded
      MessageCli.NO_PROFILE_LOADED.printMessage();
      return;
    }
    MessageCli.PROFILE_UNLOADED.printMessage(loadedProfile.getName());
    loadedProfile = null;
  }

  public void deleteProfile(String userName) { // deletes profile with valid username
    String titleUser = convertTitleCase(userName);
    boolean found = false;

    if (loadedProfile != null) { // checks if profile is loaded and cannot be deleted
      if (loadedProfile.getName().equals(titleUser)) {
        MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(titleUser);
        return;
      }
    }
    for (ClientProfile temp : clientDatabase) { // checks if profile is found and deletes it
      if (temp.getName().equals(titleUser)) {
        found = true;
        MessageCli.PROFILE_DELETED.printMessage(titleUser);
        clientDatabase.remove(temp);
        return;
      }
    }
    if (!found) { // prints message if profile is not found and cannot be deleted
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(titleUser);
    }
  }

  public void createPolicy(PolicyType type, String[] options) { // creates policy with valid inputs
    if (loadedProfile == null) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }
    if (type == PolicyType.HOME) { // checks policy type and creates policy with valid inputs
      createHomePolicy(options);
    } else if (type == PolicyType.CAR) { // checks policy type and creates policy with valid inputs
      createCarPolicy(options);
    } else if (type == PolicyType.LIFE) { // checks policy type and creates policy with valid inputs
      int age = Integer.parseInt(loadedProfile.getAge());
      if (loadedProfile.isExistingLifePolicy()) { // add logic for checking multiple life policies
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfile.getName());
        return;
      }
      if (age > 100) { // add logic for checking age limit for life policy creation (100)
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfile.getName());
        return;
      } else {
        createLifePolicy(options);
      }
    }
  }

  public void createHomePolicy(
      String[] options) { // creates home policy with valid inputs and adds it
    int requestedValue = Integer.parseInt(options[0]);
    boolean requestedRental = false;
    if (options[2].contains("y") || options[2].contains("Y")) { // checks if home is rental or not
      requestedRental = true;
    } else if (options[2].contains("n") || options[2].contains("N")) {
      requestedRental = false;
    }
    HomePolicy newPolicy =
        new HomePolicy(requestedValue, options[1], requestedRental); // creates new
    loadedProfile.addPolicy(newPolicy);
    MessageCli.NEW_POLICY_CREATED.printMessage("home", loadedProfile.getName());
  }

  public void createCarPolicy(
      String[] options) { // creates car policy with valid inputs and adds it
    int requestedValue = Integer.parseInt(options[0]);
    boolean requestedMech = false; // checks if car is mechanical or not
    if (options[3].contains("y") || options[3].contains("Y")) {
      requestedMech = true;
    } else if (options[3].contains("n") || options[3].contains("N")) {
      requestedMech = false;
    }
    int age = Integer.parseInt(loadedProfile.getAge()); // creates new car policy
    CarPolicy newPolicy = new CarPolicy(requestedValue, options[1], options[2], requestedMech, age);
    loadedProfile.addPolicy(newPolicy);
    MessageCli.NEW_POLICY_CREATED.printMessage("car", loadedProfile.getName());
  }

  public void createLifePolicy(
      String[] options) { // creates life policy with valid inputs and adds it
    int requestedValue = Integer.parseInt(options[0]);
    int age = Integer.parseInt(loadedProfile.getAge()); // creates new life policy
    LifePolicy newPolicy = new LifePolicy(requestedValue, age);
    loadedProfile.addPolicy(newPolicy); // adds policy to profile
    MessageCli.NEW_POLICY_CREATED.printMessage("life", loadedProfile.getName());
  }
}
