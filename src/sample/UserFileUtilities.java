package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class UserFileUtilities {

  private static File targetFile;
  private static Properties properties;
  private static String newLine = System.lineSeparator();


  public static void Initialize(){

    targetFile = new File("lib/guestUsernames.txt");

    properties = new Properties();

    try {
      properties.load(new FileInputStream(targetFile.getAbsolutePath()));
    } catch (IOException ioe) {
      System.err.println("Unable to read file.");
    }

  }

  public static void addUser(String username, String password)
      throws IOException {

    Initialize();

    if (!targetFile.exists()) {
      targetFile.createNewFile();
    }

    Boolean doesTheKeyValuePairExist = checkIfPairExists(username, password);

    if (doesTheKeyValuePairExist) {
      System.err.println("Username and password already exist!");
    } else {
      try {
        addNewCredentials(username, password);

        System.out.println(
            "Username and password added!");
      } catch (IOException ioe) {
        System.err.println(
            "File not found");
      }
    }
  }

  private static void addNewCredentials(String username, String password) throws IOException {
    FileWriter writer =  new FileWriter(targetFile.getAbsolutePath(), true);
    BufferedWriter buffered_writer = new BufferedWriter(writer);
    buffered_writer.write(newLine + username + ":" + password);
    buffered_writer.close();
  }

  public static Boolean checkIfPairExists(String username, String password) {
    for (String key : properties.stringPropertyNames()) {
      if (key.equals(username) && properties.getProperty(key).equals(password)) {
        return true;
      }
    }

    return false;
  }

  public static String searchUsers(String username){

    Initialize();

    String returnUser = null;

    for (String key : properties.stringPropertyNames()){
      if (key.equals(username)){
        returnUser = username;
      }
    }
    return returnUser;
  }
/*
  public static ArrayList<User2> initializeUsers(){
    ArrayList<User2> userList = new ArrayList<>();
    Initialize();

    for (String key : properties.stringPropertyNames()){
      User2 user = new User2();
      user.setUsername(key);
      user.setPassword(properties.getProperty(key));
      userList.add(user);
    }
    return userList;
  }
*/
}