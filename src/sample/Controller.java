/**
 * Resort Project
 * Controller.java
 * 10/31/2018
 *
 * The purpose of this class is to be a abstract class where all other Controller classes will
 * extend from this class
 * The data we need to transfer from each class is stored here and every controller will share the same data.
 * Hopefully this method is simipleri to understand than what we were doing before with calling Controller's Constructors.
 */

package sample;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class  Controller{
  private ArrayList<String> usernameList = new ArrayList<>(); //ArrayList of username Fields
  private ArrayList<String> passwordList = new ArrayList<>();//Array:ist of password Field
  private ArrayList<Guest> guestList = new ArrayList<>();//Arraylist of Guests
  private List<Room> rooms = new ArrayList<>();//Arraylist of rooms that Manager/Guest Menu's use
 // private Guest currentGuestLoggedIn; //LoginMenuController keeps track of guest to send to GuestMenu
  private ObservableList<EmployeeOld> data= FXCollections.observableArrayList(); // Arraylist of Employees for MaanagerMenu
  private Manager admin;//Not used

  public ArrayList<String> getUsernameList() {
    return usernameList;
  }

  public void setUsernameList(ArrayList<String> usernameList) {
    this.usernameList = usernameList;
  }

  public ArrayList<String> getPasswordList() {
    return passwordList;
  }

  public void setPasswordList(ArrayList<String> passwordList) {
    this.passwordList = passwordList;
  }

  public ArrayList<Guest> getGuestList() {
    return guestList;
  }

  public void setGuestList(ArrayList<Guest> guestList) {
    this.guestList = guestList;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public ObservableList<EmployeeOld> getData() {
    return data;
  }

  public void setData(ObservableList<EmployeeOld> data) {
    this.data = data;
  }

  public Manager getAdmin() {
    return admin;
  }

  public void setAdmin(Manager admin) {
    this.admin = admin;
  }
}