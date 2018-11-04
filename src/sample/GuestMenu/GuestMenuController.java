/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.GuestMenu;
import  javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Controller;
import sample.Employee;
import sample.Global.WindowLocation;
import sample.LoginMenu.LoginMenuController;
import sample.Global;
import sample.Guest;
import sample.Room;
import sample.Room.RoomCellFactory;


/**
 *
 *Notes:ppEtit
 *GuestMenuController.java NEED TO CHNAGE TO GUESTROOMMENU
 *
 * This controller controls the GuestMenu.fxml
 * This allows the gues to:
 * View Room List.
 * Book Room.
 *
 * User can click through list of rooms, the window will update the information  about the room.
 * What updates are: Image(Not yet), Availability, price per room.
 * If room is available, then user can enter daysStaying and book room
 *
 * Otherwise the Guest can logout, and their information is saved.
 *
 * The program is coded so it know what room is selected when booking room.
 * All room information can be seen in Manager Window.
 *
 * @author ggraber7402
 */
public class GuestMenuController extends Controller implements Initializable {
  private ArrayList<String> usernameList;
// private ArrayList<String> usernameList =super.getUsernameList();
  private ArrayList<String> passwordList;
  private ArrayList<Guest> guestList;
  private Guest currentGuest;
  private Room roomClickedOn;
  private int daysStaying;
  private boolean initializedRooms=false;
  private ObservableList<Employee> data;







  @FXML
  private Label labelTotalPriceShow,paymentSucces,labelPaymentSuccess;

  @FXML
  private Button buttonConfirmPayment;

  @FXML
  private TabPane tabPane;
@FXML
private Tab tabPayment;
    @FXML
    private Label labelAvailable;
  @FXML
  private Label labelPricePerDay;
  @FXML
  private TextField textFieldDaysStaying;
  @FXML
  private ImageView imageViewRoom;


    @FXML
    private Button signoutButton;
    @FXML
    private Button bookRoombutton;

@FXML
private ListView roomListView;
@FXML
private Tab tabViewRoomTab;



  @FXML
  protected List<Room> rooms = new ArrayList<>();
  @FXML
  protected ListProperty<Room> listProperty = new SimpleListProperty<>();



    @FXML
    public void handleBookRoom(ActionEvent event){
      /**
       * Called by BookRoom Button
       * Checks if user inputted DaysStayed textbox
       * if the input is invalid the Payment button is disabled and user has to go back
       * otherwise, it will allow user to book room.
       *
       */
      Boolean success= true;
      tabPane.getSelectionModel().select(tabPayment); //Opens tabPayment tab.
      try{
        daysStaying =Integer.parseInt(textFieldDaysStaying.getText()); //Check if valid
      }catch (NumberFormatException exception){ //Cannot create a Integer from the input, user needs to try again
        labelTotalPriceShow.setText("Error, Invalid Input");
        buttonConfirmPayment.setDisable(true);//Disables Confirm Payment Button
        success= false;
      }
      if (success==true){ //Valid Input, run this
        tabPayment.setDisable(false);
        buttonConfirmPayment.setDisable(false); //Enables Confirm Payment Button
        System.out.println("here");
        labelTotalPriceShow.setText(Double.toString(roomClickedOn.getPrice() * daysStaying)); //total PRice
        
      }





  }
@FXML
void tabClicked(Event ev) {
  /**
   * Checks if tab is clicked
   */
  if (tabViewRoomTab.isSelected()) {
//    buttonConfirmPayment.setDisable(true);
    //System.out.println("TABLCICK");
    try{ //if ViewRoomTab is clicked, then tabPayment is disabled
      tabPayment.setDisable(true);
    }catch (NullPointerException exception){

    }
    try{
      displayRoomFeatures(roomClickedOn);
    }catch (NullPointerException exception){

    }



  }
}

    @FXML
    public void handleSignout(ActionEvent event){
//        try {
//            Stage stage = (Stage) signoutButton.getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginMenu.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
      /**
       * Opening the FXMLDOCUMENT CONTROLLER is slighty different than opening other controllers
       * The controller is called after the fxml is loaded.
       * The data is stored via the storeVariables method.
       * It just works, dont ask me.
       */


      Stage stageExit = (Stage) signoutButton.getScene().getWindow();
            stageExit.close(); //close current window
      FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource(WindowLocation.LOGINMENU.getLocation())); //get FXML path
      try {
        Loader.load();
      }catch ( IOException ex){
        Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

      }
      //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
      // display.setText(name_Text,email_Text); //using displaytextcontroller's method
      LoginMenuController storeFields =Loader.getController();
      storeFields.storeVariables1(); //This method will stoer the variables


      Parent p = Loader.getRoot();
      Stage stage = new Stage();
      stage.setTitle("Login Menu"); //set title
      stage.setScene(new Scene(p));
      stage.show();//Open new window



    }

    public GuestMenuController(){
      /**
       * Happens before initalize class, this this program atleast.
       * In others it's the oppopsite
       * This stores the variables from other controllers so it all matches.
       */
      //System.out.println("In Constructor");
      this.usernameList = Global.usernameList;
      this.passwordList = Global.passwordList;
      this.guestList = Global.guestList;
      this.rooms = Global.rooms;
      this.data = Global.data;
      this.currentGuest= Global.currentGuestLoggedIn;


    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      /**
       * Starts after constructor in this program
       * This starts before the window is shown
       * It will create a default rooms, if it hasnt' been created yet
       * It also gives the ListView it's mouseClick listener
       *
       *
       */
      System.out.println("HERE IN INTAILIZE");
        bookRoombutton.setDisable(true);
      if (rooms.isEmpty())
      {
        //Make Default room list
        //System.out.println("ROOM OS EMPTY \n\n\n");
        rooms.add(new Room("Room 1A",false,200));
        rooms.get(0).setAvailable(false);
        rooms.get(0).setPictureUrl("sample/Pictures/Room1A.jpg"); //Set Picture URL
        rooms.get(0).setOccupiedGuest(new Guest("BruceWayne","batman"));//John Doe occupies room 1A
        rooms.add(new Room("Room 2A",true,300));
        rooms.get(1).setAvailable(true);
        rooms.get(1).setPictureUrl("sample/Pictures/Room2A.jpg"); //Set Picutre URL
        rooms.add(new Room("Room 3A",false,500));
        rooms.get(2).setAvailable(false);
        rooms.get(2).setOccupiedGuest(new Guest("ClarkKent","superman"));//John Doe occupies room 1A
        rooms.get(2).setPictureUrl("sample/Pictures/Room3A.jpg");
      }

      /**
       * To make a arraylist exist in ListPane first you
       * Create Arraylist
       * Create a ItemProperty
       * Then you bind the arraylist to the Listpane via the item Properrty
       */
      tabPayment.setDisable(true);
      roomListView.itemsProperty().bind(listProperty);

      listProperty.set(FXCollections.observableArrayList(rooms));
      roomListView.setCellFactory(new RoomCellFactory()); //Cell Factory allows formatting
      roomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

        /**
         * Allows us to know what item of the listView is being seleceted
         * this also called a method to update the window is the new informoation
         *
         * @param event
         */
        @Override
        public void handle(MouseEvent event) {
          /**
           * We can only call the Object, not the class
           * So by comparing toString()'s we know if it matches, then it's the same Object
           */
          String roomSelected = roomListView.getSelectionModel().getSelectedItem().toString();
          //System.out.println("To string:"+roomSelected.toString());


          for (Room r: rooms
          ) {
            if (r.toString().equalsIgnoreCase(roomSelected)){
              roomClickedOn= r; //Same ToString so we can set the object to it.
              System.out.println("Room: "+roomClickedOn.getName());
              //Same toString same, set currentRoom to r

            }
           // System.out.println(r.getName());
          }
          System.out.println("Here IS : "+roomSelected);
         // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

          //Check Room Avability.
          /**
           * Call displayRoomFeatures to update Window
           */
          displayRoomFeatures(roomClickedOn);

        }

      });

    }
@FXML
  private void displayRoomFeatures(Room roomClickedOn) {
      /**
       * This method will update the window with information about Room
       * The program know and send the current Room clicked on
       */
      if (roomClickedOn.getAvailable()){
        //true
        labelAvailable.setText("Available");
        bookRoombutton.setDisable(false);

      }else
      {
        labelAvailable.setText("Not Available");
        bookRoombutton.setDisable(true);

      }
      labelPricePerDay.setText(Double.toString(roomClickedOn.getPrice()));
 Image image = new Image(roomClickedOn.getPictureUrl());
  imageViewRoom.setImage(image);
 // Image image = new Image("sample/Room2A.jpg"); THIS WORKS
  //Image image = new Image(new File("za.png").toURI().toString());

//  ImageView iv2 = new ImageView();
//  iv2.setImage(image);
//  iv2.setFitWidth(100);
//  iv2.setPreserveRatio(true);
//  iv2.setSmooth(true);
//  iv2.setCache(true);




}

  public void handleConfirmPayment(ActionEvent event) {
    /**
     * This method adds Guest to Room
     */
    labelPaymentSuccess.setText("Payment Success");
    roomClickedOn.setAvailable(false);
    roomClickedOn.setOccupiedGuest(currentGuest);
    System.out.println("Room:"+roomClickedOn.getName() +" Availe:"+roomClickedOn.getAvailable() + " guest: "+
        roomClickedOn.getOccupiedGuest().getUserName());

    Stage currentStage = (Stage) signoutButton.getScene().getWindow();
    currentStage.setTitle("Guest Login: "+currentGuest.getUserName()+" - "+roomClickedOn.getName() );
    roomClickedOn.setDaysStaying(daysStaying);


    
  }

}
