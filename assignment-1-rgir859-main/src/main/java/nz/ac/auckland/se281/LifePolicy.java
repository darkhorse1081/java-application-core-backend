package nz.ac.auckland.se281;

public class LifePolicy extends RootPolicy { // life policy class extends root policy class
  private int clientAge;

  public LifePolicy(
      int clientSumInsured, int clientAge) { // constructor for life policy class with attributes
    this.clientAge = clientAge;
    this.sumInsured = clientSumInsured;
    this.basePremium = calculateBasePremium();
  }

  public double calculateBasePremium() { // method to calculate base premium
    double basePremium = 0;
    basePremium = sumInsured * (1 + (clientAge / 100.0)) / 100;
    return basePremium;
  }
}
