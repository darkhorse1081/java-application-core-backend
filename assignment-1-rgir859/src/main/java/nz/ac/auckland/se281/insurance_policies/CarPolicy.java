package nz.ac.auckland.se281.insurance_policies;

public class CarPolicy extends InsurancePolicies {

  private String makeModel;
  private String licensePlate;
  private boolean mechanicalBreakdown;

  public CarPolicy(String sumInsured, String makeModel, String licensePlate, String mechanicalBreakdown) {
    super(sumInsured);
    this.makeModel = makeModel;
    this.licensePlate = licensePlate;
    this.setMechanicalBreakdown(mechanicalBreakdown);

  }

  public boolean setMechanicalBreakdown(String mechanicalBreakdown) {
    if (mechanicalBreakdown == "yes") { 
      return this.mechanicalBreakdown = true;
    } else {
      return this.mechanicalBreakdown = false;
    }
  }

  @Override
  public int basePremium(int clientAge) {

    if (clientAge < 25) {
      if (mechanicalBreakdown) {
        return (sumInsured*15)/100 + 80;
      } else {
        return (sumInsured*15)/100;
      }

    } else {
      if (mechanicalBreakdown) {
        return (sumInsured*10)/100 + 80;
      } else {
        return (sumInsured*10)/100;
      }
    }

  }

}
