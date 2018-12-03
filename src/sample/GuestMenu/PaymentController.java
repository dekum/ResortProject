package sample.GuestMenu;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Global;
import sample.Global.WindowLocation;
import sample.WriteRooms;

/**
 * Screen shown when user clicks "book room" from GuestRoomController
 * Displays picked room info and check in check out date along with total cost
 * Credit Card info is purely cosmetic and is not used nor stored anywhere
 */
public class PaymentController {
  double price;

  @FXML
  private TextField ccInfoBox;
  @FXML
  private Label checkInLabel;

  @FXML
  private Label checkOutLabel;

  @FXML
  private Label roomInfoLabel;

  @FXML
  private Label roomPriceLabel;

  @FXML
  private ChoiceBox<Integer> expMonthBox;

  @FXML
  private ChoiceBox<Integer> expYearBox;

  @FXML
  private Button confirmBook;

  @FXML Button quitButton;

  /*
  Method called when window is opened
  Sets the drop down boxes for credit card info using integer List
  Then converted to ObservableList for display
  Numbers are purely arbitrary and can be changed
  Sets the labels for room info, price, and dates
   */
  @FXML
  void initialize(){
    List<Integer> expMonthNums = new ArrayList<>();
    for (int i = 1; i < 13; i++ ){
      expMonthNums.add(i);
    }
    ObservableList<Integer> expMonths = FXCollections.observableArrayList(expMonthNums);
    expMonthBox.setValue(1);
    expMonthBox.setItems(expMonths);

    List<Integer> expYearNums = new ArrayList<>();
    for (int i = 2018; i < 2025; i++){
      expYearNums.add(i);
    }
    ObservableList<Integer> expYear = FXCollections.observableArrayList(expYearNums);
    expYearBox.setValue(2018);
    expYearBox.setItems(expYear);

    checkInLabel.setText(Global.checkInDate.toString());
    checkOutLabel.setText(Global.checkOutDate.toString());

    Period period = Period.between ( Global.checkInDate,Global.checkOutDate );
    price = (period.getDays()  * Global.selectedRoom.getPrice());
    Global.selectedRoom.setTotalPrice(price);
    roomPriceLabel.setText("$" + price);

    roomInfoLabel.setText(Global.roomInfo);
  }

  /*
  Takes user back to guest menu
   */
  @FXML void handleQuit(ActionEvent event){

    Global.currentScene = confirmBook.getScene();
    Global.currentTitle = "Ruby Resort:Guest View";

    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
  }

  /*
  Sets current room and writes it to the room file
   */
  @FXML
  void handleConfirm(ActionEvent event) {
    Global.currentGuestLoggedIn.setRoomRented(Global.selectedRoom);
    Global.currentScene = confirmBook.getScene();
    Global.selectedRoom.setDayIn(Global.checkInDate);
    Global.selectedRoom.setDayOut(Global.checkOutDate);
    Global.selectedRoom.setOccupiedGuest(Global.currentGuestLoggedIn);
    new WriteRooms();
    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
  }

}
