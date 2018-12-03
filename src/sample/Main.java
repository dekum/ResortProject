/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

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

    new ReadRooms();
    new ReadEmployee();

    new ReadEvents();
    Parent root = FXMLLoader.load(getClass().getResource("LoginMenu/LoginMenu.fxml"));
    //Setting Defaults

    Scene scene = new Scene(root);
    stage.setTitle("Ruby Resort: Login Page");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}