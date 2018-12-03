/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.LoginMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.UserFileUtilities;

/**
 * Main login screen controller
 * First window user will see, able to login as guest or manager
 * Can hit the ruby button to be taken to sign up screen
 */

public class LoginMenuController implements Initializable {

  private Guest currentGuest;

  @FXML
  private CheckBox checkBox;
  @FXML
  private TextField textfieldUsername;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Button buttonExit;
  @FXML
  private TextField textFieldUsernameManager;
  @FXML
  private PasswordField textFieldForPasswordManager;
  @FXML
  private Button buttonExit1;
  @FXML
  private CheckBox checkBox2;
  @FXML
  private TextField textFieldForPassword;

  /*
  Button for handling guest login
  Gets username and password from TextField, then runs UserFileUtilities methods to check
  if login is valid
  If so, send the guest information to the Global class for use in the guest window
  If not, displays a box letting user know the login was unsuccessful
   */
  @FXML
  void handleButtonLogin(ActionEvent event) {

    String username = textfieldUsername.getText();
    String password = passwordField.getText();

    UserFileUtilities.Initialize();
    Boolean checkValid = UserFileUtilities.checkIfPairExists(username, password);

    if (checkValid) {
      try {
        Guest guest = new Guest(username, password);
        /*
        This line is used for setting the current scene so it knows which scene to close
        Then opens the next scene through the openGuestMenu method
         */
        Global.currentScene = buttonExit.getScene();
        new Global().displayPopUpWindow("Login Successful!");
        openGuestMenu(guest);
      } catch (IOException e) {
        System.out.println("error");
      }
    } else {
      Global.currentScene = buttonExit.getScene();
      new Global().displayPopUpWindow("Login Unsuccessful!");
    }

  }

  /*
  Manager login works the same as the guest login
   */
  @FXML
  void handleButtonLoginManager(ActionEvent event) {
    String username = textFieldUsernameManager.getText();
    String password = textFieldForPasswordManager.getText();

    UserFileUtilities.Initialize();
    Boolean checkValid = UserFileUtilities.checkIfPairExists(username, password);

    if (checkValid) {
      Guest guest = new Guest(username,password);
      Global.currentScene = buttonExit.getScene();
      new Global().displayPopUpWindow("Login Successful!");
      openManagerMenu(guest);
    } else {
      Global.currentScene = buttonExit.getScene();
      new Global().displayPopUpWindow("Login Unsuccessful!");
    }

  }

  /*
  Takes the user to the sign up page
   */
  @FXML
  void handleButtonCreate(ActionEvent event) {

    Global.currentScene = buttonExit1.getScene();
    Global.currentTitle="Register New Guest";
    new Global().openNewWindow(WindowLocation.SIGNUP);

  }

  /*
  Exits the program
   */
  @FXML
  void handleButtonExit(ActionEvent event) {
    Stage stageExit = (Stage) buttonExit.getScene().getWindow();
    stageExit.close();
  }

  /*
  Method used to open the guest menu
  Sets the Global currentGuestLogginIn object as the current guest for use in guest window
  Sets the title of the scene
  Opens new window for guest menu
   */
  public void openGuestMenu(Guest guest) throws IOException {
    Global.currentGuestLoggedIn = guest;
    Global.currentTitle="Ruby Resort: Guest View";
    Global.currentScene = buttonExit1.getScene();

    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
  }

  /*
  openManagerMenu has the same functionality as openGuestMenu
   */
  private void openManagerMenu(Guest guest) {
    Global.currentGuestLoggedIn = guest;
    Global.currentTitle="Ruby Resort: Manager View";
    Global.currentScene = buttonExit1.getScene();//

    new Global().openNewWindow(WindowLocation.MANAGERMENU);
  }

  /*
  Method first called when window is opened
  If a guest is logged in, sets the username field as their username for easier login
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {


    this.currentGuest = Global.currentGuestLoggedIn;

    if (currentGuest != null) {
      textfieldUsername.setText(currentGuest.getUserName());

    }
  }


}