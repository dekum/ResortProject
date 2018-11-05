package sample.EventMenu;


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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Event;
import sample.Event.EventCellFactory;
import sample.Global;
import sample.Global.WindowLocation;
import sample.GuestMenu.GuestRoomController;
import sample.Meeting;
import sample.Room;
import sample.Spa;
import sample.Wedding;

public class EventMenuHomeController implements Initializable {

  ArrayList<Event> events = new ArrayList<>();
  static ArrayList<Event> sortList = new ArrayList<>();

  @FXML
  Button buttonCreate, buttonSort,buttonResetList;
  @FXML
  ListView<Event> listViewEvent = new ListView<>();
  @FXML
  ListProperty<Event> listProperty = new SimpleListProperty<>();
  ListProperty<Event> listProperty2 = new SimpleListProperty<>();
  @FXML
  ComboBox<Event> comboBoxSort;
  @FXML void handleComboSort (ActionEvent event){
    Event choseEvent = comboBoxSort.getValue();
    showOnlyType(events, choseEvent.getClass());
    listProperty.set(FXCollections.observableArrayList(sortList));
    listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting
  }


  @FXML
  void handleCreate(ActionEvent event) {
    Stage stageExit = (Stage) buttonCreate.getScene().getWindow(); //Select an object in this window
    stageExit.close(); //close current window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();
    Loader.setLocation(
        getClass().getResource(WindowLocation.EVENTCREATE.getLocation())); //Call new window

    try {
      Loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null, ex);

    }
    //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
    // display.setText(name_Text,email_Text); //using displaytextcontroller's method
    //LoginMenuController storeFields =Loader.getController();
//    storeFields.storeVariables1(); //This method will stoer the variables

    Parent p = Loader.getRoot();
    Stage stage = new Stage();
    stage.setTitle("View Rooms -" + Global.currentGuestLoggedIn.getUserName()); //set title
    stage.setScene(new Scene(p));
    stage.show();//Open new window


  }
  @FXML
  void handleRestoreList(ActionEvent event){

    listProperty.set(FXCollections.observableArrayList(events));
    listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting


  }

  @FXML
  static ArrayList<Event> showOnlyType(List<?> list, Class<? extends Event> c){
    sortList.clear();
    for (Object o : list){
      if (c.isInstance(o)) { //Check if Object's class is a
        //Class Matches
        System.out.println(o.toString());
       sortList.add((Event) o);


      }


    }
    return sortList;

  }

  @FXML
  void handleSort(ActionEvent event){
    //listViewEvent.itemsProperty().bind(listProperty);
    sortList= showOnlyType(events,Spa.class);

    listProperty.set(FXCollections.observableArrayList(sortList));
    listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (events.isEmpty()) {
      //No events make a default list
      Event createEvent = new Event("Royal Ball");
      events.add(createEvent);
      createEvent = new Event("ZombieFest");
      events.add(createEvent);
      createEvent = new Event("Magic Kingdom!");
      events.add(createEvent);
      events.add(new Spa("Spa event"));
      events.add(new Wedding("Wedding event"));
      events.add(new Meeting("Meeting event"));
      events.add(new Spa("Spa event 2"));

      listViewEvent.itemsProperty().bind(listProperty);
      listProperty.set(FXCollections.observableArrayList(events));
      listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting
      ArrayList<Event> listofElements = new ArrayList<>();
      listofElements.add(new Spa("ShowSpa"));
      listofElements.add(new Meeting("ShowMeeting"));
      listofElements.add(new Wedding("showweeding"));
     // comboBoxSort.setItems(listProperty2);
      comboBoxSort.itemsProperty().bind(listProperty2);
      listProperty2.set(FXCollections.observableArrayList(listofElements));
      comboBoxSort.setCellFactory(new EventCellFactory());

    }
    //listViewEvent.setItems(buttonCreate);
  }
}
