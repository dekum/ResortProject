package sample.Unused.EventMenu;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;
import sample.Event;
import sample.Global;

/**
 * Create event
 * Select Wedding, Spa, Meeting, Other
 * Name of Person
 * Dates
 * How many people are coming
 * Special Accomendation
 * Contact Info (
 * Email Address
 *
 * Pick a Venue (Show Pictures)
 * DropDown Of Venue
 *
 * Buy
 *
 *
 *
 *
 *
 */

public class EventCreateController  implements Initializable {
  @FXML private DatePicker checkInDatePicker;
  @FXML private DatePicker checkOutDatePicker;
  @FXML private Button b1;
  @FXML
  TextField nameOfOrganization, numOfPeople, nameOfEvent, selectVendor, otherDetails;

  @FXML void b12(ActionEvent event){
    String nameOrg, nameEvent, numPeople, details, vendor;
    // creating strings that get the data from the text fields
    vendor = selectVendor.getText();
    details = otherDetails.getText();
    nameOrg = nameOfOrganization.getText();
    nameEvent = nameOfEvent.getText();
    numPeople = numOfPeople.getText();
//    nameOrg = "hi phil";
//    nameEvent = "im programming";
//    numPeople ="rr";

    // creates an object that uses the text fields data
    Event eventNew = new Event(nameOrg, nameEvent, numPeople, details, vendor);
    System.out
        .println(eventNew.getNumOfPeople() + '\n' + eventNew.getNameOfEvent() + '\n' +
            eventNew.getNameOfOrg() + '\n' +
            eventNew.getOtherDetails() + '\n' + eventNew.getSelectVendor());
    // Prints the create objects
    // Adds the events to the arraylist of events




  }


  @FXML void handleExit(ActionEvent event){
    Global.currentScene = b1.getScene();
   // new Global().openNewWindow(WindowLocation.EVENTMENUHOME);

  }
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("hERE");
//    checkInDatePicker = new DatePicker();
//    checkOutDatePicker = new DatePicker();
    checkInDatePicker.setValue(LocalDate.now());


    final Callback<DatePicker, DateCell> dayCellFactory =
        new Callback<DatePicker, DateCell>() {
          @Override
          public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
              @Override
              public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(

                    checkInDatePicker.getValue().plusDays(1))
                ) {
                  setDisable(true);
                  setStyle("-fx-background-color: #ffc0cb;");
                }
                long p = ChronoUnit.DAYS.between(
                    checkInDatePicker.getValue(), item
                );
                setTooltip(new Tooltip(
                    "The even will last : " + p + " days")
                );



              }
            };
          }
        };
    final Callback<DatePicker, DateCell> dayCellFactory2 =
        new Callback<DatePicker, DateCell>() {
          @Override
          public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
              @Override
              public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (checkOutDatePicker.getValue().isBefore(checkInDatePicker.getValue()) ){
                  System.out.println("...K");
                  //checkInDatePicker.setFocusTraversable(true);
                  checkOutDatePicker.requestFocus();
                  //Maybe set an error Boolean?


                }


              }
            };
          }
        };


    checkOutDatePicker.setDayCellFactory(dayCellFactory);
    checkInDatePicker.setDayCellFactory(dayCellFactory2);
    checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));

  }

//  private Stage stage;


//  public static void main(String[] args) {
//    Locale.setDefault(Locale.US);
//    launch(args);
//  }

 // @Override
//  public void start(Stage stage) {
////    this.stage = stage;
////    stage.setTitle("DatePickerSample ");
////    initUI();
////    stage.show();
//  }

  private void initUI() {
//    VBox vbox = new VBox(20);
//    vbox.setStyle("-fx-padding: 10;");
//    Scene scene = new Scene(vbox, 400, 400);
   // stage.setScene(scene);

  }
}
