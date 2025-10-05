package nz.ac.auckland.se281.insurance_policies;

public class CarPolicy extends InsurancePolicies {

  private String makeModel;
  private String licensePlate;
  private boolean breakDownStatus;

  public CarPolicy(String sumInsured, String makeModel, String licensePlate, String mechanicalBreakdown) {
    super(sumInsured);
    this.makeModel = makeModel;
    this.licensePlate = licensePlate;
    this.setMechanicalBreakdown(mechanicalBreakdown);

  }

  public boolean setMechanicalBreakdown(String mechanicalBreakdown) {
    if (mechanicalBreakdown != null && mechanicalBreakdown.equalsIgnoreCase("yes")) { 
      return this.breakDownStatus = true;
    } else {
      return this.breakDownStatus = false;
    }
  }

  public String getMakeModel() {
    return this.makeModel;
  }

  @Override
  public double basePremium(int clientAge) {

    if (clientAge < 25) {
      if (breakDownStatus) {
        return sumInsured*0.15 + 80;
      } else {
        return sumInsured*0.15;
      }

    } else {
      if (breakDownStatus) {
        return sumInsured*0.1 + 80;
      } else {
        return sumInsured*0.1;
      }
    }

  }

}
