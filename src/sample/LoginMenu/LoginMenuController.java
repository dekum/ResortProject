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
import sample.Controller;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.UserFileUtilities;

/**
 * Notes Chck the text file of fxml files, they will sometimes have redtext/eerors which IDEA IDE
 * will not tell you Make sure there no java.awt as it will case Errors This classes was foremly
 * named FXMLCONTROLLER.java
 *
 * LoginMenuController.java Notes by:pPetit This is bascially the "main" of the project. This houses
 * the login screen: Guest Login and Manager Login and also Signup Login Signup Login opens a new
 * window where you can can create a new account, firsttime users do this. To login to guest, you
 * have to enter a Username and Password, it has to match a created Account first. Usernames aren't
 * case sensitive but passwords are.
 *
 * For Manager: The default Login in U: Nicolo P: Martina Same case sensitivity, For extra
 * functionality, you can show/hide passwords. There is also a toggle for show Password Field on
 * both Manager and Guest. This code is done by having a password txtfield and a regular txtfield in
 * the same place and making thme hidden. See more in the initialize portion.
 *
 * There is also a function that will populate the username Field automically if the user creates a
 * new Account
 */


/**
 * @author ggrab
 */

public class LoginMenuController extends Controller implements Initializable {

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
  private PasswordField passwordFieldManager;
  @FXML
  private Button buttonExit1;
  @FXML
  private CheckBox checkBox2;
  @FXML
  private TextField textFieldForPassword;
  @FXML
  private TextField textFieldForPasswordManager;

  @FXML
  void handleButtonLogin(ActionEvent event) {

    String username = textfieldUsername.getText();
    String password = passwordField.getText();

    UserFileUtilities.Initialize();
    Boolean checkValid = UserFileUtilities.checkIfPairExists(username, password);

    if (checkValid) {
      try {
        Guest guest = new Guest(username, password);
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

  @FXML
  void handleButtonLoginManager(ActionEvent event) {
    String username = textFieldUsernameManager.getText();
    String password = textFieldForPasswordManager.getText();

    UserFileUtilities.Initialize();
    Boolean checkValid = UserFileUtilities.checkIfPairExists(username, password);

    if (checkValid) {
      Global.currentScene = buttonExit.getScene();
      new Global().displayPopUpWindow("Login Successful!");
      openManagerMenu();
    } else {
      Global.currentScene = buttonExit.getScene();
      new Global().displayPopUpWindow("Login Unsuccessful!");
    }

  }

  @FXML
  void handleButtonCreate(ActionEvent event) {

    Global.currentScene = buttonExit1
        .getScene();//Sets the current Scene for Global Class, so it know
    //what window to close
    new Global().openNewWindow(WindowLocation.SIGNUP); //Open new SignupScreen window

  }

  @FXML
  void handleButtonExit(ActionEvent event) {
    /**
     * Exit Program, closes window
     */
    System.out.println("Goodbye");
    Stage stageExit = (Stage) buttonExit.getScene().getWindow();
    stageExit.close();
  }

  private void updateGlobal() {
    /**
     * UpdateGlobal is a method to change the Global class's values
     * ArrayLists seem to update automically if you make a copy of them
     * but Strings and Ints dont, so before moving to a new window you need to update Global Values
     *
     */
    Global.currentGuestLoggedIn = currentGuest; //Needed so GuestMenu knows who logged in.
  }

  public void openGuestMenu(Guest g1) throws IOException {
    currentGuest = g1;
    updateGlobal();
    Global.currentScene = buttonExit1.getScene();

    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
  }

  private void openManagerMenu() {
    Global.currentScene = buttonExit1.getScene();//

    new Global().openNewWindow(WindowLocation.MANAGERMENU);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /**
     * This method is called first when LoginMenu IS opened.
     * It will set the properties for passwordFields to able to show/hide
     * In SceneBuilder I made a textfield adn a password field on the same spot, the user won't notice
     * the difference.
     *
     *
     * And also it will take the data of Global class so data stays consistent between Controllers.
     */

    this.currentGuest = Global.currentGuestLoggedIn;

    if (currentGuest != null) {
      //if currentGuest isn't null, then fill the usernameField with the current's guest username
      //for more user-friendly design
      textfieldUsername.setText(currentGuest.getUserName());

    }

    textFieldForPassword.setManaged(false);
    textFieldForPassword.setVisible(false);//Hide regular textfield

    textFieldForPasswordManager.setManaged(false);
    textFieldForPasswordManager.setVisible(false);//Hide regular textfield

    textFieldForPasswordManager.setFocusTraversable(false); //Tab wont select second textbox
    textFieldForPasswordManager.focusTraversableProperty()
        .bind(checkBox2.selectedProperty()); //Bind property to checkbox
    passwordFieldManager.focusTraversableProperty()
        .bind(checkBox2.selectedProperty().not()); //Make focusTraanableProperty the opposite

    textFieldForPasswordManager.managedProperty().bind(
        checkBox2.selectedProperty());// TextField's setManageProperty will be changed by CheckBox
    textFieldForPasswordManager.visibleProperty().bind(
        checkBox2.selectedProperty());// TextField's setVisibleProperty will be changed by CheckBox

    passwordFieldManager.managedProperty()
        .bind(checkBox2.selectedProperty().not());//Same as above but oppsite?
    passwordFieldManager.visibleProperty().bind(checkBox2.selectedProperty().not());

    // Bind the textField and passwordField text values bidirectionally.
    textFieldForPasswordManager.textProperty()
        .bindBidirectional(passwordFieldManager.textProperty()); //MAkes two textfie

    // CheckBox checkBox = new CheckBox("Show/Hide password");//Create checkbox that will toggle

    // Bind properties. Toggle textField and passwordField
    // visibility and managability properties mutually when checkbox's state is changed.
    // Because we want to display only one component (textField or passwordField)
    // on the scene at a time.
    textFieldForPassword.managedProperty().bind(
        checkBox.selectedProperty());// TextField's setManageProperty will be changed by CheckBox
    textFieldForPassword.visibleProperty().bind(
        checkBox.selectedProperty());// TextField's setVisibleProperty will be changed by CheckBox

    passwordField.managedProperty()
        .bind(checkBox.selectedProperty().not());//Same as above but oppsite?
    passwordField.visibleProperty().bind(checkBox.selectedProperty().not());

    // Bind the textField and passwordField text values bidirectionally.
    textFieldForPassword.textProperty().bindBidirectional(
        passwordField.textProperty()); //MAkes two textfields share share same input
    //If this code isn't there the two textfields are seperate

  }


}