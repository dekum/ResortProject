package sample.LoginMenu;


import javafx.scene.control.CheckBox;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Room;
import sample.UserFileUtilities;

/**
 * Screen for guest and manager sign up page
 * First/Last Name, username password, Date of birth. The class will verify valid inputs for
 * username and password with rules displayed on the screen If the inputs are valid a new guest
 * account is created and the user can login with it. When user successfully makes new a account,
 * username field in LoginMenu is automatically filled.
 */

public class SignupScreenController implements Initializable {

  public List<Room> rooms;
  @FXML
  Button buttonSubmit, buttonExit;
  @FXML
  TextField textFieldUserName,
      textFieldFirstName, textFieldLastName,
      textFieldForPassword, textFieldForPasswordConfirm;
  @FXML
  DatePicker datePickerDOB;
  @FXML
  Label labelSubmitSuccess;
  @FXML
  PasswordField passwordField, passwordFieldPasswordConfirm;
  @FXML
  CheckBox checkBox1, checkBox2;
  @FXML
  Text textUserNameRule, textPasswordRule;

  /*
  Method called when "Submit" button is pressed
  Checks for valid input, creates new user
  First name, last name, and DOB are not saved anywhere
   */
  @FXML
  void handleSubmit(ActionEvent event) {
    String userName, password;
    userName = textFieldUserName.getText();
    password = passwordField.getText();

    Boolean isValid = ValidateInputs();
    if (isValid) {
      /*
       * Using UserFileUtilities class to access file in library
       * Must use initialize() method in order to avoid NullPointerException error
       */
      UserFileUtilities.Initialize();
      try {
        UserFileUtilities.addUser(userName, password);
      } catch (IOException e) {
        System.out.println("Error");
      }

      labelSubmitSuccess.setText("Success");
      Global.currentScene = buttonExit.getScene();
      new Global().displayPopUpWindow("Signup Complete! Welcome to Ruby Resort");
      handleExit(event);


    }

  }

  /*
  Validates user inputs
  Checks for necessary username and password inputs
   */
  private Boolean ValidateInputs() {
    String userName, password, firstName, lastName, dob;
    userName = textFieldUserName.getText();
    password = passwordField.getText();
    String passwordConfirm = passwordFieldPasswordConfirm.getText();
    firstName = textFieldFirstName.getText();
    lastName = textFieldLastName.getText();
    textUserNameRule.setFill(Color.BLACK);
    textPasswordRule.setFill(Color.BLACK);
    Boolean allInputsValid = true;

    try {
      dob = datePickerDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    } catch (java.lang.RuntimeException exception) {
      datePickerDOB.requestFocus();
      labelSubmitSuccess.setText("Date is Invalid");
      allInputsValid = false;
    }

    if (lastName.isEmpty()) {
      textFieldLastName.requestFocus();
      labelSubmitSuccess.setText("Enter Last Name");
      allInputsValid = false;
    }

    if (firstName.isEmpty()) {
      textFieldFirstName.requestFocus();
      labelSubmitSuccess.setText("Enter First Name");
      allInputsValid = false;
    }

    boolean hasDigit = password.matches(".*\\d+.*");
    if (!hasDigit) {
      labelSubmitSuccess.setText("Password Invalid");
      textUserNameRule.setFill(Color.BLACK);
      textPasswordRule.setFill(Color.RED);
      textFieldForPassword
          .requestFocus();//Making sure one of the textBoxes for the first password is focused on
      textFieldForPassword.selectAll();
      //password doesn't have a digit
      passwordField.requestFocus();
      passwordField.selectAll();

      allInputsValid = false;
    }
    if (password.length() < 5) {
      labelSubmitSuccess.setText("Password Invalid, See Rules");
      //password doesnt have enough characters
      passwordField.requestFocus();
      textFieldForPassword
          .requestFocus();//Making sure one of the textBoxes for the first password is focused on
      textFieldForPassword.selectAll();
      passwordField.selectAll();

      textUserNameRule.setFill(Color.BLACK);
      textPasswordRule.setFill(Color.RED);
      allInputsValid = false;

    } else if (!password.equals(passwordConfirm)) {
      labelSubmitSuccess.setText("Passwords don't match");
      //The password and the ConfirmPassword boxes dont match
      passwordField.requestFocus();
      textFieldForPassword
          .requestFocus();//Making sure one of the textBoxes for the first password is focused on

      textFieldForPassword.selectAll();

      passwordField.selectAll();
      textUserNameRule.setFill(Color.BLACK);
      textPasswordRule.setFill(Color.RED);

      allInputsValid = false;
    }

    //Check for duplicate username
    for (String uname : Global.usernameList
    ) {
      if (uname.equalsIgnoreCase(userName)) {
        labelSubmitSuccess.setText("Username already taken");
        //If the username the Guest enetered is already taken thne it cant be used against
        //not case sensitive so there can't be a jackjones and a Jackjones
        textPasswordRule.setFill(Color.BLACK);
        textUserNameRule.setFill(Color.RED);
        allInputsValid = false;


      }

    }
    if (userName.length() < 5) {
      //if username is less than 6 it's invalid
      labelSubmitSuccess.setText("Username Invalid");
      textPasswordRule.setFill(Color.BLACK);
      textUserNameRule.setFill(Color.RED);
      textFieldUserName.requestFocus();

      allInputsValid = false;

    }

    return allInputsValid;
  }

  /*
  Takes user back to login screen
   */
  @FXML
  void handleExit(ActionEvent event) {
    Global.currentTitle="Ruby Resort: Login Window";
    Global.currentScene = buttonExit.getScene();//

    new Global().openNewWindow(WindowLocation.LOGINMENU);


  }

  /*
  Initializes text fields to work with password fields
  Makes it so user cannot select a date that would make them less than 18 years old
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    TextField textFieldForPasswordManager = textFieldForPasswordConfirm;
    PasswordField passwordFieldManager = passwordFieldPasswordConfirm;

    textFieldForPassword.setManaged(false);
    textFieldForPassword.setVisible(false);//Hide regular textfield

    textFieldForPasswordManager.setManaged(false);
    textFieldForPasswordManager.setVisible(false);//Hide regular textfield

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

    textFieldForPassword.managedProperty().bind(
        checkBox1.selectedProperty());// TextField's setManageProperty will be changed by CheckBox
    textFieldForPassword.visibleProperty().bind(
        checkBox1.selectedProperty());// TextField's setVisibleProperty will be changed by CheckBox

    passwordField.managedProperty()
        .bind(checkBox1.selectedProperty().not());//Same as above but oppsite?
    passwordField.visibleProperty().bind(checkBox1.selectedProperty().not());

    // Bind the textField and passwordField text values bidirectionally.
    textFieldForPassword.textProperty().bindBidirectional(
        passwordField.textProperty()); //MAkes two textfields share share same input
    //If this code isn't there the two textfields are seperate

    datePickerDOB.setValue(LocalDate.now().minusYears(
        18)); //If you are going touse a daFactor need to have the datepicker have a claue first
    //Otherwise it will a nullopinter error
    LocalDate earliestDate = datePickerDOB.getValue();
    final Callback<DatePicker, DateCell> dayCellFactory =
        new Callback<DatePicker, DateCell>() {
          @Override
          public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
              @Override
              public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isAfter(
                    //    datePickerDOB.getValue().minusYears(18)) I don't need a minus 18 here since it's default value already did that
                    earliestDate)
                ) {
                  setDisable(true);
                  setStyle("-fx-background-color: #ffc0cb;");
                }


              }
            };
          }
        };
    datePickerDOB.setDayCellFactory(dayCellFactory);


  }
}
