package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ClientProfile {

  private String clientName; // private attributes for encapsulation
  private String clientAge;
  private double totalPremium = 0;
  private ArrayList<RootPolicy> policyList;

  public ClientProfile(String custName, String custAge) { // constructor initializes attributes
    this.clientName = custName;
    this.clientAge = custAge;
    policyList = new ArrayList<>();
  }

  private double getDiscountedPremium(
      int count, double premium) { // method to calculate discounted premium
    double revisedPremium = premium;
    if (count < 2) { // if client has less than 2 policies, no discount
      revisedPremium = premium * 1.0;
    }
    if (count == 2) { // if client has 2 policies, 10% discount
      revisedPremium = premium * 0.9;
    }
    if (count >= 3) { // if client has 3 or more policies, 20% discount
      revisedPremium = premium * 0.8;
    }
    return revisedPremium;
  }

  public String getName() { // method returns client name attribute
    return clientName;
  }

  public String getAge() { // method returns client age attribute
    return clientAge;
  }

  public String getTotalPremium() { // method returns total premium attribute as a string
    for (RootPolicy policy : policyList) {
      if (policy instanceof LifePolicy) {
        LifePolicy lifePolicyInstance = (LifePolicy) policy; // casting to life policy instance
        totalPremium += getDiscountedPremium(getPolicyCount(), lifePolicyInstance.getBasePremium());
      } else if (policy instanceof CarPolicy) {
        CarPolicy carPolicyInstance = (CarPolicy) policy; // casting to car policy instance
        totalPremium += getDiscountedPremium(getPolicyCount(), carPolicyInstance.getBasePremium());
      } else if (policy instanceof HomePolicy) {
        HomePolicy homePolicyInstance = (HomePolicy) policy; // casting to home policy instance
        totalPremium += getDiscountedPremium(getPolicyCount(), homePolicyInstance.getBasePremium());
      }
    }
    return String.valueOf((int) (totalPremium));
  }

  public void setName(String name) { // this method sets the name
    this.clientName = name;
  }

  public void setAge(String age) { // this method sets the age
    this.clientAge = age;
  }

  public String toString() { // string to override
    return clientName + "," + clientAge;
  }

  public void addPolicy(RootPolicy policy) { // method adds policy to policy list
    policyList.add(policy);
  }

  public void removePolicy(RootPolicy policy) { // method removes policy from policy list
    policyList.remove(policy);
  }

  public boolean isExistingLifePolicy() { // method checks if life policy exists
    for (RootPolicy policy : policyList) {
      if (policy instanceof LifePolicy) {
        return true;
      }
    }
    return false;
  }

  public int getPolicyCount() { // method returns policy count
    return policyList.size();
  }

  public void printPolicyList() { // method prints policy list
    for (RootPolicy policy : policyList) {
      if (policy instanceof LifePolicy) { // casting to life policy instance and printing out details
        LifePolicy lifePolicyInstance = (LifePolicy) policy;
        MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
            String.valueOf(lifePolicyInstance.getSumInsured()),
            String.valueOf((int) lifePolicyInstance.getBasePremium()),
            String.valueOf(
                (int) getDiscountedPremium(getPolicyCount(), lifePolicyInstance.getBasePremium())));
      } else if (policy instanceof CarPolicy) { // casting to car policy instance and printing
        CarPolicy carPolicyInstance = (CarPolicy) policy;
        MessageCli.PRINT_DB_CAR_POLICY.printMessage(
            carPolicyInstance.getMakeModel(),
            String.valueOf(carPolicyInstance.getSumInsured()),
            String.valueOf((int) carPolicyInstance.getBasePremium()),
            String.valueOf(
                (int) getDiscountedPremium(getPolicyCount(), carPolicyInstance.getBasePremium())));
      } else if (policy instanceof HomePolicy) { // casting to home policy instance and printing out details
        HomePolicy homePolicyInstance = (HomePolicy) policy;
        MessageCli.PRINT_DB_HOME_POLICY.printMessage(
            homePolicyInstance.getAddress(), 
            String.valueOf(homePolicyInstance.getSumInsured()),
            String.valueOf((int) homePolicyInstance.getBasePremium()),
            String.valueOf(
                (int) getDiscountedPremium(getPolicyCount(), homePolicyInstance.getBasePremium())));
      }
    }
  }
}
