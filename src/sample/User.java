package sample;


/**
 * Abstract class used by guest account
 * Only used to fetch username
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
