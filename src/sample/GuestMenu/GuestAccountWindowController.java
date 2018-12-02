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

public class GuestAccountWindowController implements Initializable {
  @FXML
  public Label labelRoomName;
  @FXML
  public Label labelPricePerDay;
  @FXML
  public Label labelTotalCost;
  @FXML
  public Label labelDOB;
  @FXML
  public Label labelFullName;
  @FXML
  public Label labelUserName;
  @FXML
  Label labelDaysStaying;
  @FXML
  AnchorPane anchorPanePayment;
  @FXML
  private ImageView imageViewRoom;


  @FXML
  void handleExit(ActionEvent event){
    Global.currentScene = labelTotalCost.getScene();//
    Global.currentTitle="Ruby Resort: Guest View";
    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
    Global.selectedRoom= null;


  }

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
    //labelFullName.setText(currentGuest.getFirstName()+" "+currentGuest.getLastName());
//    labelDOB.setText(currentGuest.getDateOfBirth());
    labelUserName.setText(currentGuest.getUserName());




  }
}
