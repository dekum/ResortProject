/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ggrab, pPetit Reosrt Project Main.java This project will allow guests to book rooms, and
 * Managers to manage employees and rooms This main file opens the LoginMenuController
 */
public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    ArrayList<Room> rooms = new ArrayList<>();
    rooms.add(new Room("1 Queen Bed", true, 250, "sample/Pictures/1Queen.jpg"));
    rooms.add(new Room("2 Twin Beds", true, 200, "sample/Pictures/TwinBed.jpg"));
    rooms.add(new Room("Suite: 2 Queen Beds", true, 500, "sample/Pictures/2Queen.jpg"));
    rooms.add(new Room("Suite: 1 King Bed", true, 525, "sample/Pictures/SuiteKing.jpg"));
    Global.roomList = rooms;

    ArrayList<Employee2> emps = new ArrayList<>();
    emps.add(new Employee2("Nick","Fileccia",16.8, LocalDate.of(1994, Month.MARCH,23)));
    emps.add(new Employee2("Chris","Garcia",15.5, LocalDate.of(1994, Month.MAY,13)));
    emps.add(new Employee2("Ariel","Delgado",12.9, LocalDate.of(1993, Month.AUGUST,3)));
    emps.add(new Employee2("Josh","Hendry",19.9, LocalDate.of(1992, Month.JANUARY,11)));
    emps.add(new Employee2("Gabe","Thorn",10.4, LocalDate.of(1986, Month.FEBRUARY,23)));
    emps.add(new Employee2("Timmy","Turner",19.9, LocalDate.of(1977, Month.MARCH  ,1)));
    Global.empList = emps;

    ArrayList<ResortEvent> events = new ArrayList<>();
    events.add((new ResortEvent("ZombieCon", "Nov 29")));
    events.add((new ResortEvent("Crab Race", "Nov 31")));
    events.add((new ResortEvent("Karaoke", "Dec 2")));
    events.add((new ResortEvent("Boat Show", "Dec 4")));
    events.add((new ResortEvent("ArtWalk", "Dec 5")));
    events.add((new ResortEvent("Car Show", "Dec 9")));
    Global.eventList = events;


    Parent root = FXMLLoader.load(getClass().getResource("LoginMenu/LoginMenu.fxml"));
    //Setting Defaults

    Scene scene = new Scene(root);
    stage.setTitle("Login Page");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}