package sample.EventMenu;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EventCreateController  implements Initializable {
  @FXML private DatePicker checkInDatePicker;
  @FXML private DatePicker checkOutDatePicker;


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
//    GridPane gridPane = new GridPane();
//    gridPane.setHgap(10);
//    gridPane.setVgap(10);
    Label checkInlabel = new Label("Check-In Date:");
    //gridPane.add(checkInlabel, 0, 0);
    //GridPane.setHalignment(checkInlabel, HPos.LEFT);
    //gridPane.add(checkInDatePicker, 0, 1);
    //Label checkOutlabel = new Label("Check-Out Date:");
    //gridPane.add(checkOutlabel, 0, 2);
    //GridPane.setHalignment(checkOutlabel, HPos.LEFT);
    //gridPane.add(checkOutDatePicker, 0, 3);
//    vbox.getChildren().add(gridPane);
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
