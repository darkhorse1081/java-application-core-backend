package nz.ac.auckland.se281;

public abstract class RootPolicy {
  public int sumInsured;
  public double basePremium;

  public abstract double calculateBasePremium();

  public double getBasePremium() {
    return basePremium;
  }

  public int getSumInsured() {
    return sumInsured;
  }
}
