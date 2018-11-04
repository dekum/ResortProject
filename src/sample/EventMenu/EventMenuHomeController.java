package sample.EventMenu;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Event;
import sample.Global;
import sample.Global.WindowLocation;
import sample.GuestMenu.GuestRoomController;
import sample.Room;

public class EventMenuHomeController implements Initializable {
  ArrayList<Event> events = new ArrayList<>();
@FXML
Button buttonCreate;
@FXML
  ListView<Event> listViewEvent = new ListView<>();
@FXML ListProperty<Event> listProperty = new SimpleListProperty<>();
@FXML void handleCreate(ActionEvent event){
  Stage stageExit = (Stage) buttonCreate.getScene().getWindow(); //Select an object in this window
  stageExit.close(); //close current window


  //Loads FXML Loader
  FXMLLoader Loader = new FXMLLoader();
  Loader.setLocation(getClass().getResource(WindowLocation.EVENTCREATE.getLocation())); //Call new window

  try {
    Loader.load(); //Loads
  }catch ( IOException ex){
    Logger.getLogger(GuestRoomController.class.getName()).log(Level.SEVERE, null ,ex);

  }
  //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
  // display.setText(name_Text,email_Text); //using displaytextcontroller's method
  //LoginMenuController storeFields =Loader.getController();
//    storeFields.storeVariables1(); //This method will stoer the variables


  Parent p = Loader.getRoot();
  Stage stage = new Stage();
  stage.setTitle("View Rooms -"+ Global.currentGuestLoggedIn.getUserName()); //set title
  stage.setScene(new Scene(p));
  stage.show();//Open new window


}

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (events.isEmpty()){
      //No events make a default list
      Event createEvent = new Event("Royal Ball");
      listProperty.set(FXCollections.observableArrayList(events));

    }
    //listViewEvent.setItems(buttonCreate);
  }
}
