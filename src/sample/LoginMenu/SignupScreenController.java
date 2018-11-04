package sample.LoginMenu;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.GuestMenu.GuestMenuController;
import sample.Room;

/**
 * ScreenupScreenController.java
 * This will allow a user to create a new Guest Account
 * They can input First/Last Name, username password, Date of birth.
 * The Program will verify valid inputs for username and password with rules displayed on the screen
 * If the inputs are valid a new guest account is created and the user can login with it.
 *
 *
 */

public class SignupScreenController implements Initializable {
  private ArrayList<String> usernameList;
  // private ArrayList<String> usernameList =super.getUsernameList();
  public List<Room> rooms;
  private ArrayList<String> passwordList;
  private ArrayList<Guest> guestList;
  private Guest currentGuest;
  private Room roomClickedOn;
  private int daysStaying;
  private boolean initializedRooms=false;
  private ObservableList<Employee> data;
  private final String pattern = "yyyy-MM-dd";

  public ArrayList<String> getPasswordList() {
    return passwordList;
  }

  public void setPasswordList(ArrayList<String> passwordList) {
    this.passwordList = passwordList;
  }

  public ArrayList<Guest> getGuestList() {
    return guestList;
  }

  public void setGuestList(ArrayList<Guest> guestList) {
    this.guestList = guestList;
  }

  public ArrayList<String> getUsernameList() {
    return usernameList;
  }

  public void setUsernameList(ArrayList<String> usernameList) {
    this.usernameList = usernameList;
  }

  @FXML
  Button buttonSubmit, buttonExit;
  @FXML
  TextField textFieldUserName, textFieldPassword, textFieldPasswordConfirm,
  textFieldFirstName,textFieldLastName;

  @FXML
  DatePicker datePickerDOB;
  @FXML
  Label labelSubmitSuccess;

  public void createGuest(String n, String pw){


  }

  @FXML
  void handleSubmit(ActionEvent event){
    String userName, password,firstName,lastName,dob;
    userName = textFieldUserName.getText();
    password = textFieldPassword.getText();
    firstName = textFieldFirstName.getText();
    lastName = textFieldLastName.getText();


    //Calls method to create new Guest option nothing special

    Boolean isValid = ValidateInputs();
    if (isValid){
      //if true then create a new guest
      dob = datePickerDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      Guest createGuest = new Guest(userName,password,firstName,lastName,dob);
      getUsernameList().add(userName);// No need for method this already adds it in
      getPasswordList().add(password);
      getGuestList().add(createGuest);
      labelSubmitSuccess.setText("Success");
      System.out.println("UserName: "+userName +"\npw: "+password+"\nFname"+firstName + "\nLastname:"+lastName
          +"\nDOB:"+dob);
    }
    else {
      System.out.println("WRong");

    }





  }

  private Boolean ValidateInputs() {
    String userName, password,firstName,lastName,dob;
    userName = textFieldUserName.getText();
    password = textFieldPassword.getText();
    String passwordConfirm = textFieldPasswordConfirm.getText();
    firstName = textFieldFirstName.getText();
    lastName = textFieldLastName.getText();
   // dob = datePickerDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    Boolean allInputsValid = true;

    try{
      dob = datePickerDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }catch (java.lang.RuntimeException exception){
      datePickerDOB.requestFocus();
      labelSubmitSuccess.setText("Date is Invalid");
      System.out.println("Invalid date yo");
      allInputsValid=false;
    }


    boolean hasDigit = password.matches(".*\\d+.*");
    if (!hasDigit){
      labelSubmitSuccess.setText("Password Invalid");
      //password doesn't have a digit
      textFieldPassword.requestFocus();
      textFieldPassword.selectAll();
      allInputsValid = false;
    }
    if (password.length() <6){
      labelSubmitSuccess.setText("Password Invalid");
      //password doesnt have enough characters
      textFieldPassword.requestFocus();
      textFieldPassword.selectAll();
      allInputsValid = false;

    }else if (!password.equals(passwordConfirm))
    {
      labelSubmitSuccess.setText("Passwords don't match");
      //The password and the ConfirmPassword boxes dont match
      textFieldPassword.requestFocus();
      textFieldPassword.selectAll();
      allInputsValid = false;
    }


    //Check for duplicate username
    for (String uname: Global.usernameList
    ) {
      if (uname.equalsIgnoreCase(userName)){
        labelSubmitSuccess.setText("Username already taken");
        //If the username the Guest enetered is already taken thne it cant be used against
        //not case sensitive so there can't be a jackjones and a Jackjones
        allInputsValid=false;



      }

    }
    if (userName.length()<6){
      //if username is less than 6 it's invalid
      labelSubmitSuccess.setText("Username Invalid");
      textFieldUserName.requestFocus();
      textFieldFirstName.selectAll();
      allInputsValid =false;

    }

return allInputsValid;
  }

  @FXML
  void handleExit(ActionEvent event){
    Stage stage = (Stage) buttonExit.getScene().getWindow(); //Asks a object in the window to store it's WindowID
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();
    //Using Global's Enum named WindowLocation get the Url for the EnumType
    String url = WindowLocation.LOGINMENU.getLocation();
    //load the url you just acquired.
    Loader.setLocation(getClass().getResource(url));
    try {
      // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
      Loader.load(); //Loads
    }catch ( IOException ex){
      Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

    }

    Parent p = Loader.getRoot();
    stage = new Stage();
    stage.setTitle("Login");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window


  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.usernameList = Global.usernameList;
    this.passwordList = Global.passwordList;
    this.guestList = Global.guestList;
    this.rooms = Global.rooms;
    this.data = Global.data;
    datePickerDOB.setValue(LocalDate.now().minusYears(18)); //If you are going touse a daFactor need to have the datepicker have a claue first
    //Otherwise it will a nullopinter error
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
                    datePickerDOB.getValue())
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
