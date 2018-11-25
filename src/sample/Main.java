/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ggrab, pPetit
 * Reosrt Project Main.java
 * This project will allow guests to book rooms, and Managers to manage employees and rooms
 * This main file opens the LoginMenuController
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("1 Queen Bed", true, 250, "sample/Pictures/QueenBed.jpg"));
        rooms.add(new Room("2 Twin Beds", true, 200, "sample/Pictures/TwinBed.jpg"));
        rooms.add(new Room("Suite: 2 Queen Beds", true, 500, "sample/Pictures/SuiteQueen.jpg"));
        rooms.add(new Room("Suite: 1 King Bed", true, 525, "sample/Pictures/SuiteKing.jpg"));
        Global.roomList = rooms;
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