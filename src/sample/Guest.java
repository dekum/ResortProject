package sample;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class used for Guest info
 * Contains first and last name, DOB, and rented Room
 * Guest info read from textfile
 */

public class Guest extends User {
  private String firstName;
  private String lastName;
  private String dateOfBirth; //Format of MM/DD/YY, Actually might stick to Year for simplciity.
  private LocalDate DOB;
  private Room roomRented;

  public Guest() {
    super("","");
  }

  public LocalDate getDOB() {
    return DOB;
  }

  public void setDOB(LocalDate DOB) {
    this.DOB = DOB;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Room getRoomRented() {
    return roomRented;
  }

  public void setRoomRented(Room roomRented) {
    this.roomRented = roomRented;
  }

  String getFullName(){
    return firstName+lastName;
  }

  public Guest(String userName, String password) {

    super(userName, password);
  }

  public Guest(String userName, String password, String firstName, String lastName,
      String dateOfBirth) {
    super(userName, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }
  public Guest(String userName, String password, String firstName, String lastName,
      LocalDate dateOfBirth) {
    super(userName, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.DOB = dateOfBirth;
  }
}
