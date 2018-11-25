package sample.ManagerMenu;

import static sample.Global.empList;
import static sample.Global.roomList;
import static sample.Global.selectedEmp;
import static sample.Global.selectedRoom;

import java.lang.reflect.Array;
import java.time.LocalDate;
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
import sample.Employee2;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Room;

public class ManagerHomeController extends Controller {
  @FXML
  private TableView<Employee2> employeeTableView;
  @FXML
  private TableColumn<Employee2, String> employeeColumn;
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
  private Button signoutButton;
  @FXML
  void initialize() {
    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
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

    ObservableList<Employee2> empView = FXCollections.observableArrayList(empList);
    employeeColumn.setCellValueFactory(cellData -> cellData.getValue().getBothNamesProperty());

    employeeTableView.setItems(empView);

    employeeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Employee2 empClickedOn = employeeTableView.getSelectionModel().getSelectedItem();
        firstNameText.setText(empClickedOn.getFirstName());
        lastNameText.setText(empClickedOn.getLastName());
        wageText.setText(String.valueOf(empClickedOn.getWage()));
        DOB.setValue(empClickedOn.getDOB());
        selectedEmp = empClickedOn;
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
  void addEmployee(ActionEvent event) {
    if (firstNameText.getText().equals("")){
      Global.currentScene = signoutButton.getScene();
      new Global().displayPopUpWindow("Please Enter New Employee Information");
    }
    else if(lastNameText.getText().equals("")){
      Global.currentScene = signoutButton.getScene();
      new Global().displayPopUpWindow("Please Enter New Employee Information");
    }
    else if(wageText.getText().equals("")){
      Global.currentScene = signoutButton.getScene();
      new Global().displayPopUpWindow("Please Enter New Employee Information");
    } else {
      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      double wage = Double.parseDouble(wageText.getText());
      LocalDate birthdate = DOB.getValue();

      Employee2 newEmp = new Employee2(firstName,lastName,wage,birthdate);
      Global.empList.add(newEmp);

      ObservableList<Employee2> empView = FXCollections.observableArrayList(empList);
      employeeTableView.setItems(empView);

    }
  }

  @FXML
  void deleteEmployee(ActionEvent event) {
    empList.remove(selectedEmp);
    ObservableList<Employee2> empView = FXCollections.observableArrayList(empList);
    employeeTableView.setItems(empView);
    firstNameText.setText("");
    lastNameText.setText("");
    wageText.setText("");
    DOB.setValue(null);

  }

  @FXML
  void updateEmployee(ActionEvent event) {
    if (firstNameText.getText() != null){
      selectedEmp.setFirstName(firstNameText.getText());
    }
    if (lastNameText.getText() != null){
      selectedEmp.setLastName(lastNameText.getText());
    }
    if (wageText.getText() != null){
      selectedEmp.setWage(Double.parseDouble(wageText.getText()));
    }
    if ((DOB.getValue() != null)){
      selectedEmp.setDOB(DOB.getValue());
    }

    Employee2 updatedEmp = new Employee2(selectedEmp.getFirstName(),selectedEmp.getLastName(),
        selectedEmp.getWage(),selectedEmp.getDOB());
    empList.remove(selectedEmp);
    empList.add(updatedEmp);

    ObservableList<Employee2> empView = FXCollections.observableArrayList(empList);
    employeeTableView.setItems(empView);
  }

  @FXML
  void signOut(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();//
    new Global().openNewWindow(WindowLocation.LOGINMENU);
  }

}
