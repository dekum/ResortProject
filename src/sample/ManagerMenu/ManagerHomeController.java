package sample.ManagerMenu;

import static sample.Global.empList;
import static sample.Global.eventList;
import static sample.Global.roomList;
import static sample.Global.selectedEmp;
import static sample.Global.selectedRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Controller;
import sample.Employee2;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Report;
import sample.Room;
import sample.UserFileUtilities;

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
  private TableView<Report> summaryTableView;
  @FXML
  private TableColumn<Report, String> summaryFields;
  @FXML
  private TableColumn<Report, String> summaryTotals;

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
    populateSummaryReports();
  }
//TODO
  @FXML
  void addRoom(ActionEvent event) {
    if (roomTypeText.getText() != null) {
      String roomName = roomTypeText.getText();
      double roomPrice = Double.parseDouble(roomPriceText.getText());
      Room newRoom = new Room(roomName, true, roomPrice, "/sample/Pictures/SuiteQueen.jpg");
      roomList.add(newRoom);
    } else {
      Global.currentScene = signoutButton.getScene();
      new Global().displayPopUpWindow("Please Enter New Room Information");
    }
    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
    roomTableView.setItems(roomsView);

    populateSummaryReports();
  }

  @FXML
  void deleteRoom(ActionEvent event) {
    roomList.remove(selectedRoom);
    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
    roomTableView.setItems(roomsView);
    roomTypeText.setText(null);
    roomPriceText.setText(null);

    populateSummaryReports();
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

    populateSummaryReports();
  }

  //TODO
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

      populateSummaryReports();
    }
  }

  @FXML
  void deleteEmployee(ActionEvent event) {
    empList.remove(selectedEmp);
    ObservableList<Employee2> empView = FXCollections.observableArrayList(empList);
    employeeTableView.setItems(empView);
    firstNameText.setText(null);
    lastNameText.setText(null);
    wageText.setText(null);
    DOB.setValue(null);

    populateSummaryReports();

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

    populateSummaryReports();
  }

  @FXML
  void clearFields(ActionEvent event) {

    firstNameText.setText(null);
    lastNameText.setText(null);
    wageText.setText(null);
    DOB.setValue(null);

    roomTypeText.setText(null);
    roomPriceText.setText(null);
  }

  @FXML
  void signOut(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();//
    new Global().openNewWindow(WindowLocation.LOGINMENU);
  }

  void populateSummaryReports(){
    int empCount = 0;
    for (int i = 0; i < empList.size(); i++){
        empCount++;
    }

    int roomCount = 0;
    for (int i = 0; i < roomList.size(); i++){
      roomCount++;
    }

    int eventCount = 0;
    for (int i = 0; i < eventList.size(); i++){
      eventCount++;
    }

    int guestCount = UserFileUtilities.getGuestAmount("","");

    ArrayList<Report> reports = new ArrayList<>();
    reports.add(new Report("Number of Employees",empCount));
    reports.add(new Report("Number of Rooms",roomCount));
    reports.add(new Report("Number of Guests",guestCount));
    reports.add(new Report("Number of Events",eventCount));
    reports.add(new Report("Rooms Booked",1));

    summaryFields.setCellValueFactory(cellData -> cellData.getValue().fieldPropertyProperty());
    summaryTotals.setCellValueFactory(cellData -> cellData.getValue().countPropertyProperty());
    ObservableList<Report> reportView = FXCollections.observableArrayList(reports);
    summaryTableView.setItems(reportView);
  }

}
