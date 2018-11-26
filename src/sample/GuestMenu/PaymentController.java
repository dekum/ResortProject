package sample.GuestMenu;

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
import sample.Controller;
import sample.Global;
import sample.Global.WindowLocation;

public class PaymentController extends Controller {

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

    double price = (Global.checkOutDate.getDayOfMonth() - Global.checkInDate.getDayOfMonth())
        * Global.selectedRoom.getPrice();
    roomPriceLabel.setText("$" + price);

    roomInfoLabel.setText(Global.roomInfo);
  }

  @FXML void handleQuit(ActionEvent event){

    Global.currentScene = confirmBook.getScene();

    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
  }

  @FXML
  void handleConfirm(ActionEvent event) {

    Global.currentScene = confirmBook.getScene();

    new Global().openNewWindow(WindowLocation.GUESTMENUHOME);
  }

}
