package nz.ac.auckland.se281.insurance_policies;

public abstract class InsurancePolicies {

  protected Integer sumInsured;

  public InsurancePolicies(String sumInsured) {
    this.sumInsured = Integer.parseInt(sumInsured);
  }

  public String getSumInsured() {
    return String.valueOf(this.sumInsured);
  }

  public abstract double basePremium(int clientAge);

}
