package nz.ac.auckland.se281;

public class UserProfile {
     
  private String userName = "";
  private String age = "";
  private boolean profileLoaded;

  public UserProfile(String userName, String age) {
    this.userName = userName.substring(0, 1).toUpperCase() + 
    userName.substring(1).toLowerCase();
    this.age = age;
    this.profileLoaded = false;
  }

  public String getFirstName() {
    return this.userName;
	}

  public String getAge() {
    return this.age;
  }

  public void setProfileLoaded(boolean flag) {
    this.profileLoaded = flag;
  }

  public boolean getProfileLoaded() {
    return this.profileLoaded;
  }

}