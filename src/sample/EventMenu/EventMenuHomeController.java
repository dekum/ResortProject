package sample.EventMenu;

/**
 * EventMenuHomeController.java
 * This controllers allows user to see the events made by Manager
 * the lsit view shows Events created by the manager
 * Guests can also create their event, but they arent public to all users
 * This controlls has a lsitView, and a combobox for sorting by type (This can be used in other controllers
 *
 *
 */


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

  ArrayList<Event> events; //= new ArrayList<>();
  static ArrayList<Event> sortList = new ArrayList<>();


  @FXML
  Button buttonCreate, buttonSort,buttonResetList,buttonExit;
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

@FXML  void handleExit(ActionEvent event){
    Global.currentScene = buttonCreate.getScene();
    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
}

  @FXML
  void handleCreate(ActionEvent event) {
    Global.currentScene = buttonCreate.getScene();//

    new Global().openNewWindow(WindowLocation.EVENTCREATE);



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
    /**
     * May delete this method
     */
    //listViewEvent.itemsProperty().bind(listProperty);
    sortList= showOnlyType(events,Spa.class); //alls showOnlyType so it only shows objects of Spa class

    listProperty.set(FXCollections.observableArrayList(sortList));
    listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    events = Global.eventList;
    if (events.isEmpty()) {
      /**
       * This is called first before the window is shown
       * use this to initalize class variables so they are equal to global
       *
       */
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
    }

      listViewEvent.itemsProperty().bind(listProperty); //Bind a list property to listview
      listProperty.set(FXCollections.observableArrayList(events)); //Set the elements in the list
      listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting
      ArrayList<Event> listofElements = new ArrayList<>();
      listofElements.add(new Spa("ShowSpa"));
      listofElements.add(new Meeting("ShowMeeting"));
      listofElements.add(new Wedding("showweeding"));
     // comboBoxSort.setItems(listProperty2);
      comboBoxSort.itemsProperty().bind(listProperty2); //Bind a list property to the combobox
      listProperty2.set(FXCollections.observableArrayList(listofElements)); //Add a list to the combobox
      comboBoxSort.setCellFactory(new EventCellFactory());/// allows cells to be formatted


    //listViewEvent.setItems(buttonCreate);
  }
}
