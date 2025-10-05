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

  public String discountedPrice() {

    double discountedValue = 0;

    if (policyArrayList.size()==2) {
      discountedValue = policyArrayList.get(0).basePremium(Integer.parseInt(this.age)) 
      +policyArrayList.get(1).basePremium(Integer.parseInt(this.age));
      discountedValue = discountedValue * 0.9;
      return String.valueOf((int)discountedValue);

    } else if (policyArrayList.size()>=3) {
      for (InsurancePolicies insurancePolicies : policyArrayList) {
        discountedValue = discountedValue + insurancePolicies.basePremium(Integer.parseInt(this.age));
      }
      discountedValue = discountedValue*0.8;
      return String.valueOf((int)discountedValue);

    } else if(policyArrayList.size()==1) {
      discountedValue = policyArrayList.get(0).basePremium(Integer.parseInt(this.age));
      return String.valueOf((int)discountedValue);

    } else {
      return String.valueOf((int)discountedValue);
    }
  }



}