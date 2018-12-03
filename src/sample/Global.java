package sample;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.GuestMenu.GuestRoomController;

public class Global {

  /**
   * This class stores all our data, that is shared by all the
   * controllers. By using this we won't have to use a method to pass fields through each
   * controller.
   * Global also holds enum for WindowLocation will stores url for the FXML files. It makes it
   * easier to get the url just incase the files change location.
   *
   */
  //Used in ManagerHomeController
  static public ArrayList<Room> roomList;
  static public Room selectedRoom;

  //Used in GuestRoomController and PaymentWindowController
  static public LocalDate checkInDate;
  static public LocalDate checkOutDate;
  static public String roomInfo;

  //Used in ManagerHomeController
  static public ArrayList<Employee> empList;
  static public Employee selectedEmp;
  static public ResortEvent selectedEvent;
  static public ArrayList<ResortEvent> eventList;


  //Used in signup window
  static public ArrayList<String> usernameList = new ArrayList<>(); //ArrayList of username Fields
  static public ArrayList<String> passwordList = new ArrayList<>();//Array:ist of password Field
  static public ArrayList<Guest> guestList = new ArrayList<>();//Arraylist of Guests
  static public List<Room> rooms = new ArrayList<>();//Arraylist of rooms that Manager/Guest Menu's use
  static public Guest currentGuestLoggedIn; //LoginMenuController keeps track of guest to send to GuestMenu
  static public Scene currentScene;
  static public String currentTitle;


  /*
  Enum for easy reference for window fmxl locations
   */
  public enum WindowLocation {

    LOGINMENU("/sample/LoginMenu/LoginMenu.fxml"),
    SIGNUP("/sample/LoginMenu/SignupScreen.fxml"),
    MANAGERMENU("/sample/ManagerMenu/NewManagerHome.fxml"),
    ACCOUNTWINDOW("/sample/GuestMenu/GuestAccountWindow.fxml"),
    GUESTMENUHOME("/sample/GuestMenu/GuestRoomTest1.fxml"),
    PAYMENT("/sample/GuestMenu/PaymentWindow.fxml");
    private String url;


    WindowLocation(String urlL) {
      url = urlL;
    }

    public String getLocation() {

      return url;
    }


  }

  /*
    Method used to open new window
    Must set Global currentScene in the current controller file so it can close the window
   * First Close the Window
   * Then we get the url of the next FXML/Window from the global WindowLocation Enum
   * Open the new FXML
   * When the new window is opened the data is stored.
   *
   * Some window calls are different because some of them have have thier Controllers linked to
   * FXML file, and other dont
   * In this case GUESTMENUHOME's controller is linked to FXML.
   */
  public void openNewWindow(WindowLocation window) {

    Stage stage = (Stage) currentScene.getWindow(); //Ask currentScene what window it is.
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();
    //Using Global's Enum named WindowLocation get the Url for the EnumType, uses the enum sent by parameter
    String url = window.getLocation();
    //load the url you just acquired.
    Loader.setLocation(getClass().getResource(url));
    try {
      // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
      Loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = Loader.getRoot();
    stage = new Stage();

    stage.setTitle(currentTitle);
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window

  }


  /*
  Method used to display small pop up window
   */
  public void displayPopUpWindow(String message) {
    final Stage myDialog = new Stage();
    /*
    Window modality makes it so user cannot click outside the pop up window
     */
    myDialog.initModality(Modality.WINDOW_MODAL);
    Button okButton = new Button("Ok");
    okButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent arg0) {
        myDialog.close();
      }

    });

    Scene myDialogScene = new Scene(VBoxBuilder.create()
        .children(new Text(message), okButton)
        .alignment(Pos.CENTER)
        .padding(new Insets(10))
        .build());
    myDialog.initOwner(currentScene.getWindow()); //Set this to the parent of the opup window
    myDialog.setScene(myDialogScene);
    myDialog.showAndWait(); //USE showAndWait to wait for the popto close
    //REgular wait will ignore modality and will call second window regardlessly.

  }

}
