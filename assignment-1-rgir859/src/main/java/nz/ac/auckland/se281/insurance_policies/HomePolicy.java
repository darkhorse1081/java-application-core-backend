package nz.ac.auckland.se281.insurance_policies;

public class HomePolicy extends InsurancePolicies {

  private String address;
  private boolean isRented;

  public HomePolicy(String sumInsured, String address, String isRented) {
    super(sumInsured);
    this.address = address;
    this.setIsRented(isRented);
    
  }

  public boolean setIsRented(String isRented) {
    if (isRented == "yes") { 
      return this.isRented = true;
    } else {
      return this.isRented = false;
    }
  }

  @Override
  public int basePremium(int clientAge) {
    if (isRented == true) {
      return (sumInsured * 2)/100;
    } else {
      return (sumInsured * 1)/100;
    }
  }
}
