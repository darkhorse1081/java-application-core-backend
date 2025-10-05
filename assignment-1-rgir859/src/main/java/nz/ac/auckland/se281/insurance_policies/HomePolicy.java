package nz.ac.auckland.se281.insurance_policies;

public class HomePolicy extends InsurancePolicies {

  private String address;
  private boolean rentStatus;

  public HomePolicy(String sumInsured, String address, String isRented) {
    super(sumInsured);
    this.address = address;
    setIsRented(isRented);
  }

  public void setIsRented(String isRented) {
    if (isRented != null && isRented.equalsIgnoreCase("yes")) { 
      this.rentStatus = true;
    } else {
      this.rentStatus = false;
    }
  }

  public String getAddress() {
    return this.address;
  }

  @Override
  public double basePremium(int clientAge) {
    if (this.rentStatus) {
      return sumInsured*0.02;
    } else {
      return sumInsured*0.01;
    }
  }
}
