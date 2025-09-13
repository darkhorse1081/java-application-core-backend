package nz.ac.auckland.se281;
import java.util.ArrayList;

public class UserProfile {
     
  private String userName = "";
  private String age = "";
  private ArrayList<String> valuePair;

  public UserProfile(String userName, String age) {
    this.userName = userName.substring(0, 1).toUpperCase() + 
    userName.substring(1).toLowerCase();
    this.age = age;
    this.valuePair = new ArrayList<String>();
  }

  public String getFirstName() {
    return this.userName;
	}

  public String getAge() {
    return this.age;
  }

  public ArrayList<String> setPair() {
    valuePair.add(userName);
    valuePair.add(age);
    return valuePair;

  }


}
