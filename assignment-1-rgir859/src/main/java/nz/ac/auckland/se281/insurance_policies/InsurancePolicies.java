package nz.ac.auckland.se281.insurance_policies;

public abstract class InsurancePolicies {

  protected int sumInsured;

  public InsurancePolicies(String sumInsured) {
    this.sumInsured = Integer.parseInt(sumInsured);
  }

  public abstract int basePremium(int clientAge);

}
