/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.ManagerMenu;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.Action;
import sample.Controller;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.GuestMenu.GuestRoomController;
import sample.LoginMenu.LoginMenuController;
import sample.Room;
import sample.Room.RoomCellFactory;

/**
 * FXML Controller class
 *
 * ManagerMenuConroller.java
 * Notes by: pPetit
 *
 * Manager Window Probably Most Important
 * Manager Can:
 * View Employees
 * Add Employees/Delete Emplyee
 * View Rooms
 * Remove People from rooms.
 *
 * With View Employees:
 * Employees are a class of Employee class and are displayed in Table View.
 * Manager can select an Employee and delete them
 * Mananger can select Add Button,and be taken to tabAddEmployee
 * As long as the fields are correct an employee can be successfully Added.
 *
 * View Rooms:
 * Almost indentical to Guest's
 * Manager can also delete Guest from Room.
 *
 * Source for java datePicker info: https://stackoverflow.com/questions/49531768/how-to-fire-an-event-in-datepicker-when-weekday-clicked-in-javafx
 * @author ggraber7402
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
    private boolean initializedRooms=false;
    @FXML
    private TableView<Employee> employeeTable = new TableView<Employee>();//Table View to show employees
    private ObservableList<Employee> data; //This is where Employee List of served
    final HBox hb = new HBox();

    @FXML//Unused
    public void storeVariables2(ArrayList<String> unLIST, ArrayList<String> pwList,
        ArrayList<Guest> gList, List<Room> rooms){
        System.out.println("HERE FIRST?");
        this.usernameList = unLIST;
        this.passwordList = pwList;
        this.guestList = gList;
       // this.currentGuestLoggedIn = g1;
        this.rooms = rooms;
        int i=0;
        for (String word :usernameList){
            System.out.println("Name = " + word);
            System.out.println(passwordList.get(i));
            i++;
        }



    }

    @FXML
    private TabPane tabPane;
    @FXML
    private ImageView imageViewRoom;

    @FXML
    private Label labelAvailable,labelGuestName;
    @FXML
    private Label labelPricePerDay;
    @FXML
    private Label labelDaysStaying;
    @FXML Tab tabEmployeeSchedule;


//  @FXML
//  private ImageView imageViewRoom;
  @FXML ComboBox comboBoxStart1,comboBoxStart2,comboBoxStart3,comboBoxStart4,comboBoxStart5,comboBoxStart6,comboBoxStart7,
    comboBoxEnd1,comboBoxEnd2,comboBoxEnd3,comboBoxEnd4,comboBoxEnd5,comboBoxEnd6,comboBoxEnd7;
  @FXML Label labelDay1,labelDay2,labelDay3,labelDay4,labelDay5,labelDay6,labelDay7;
  @FXML
  DatePicker datePickerWeek;


    @FXML
    private Button signoutButton,buttonRemoveGuest;
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
    protected List<Room> rooms = new ArrayList<>();
    @FXML
    protected ListProperty<Room> listProperty = new SimpleListProperty<>();

 //   @FXML
    //protected List<Employee> employees = new ArrayList<>();
    @FXML
    protected ListProperty<Employee> listPropertyEmployee = new SimpleListProperty<>();


    @FXML private TableView<Employee> tableViewEmployees;
    @FXML private TableColumn<Employee, Integer> columnEmployeeID;
    @FXML private TableColumn<Employee, String> columnNameEmployee;
    @FXML private TableColumn<Employee, Double> columnPayroll;
    @FXML private Button buttonAdd, buttonDelete;

    @FXML private Button buttonSubmit;
    @FXML private TextField textFieldEmpName,textFieldEmpID,textFieldEmpPayRoll;
    @FXML private Label labelSuccessSubmit;
    @FXML private Tab tabAddEmployee;

    @FXML void setupEmployeeSchedule(){
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



    }
    @FXML void handlePickDate(ActionEvent event){
      /**
       * Happens after clicking a date on datePicker
       * THis will tell us what date user selected!
       *
       *
       */
      System.out.println("Here");
      System.out.println(datePickerWeek.getValue());
      LocalDate date = datePickerWeek.getValue();
      TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
      int weekNumber = date.get(woy);
      //System.out.println(weekNumber);
      labelWeek.setText("Week: "+String.valueOf(weekNumber));


    }
    @FXML void setupEmployee(Event event){
      System.out.println("Im here");


      //datePickerWeek.setValue(LocalDate.now());
      if (tabEmployeeSchedule.isSelected()) {
//    buttonConfirmPayment.setDisable(true);
        //System.out.println("TABLCICK");
        try{ //if ViewRoomTab is clicked, then tabPayment is disabled
        //  datePickerWeek.setDayCellFactory(dayCellFactory);
        }catch (NullPointerException exception){
          System.out.println("Error."
          );

        }
        try{

        }catch (NullPointerException exception){

        }



      }


    }

    @FXML void handleAddButton(){
        /**
         * When user click add button it the program will move to the Add employee tab
         */
        tabAddEmployee.setDisable(false);
        tabPane.getSelectionModel().select(tabAddEmployee);

    }
    @FXML void handleSubmit(){
        /**
         * When user tries to create an Employee this method will run
         * It will check if the inputs are valud
         * Emp Name needs to be a string
         * EMP ID needs to be a int or it will it throw an error
         * EMP Payroll needs to be a double or it will throw an error
         * Added try and catches to catch error and tell user what to fix
         *
         */
        Boolean successful11=true;
        String empName= textFieldEmpName.getText();
        int empID=0;
        Double empPayRoll =0.0;
        System.out.println(textFieldEmpPayRoll.getText());
        //labelSuccessSubmit.setText("WHATTHE");

        try{
            empID= Integer.parseInt(textFieldEmpID.getText());
        }catch(NumberFormatException exception){
            successful11=false;
            labelSuccessSubmit.setText("Employee ID Input Error.");
        }

       Boolean successful12=true;
        try{

            empPayRoll =Double.parseDouble(textFieldEmpPayRoll.getText());
        }catch (NullPointerException exception){
            successful12=false;
            labelSuccessSubmit.setText("Payroll Input Error.");
        }
        if (successful11== true && successful12 == true){
            /**
             * Allow Employee to be sumbited to employeelist and added to the tableView
             */
            //correct inputs, allow submittion
            labelSuccessSubmit.setText("Employee added succesfully!");
            data.add(new Employee(empName,empPayRoll,empID ));

        }



    }

    @FXML void handleDeleteEmployee(){
        /**
         * This deletes the employee was that seleceted
         */
        if (employeeClickedOn== null)
        {
          //No employee was clicked, so dont do anything
        }else{
          data.remove(employeeClickedOn);
          System.out.println(employeeClickedOn.getName()+"IS GONE");
          employeeClickedOn= null;
        }




    }

    @FXML
    void handleAdd(){ //No Longer used
        System.out.println("add");
        data.add(new Employee(
            "Mark Jackson",
            12.3,
            6));
       // Employee createemp = new Employee("Joe Jackson", "12.0","6");
     //   employees.add(createemp);



    }












    @FXML
    public void handleSignout(ActionEvent event){

      Global.currentScene = signoutButton.getScene();//

      new Global().openNewWindow(WindowLocation.LOGINMENU);

      int i=0;
        //Check is lists are same
        for (String word :usernameList){
            System.out.println("Name = " + word);
            System.out.println(passwordList.get(i));
            i++;
        }



        for (Employee e: data
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
        if (data.isEmpty()){
            //Make default table of employees
            System.out.println("DATA EMPTY\n\n\n\n");
            data =
                FXCollections.observableArrayList(
                    new Employee("Jacob", 12.3, 1),
                    new Employee("Isabella", 13.8, 2),
                    new Employee("Ethan", 12.8, 3),
                    new Employee("Emma", 12.9, 4),
                    new Employee("Michael", 15.0, 5)
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
            new PropertyValueFactory<Employee, String>("name")); //What goes in the () is the name of the Variable in the Employe class


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
                String employeeSelected = tableViewEmployees.getSelectionModel().getSelectedItem().toString();
                //System.out.println("To string:"+roomSelected.toString());

                for (Employee e: data
                ) {
                    if (e.toString().equalsIgnoreCase(employeeSelected)){
                        employeeClickedOn= e;
                        System.out.println("Room: "+employeeClickedOn.getName());
                        //Same toString same, set currentRoom to r

                    }
                    // System.out.println(r.getName());
                }
                System.out.println("Here is : "+employeeClickedOn);
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

        if (rooms.isEmpty())
        {
            System.out.println("ROOM OS EMPTY \n\n\n");
            rooms.add(new Room("1A",false,200));
            rooms.get(0).setAvailable(false);
            rooms.get(0).setPictureUrl("sample/Pictures/Room1A.jpg"); //Set Picture URL
            rooms.get(0).setOccupiedGuest(new Guest("BruceWayne","batman"));//John Doe occupies room 1A
            rooms.add(new Room("Room 2A",true,300));
            rooms.get(1).setAvailable(true);
            rooms.get(1).setPictureUrl("sample/Pictures/Room2A.jpg"); //Set Picture URL
            rooms.add(new Room("Room 3A",false,500));
            rooms.get(2).setAvailable(false);
            rooms.get(2).setOccupiedGuest(new Guest("ClarkKent","superman"));//John Doe occupies room 1A
            rooms.get(2).setPictureUrl("sample/Pictures/Room3A.jpg"); //Set Picture URL
        }


        roomListView.itemsProperty().bind(listProperty);

        listProperty.set(FXCollections.observableArrayList(rooms));
        roomListView.setCellFactory(new RoomCellFactory());
        /**
         * Makes a listener so we can know what the user seleceted
         *
         */
        roomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String roomSelected = roomListView.getSelectionModel().getSelectedItem().toString();
                //System.out.println("To string:"+roomSelected.toString());

                for (Room r: rooms
                ) {
                    if (r.toString().equalsIgnoreCase(roomSelected)){
                        roomClickedOn= r;
                        System.out.println("Room: "+roomClickedOn.getName());
                        //Same toString same, set currentRoom to r

                    }
                    // System.out.println(r.getName());
                }
                System.out.println("Here iszzz : "+roomSelected);
                // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

                //Check Room Avability.
                displayRoomFeatures(roomClickedOn);

            }

        });

    }
    @FXML
    private void handleRemoveGuest(){
        /**
         * Removes Guest from listViewRooms
         *
         */
        System.out.println("REmvoing Guest");
        if (roomClickedOn != null){
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
        if (roomClickedOn.getAvailable()){
            //true
            labelAvailable.setText("Not Book");
            //bookRoombutton.setDisable(false);

        }else
        {
            labelAvailable.setText("Booked");
            //bookRoombutton.setDisable(true);

        }
        Image image = new Image(roomClickedOn.getPictureUrl());
        imageViewRoom.setImage(image);
        labelPricePerDay.setText(Double.toString(roomClickedOn.getPrice()));

        labelDaysStaying.setText(Integer.toString(roomClickedOn.getDaysStaying()));

        try {
            labelGuestName.setText(roomClickedOn.getOccupiedGuest().getUserName());

        }catch (NullPointerException exception){
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


