/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.GuestMenu;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Controller;
import sample.Employee;
import sample.Global.WindowLocation;
import sample.LoginMenu.LoginMenuController;
import sample.Global;
import sample.Guest;
import sample.ResortEvent;
import sample.Room;
import sample.Room.RoomCellFactory;


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
  void handleCheckIn(ActionEvent event) {
    //Not used yet
  }

  @FXML
  void handleCheckOut(ActionEvent event) {
//Not used yet
  }

  @FXML
  void initialize() {
    List<ResortEvent> event = new ArrayList<>();
    event.add((new ResortEvent("ZombieCon", "Nov 29")));
    event.add((new ResortEvent("Crab Race", "Nov 31")));
    event.add((new ResortEvent("Karaoke", "Dec 2")));
    event.add((new ResortEvent("Boat Show", "Dec 4")));
    event.add((new ResortEvent("ArtWalk", "Dec 5")));
    event.add((new ResortEvent("Car Show", "Dec 9")));

    ObservableList<ResortEvent> event2 = FXCollections.observableArrayList(event);
    eventTableColumn.setCellValueFactory(cellData -> cellData.getValue().getNameproperty());
    eventDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
    eventTableView.setItems(event2);

    roomTableView.setVisible(false);
    roomPreviewLabel.setVisible(false);
    roomPriceLabel.setVisible(false);
    bookRoombutton.setVisible(false);

  }

  @FXML
  void populateRooms(ActionEvent event) {
    List<Room> rooms2 = new ArrayList<>();
    rooms2.add(new Room("1 Queen Bed", true, 250, "sample/Pictures/QueenBed.jpg"));
    rooms2.add(new Room("2 Twin Beds", true, 200, "sample/Pictures/TwinBed.jpg"));
    rooms2.add(new Room("Suite - 2 Queen Beds", true, 500, "sample/Pictures/SuiteQueen.jpg"));
    rooms2.add(new Room("Suite - 1 King Bed", true, 525, "sample/Pictures/SuiteKing.jpg"));

    ObservableList<Room> rooms2View = FXCollections.observableArrayList(rooms2);
    roomTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    roomTableView.setVisible(true);
    roomPreviewLabel.setVisible(true);
    roomPriceLabel.setVisible(true);
    bookRoombutton.setVisible(true);

    roomTableView.setItems(rooms2View);

    roomTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Room roomClickedOn = roomTableView.getSelectionModel().getSelectedItem();
        labelPricePerDay.setText("$" + roomClickedOn.getPrice());
        Image image = new Image(roomClickedOn.getPictureUrl());
        imageViewRoom.setImage(image);
      }
    });
  }


  @FXML
  public void handleBookRoom(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();
    new Global().openNewWindow(WindowLocation.PAYMENT);

  }

  @FXML
  public void handleSignout(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();//
    new Global().openNewWindow(WindowLocation.LOGINMENU);
  }
}
