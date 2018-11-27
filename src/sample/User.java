package sample;

/**
 * User.java
 * Could also be called UserAccount
 * Manager and Guest Class extend from this.
 *
 */

public abstract class  User {
  private  String userName;
  private  String password;

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }
}
