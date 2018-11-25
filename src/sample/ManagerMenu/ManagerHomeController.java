package sample.ManagerMenu;

import static sample.Global.roomList;
import static sample.Global.selectedRoom;

import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Controller;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Room;

public class ManagerHomeController extends Controller {

  @FXML
  private TableView<Employee> employeeTableView;

  @FXML
  private TableColumn<Employee, String> employeeColumn;

  @FXML
  private TextField firstNameText;

  @FXML
  private TextField lastNameText;

  @FXML
  private DatePicker DOB;

  @FXML
  private TextField wageText;

  @FXML
  private TableView<Room> roomTableView;

  @FXML
  private TableColumn<Room, String> roomTableColumn;

  @FXML
  private TextField roomTypeText;

  @FXML
  private TextField roomPriceText;
  @FXML
  private Button updateRoomButton;

  @FXML
  private Button deleteRoomButton;

  @FXML
  private Button addRoomButton;

  @FXML
  private Button signoutButton;

  @FXML
  void initialize() {
    ArrayList<Room> rooms = roomList;

    ObservableList<Room> roomsView = FXCollections.observableArrayList(rooms);
    roomTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    roomTableView.setItems(roomsView);

    roomTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Room roomClickedOn = roomTableView.getSelectionModel().getSelectedItem();
        roomTypeText.setText(roomClickedOn.getName());
        roomPriceText.setText("" + roomClickedOn.getPrice());
        selectedRoom = roomClickedOn;
      }
    });
  }

  @FXML
  void addRoom(ActionEvent event) {
    if (roomTypeText.getText().equals("")) {
      Global.currentScene = signoutButton.getScene();
      new Global().displayPopUpWindow("Please Enter New Room Information");
    } else {
      String roomName = roomTypeText.getText();
      double roomPrice = Double.parseDouble(roomPriceText.getText());
      Room newRoom = new Room(roomName, true, roomPrice, "/sample/Pictures/SuiteQueen.jpg");
      roomList.add(newRoom);
    }
    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
    roomTableView.setItems(roomsView);
  }

  @FXML
  void deleteRoom(ActionEvent event) {
    roomList.remove(selectedRoom);
    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
    roomTableView.setItems(roomsView);
    roomTypeText.setText("");
    roomPriceText.setText("");
  }

  @FXML
  void updateRoom(ActionEvent event) {
    if (roomTypeText.getText() != null) {
      selectedRoom.setName(roomTypeText.getText());
    }
    if (roomPriceText.getText() != null) {
      selectedRoom.setPrice(Double.parseDouble(roomPriceText.getText()));
    }
    Room updatedRoom = new Room(selectedRoom.getName(),true,selectedRoom.getPrice(),selectedRoom.getPictureUrl());
    roomList.remove(selectedRoom);
    roomList.add(updatedRoom);

    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
    roomTableView.setItems(roomsView);

  }

  @FXML
  void signOut(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();//
    new Global().openNewWindow(WindowLocation.LOGINMENU);
  }

}
