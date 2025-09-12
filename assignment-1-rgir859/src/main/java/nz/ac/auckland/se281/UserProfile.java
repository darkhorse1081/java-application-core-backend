package nz.ac.auckland.se281;

public class UserProfile {
     
  private String firstName = "";
  private String age = "";

  public UserProfile(String firstName, String age){
    this.firstName = firstName;
    this.age = age;
  }

  public String getFirstName() {
    return this.firstName;
	}

  public String getAge() {
    return this.age;
  }


}
