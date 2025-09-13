package nz.ac.auckland.se281;

public class UserProfile {
     
  private String userName = "";
  private String age = "";

  public UserProfile(String userName, String age) {
    this.userName = userName.substring(0, 1).toUpperCase() + 
    userName.substring(1).toLowerCase();
    this.age = age;
  }

  public String getFirstName() {
    return this.userName;
	}

  public String getAge() {
    return this.age;
  }

}
