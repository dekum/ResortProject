/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.ManagerMenu;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javax.swing.Action;
import sample.Controller;
import sample.CustomDate;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.Room;
import sample.Room.RoomCellFactory;

/**
 * FXML Controller class
 *
 * ManagerMenuConroller.java Notes by: pPetitFrere
 *
 * Manager Window Probably Most Important Manager Can: View Employees Add Employees/Delete Emplyee
 * View Rooms Remove People from rooms.
 *
 * With View Employees: Employees are a class of Employee class and are displayed in Table View.
 * Manager can select an Employee and delete them Mananger can select Add Button,and be taken to
 * tabAddEmployee As long as the fields are correct an employee can be successfully Added.
 *
 * View Rooms: Almost indentical to Guest's Manager can also delete Guest from Room.
 *
 * Source for java datePicker info: https://stackoverflow.com/questions/49531768/how-to-fire-an-event-in-datepicker-when-weekday-clicked-in-javafx
 *
 * @author ggraber7402
 *
 * ManagaMenuController also handles Employee schedule The tab ManageSchedule will allow manager to
 * set the hours a employee pays. The DatePicker(Calender) will let mamnager pick a day, but what
 * the prgram will read is the week The datepicker has a listener that will call the datePicked
 * method The hours of the week are changed, When the manager is done they will click "Update"
 * Button which opens up a popup showing success or failure If succesfful the program will modify
 * Employee's class varaible daysWorked arraylist with the new data
 */


public class ManagerMenuController extends Controller implements Initializable {

  //  private ArrayList<String> usernameList;
  private ArrayList<String> usernameList;
  private ArrayList<String> passwordList;
  private ArrayList<Guest> guestList;
  private Guest currentGuest;
  private Room roomClickedOn;
  private Employee employeeClickedOn;
  private int daysStaying;
  private boolean initializedRooms = false;
  @FXML
  private TableView<Employee> employeeTable = new TableView<Employee>();//Table View to show employees
  private ObservableList<Employee> data; //This is where Employee List of served
  final HBox hb = new HBox();
  ArrayList<ComboBox> comboBoxes = new ArrayList<>();
  ArrayList<ComboBox> comboBoxesStart = new ArrayList<>();
  ArrayList<ComboBox> comboBoxesEnd = new ArrayList<>();
  ArrayList<LocalDate> daysOfSelectedWeek = new ArrayList<>();
  List<Label> listOfLabels = new ArrayList<>();
  LocalDate workDateSelected;

  @FXML//Unused
  public void storeVariables2(ArrayList<String> unLIST, ArrayList<String> pwList,
      ArrayList<Guest> gList, List<Room> rooms) {
    System.out.println("HERE FIRST?");
    this.usernameList = unLIST;
    this.passwordList = pwList;
    this.guestList = gList;
    // this.currentGuestLoggedIn = g1;
    this.rooms = rooms;
    int i = 0;
    for (String word : usernameList) {
      System.out.println("Name = " + word);
      System.out.println(passwordList.get(i));
      i++;
    }


  }

  @FXML
  Label labelEmployeeName;
  @FXML
  private TabPane tabPane;
  @FXML
  private ImageView imageViewRoom;

  @FXML
  private Label labelAvailable, labelGuestName;
  @FXML
  private Label labelPricePerDay;
  @FXML
  private Label labelDaysStaying;
  @FXML
  Tab tabEmployeeSchedule;


  //  @FXML
//  private ImageView imageViewRoom;
  @FXML
  ListProperty<String> listProperty2 = new SimpleListProperty<>();

  @FXML
  ComboBox<String> comboBoxStart1, comboBoxStart2, comboBoxStart3, comboBoxStart4, comboBoxStart5, comboBoxStart6, comboBoxStart7,
      comboBoxEnd1, comboBoxEnd2, comboBoxEnd3, comboBoxEnd4, comboBoxEnd5, comboBoxEnd6, comboBoxEnd7;
  @FXML
  Label labelDay1, labelDay2, labelDay3, labelDay4, labelDay5, labelDay6, labelDay7;
  @FXML
  DatePicker datePickerWeek;


  @FXML
  private Button signoutButton, buttonRemoveGuest, buttonSetSchedule, buttonReturnHome;
  // @FXML
  //private Button bookRoombutton;

  @FXML
  private ListView roomListView;

  ///@FXML
  //TableColumn columnEmployeeID,columnNameEmployee,columnPayroll;
  @FXML
  Tab tabEmployeeList;
  //@FXML
  //  TableView tableViewEmployees;
  @FXML
  Label labelWeek;
  @FXML
  TableView<Employee> tableViewShift;
  @FXML
  Tab tabShift;


  @FXML
  private TableColumn<Employee, Integer> colEmpID;
  @FXML
  private TableColumn<Employee, String> colEmployeeName;
  @FXML
  private TableColumn<Employee, String> colStart;
  @FXML
  private TableColumn<Employee, String> colEnd;
  @FXML
  private TableColumn<Employee, String> colTitle;
  @FXML
  private DatePicker datePickerShiftDay;

  @FXML
  void handleReturnHome(ActionEvent event) {
    Global.currentScene = signoutButton.getScene();

    new Global().openNewWindow(WindowLocation.LOGINMENU);

  }

  @FXML
  void handleShiftDay(ActionEvent event) {
    /**
     * When manager selects a date in the datePicker for seing the Daily shift schedule
     * this method is called
     * this method will update the tableViewShift with new data, and the date from the DatePicker
     * The date will be uaed to see if Employee daysWorked array has that,
     * if it the program will get the start hour and end hour and will populate the tableview with it.
     *
     */
    tableViewShift.setItems(data);

    LocalDate shiftDay = datePickerShiftDay.getValue();
    //System.out.println("changing days");
    System.out.println(data.get(0).getShiftStart() + "   " + data.get(0).getShiftEnd());

    for (Employee e : data //go thourgh emplyee list
    ) {
      for (CustomDate cdate : e.getWorkDays()) {//go through one employee's daysWorked arraylist
        if (cdate.getDate().isEqual(shiftDay)) {
          //Okay so the employee was schedule hours for this day, now update the employee's hours worked and end
          System.out.println(e.getName() + e.getShiftStart() + e.getShiftEnd());
          System.out.println(cdate.getStartHour() + " ddd " + cdate.getEndHour());
          e.setShiftStart(cdate.getStartHour());//set Start hour
          e.setShiftEnd(cdate.getEndHour());//set end date
          System.out.println("I updated");


        }

      }


    }

    //THe only way i coudl find to update table view was to make it inisble and then reappear
    tableViewShift.getColumns().get(0).setVisible(false);
    tableViewShift.getColumns().get(0).setVisible(true);


  }


  @FXML
  void handleSetSchedule(ActionEvent e) {
    if (employeeClickedOn != null) {
      tabPane.getSelectionModel().select(tabEmployeeSchedule);
      labelEmployeeName.setText(employeeClickedOn.getName());

    }

  }


  @FXML
  private Button buttonResetWeek;

  @FXML
  void handleResetWeek(ActionEvent event) {
//Clear All Fields and then update the Employee's daysworked array
    for (int j = 0; j < 7; j++) {
      comboBoxesStart.get(j).getSelectionModel().select("Set Hour");

      comboBoxesEnd.get(j).getSelectionModel().select("Set Hour");
      handleUpdate(new ActionEvent());


    }

  }

  @FXML
  void handleViewShift(Event event) {
    if (tabShift.isSelected()) {
      /**
       * Sets default day value to the same date that was in the setSchedule window
       *
       */
      datePickerShiftDay.setValue(datePickerWeek.getValue());
      handleShiftDay(new ActionEvent());

      //let me try to change first one start day

      //Okay Logicallly, I want to ask user to pick a date (Automically Locla Date Today)
      //(then I loop through Employee list daysWorkw thi that date, looking for an euqal
      // THen I autically set HourStart and Hour end to the whats in that Custom Date
      //If user doesnt have the date, it up not scheduled
      //Will decide to do with Employees not working alter
      //I get this done I'm basically odne with the schedule!!
      /**
       *if Tabview Shift is seleceted, this will populate the tablview for tableViewShift
       *
       */
      colEmployeeName.setCellValueFactory(
          new PropertyValueFactory<Employee, String>(
              "name")); //What goes in the () is the name of the Variable in the Employe class

      //TableColumn lastNameCol = new TableColumn("Last Name");
      //  columnNameEmployee.setMinWidth(100);
      /**
       * This fills the Column payroll with Data
       * Need to look up how setCellValueFactories work
       *
       */
      colTitle.setCellValueFactory(
          new PropertyValueFactory<Employee, String>("title"));

      //TableColumn emailCol = new TableColumn("Email");
      //columnPayroll.setMinWidth(200);
      /**
       * This fills the Column name EmployeeID with Data
       * Need to look up how setCellValueFactories work
       *
       */
      colEmpID.setCellValueFactory(
          new PropertyValueFactory<Employee, Integer>("employeeID"));
      /**
       * This fills the Column name colStart with start hour
       * Need to look up how setCellValueFactories work
       *
       */
      colStart.setCellValueFactory(
          new PropertyValueFactory<Employee, String>("shiftStart"));

      /**
       * This fills the Column name colEnd with end Shift hour
       * Need to look up how setCellValueFactories work
       *
       */
      colEnd.setCellValueFactory(
          new PropertyValueFactory<Employee, String>("shiftEnd"));

      /**
       * This fills the table with the employee list named data
       *
       */
      tableViewShift.setItems(data);
      // tableViewShift.getColumns().addAll(columnEmployeeID, columnPayroll, columnEmployeeID);
      // TODO
      // tableViewShift.setOnMousePressed((MouseEvent event) );
      tableViewShift.setOnMouseClicked(new EventHandler<MouseEvent>() {
        /**
         * This will add a MouseClick event listener to the TableView Employees
         * that will check if a Employee is selected.
         *
         */
        @Override
        public void handle(MouseEvent event) {
          String employeeSelected = tableViewShift.getSelectionModel().getSelectedItem().toString();
          //System.out.println("To string:"+roomSelected.toString());

          for (Employee e : data
          ) {
            if (e.toString().equalsIgnoreCase(employeeSelected)) {
              employeeClickedOn = e;


            }
            // System.out.println(r.getName());
          }
          System.out.println("Employee clicked on is : " + employeeClickedOn);
          // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

          //Check Room Avability.
          //displayRoomFeatures(roomClickedOn);

        }

      });


    }


  }


  @FXML
  protected List<Room> rooms = new ArrayList<>();
  @FXML
  protected ListProperty<Room> listProperty = new SimpleListProperty<>();

  //   @FXML
  //protected List<Employee> employees = new ArrayList<>();
  @FXML
  protected ListProperty<Employee> listPropertyEmployee = new SimpleListProperty<>();


  @FXML
  private TableView<Employee> tableViewEmployees;
  @FXML
  private TableColumn<Employee, Integer> columnEmployeeID;
  @FXML
  private TableColumn<Employee, String> columnNameEmployee;
  @FXML
  private TableColumn<Employee, Double> columnPayroll;
  @FXML
  private Button buttonAdd, buttonDelete;

  @FXML
  private Button buttonSubmit;
  @FXML
  private TextField textFieldEmpName, textFieldEmpID, textFieldEmpPayRoll;
  @FXML
  private Label labelSuccessSubmit;
  @FXML
  private Tab tabAddEmployee;

  @FXML
  void setupEmployeeSchedule() {
    /**
     * Called in initalize, sets up Datepicker and other elements for tab Employee tab
     */
    final Callback<DatePicker, DateCell> dayCellFactory =
        new Callback<DatePicker, DateCell>() {
          @Override
          public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
              @Override
              public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                //System.out.println("Ok here");

              }
            };
          }
        };
    datePickerWeek.setDayCellFactory(dayCellFactory);
    datePickerWeek.setValue(LocalDate.now());
    handlePickDate(new ActionEvent());


  }

  @FXML
  void handlePickDate(ActionEvent event) {
    /**
     * Happens after clicking a date on datePicker
     * THis will tell us what date user selected!
     *
     *
     */
    System.out.println("Here");
    System.out.println(datePickerWeek.getValue());
    LocalDate date = datePickerWeek.getValue();

    LocalDate date2 = datePickerWeek.getValue();
    TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    int weekNumber = date.get(woy);
    //System.out.println(weekNumber);
    // labelWeek.setText("Week: "+String.valueOf(weekNumber));
    Calendar cal = Calendar.getInstance(); // new calender instrnace
    TemporalField fieldISO = WeekFields.of(Locale.US)
        .dayOfWeek(); // Sets First day of week to US with is Sunday
    System.out
        .println("OOOOO: \n" + date.with(fieldISO, 1)); //Changes to first day of the week given

//      SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
//      cal.set(Calendar.WEEK_OF_YEAR,weekNumber);
    System.out.println("days of week yo");
    LocalDate firstDayofWeek = date.with(fieldISO, 1);
    //Starts with Sunday
    daysOfSelectedWeek = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      System.out.println(firstDayofWeek.plusDays(i));
      daysOfSelectedWeek.add(firstDayofWeek.plusDays(i));

    }
    labelWeek.setText("Week: " + daysOfSelectedWeek.get(0) + " to  " + daysOfSelectedWeek.get(6));

    listOfLabels = new ArrayList<>(
        Arrays.asList(labelDay1, labelDay2, labelDay3, labelDay4, labelDay5, labelDay6, labelDay7)

    );
    String[] weekDaysNamesShort = new java.text.DateFormatSymbols().getShortWeekdays();
    //for some reason this array index at [
    // 0] is blank so you gotta start at one to get Mon

    int i = 0;
    for (Label l : listOfLabels) {
      l.setText(weekDaysNamesShort[i + 1] + "  " + daysOfSelectedWeek.get(i));
      l.setTextFill(Color.BLACK);
      System.out.println(date2 + "  " + daysOfSelectedWeek.get(i));
      if (daysOfSelectedWeek.get(i).isEqual(date2)) {
        l.setTextFill(Color.PURPLE);
        System.out.println("JJJ");

      }
      i++;

    }
    //clear fields before setting defaults
    for (ComboBox cb2 : comboBoxes
    ) {
      cb2.getSelectionModel().select(0);

    }

    if (employeeClickedOn != null) {
      if (employeeClickedOn.getWorkDays().isEmpty() == false) {
        //so there already dates in here now i will populate comboxe with the data in those dates
        ArrayList<CustomDate> datesWorked = new ArrayList<>();
        datesWorked = employeeClickedOn.getWorkDays();
        for (CustomDate date3 : datesWorked
        ) {
          for (int j = 0; j < 7; j++) {
            if (daysOfSelectedWeek.get(j).isEqual(date3.getDate())) {
              System.out.println("atch!");
              //Ok Day Selected Date and CustomDate's Date are equal, now set the textfields to what in CustomDate
              comboBoxesStart.get(j).getSelectionModel().select(date3.getStartHour());
              comboBoxesEnd.get(j).getSelectionModel().select(date3.getEndHour());
              //If startdate or enddate is null set as Not avaiable
              //Prob shoudl force maanag er to fill in firelds
              if (date3.getStartHour() == "null") {
                //If either
                //System.out.println("He set no 1");
                comboBoxesStart.get(j).getSelectionModel().select("Not Available");
              }
              if (date3.getEndHour() == "null") {
                //  System.out.println("Hey et not 2");
                comboBoxesEnd.get(j).getSelectionModel().select("Not Available");
              }

            }

          }

        }

      }


    }

    //    comboBoxStart1.setItems(hourList);

  }

  @FXML
  void setupEmployee(Event event) {
    /**
     *
     * Schuelde employee tabw as clicked
     */
    System.out.println("Im here");

    //datePickerWeek.setValue(LocalDate.now());
    if (tabEmployeeSchedule.isSelected()) {
//    buttonConfirmPayment.setDisable(true);
      //System.out.println("TABLCICK");
      try { //if ViewRoomTab is clicked, then tabPayment is disabled
        //  datePickerWeek.setDayCellFactory(dayCellFactory);
      } catch (NullPointerException exception) {
        System.out.println("Error."
        );

      }
      try {

      } catch (NullPointerException exception) {

      }


    }

    comboBoxes = new ArrayList<ComboBox>(Arrays.asList(
        comboBoxEnd1, comboBoxEnd2, comboBoxEnd3, comboBoxEnd4, comboBoxEnd5, comboBoxEnd6,
        comboBoxEnd7,
        comboBoxStart1, comboBoxStart2, comboBoxStart3, comboBoxStart4, comboBoxStart5,
        comboBoxStart6,
        comboBoxStart7
    ));
    comboBoxesStart = new ArrayList<ComboBox>(Arrays.asList(
        comboBoxStart1, comboBoxStart2, comboBoxStart3, comboBoxStart4, comboBoxStart5,
        comboBoxStart6,
        comboBoxStart7
    ));

    comboBoxesEnd = new ArrayList<ComboBox>(Arrays.asList(
        comboBoxEnd1, comboBoxEnd2, comboBoxEnd3, comboBoxEnd4, comboBoxEnd5, comboBoxEnd6,
        comboBoxEnd7

    ));

    ArrayList<String> hourList = new ArrayList<>(
        Arrays.asList(

            "Set Hour",
            "6:00 AM",
            "7:00 AM",
            "8:00 AM",
            "9:00 AM",
            "10:00 AM",
            "11:00 AM",
            "12:00 PM",
            "1:00 PM",
            "2:00 PM",
            "3:00 PM",
            "4:00 PM",
            "5:00 PM",
            "6:00 PM",
            "7:00 PM",
            "8:00 PM",
            "9:00 PM",
            "10:00 PM",
            "11:00 PM",
            "12:00 AM",
            "1:00 AM",
            "2:00 AM",
            "3:00 AM",
            "4:00 AM",
            "5:00 AM",
            "Not Available"

        )

    );
    listProperty2.set(FXCollections.observableArrayList(hourList));
    for (ComboBox cb : comboBoxes
    ) {
      cb.itemsProperty().bind(listProperty2);

      //  cb.setItems(hourList);

    }


  }

  @FXML
  void handleUpdate(ActionEvent event) {
    //for testint click on emplyee
    String startDate = comboBoxStart1.getValue();
    String endDate = comboBoxStart2.getValue();
    //  LocalDate dateofWork;
    ArrayList<CustomDate> daysWorkedForEmployee = new ArrayList<>();
    System.out.println("Days of week again..");
    for (int i = 0; i < 7; i++) {
      System.out.println(daysOfSelectedWeek.get(i));


    }

    int i = 0;
    //employeeClickedOn= data.get(0);
    if (daysOfSelectedWeek.isEmpty()) {
      //if this is empty dont do anytihng
      System.out.println("im empy pick a date yo");


    } else {
      daysWorkedForEmployee = employeeClickedOn.getWorkDays();
      for (LocalDate localDate : daysOfSelectedWeek
      ) {
        startDate = String.valueOf(
            comboBoxesStart.get(i).getValue()); //get start date from comboboxes of start dates
        endDate = String
            .valueOf(comboBoxesEnd.get(i).getValue());//get start date from comboxes of end date
        //startDate = String.valueOf("7:00"); //get start date from comboboxes of start dates
        //endDate =String.valueOf("8:00");//get start date from comboxes of end date
        // dateofWork = localDate;
        if (employeeClickedOn.checkIfWorkDayExists(localDate)) {
          //if tue make new custom date

          //Trying to substract two Hours
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
//            Date dt1 = format.parse(startDate);
//            DateTime dt2 = format.parseDateTime(yourSecondHour);
//            System.out.print(Hours.hoursBetween(dt1, dt2).getHours()).

          // System.out.println("HEY MAKE NEW ONE");
          daysWorkedForEmployee.add(new CustomDate(startDate, endDate, localDate));

        } else {
          //There a date for that in the employee's daysWorked array
          //so I want ot update the custom date with new info
          daysWorkedForEmployee.get(i).setStartHour(startDate);
          daysWorkedForEmployee.get(i).setEndHour(endDate);


        }
        System.out.println(daysWorkedForEmployee.get(i).getDate() + "\n" +
            daysWorkedForEmployee.get(i).getStartHour() + "\n" +
            daysWorkedForEmployee.get(i).getEndHour() + "\n"
        );

        i++;//increment i for next index
      }
      employeeClickedOn.setWorkDays(daysWorkedForEmployee);
      System.out.println(daysWorkedForEmployee.size());
    }

    /**
     * Okay I enter the datese, then i click this button
     * for loop
     * I need each startdate and each end datef rom the Comboboxes
     * I also need the date of of the start and end date
     */

  }

  @FXML
  void handleAddButton() {
    /**
     * When user click add button it the program will move to the Add employee tab
     */
    tabAddEmployee.setDisable(false);
    tabPane.getSelectionModel().select(tabAddEmployee);

  }

  @FXML
  void handleSubmit() {
    /**
     * When user tries to create an Employee this method will run
     * It will check if the inputs are valud
     * Emp Name needs to be a string
     * EMP ID needs to be a int or it will it throw an error
     * EMP Payroll needs to be a double or it will throw an error
     * Added try and catches to catch error and tell user what to fix
     *
     */
    Boolean successful11 = true;
    String empName = textFieldEmpName.getText();
    int empID = 0;
    Double empPayRoll = 0.0;
    System.out.println(textFieldEmpPayRoll.getText());
    //labelSuccessSubmit.setText("WHATTHE");

    try {
      empID = Integer.parseInt(textFieldEmpID.getText());
    } catch (NumberFormatException exception) {
      successful11 = false;
      labelSuccessSubmit.setText("Employee ID Input Error.");
    }

    Boolean successful12 = true;
    try {

      empPayRoll = Double.parseDouble(textFieldEmpPayRoll.getText());
    } catch (NullPointerException exception) {
      successful12 = false;
      labelSuccessSubmit.setText("Payroll Input Error.");
    }
    if (successful11 == true && successful12 == true) {
      /**
       * Allow Employee to be sumbited to employeelist and added to the tableView
       */
      //correct inputs, allow submittion
      labelSuccessSubmit.setText("Employee added succesfully!");
      data.add(new Employee(empName, empPayRoll, empID));

    }


  }

  @FXML
  void handleDeleteEmployee() {
    /**
     * This deletes the employee was that seleceted
     */
    if (employeeClickedOn == null) {
      //No employee was clicked, so dont do anything
    } else {
      data.remove(employeeClickedOn);
      System.out.println(employeeClickedOn.getName() + "IS GONE");
      employeeClickedOn = null;
    }


  }

  @FXML
  void handleAdd() { //No Longer used
    System.out.println("add");
    data.add(new Employee(
        "Mark Jackson",
        12.3,
        6));
    // Employee createemp = new Employee("Joe Jackson", "12.0","6");
    //   employees.add(createemp);

  }


  @FXML
  public void handleSignout(ActionEvent event) {

    Global.currentScene = signoutButton.getScene();//

    new Global().openNewWindow(WindowLocation.LOGINMENU);

    int i = 0;
    //Check is lists are same
    for (String word : usernameList) {
      System.out.println("Name = " + word);
      System.out.println(passwordList.get(i));
      i++;
    }

    for (Employee e : data
    ) {

      System.out.println(e.getName());
    }


  }


  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    this.usernameList = Global.usernameList;
    this.passwordList = Global.passwordList;
    this.guestList = Global.guestList;
    this.rooms = Global.rooms;
    this.data = Global.data;
    /**
     * In this initalizer, it happens after the constructor
     * Here we will add items ot the TableView Emplyees and the ListView of Rooms
     * It will make a defeault set if both of those are empty.
     *
     */
    tabAddEmployee.setDisable(true);
    System.out.println("HERE IN INTAILIZE MANAGER");
    // data =;
    if (data.isEmpty()) {
      //Make default table of employees
      System.out.println("DATA EMPTY\n\n\n\n");
      data =
          FXCollections.observableArrayList(
              new Employee("Jacob", 12.3, 1, "Associate", "7:00 PM", "8:00PM"),
              new Employee("Isabella", 13.8, 2, "Associate", "7:00 PM", "8:00PM"),
              new Employee("Ethan", 12.8, 3, "Hospitality Agent", "N/A", "N/A"),
              new Employee("Emma", 12.9, 4, "Hospitality Agent", "N/A", "N/A"),
              new Employee("Michael", 15.0, 5, "Hospitality Agent", "N/A", "N/A")
          );

    }
    System.out.println("INTY");
//        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                data.add(new Employee(
//                    addFirstName.getText(),
//                    addLastName.getText(),
//                    addEmail.getText()
//                ));
//                addFirstName.clear();
//                addLastName.clear();
//                addEmail.clear();
//            }
//        });
//        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                data.add(new Employee(
//                  "Mark Jackson",
//                   "12.3",
//                    "6"
//                ));
//            }
//        });
    //columnEmployeeID.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
    //columnNameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
    //columnPayroll.setCellValueFactory(new PropertyValueFactory<Employee, String>("active"));
    //TableColumn firstNameCol = new TableColumn("First Name");
    // columnEmployeeID.setMinWidth(100);
    /**
     * This fills the Column name EmployeeNAme with DataFac
     * Need to look up how setCellValueFactories work
     *
     */
    columnNameEmployee.setCellValueFactory(
        new PropertyValueFactory<Employee, String>(
            "name")); //What goes in the () is the name of the Variable in the Employe class

    //TableColumn lastNameCol = new TableColumn("Last Name");
    //  columnNameEmployee.setMinWidth(100);
    /**
     * This fills the Column payroll with Data
     * Need to look up how setCellValueFactories work
     *
     */
    columnPayroll.setCellValueFactory(
        new PropertyValueFactory<Employee, Double>("payHourly"));

    //TableColumn emailCol = new TableColumn("Email");
    //columnPayroll.setMinWidth(200);
    /**
     * This fills the Column name EmployeeID with Data
     * Need to look up how setCellValueFactories work
     *
     */
    columnEmployeeID.setCellValueFactory(
        new PropertyValueFactory<Employee, Integer>("employeeID"));

    /**
     * This fills the table with the employee list named data
     *
     */
    tableViewEmployees.setItems(data);
    // tableViewEmployees.getColumns().addAll(columnEmployeeID, columnPayroll, columnEmployeeID);
    // TODO
    // tableViewEmployees.setOnMousePressed((MouseEvent event) );
    tableViewEmployees.setOnMouseClicked(new EventHandler<MouseEvent>() {
      /**
       * This will add a MouseClick event listener to the TableView Employees
       * that will check if a Employee is selected.
       *
       */
      @Override
      public void handle(MouseEvent event) {
        String employeeSelected = tableViewEmployees.getSelectionModel().getSelectedItem()
            .toString();
        //System.out.println("To string:"+roomSelected.toString());

        for (Employee e : data
        ) {
          if (e.toString().equalsIgnoreCase(employeeSelected)) {
            employeeClickedOn = e;
            System.out.println("Room: " + employeeClickedOn.getName());
            //Same toString same, set currentRoom to r

          }
          // System.out.println(r.getName());
        }
        System.out.println("Here is : " + employeeClickedOn);
        // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

        //Check Room Avability.
        //displayRoomFeatures(roomClickedOn);

      }

    });

    setupEmployeeSchedule();

/**
 * See Guest Menu for Notes, exactly the same
 *
 */

//    if (rooms.isEmpty()) {
  //    System.out.println("ROOM OS EMPTY \n\n\n");
    //  rooms.add(new Room("1A", false, 200));
      //rooms.get(0).setAvailable(false);
//      rooms.get(0).setPictureUrl("sample/Pictures/Room1A.jpg"); //Set Picture URL
//      rooms.get(0).setOccupiedGuest(new Guest("BruceWayne", "batman"));//John Doe occupies room 1A
//      rooms.add(new Room("Room 2A", true, 300));
//      rooms.get(1).setAvailable(true);
//      rooms.get(1).setPictureUrl("sample/Pictures/Room2A.jpg"); //Set Picture URL
//      rooms.add(new Room("Room 3A", false, 500));
//      rooms.get(2).setAvailable(false);
//      rooms.get(2).setOccupiedGuest(new Guest("ClarkKent", "superman"));//John Doe occupies room 1A
//      rooms.get(2).setPictureUrl("sample/Pictures/Room3A.jpg"); //Set Picture URL
    }

//    roomListView.itemsProperty().bind(listProperty);

//    listProperty.set(FXCollections.observableArrayList(rooms));
//    roomListView.setCellFactory(new RoomCellFactory());
    /**
     * Makes a listener so we can know what the user seleceted
     *
     */
//    roomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
/*
      @Override
      public void handle(MouseEvent event) {
        String roomSelected = roomListView.getSelectionModel().getSelectedItem().toString();
        //System.out.println("To string:"+roomSelected.toString());

        for (Room r : rooms
        ) {
          if (r.toString().equalsIgnoreCase(roomSelected)) {
            roomClickedOn = r;
            System.out.println("Room: " + roomClickedOn.getName());
            //Same toString same, set currentRoom to r

          }
          // System.out.println(r.getName());
        }
        System.out.println("Here iszzz : " + roomSelected);
        // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

        //Check Room Avability.
        displayRoomFeatures(roomClickedOn);

      }

    });

  }
*/
  @FXML
  private void handleRemoveGuest() {
    /**
     * Removes Guest from listViewRooms
     *
     */
    System.out.println("REmvoing Guest");
    if (roomClickedOn != null) {
      //So theres a room clicked on
      roomClickedOn.removeGuest();
      displayRoomFeatures(roomClickedOn);

    }


  }


  private void displayRoomFeatures(Room roomClickedOn) {
    /**
     * Same as GuestRoomController
     *
     */
    if (roomClickedOn.getAvailable()) {
      //true
      labelAvailable.setText("Not Book");
      //bookRoombutton.setDisable(false);

    } else {
      labelAvailable.setText("Booked");
      //bookRoombutton.setDisable(true);

    }
    Image image = new Image(roomClickedOn.getPictureUrl());
    imageViewRoom.setImage(image);
    labelPricePerDay.setText(Double.toString(roomClickedOn.getPrice()));

    labelDaysStaying.setText(Integer.toString(roomClickedOn.getDaysStaying()));

    try {
      labelGuestName.setText(roomClickedOn.getOccupiedGuest().getUserName());

    } catch (NullPointerException exception) {
      labelGuestName.setText("None");

    }
  }

//        public void EmployeeWindow(){
//            System.out.println("HERE IN INTAILIZE MANAGER");
//
//            if (employees.isEmpty())
//            {
//                System.out.println("Employees is empty \n\n\n");
//                employees.add(new Employee("Brad",9.5,1));
//
//                employees.add(new Employee("Tom",11.5,2));
//                employees.add(new Employee("Pete",8.0,3));
//
//            }

  //     tableViewEmployees.itemsProperty().bind(listProperty);
  //       columnEmployeeID.setCellFactory(new PropertyValueFactory<Employee, String>("EmployeeID"));

  //tableViewEmployees.set(FXCollections.observableArrayList(employees));
  // tableViewEmployees.setCellFactory(new RoomCellFactory());
  //   tableViewEmployees.setOnMouseClicked(new EventHandler<MouseEvent>() {

//                @Override
//                public void handle(MouseEvent event) {
//                    String employeeSelected = tableViewEmployees.getSelectionModel().getSelectedItem().toString();
//                    //System.out.println("To string:"+roomSelected.toString());
//
//                    for (Employee emp: employees
//                    ) {
//                        if (emp.toString().equalsIgnoreCase(employeeSelected)){
//                            employeeClickedon= emp;
//                            System.out.println("Room: "+roomClickedOn.getName());
//                            //Same toString same, set currentRoom to r
//
//                        }
//                        // System.out.println(r.getName());
//                    }
//                    System.out.println("Here iszzz : "+roomSelected);
//                    // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());
//
//                    //Check Room Avability.
//                    displayRoomFeatures(roomClickedOn);
//
//                }

}


