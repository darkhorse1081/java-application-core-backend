package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.insurance_policies.InsurancePolicies;
import nz.ac.auckland.se281.insurance_policies.LifePolicy;

public class UserProfile {
     
  private String userName = "";
  private String age = "";
  private boolean profileLoaded;
  private ArrayList<InsurancePolicies> policyArrayList;

  public UserProfile(String userName, String age) {
    this.userName = userName.substring(0, 1).toUpperCase() + 
    userName.substring(1).toLowerCase();
    this.age = age;
    this.profileLoaded = false;
    this.policyArrayList = new ArrayList<InsurancePolicies>();
  }

  public String getFirstName() {
    return this.userName;
	}

  public String getAge() {
    return this.age;
  }

  public void setProfileLoaded(boolean flag) {
    this.profileLoaded = flag;
  }

  public boolean getProfileLoaded() {
    return this.profileLoaded;
  }

  public void addPolicyObject(InsurancePolicies object) {
    this.policyArrayList.add(object);
  }

  public ArrayList<InsurancePolicies> getPolicyList() {
    return this.policyArrayList;
  }

  public boolean containsInstance() {
    for (InsurancePolicies insurancePolicies : policyArrayList) {
      if (insurancePolicies instanceof LifePolicy) {
        return true;
      }
    }
    return false;
  }

}