/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.LoginMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.GuestMenu.GuestRoomController;
import sample.ManagerMenu.ManagerMenuController;
import sample.Room;

/**
 * Notes
 * Chck the text file of fxml files, they will sometimes have redtext/eerors which IDEA IDE will not tell you
 * Make sure there no java.awt as it will case Errors
 * This classes was foremly named FXMLCONTROLLER.java
 *
 * LoginMenuController.java
 * Notes by:pPetit
 * This is bascially the "main" of the project.
 * This houses the login screen: Guest Login and Manager Login and also Signup Login
 * Signup Login opens a new window where you can can create a new account, firsttime users do this.
 * To login to guest, you  have to enter a Username and Password, it has to match a created Account first.
 * Usernames aren't case sensitive but passwords are.
 *
 * For Manager: The default Login in U: Nicolo P: Martina
 * Same case sensitivity,
 * For extra functionality, you can show/hide passwords.
 * There is also a toggle for show Password Field on both Manager and Guest.
 * This code is done by having a password txtfield and a regular txtfield in the same place and making thme hidden.
 * See more in the initialize portion.
 *
 */


/**
 *
 * @author ggrab
 */

public class LoginMenuController extends Controller implements Initializable {

  /**
   * Every Controller should have the fields it uses on the top.
   * It can update its current data in it's constructor, or in some cases initialize.
   */
   // private ArrayList<String> usernameList = new ArrayList<>(); //ArrayList of username Fields
   private ArrayList<String> usernameList;
  // private ArrayList<String> usernameList =super.getUsernameList();
  public List<Room> rooms;
  private ArrayList<String> passwordList;
  private ArrayList<Guest> guestList;
  private Guest currentGuest;
  private Room roomClickedOn;
  private int daysStaying;
  private boolean initializedRooms=false;
  private ObservableList<Employee> data;



  enum UserType{ //not used
        GUEST,MANAGER

    }

    private void updateGlobal(){
      /**
       * UpdateGlobal is a method to change the Global class's values
       * ArrayLists seem to update automically if you make a copy of them
       * but Strings and Ints dont, so before moving to a new window you need to update Global Values
       *
       */
      Global.currentGuestLoggedIn = currentGuest; //Needed so GuestMenu knows who logged in.
    }

@FXML
private CheckBox checkBox; //CheckBo for handling showPassword


  public void displayPopUpWindow(String message){
    final Stage myDialog = new Stage();
    myDialog.initModality(Modality.WINDOW_MODAL);


    Button okButton = new Button("OK");
    okButton.setOnAction(new EventHandler<ActionEvent>(){

      @Override
      public void handle(ActionEvent arg0) {
        myDialog.close();
      }

    });

    Scene myDialogScene = new Scene(VBoxBuilder.create()
        .children(new Text(message), okButton)
        .alignment(Pos.CENTER)
        .padding(new Insets(10))
        .build());
    myDialog.initOwner(buttonCreate.getScene().getWindow()); //Set this to the parent of the opup window
    myDialog.setScene(myDialogScene);
    myDialog.showAndWait(); //USE showAndWait to wait for the popto close
    //REgular wait will ignore modality and will call second window regardlessly.
  }
    @FXML
  public void storeVariables1(){
      /**
       * To send and store variables across windows.
       * Other Controllers will call this method and send their lists, so the whole programs are sharing
       * the same data.
       */
      //
     //These are done in initialize
//    this.usernameList = Global.usernameList;
//    this.passwordList = Global.passwordList;
//    this.guestList = Global.guestList;
//    this.rooms = Global.rooms;
//    this.data = Global.data;
    int i=0;
    for (String word :usernameList){ //Show UserList to check if correct data is stored
      System.out.println("Name = " + word);
      System.out.println(passwordList.get(i));
      i++;
    }
      for (Room r: rooms) {

        System.out.println("Room:" +r.getName()
            + " Price "+ r.getPrice()
            + " Avail: "+r.getAvailable()
        //    + " Guest Name: "+r.getOccupiedGuest().getUserName()
        );
        // System.out.println(r.getName());
        if (r.getOccupiedGuest() ==null)
        {
          System.out.println("NULL GUEST");
        }else{
          System.out.println("Guest name"+r.getOccupiedGuest().getUserName());
        }
      }

    }


    public  void openGuestMenu(Guest g1) throws IOException {
      Global.currentScene = buttonExit1.getScene();//

    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
    }


    void checkLogin(String usernameSent, String passwordSent)
    {
      /**
       * Login Button calls this method
       * This will check if the user inputted username and password match one  of the arraylist Record
       * Will give back different errors in the console
       * If successful than a guest is chosen and that Guest will be altered in the GuestMenu Window
       *
       */
        // usernameSent= usernameSent.toLowerCase();
        //passwordSent= passwordSent.toLowerCase();
        boolean isNameCorrect=false;
        boolean isPasswordCorrect=false;

       guestList.add(new Guest(usernameSent,passwordSent));
        for (Guest g1: getGuestList()) {

            if(g1.getUserName().equalsIgnoreCase(usernameSent)){
                //they match!
                isNameCorrect= true;
                //now check password


              if (g1.getPassword().equals(passwordSent)){
                    try {

                      //Both Username and Password match now to open Guest Menu
                      displayPopUpWindow("Login Successful!");//Opens PopUp window to show user successful login.
                        openGuestMenu(g1); //Open Guest Menu, send g1
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Successful!
                    System.out.println("Login Succesful");
                    isPasswordCorrect= true;
                    break;
                }


            }


        }
        //Make a Label for this?
        if (isNameCorrect == false && isPasswordCorrect == false){
          labelLoginSuccess.setText("Error, login credentials not found");
            //System.out.println("Error, login credentials not found");
        }else if (isNameCorrect==true && isPasswordCorrect== false){
          labelLoginSuccess.setText("Error wrong password.");
            System.out.println("Error wrong password.");
        }else {

         // labelLoginSuccess.setText("Success");

        }


    }


    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(ArrayList<Guest> guestList) {
        this.guestList = guestList;
    }

    public ArrayList<String> getUsernameList() {
        return usernameList;
    }

    public ArrayList<String> getPasswordList() {
        return passwordList;
    }

    public void setUsernameList(ArrayList<String> usernameList) {
        this.usernameList = usernameList;
    }

    public void setPasswordList(ArrayList<String> passwordList) {
        this.passwordList = passwordList;
    }

    public void createGuest(String n, String pw){
        Guest createGuest = new Guest(n,pw); //Calls method to create new Guest option nothing special

        getUsernameList().add(n);// No need for method this already adds it in
        getPasswordList().add(pw);
        getGuestList().add(createGuest);

    }



    @FXML
    private Button buttonCreate;
    @FXML
    private Tab tabManager;
    @FXML
    private Tab tabGuest;

    @FXML
    private Button buttonLogin;

    @FXML
    private Label labelLogin;

    @FXML
    private TextField textfieldUsername;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button buttonExit;

    @FXML
    private TextField textFieldUsernameManager;

    @FXML
    private PasswordField passwordFieldManager;

    @FXML
    private Button buttonManagerLogin;

    @FXML
    private Button buttonExit1;

    @FXML
    private Label labelManagerLogin,labelLoginSuccess;





    @FXML
    void handleButtonExit(ActionEvent event) {
      /**
       * Exit Program, closes window
       */
        System.out.println("Goodbye");
      Stage stageExit = (Stage) buttonExit.getScene().getWindow();
      stageExit.close();
    }

    @FXML
    void handleButtonLogin(ActionEvent event) {

        String field1, field2;//Declare Strings
        field1 = textfieldUsername.getText();//Store what in txtField Username into field1
        field2 = passwordField.getText();//Store whats in txtFieldPassword into field2
        //UserType tyoe= UserType.GUEST; Not used

        checkLogin(field1,field2);//Calls method to check if inputs are valid






    }

    @FXML
    void handleButtonUsername(ActionEvent event) {

    }

    @FXML
    void handleButtonLoginManager(ActionEvent event){
      //see HandleButtonLogin for guests, basically same code
        System.out.println("MAngaer");
        boolean isNameCorrect=false;
        boolean isPasswordCorrect=false;
        String field1, field2;
        field1 = textFieldUsernameManager.getText();
        field2 = passwordFieldManager.getText();

        String managerUsername = "Nicolo";
        String managerPassword = "Martina";
        //UserType tyoe= UserType.MANAGER;
        if (field1.equalsIgnoreCase(managerUsername)){
            isNameCorrect= true;
            if (field2.equals(managerPassword)){
                isPasswordCorrect= true;
                System.out.println("Login Manager");



            }

        }
        if (isNameCorrect == false && isPasswordCorrect == false){
            System.out.println("Error, Sorry login credentials not found");
        }else if (isNameCorrect==true && isPasswordCorrect== false){
            System.out.println("Password Incorrect");
        }else {
            System.out.println("Successful Login");
        }

      openManagerMenu();
    }

  private void openManagerMenu() {
  //  ManagerMenuController guestController = new ManagerMenuController(
    //    usernameList,passwordList,guestList,rooms
    //);
    //guestController.storeVariables(usernameList,passwordList,guestList,g1,rooms);
    //FXMLLoader loader = new FXMLLoader();


//  FXMLLoader Loader = new FXMLLoader();
 // Loader.setLocation(getClass().getResource("ManagerMenuController.fxml")); //Call new window
   // try {
    //  Loader.setController(guestController);
     // Loader.load(); //Loads
    //}catch ( IOException ex){
     // Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null ,ex);

    //}
//      DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
//     display.setText(name_Text,email_Text); //using displaytextcontroller's method
//      GuestRoomController storeFields =Loader.getController(); //Calling the new window's  controller
//     storeFields.storeVariables(usernameList,passwordList,guestList,g1,rooms); //Calling the controller's method
//    Store Variables will store the lists of users, passwords and guest
//    GuestMenu's store Variables also takes the guest selected by user.
/**
 * See Guest Controller open for Notes, since it's basically the same
 *
 */

    updateGlobal();
    ManagerMenuController managerController = new ManagerMenuController();
    Stage stageExit = (Stage) buttonExit1.getScene().getWindow();//
    stageExit.close();//Closes curerent Stage

    FXMLLoader Loader = new FXMLLoader(); //sample.LoginMenu.LoginMenuController
    Loader.setLocation(getClass().getResource("/sample/ManagerMenu/ManagerMenu.fxml")); //Call new window
    try {
      Loader.setController(managerController);
      Loader.load(); //Loads
    }catch ( IOException ex){
      Logger.getLogger(ManagerMenuController.class.getName()).log(Level.SEVERE, null ,ex);

    }


//    FXMLLoader Loader = new FXMLLoader();
//    Loader.setLocation(getClass().getResource("ManagerMenu.fxml"));
//    try {
//      Loader.load();
//    }catch ( IOException ex){
//      Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null ,ex);
//
//    }
//    ManagerMenuController storeFields =Loader.getController(); //Calling the new window's  controller
// storeFields.storeVariables2(usernameList,passwordList,guestList,rooms); //Calling the controller's method
    //Dont' do these two lines again. What happens first is Controller's intilize with makez default 3 rooms.
    //And then it gets set with the empty room the second send it cassing error. We have a better way doing this.

    Parent p = Loader.getRoot();
    Stage stage = new Stage();
    stage.setTitle("Manager Menu");
    stage.setScene(new Scene(p));
    stage.show(); //Opens
  }
  @FXML
  CheckBox checkBox2;
  @FXML
  TextField textFieldForPassword;
    @FXML TextField textFieldForPasswordManager;
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /**
     * This method is called first when FXMLDOCUEMENTCONTROLLER IS opened.
     * It will set the properties for passwordFields to able to show/hide
     * In SceneBuilder I made a textfield adn a password field on the same spot, the user won't notice
     * the difference.
     *
     *
     * And also it will take the data of Global class so data stays consistent between Controllers.
     */
    this.usernameList = Global.usernameList;
    this.passwordList = Global.passwordList;
    this.guestList = Global.guestList;
    this.rooms = Global.rooms;
    this.data = Global.data;

  //  guestList.add(new Guest("Hi","ko"));
   // System.out.println(Global.guestList.get(0));

    // text field to show password as unmasked
    //final TextField textFieldForPassword = new TextField(); //REgular textfield show characters
    //System.out.println(passwordField.getLayoutY());
    //System.out.println(passwordField.getLayoutX());
   // textFieldForPassword.setLayoutY(passwordField.getLayoutY());
   // textFieldForPassword.setLayoutX(passwordField.getLayoutX());
    // Set initial state
    textFieldForPassword.setManaged(false);
    textFieldForPassword.setVisible(false);//Hide regular textfield

    textFieldForPasswordManager.setManaged(false);
    textFieldForPasswordManager.setVisible(false);//Hide regular textfield

    textFieldForPasswordManager.managedProperty().bind(checkBox2.selectedProperty());// TextField's setManageProperty will be changed by CheckBox
    textFieldForPasswordManager.visibleProperty().bind(checkBox2.selectedProperty());// TextField's setVisibleProperty will be changed by CheckBox

    passwordFieldManager.managedProperty().bind(checkBox2.selectedProperty().not());//Same as above but oppsite?
    passwordFieldManager.visibleProperty().bind(checkBox2.selectedProperty().not());

    // Bind the textField and passwordField text values bidirectionally.
    textFieldForPasswordManager.textProperty().bindBidirectional(passwordFieldManager.textProperty()); //MAkes two textfie

    // Actual password field
    //final PasswordField passwordField = new PasswordField();//Password Field shows ***

    // CheckBox checkBox = new CheckBox("Show/Hide password");//Create checkbox that will toggle

    // Bind properties. Toggle textField and passwordField
    // visibility and managability properties mutually when checkbox's state is changed.
    // Because we want to display only one component (textField or passwordField)
    // on the scene at a time.
    textFieldForPassword.managedProperty().bind(checkBox.selectedProperty());// TextField's setManageProperty will be changed by CheckBox
    textFieldForPassword.visibleProperty().bind(checkBox.selectedProperty());// TextField's setVisibleProperty will be changed by CheckBox

    passwordField.managedProperty().bind(checkBox.selectedProperty().not());//Same as above but oppsite?
    passwordField.visibleProperty().bind(checkBox.selectedProperty().not());

    // Bind the textField and passwordField text values bidirectionally.
    textFieldForPassword.textProperty().bindBidirectional(passwordField.textProperty()); //MAkes two textfields share share same input
    //If this code isn't there the two textfields are seperate

  }


  @FXML
    void handleButtonCreate(ActionEvent event){
    /**
     * Will be deleted and changed to the signupWindow
     *
     */
//        textfieldUsername.requestFocus(); //Can be used if user entered something wrong
//        textfieldUsername.selectAll();//Highlights the text so user can easily fix it wiothut mouse click
//
//        System.out.println(field1+" "+field2);
        Guest createGuest;
        if (guestList.isEmpty()){
          //Since first time running this code, make default guestlist
         // createGuest(field1,field2);
          createGuest("john doe","password");
          createGuest("jane doe","qwerty");
          createGuest("Juan Doe","asdf");
        }else{
         // createGuest(field1,field2);
        }
        int i=0;
      System.out.println("\n");
        for (String word :getUsernameList()){
            System.out.println("Name = " + word);
            System.out.println(getPasswordList().get(i));
            i++;
        }
    //    currentGuest= getGuestList().get(1);
    Stage stage = (Stage) buttonCreate.getScene().getWindow(); //Asks a object in the window to store it's WindowID
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();
    //Using Global's Enum named WindowLocation get the Url for the EnumType
    String url = WindowLocation.SIGNUP.getLocation();
    //load the url you just acquired.
    Loader.setLocation(getClass().getResource(url));
    try {
      // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
      Loader.load(); //Loads
    }catch ( IOException ex){
      Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null ,ex);

    }

    Parent p = Loader.getRoot();
    stage = new Stage();
    stage.setTitle("Create Account");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window

    }

}


//
//    public  void openGuestMenu(Guest g1) throws IOException {
//        try {
//            Stage stage = (Stage) buttonCreate.getScene().getWindow();
//            stage.close();
//            Parent root2 = FXMLLoader.load(getClass().getResource("GuestRoom.fxml" ));
//
//            stage = new Stage();
//
//            stage.setScene(new Scene(root2));
//            stage.show();
//
//
//
//        }finally {
//
//        }
//    }