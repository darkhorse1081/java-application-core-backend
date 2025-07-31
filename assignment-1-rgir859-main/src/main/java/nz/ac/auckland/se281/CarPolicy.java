package nz.ac.auckland.se281;

public class CarPolicy extends RootPolicy {
  private String makeModel;
  private String licensePlate;
  private boolean mechanicalBreakdown;
  private int clientAge;

  public CarPolicy( // constructor for car policy class with attributes
      int clientSumInsured,
      String clientMakeModel,
      String clientLicensePlate,
      boolean clientMechanicalBreakdown,
      int clientAge) {
    this.makeModel = clientMakeModel; // assigning attributes to variables
    this.licensePlate = clientLicensePlate;
    this.mechanicalBreakdown = clientMechanicalBreakdown;
    this.sumInsured = clientSumInsured;
    this.clientAge = clientAge;
    this.basePremium = calculateBasePremium();
  }

  public double calculateBasePremium() { // method to calculate base premium
    double basePremium = 0;
    if (clientAge < 25) { // if client is under 25, base premium is 15% of sum insured
      basePremium = sumInsured * 0.15;
    } else { // if client is over 25, base premium is 10% of sum insured
      basePremium = sumInsured * 0.1;
    }
    if (mechanicalBreakdown) {
      basePremium += 80; // if client has mechanical breakdown, add $80 to base premium
    }
    return basePremium;
  }

  public String getMakeModel() { // getters for encapsulation of attributes in car policy class
    return makeModel;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public boolean getMechanicalBreakdown() {
    return mechanicalBreakdown;
  }
}
