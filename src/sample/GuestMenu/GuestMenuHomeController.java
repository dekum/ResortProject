package sample.GuestMenu;


import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.Room;

/**
 *
 * GuestMenuHomeController.java
 * This is the controller for GuestMenuHome.fxml
 * Guest Menu home is the frist screen the Guest sees when they login
 * From here they can: View Rooms,View Events, See Calender, Chhange Settings, Exit
 *
 *
 * Notes: For FXML if you are using a gridpane put a PAne in each grid to allow for more customization.
 */

public class GuestMenuHomeController implements Initializable {
  private ArrayList<String> usernameList;
  private ArrayList<String> passwordList;
  private ArrayList<Guest> guestList;
  private Guest currentGuest;
  private Room roomClickedOn;
  private int daysStaying;
  private boolean initializedRooms=false;
  private ObservableList<Employee> data;
  protected List<Room> rooms = new ArrayList<>();
@FXML
Button buttonBookRoom,buttonEvent,buttonAccount,buttonSettings,buttonExit,buttonCalender ;

@FXML
void handleExit(ActionEvent event){
  Stage stage = (Stage) buttonExit.getScene().getWindow(); //Asks a object in the window to store it's WindowID
  stage.close(); //Close current Window

  //Loads FXML Loader
  FXMLLoader Loader = new FXMLLoader();
  //Using Global's Enum named WindowLocation get the Url for the EnumType

  //load the url you just acquired.
  String url = WindowLocation.LOGINMENU.getLocation();
  Loader.setLocation(getClass().getResource(url));
  try {
    // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
    Loader.load(); //Loads
  }catch ( IOException ex){
    Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null ,ex);

  }

  Parent p = Loader.getRoot();
  stage = new Stage();
  stage.setTitle("Login");
  stage.setScene(new Scene(p));
  stage.show(); //Opens new Window
}

  @FXML
  public void handleBookRoom(ActionEvent event){

    /**
     * Opening Guest controller.
     *First you exit this current Stage
     * Then you load a new controller object
     * Get the url from Global.windowLocation.
     * Set the controller.
     * Open the new window
     *
     */
    Stage stageExit = (Stage) buttonBookRoom.getScene().getWindow(); //Select an object in this window
    stageExit.close(); //close current window
    GuestRoomController guestController = new GuestRoomController(); // Create object of new Controller


    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();
    Loader.setLocation(getClass().getResource(WindowLocation.GUESTMENUROOM.getLocation())); //Call new window

    try {
      //Loader.setController(guestController); //Set Controller as guestMenuRoom doesn't have one linked to fxml.
      Loader.load(); //Loads
    }catch ( IOException ex){
      Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null ,ex);

    }
    //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
    // display.setText(name_Text,email_Text); //using displaytextcontroller's method
    //LoginMenuController storeFields =Loader.getController();
//    storeFields.storeVariables1(); //This method will stoer the variables


    Parent p = Loader.getRoot();
    Stage stage = new Stage();

    stage.setTitle("View Rooms -"+ Global.currentGuestLoggedIn.getUserName()); //set title
    stage.setScene(new Scene(p));
    stage.show();//Open new window

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.usernameList = Global.usernameList;
    this.passwordList = Global.passwordList;
    this.guestList = Global.guestList;
    this.rooms = Global.rooms;
    this.data = Global.data;
    this.currentGuest= Global.currentGuestLoggedIn;


  }

  public void handleSettings(ActionEvent event) {
    /**
     *
     * Open Account Window
      */
    Global.currentScene = buttonExit.getScene();//

    new Global().openNewWindow(WindowLocation.GUESTMENUACCOUNT);


  }

  public void handleAccount(ActionEvent event) {
  }

  public void handleCalender(ActionEvent event) {
  }
  public void handleEventButton(ActionEvent event){
    Global.currentScene = buttonExit.getScene();//

    new Global().openNewWindow(WindowLocation.EVENTMENUHOME);


  }
}
