package sample.GuestMenu;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;

/**
 * Screen shown when user clicks "payment history" from GuestRoomController
 * Shows dates, room info, and total price along with username
 */
public class GuestAccountWindowController implements Initializable {
  @FXML
  public Label labelRoomName;
  @FXML
  public Label labelPricePerDay;
  @FXML
  public Label labelTotalCost;
  @FXML
  public Label labelUserName;
  @FXML
  Label labelDaysStaying;
  @FXML
  AnchorPane anchorPanePayment;
  @FXML
  private ImageView imageViewRoom;

  /*
  Sends user back to guest menu
   */
  @FXML
  void handleExit(ActionEvent event){
    Global.currentScene = labelTotalCost.getScene();//
    Global.currentTitle="Ruby Resort: Guest View";
    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
    Global.selectedRoom= null;
  }

  /*
  Method called when window is opened
   */
  public void initialize(URL location, ResourceBundle resources) {
    Guest currentGuest = Global.currentGuestLoggedIn;
    if (Global.currentGuestLoggedIn.getRoomRented()!=null){
    //guest booked a room, show history
      anchorPanePayment.setVisible(true);
      labelRoomName.setText(currentGuest.getRoomRented().getName());
      labelPricePerDay.setText(String.valueOf(currentGuest.getRoomRented().getPrice()));
      labelTotalCost.setText(String.valueOf(currentGuest.getRoomRented().getTotalPrice()));
      labelDaysStaying.setText(currentGuest.getRoomRented().getDayIn() +" - "+ currentGuest.getRoomRented().getDayOut());
      Image image = new Image(currentGuest.getRoomRented().getPictureUrl());
      imageViewRoom.setImage(image);

    }
    labelUserName.setText(currentGuest.getUserName());
  }
}
