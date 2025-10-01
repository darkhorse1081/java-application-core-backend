package nz.ac.auckland.se281.insurance_policies;


public class LifePolicy extends InsurancePolicies {

  public LifePolicy(String sumInsured) {
    super(sumInsured);
  }

  @Override
  public int basePremium(int clientAge) {
      return (sumInsured*(1 + clientAge/100))/100;
  }

}
