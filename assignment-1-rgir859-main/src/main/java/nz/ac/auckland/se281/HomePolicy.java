package nz.ac.auckland.se281;

public class HomePolicy extends RootPolicy {
  private String address;
  private boolean rental;

  public HomePolicy(int clientSumInsured, String clientAddress, boolean clientRental) {
    this.address = clientAddress; // assigning attributes to variables
    this.rental = clientRental;
    this.sumInsured = clientSumInsured;
    this.basePremium = calculateBasePremium();
  }

  public double calculateBasePremium() {
    double basePremium = 0; // if client is renting, base premium is 2% of sum insured
    if (rental) {
      basePremium = sumInsured * 0.02;
    } else { // if client is not renting, base premium is 1% of sum insured
      basePremium = sumInsured * 0.01;
    }
    return basePremium;
  }

  public String getAddress() { // getters for encapsulation of attributes in home policy class
    return address;
  }

  public boolean getRental() {
    return rental;
  }
}
