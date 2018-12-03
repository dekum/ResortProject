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
 * Main class to set scene and begin application
 * Calls Read classes to get text file information
 */
public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    new ReadRooms();
    new ReadEmployee();
    new ReadEvents();
    Parent root = FXMLLoader.load(getClass().getResource("LoginMenu/LoginMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setTitle("Ruby Resort: Login Page");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}