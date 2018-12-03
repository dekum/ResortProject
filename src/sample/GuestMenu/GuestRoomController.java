/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.GuestMenu;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.Global.WindowLocation;
import sample.Global;
import sample.ResortEvent;
import sample.Room;


  /**
   * Main guest menu
   * User must select check in and check out date, then hit view rooms button
   * Rooms are displayed on TableView and description is shown when clicked on
   * Events are displayed on TableView on the right
   * Once a room is chosen, user can book room which takes them to Payment screen
   **/
public class GuestRoomController {

  @FXML
  private Label labelPricePerDay;
  @FXML
  private ImageView imageViewRoom;
  @FXML
  private Label roomPreviewLabel;
  @FXML
  private Label roomPriceLabel;
  @FXML
  private Button signoutButton;
  @FXML
  private Button bookRoombutton;
  @FXML
  private DatePicker checkInDate;
  @FXML
  private DatePicker checkOutDate;
  @FXML
  private TableView<Room> roomTableView;
  @FXML
  private TableColumn<Room, String> roomTableColumn;
  @FXML
  private TableView<ResortEvent> eventTableView;
  @FXML
  private TableColumn<ResortEvent, String> eventTableColumn;
  @FXML
  private TableColumn<ResortEvent, String> eventDateColumn;
  @FXML
  Button buttonPaymentHistory;

  /*
  Method called when window is opened
  Gets the ArrayList for events from Global class
  Creates an ObservableList for display in TableView
   */
  @FXML
  void initialize() {


    ObservableList<ResortEvent> event2 = FXCollections.observableArrayList(Global.eventList);
    eventTableColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    eventDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
    eventTableView.setItems(event2);

    //Set to invisible until user clicks "view rooms" button
    roomTableView.setVisible(false);
    roomPreviewLabel.setVisible(false);
    roomPriceLabel.setVisible(false);
    bookRoombutton.setVisible(false);

    //Check in date set to current date
    checkInDate.setValue(LocalDate.now());

    //Payment history button not available until user books a room
    if (Global.currentGuestLoggedIn.getRoomRented() ==null){

      buttonPaymentHistory.setVisible(false);

    }else{
      buttonPaymentHistory.setVisible(true);

    }



    /*
    Code used to set available DatePicker boxes
    Doesn't allow you to select before current date for check in
    Once check in is selected, check out must be at least a day after
     */
    final Callback<DatePicker, DateCell> dayCellFactory =
        new Callback<DatePicker, DateCell>() {
          @Override
          public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
              @Override
              public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                //Disables dates for checkout that are before the checkin to avoid errors
                if (item.isBefore(
                    checkInDate.getValue().plusDays(1))
                ) {
                  setDisable(true);
                  setStyle("-fx-background-color: #ffc0cb;");
                }
                long p = ChronoUnit.DAYS.between(
                    checkInDate.getValue(), item
                );
                setTooltip(new Tooltip(
                    "The even will last : " + p + " days")
                );



              }
            };
          }
        };
    final Callback<DatePicker, DateCell> dayCellFactory2 =
        new Callback<DatePicker, DateCell>() {
          @Override
          public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
              @Override
              public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (checkOutDate.getValue().isBefore(checkInDate.getValue()) ){
                  System.out.println("...K");
                  //checkInDatePicker.setFocusTraversable(true);
                  checkOutDate.requestFocus();
                  //Maybe set an error Boolean?


                }
                if (item.isBefore(
                  LocalDate.now()

                )) {
                  setDisable(true);
                  setStyle("-fx-background-color: rgba(255,5,21,0.69);");
                }



              }
            };
          }
        };


    checkOutDate.setDayCellFactory(dayCellFactory);
    checkInDate.setDayCellFactory(dayCellFactory2);
    checkOutDate.setValue(checkInDate.getValue().plusDays(1));



  }

  /*
  Method called when user clicks "view room" button
  Gets the ArrayList for rooms from Global class
  Creates an ObservableList for display in TableView
   */
  @FXML
  void populateRooms(ActionEvent event) {

    ObservableList<Room> roomsView = FXCollections.observableArrayList(Global.roomList);
    roomTableColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());

    roomTableView.setVisible(true);
    roomPreviewLabel.setVisible(true);
    roomPriceLabel.setVisible(true);
    bookRoombutton.setVisible(true);

    roomTableView.setItems(roomsView);

    //Displays room information when clicked on in TableView
    roomTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Room roomClickedOn = roomTableView.getSelectionModel().getSelectedItem();
        labelPricePerDay.setText("$" + roomClickedOn.getPrice());
        Image image = new Image(roomClickedOn.getPictureUrl());
        imageViewRoom.setImage(image);
        Global.roomInfo = roomClickedOn.getName();
        Global.selectedRoom = roomClickedOn;
      }
    });
  }


  /*
  Method called when user clicks "book room"
  Sets the Global check in and check out date
  Sends user to payment screen
   */
  @FXML
  public void handleBookRoom(ActionEvent event) {
    LocalDate checkinDate = checkInDate.getValue();
    LocalDate checkoutDate = checkOutDate.getValue();
    Global.checkInDate = checkinDate;
    Global.checkOutDate = checkoutDate;
    Boolean success1= true;
    try {
      Global.selectedRoom.getName();
    }catch (NullPointerException exception){
      new Global().displayPopUpWindow("Please click on a room");
      success1= false;
    }
    if (success1){
      Global.currentTitle="Ruby Resort: Book Room";
      Global.currentScene = signoutButton.getScene();
      new Global().openNewWindow(WindowLocation.PAYMENT);
    }
  }

  /*
  Takes guest back to login screen
   */
  @FXML
  public void handleSignout(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();//
    Global.currentTitle="Ruby Resort: Login Menu";
    new Global().openNewWindow(WindowLocation.LOGINMENU);
    Global.selectedRoom= null;
  }

  /*
  Shows room booking
   */
  @FXML
  public void handleHistory(ActionEvent event){
    Global.currentScene = signoutButton.getScene();//
    Global.currentTitle="Ruby Resort: Purchase History";
    new Global().openNewWindow(WindowLocation.ACCOUNTWINDOW);
    Global.selectedRoom= null;

  }

}
