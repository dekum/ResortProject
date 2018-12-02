package sample.ManagerMenu;

import static sample.Global.empList;
import static sample.Global.eventList;
import static sample.Global.roomList;
import static sample.Global.selectedEmp;
import static sample.Global.selectedEvent;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.Action;
import sample.Controller;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Report;
import sample.ResortEvent;
import sample.Room;
import sample.UserFileUtilities;
import sample.WriteEmployee;
import sample.WriteEvent;
import sample.WriteRooms;

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
  private TableView<ResortEvent> tableViewEvents;
  @FXML
  private TextField textfieldEventName, textFieldEventPrice;
  @FXML
  private DatePicker datePickerEventDate;
  @FXML
  private TableColumn<ResortEvent, String> columnEvent;
  @FXML
  private TextArea textAreaEventDes;
  @FXML
  Button addEventButton, removeEventButton, updateEventButton;

  @FXML void addEvent (ActionEvent e){
    String errorMessage = validateEventInputs();

    if (!errorMessage.equals("")){
      //There was an error, print popup displaying errors
      new Global().displayPopUpWindow(errorMessage);
    }
    else {
      //Inputs are fine, add employee
      String eventName = textfieldEventName.getText();
      String eventPrice = textAreaEventDes.getText();
      Double price = Double.parseDouble(textFieldEventPrice.getText());

      LocalDate eventDate = datePickerEventDate.getValue();

      ResortEvent newEvent = new ResortEvent(textfieldEventName.getText(),datePickerEventDate.getValue().toString(),
          Double.parseDouble(textFieldEventPrice.getText()),textAreaEventDes.getText());
      eventList.add(newEvent);

      ObservableList<ResortEvent> eventView = FXCollections.observableArrayList(eventList);
      tableViewEvents.setItems(eventView);
      new WriteEvent();
      new Global().displayPopUpWindow("Event Added.");
      populateSummaryReports();
    }

  }
  @FXML void deleteEvent (ActionEvent e){
    if (selectedEvent !=null){
      eventList.remove(selectedEvent); //Remove the object stored in SelectedRoom from the roomList Array
      selectedEvent= null;
      ObservableList<ResortEvent> eventView = FXCollections.observableArrayList(eventList); //reset list, to show change
      tableViewEvents.setItems(eventView);//clear textfield's data
      textfieldEventName.setText("");
      textFieldEventPrice.setText("");
      textAreaEventDes.setText("");
      datePickerEventDate.setValue(null);
      new WriteEvent();
      populateSummaryReports();
    }else{
      new Global().displayPopUpWindow("No Room was selected.");
    }

  }
  @FXML void updateEvent (ActionEvent e){

    String errorMessage = validateEventInputs();
    if(errorMessage.equals("")){
      for (ResortEvent event: eventList
      ) {
        if (event.toString().equals(selectedEvent.toString())){
          event.setName(textfieldEventName.getText());
          event.setNameproperty(textfieldEventName.getText());
          event.setEventDescription(textAreaEventDes.getText());
          event.setPrice(Double.parseDouble(textFieldEventPrice.getText()));
          event.setDate(datePickerEventDate.getValue().toString());
          event.setDateproperty(event.getDate());

          ObservableList<ResortEvent> eventView = FXCollections.observableArrayList(eventList);
          tableViewEvents.setItems(eventView);
          new WriteEvent();
          new Global().displayPopUpWindow("Event updated.");
          populateSummaryReports();

        }

      }
    }else{
      new Global().displayPopUpWindow(errorMessage);

    }






  }

  public String validateEventInputs(){
    String errorMessage="";
    Global.currentScene = signoutButton.getScene();

    double priceOfEvent = -1;





    if (textfieldEventName.getText().equals("")){

      errorMessage += "Please Enter New Event Name\n";

    }

    if(textFieldEventPrice.getText().equals("")){
      errorMessage += "Please Enter New Event Price\n";

    }else {
      //Check if price is valid
      Boolean successful12 = true;
      try {
        //do this after checking if wagetextfield isnt empty or another error happens.
        priceOfEvent = Double.parseDouble(textFieldEventPrice.getText());
      }
      catch (NullPointerException | NumberFormatException exception ) {
        //numberformatexpection if user accidently enters a letter
        successful12 = false;
        errorMessage+="Event Price input is invalid.\n";
      }


    }

    if (datePickerEventDate.getValue()==null){
      errorMessage += "Please Enter New Event Date\n";

    }
    if(textAreaEventDes.getText().equals("")){

      errorMessage += "Please Enter New Event Description\n";

    }

    return errorMessage;
  }


  @FXML
  void initialize() {
    /**
     * This method is excuted first, before the window loads.
     */
    //Global.currentTitle="Ruby Resort: Manager View";
    ObservableList<ResortEvent> eventsView = FXCollections.observableArrayList(eventList);
    columnEvent.setCellValueFactory(cellData -> cellData.getValue().getNameProperty()); //set Cellfactory for  room column

    tableViewEvents.setItems(eventsView); //Tableview gets elements from roomsView list

    tableViewEvents.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        ResortEvent eventClickedOn = tableViewEvents.getSelectionModel().getSelectedItem(); //set the current room clicked on
        textfieldEventName.setText(eventClickedOn.getName()); //Update textfield with new room type
        textFieldEventPrice.setText("" + eventClickedOn.getPrice());//Update textfield with new room price
        textAreaEventDes.setText(eventClickedOn.getEventDescription());

        datePickerEventDate.setValue(LocalDate.parse(eventClickedOn.getDate()));
        selectedEvent = eventClickedOn;
      }
    });


    ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
    roomTableColumn.setCellValueFactory(cellData -> cellData.getValue().getnameProperty()); //set Cellfactory for  room column

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
//    employeeColumn.setCellValueFactory(
//        new PropertyValueFactory<Employee, String>("bothNamesProperty"));
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

  public String validateEmployeeInputs(){
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
      }
      catch (NullPointerException | NumberFormatException exception ) {
        //numberformatexpection if user accidently enters a letter
        successful12 = false;
        errorMessage+="Wage input invalid.\n";
      }


    }

    if (DOB.getValue()==null){
      errorMessage += "Please Enter New Employee Date of Birth\n";

    }

    return errorMessage;
  }

//TODO
  @FXML
  void addRoom(ActionEvent event) {
    double roomPrice=-1;
    String roomName="";
    String errorMessage="";
    boolean validPrice = true;
    try{
      if(roomTypeText.getText().equals("")||roomTypeText.getText()== null){
        //empty textfield
        errorMessage += "Please Enter New Room Type\n";

      }
    }catch (NullPointerException exception){

    }

    if(roomPriceText.getText().equals("")||roomPriceText.getText() == null){
      //empty textfield
      errorMessage += "Please Enter New Room Price\n";

    }else {
      //Check if price is valid

      try {

        roomPrice = Double.parseDouble(roomPriceText.getText());
      }
      catch (NullPointerException | NumberFormatException exception ) {

        errorMessage+="Wage input invalid.\n";
      }


    }


    if (errorMessage.equals("")){
      //roomPrice = Double.parseDouble(roomPriceText.getText());
      roomName= roomTypeText.getText();
      Room newRoom = new Room(roomName, true, roomPrice, "/sample/Pictures/SuiteQueen.jpg");
      roomList.add(newRoom);

      ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
      roomTableView.setItems(roomsView);
      new WriteRooms();
      populateSummaryReports();

    }
    else {
      //There was an error, print popup displaying errors
      new Global().displayPopUpWindow(errorMessage);

    }




  }

  @FXML
  void deleteRoom(ActionEvent event) {
    //need to check if selected room Exists/ or is null
    if (selectedRoom !=null){
      roomList.remove(selectedRoom); //Remove the object stored in SelectedRoom from the roomList Array
      ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList); //reset list, to show change
      roomTableView.setItems(roomsView);//clear textfield's data
      roomTypeText.setText(null);//clear textfield's data
      roomPriceText.setText(null); //clear textfield's data
      new WriteRooms();
      populateSummaryReports();
    }else{
      new Global().displayPopUpWindow("No Room was selected.");
    }

  }

  @FXML
  void updateRoom(ActionEvent event) {
    double roomPrice=-1;
    String roomName="";
    String errorMessage="";
    boolean validPrice = true;
    try{
      if(roomTypeText.getText().equals("")||roomTypeText.getText()== null){
        //empty textfield
        errorMessage += "Please Enter New Room Type\n";

      }
    }catch (NullPointerException exception){

    }

    if(roomPriceText.getText().equals("")||roomPriceText.getText() == null){
      //empty textfield
      errorMessage += "Please Enter New Room Price\n";

    }else {
      //Check if price is valid

      try {

        roomPrice = Double.parseDouble(roomPriceText.getText());
      }
      catch (NullPointerException | NumberFormatException exception ) {

        errorMessage+="Wage input invalid.\n";
      }


    }
    if(errorMessage.equals("")){
      for (Room r: roomList
      ) {
        if (r.toString().equals(selectedRoom.toString())){
          r.setName(roomTypeText.getText());
          r.setnameProperty(roomTypeText.getText());//Changing this changes text on tableview
          r.setPrice(Double.parseDouble(roomPriceText.getText()));

          ObservableList<Room> roomsView = FXCollections.observableArrayList(roomList);
          roomTableView.setItems(roomsView);
          roomTableColumn.setVisible(false);//To update tableview
          roomTableColumn.setVisible(true);//to update tableview
          new WriteRooms();
          populateSummaryReports();

        }

      }
    }



    //Room updatedRoom = new Room(selectedRoom.getName(),true,selectedRoom.getPrice(),selectedRoom.getPictureUrl());
    //roomList.remove(selectedRoom);
    //roomList.add(updatedRoom);


  }

  //TODO
  @FXML
  void addEmployee(ActionEvent event) {
    String errorMessage = validateEmployeeInputs();

    if (!errorMessage.equals("")){
      //There was an error, print popup displaying errors
      new Global().displayPopUpWindow(errorMessage);
    }
    else {
      //Inputs are fine, add employee
      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      Double wage = Double.parseDouble(wageText.getText());

      LocalDate birthDate = DOB.getValue();

      Employee newEmp = new Employee(firstName,lastName,wage,birthDate);
      Global.empList.add(newEmp);

      ObservableList<Employee> empView = FXCollections.observableArrayList(empList);
      employeeTableView.setItems(empView);
      new Global().displayPopUpWindow("Employee Successfully created");
      new WriteEmployee();
      populateSummaryReports();
    }
  }

  @FXML
  void deleteEmployee(ActionEvent event) {
    if (selectedEmp != null){
      empList.remove(selectedEmp);
      ObservableList<Employee> empView = FXCollections.observableArrayList(empList);
      employeeTableView.setItems(empView);
      firstNameText.setText(null);
      lastNameText.setText(null);
      wageText.setText(null);
      DOB.setValue(null);
      new WriteEmployee();
      populateSummaryReports();
    }else
    {
      new Global().displayPopUpWindow("Employee not selected.");
    }




  }

  @FXML
  void updateEmployee(ActionEvent event) {
    String errorMessage = validateEmployeeInputs();
    if (errorMessage.equals("")){
      for (Employee e: empList
      ) {
        if (e.toString().equals(selectedEmp.toString())){
          //Search through empList to find object matching employee selected
          //Then update the empList's object with new data
          e.setFirstName(firstNameText.getText());
          System.out.println(firstNameText.getText());

          e.setLastName(lastNameText.getText());

          e.setWage(Double.parseDouble(wageText.getText()));
          e.setDOB(DOB.getValue());
          ObservableList<Employee> empView = FXCollections.observableArrayList(empList);
          employeeTableView.setItems(empView);
          employeeColumn.setVisible(false);
          employeeColumn.setVisible(true);

          new WriteEmployee();
          populateSummaryReports();

        }
    }

    }else{
 new Global().displayPopUpWindow(errorMessage);
    }




  }

  @FXML
  void clearFields(ActionEvent event) {

    firstNameText.setText("");
    lastNameText.setText("");
    wageText.setText("");
    DOB.setValue(null);
    roomTypeText.setText("");
    roomPriceText.setText("");
    textfieldEventName.setText("");
    textFieldEventPrice.setText("");
    textAreaEventDes.setText("");
    datePickerEventDate.setValue(null);

  }

  @FXML
  void signOut(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();//
    Global.currentTitle="Ruby Resort: Login Window";
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
