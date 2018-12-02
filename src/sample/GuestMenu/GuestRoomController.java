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
import javax.swing.Action;
import sample.Controller;
import sample.Global.WindowLocation;
import sample.Global;
import sample.ResortEvent;
import sample.Room;


/**
 * Notes:ppEtit GuestRoomController.java NEED TO CHNAGE TO GUESTROOMMENU
 *
 * This controller controls the GuestRoom.fxml This allows the gues to: View Room List. Book Room.
 *
 * User can click through list of rooms, the window will update the information  about the room.
 * What updates are: Image(Not yet), Availability, price per room. If room is available, then user
 * can enter daysStaying and book room
 *
 * Otherwise the Guest can logout, and their information is saved.
 *
 * The program is coded so it know what room is selected when booking room. All room information can
 * be seen in Manager Window.
 *
 * @author ggraber7402
 */
public class GuestRoomController extends Controller {

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

  @FXML
  void initialize() {


    ObservableList<ResortEvent> event2 = FXCollections.observableArrayList(Global.eventList);
    eventTableColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    eventDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
    eventTableView.setItems(event2);

    roomTableView.setVisible(false);
    roomPreviewLabel.setVisible(false);
    roomPriceLabel.setVisible(false);
    bookRoombutton.setVisible(false);

    checkInDate.setValue(LocalDate.now());

    if (Global.currentGuestLoggedIn.getRoomRented() ==null){

      buttonPaymentHistory.setVisible(false);

    }else{
      buttonPaymentHistory.setVisible(true);

    }



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

  @FXML
  void populateRooms(ActionEvent event) {

    ObservableList<Room> roomsView = FXCollections.observableArrayList(Global.roomList);
    roomTableColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty());

    roomTableView.setVisible(true);
    roomPreviewLabel.setVisible(true);
    roomPriceLabel.setVisible(true);
    bookRoombutton.setVisible(true);

    roomTableView.setItems(roomsView);

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

  @FXML
  public void handleSignout(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();//
    Global.currentTitle="Ruby Resort: Login Menu";
    new Global().openNewWindow(WindowLocation.LOGINMENU);
    Global.selectedRoom= null;
  }

  @FXML
  public void handleHistory(ActionEvent event){
    Global.currentScene = signoutButton.getScene();//
    Global.currentTitle="Ruby Resort: Purchase History";
    new Global().openNewWindow(WindowLocation.ACCOUNTWINDOW);
    Global.selectedRoom= null;

  }

}
