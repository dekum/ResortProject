package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class used to read guest and manager usernames and password
 * Uses Properties key and element pair to iterate through text file to find valid match
 *
 */
public class UserFileUtilities {

  private static File targetFile;
  private static Properties properties;
  private static String newLine = System.lineSeparator();


  /*
  Called when needed to read file
  Sets file path and creates properties
   */
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

    //Checks if file exits, if not creates one
    if (!targetFile.exists()) {
      targetFile.createNewFile();
    }

    /*
    Passes username and password to method to check if username and password exist
    If they do, then it sends an error
    If not, creates new username and password and writes to file
     */
    Boolean doesTheKeyValuePairExist = checkIfPairExists(username, password);

    if (doesTheKeyValuePairExist) {
      System.err.println("Username and password already exist!");
    } else {
      try {
        addNewCredentials(username, password);
      } catch (IOException ioe) {
        System.err.println(
            "File not found");
      }
    }
  }

  /*
  Creates new BufferedWriter to append text file with new user info
   */
  private static void addNewCredentials(String username, String password) throws IOException {
    FileWriter writer =  new FileWriter(targetFile.getAbsolutePath(), true);
    BufferedWriter buffered_writer = new BufferedWriter(writer);
    buffered_writer.write(newLine + username + ":" + password);
    buffered_writer.close();
  }

  /*
  Iterates through the text file to check if theres a match between key and element pair
  If so, returns true
   */
  public static Boolean checkIfPairExists(String username, String password) {
    for (String key : properties.stringPropertyNames()) {
      if (key.equals(username) && properties.getProperty(key).equals(password)) {
        return true;
      }
    }

    return false;
  }

  /*
  Used to iterate through text file and find total amount of guests for display in summary reports
   */
  public static int getGuestAmount(String username, String password){
    int count = 0;
    for (String key : properties.stringPropertyNames()){
      count++;
    }
    return count;
  }
}