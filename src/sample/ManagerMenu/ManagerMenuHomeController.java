package sample.ManagerMenu;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Global;
import sample.GuestMenu.GuestRoomController;

public class ManagerMenuHomeController {
@FXML
Button bookRoomButton;

  @FXML
  public void handleBookRoom(ActionEvent event){
    Stage stageExit = (Stage) bookRoomButton.getScene().getWindow();
    stageExit.close(); //close current window
    GuestRoomController guestController = new GuestRoomController();


    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader(); //sample.GuestMenu.LoginMenuController
    Loader.setLocation(getClass().getResource("/sample/GuestMenu/GuestRoom.fxml")); //Call new window
    //Make sure / is at the beginning at the Url if you are leaving the folder
    try {
      Loader.setController(guestController);
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


}
