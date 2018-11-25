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
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Report;
import sample.Room;
import sample.UserFileUtilities;

public class ManagerHomeController extends Controller {
  @FXML
  private TableView<Employee> employeeTableView; //TableView for Employee
  @FXML
  private TableColumn<Employee, String> employeeColumn;
  @FXML
  private TextField firstNameText; //textfield for employee first name
  @FXML
  private TextField lastNameText; //textfield for employee last name
  @FXML
  private DatePicker DOB; //Datepicker for DOB of employee
  @FXML
  private TextField wageText; //textField for employee wage
  @FXML
  private TableView<Room> roomTableView; // tableview for the resort Rooms
  @FXML
  private TableColumn<Room, String> roomTableColumn;
  @FXML
  private TextField roomTypeText; //textfield for room type
  @FXML
  private TextField roomPriceText; //textfield for room price
  @FXML
  private Button signoutButton; //button to signout or leave window
  @FXML
  private TableView<Report> summaryTableView; //tableview to show summary report
  @FXML
  private TableColumn<Report, String> summaryFields;
  @FXML
  private TableColumn<Report, String> summaryTotals;

  @FXML
  void initialize() {
    /**
     * This method is excuted first, before the window loads.
     */
    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
    roomTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty()); //set Cellfactory for  room column

    roomTableView.setItems(roomsView); //Tableview gets elements from roomsView list

    roomTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Room roomClickedOn = roomTableView.getSelectionModel().getSelectedItem(); //set the current room clicked on
        roomTypeText.setText(roomClickedOn.getName()); //Update textfield with new room type
        roomPriceText.setText("" + roomClickedOn.getPrice());//Update textfield with new room price
        selectedRoom = roomClickedOn;
      }
    });

    ObservableList<Employee> empView = FXCollections.observableArrayList(empList);
    employeeColumn.setCellValueFactory(cellData -> cellData.getValue().getBothNamesProperty());

    employeeTableView.setItems(empView);

    employeeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      //Add a mouseclick event to the employee TableView
      @Override
      public void handle(MouseEvent event) {
        Employee empClickedOn = employeeTableView.getSelectionModel().getSelectedItem();
        firstNameText.setText(empClickedOn.getFirstName()); //update textfield with current employee's data
        lastNameText.setText(empClickedOn.getLastName()); //update textfield with current employee's data
        wageText.setText(String.valueOf(empClickedOn.getWage())); //update textfield with current employee's data
        DOB.setValue(empClickedOn.getDOB()); //update textfield with current employee's data
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
    //need to check if selected room Exists/ or is null
    roomList.remove(selectedRoom); //Remove the object stored in SelectedRoom from the roomList Array
    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList); //reset list, to show change
    roomTableView.setItems(roomsView);//clear textfield's data
    roomTypeText.setText(null);//clear textfield's data
    roomPriceText.setText(null); //clear textfield's data

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
    String errorMessage="";
    Global.currentScene = signoutButton.getScene();

    double wage = -1;





    if (firstNameText.getText().equals("")){

      errorMessage += "Please Enter New Employee First Name\n";
      //new Global().displayPopUpWindow("Please Enter New Employee Information");
    }
    if(lastNameText.getText().equals("")){

      errorMessage += "Please Enter New Employee Last Name\n";
      //Global.currentScene = signoutButton.getScene();
      //new Global().displayPopUpWindow("Please Enter New Employee Information");
    }
    if(wageText.getText().equals("")){
      errorMessage += "Please Enter New Employee Wages\n";
      //Global.currentScene = signoutButton.getScene();
     // new Global().displayPopUpWindow("Please Enter New Employee Information");
    }else {
      //Check if wages is valid
      Boolean successful12 = true;
      try {
        //do this after checking if wagetextfield isnt empty or another error happens.
        wage = Double.parseDouble(wageText.getText());
      } catch (NullPointerException exception) {
        successful12 = false;
        errorMessage+="Wage input invalid.";
      }


    }


    if (!errorMessage.equals("")){
      new Global().displayPopUpWindow(errorMessage);
    }
    else {
      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();

      LocalDate birthDate = DOB.getValue();

      Employee newEmp = new Employee(firstName,lastName,wage,birthDate);
      Global.empList.add(newEmp);

      ObservableList<Employee> empView = FXCollections.observableArrayList(empList);
      employeeTableView.setItems(empView);

      populateSummaryReports();
    }
  }

  @FXML
  void deleteEmployee(ActionEvent event) {
    empList.remove(selectedEmp);
    ObservableList<Employee> empView = FXCollections.observableArrayList(empList);
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

    Employee updatedEmp = new Employee(selectedEmp.getFirstName(),selectedEmp.getLastName(),
        selectedEmp.getWage(),selectedEmp.getDOB());
    empList.remove(selectedEmp);
    empList.add(updatedEmp);

    ObservableList<Employee> empView = FXCollections.observableArrayList(empList);
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
    /**
     * This method sets the Summary Report table information
     * It gathers the data of employee list, roomlist and event list and prints it in a tableview
     * The data is then showed to the user for easy readability.
     *
     */
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
