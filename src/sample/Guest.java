package sample;

import java.util.ArrayList;

/**
 * Guest.java
 * Maybe it should be called GuestAccount.
 * Guest is a subclass of the Abstract class User.
 * Guest store the information a guest user inputs when they create a account.
 * Guests will be able to view Rooms, Book a Room, View Events, Book Event and if time allows be able to change Settings
 *
 */

public class Guest extends User {
  private String firstName;
  private String lastName;
  private String dateOfBirth; //Format of MM/DD/YY, Actually might stick to Year for simplciity.
  private ArrayList<Event> eventsCreated = new ArrayList<>();
  private int guestId;
  private static int guestIds; //This increments as new guest accounts are created
  private Room roomRented;

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

  public ArrayList<Event> getEventsCreated() {
    return eventsCreated;
  }

  public void setEventsCreated(ArrayList<Event> eventsCreated) {
    this.eventsCreated = eventsCreated;
  }

  public int getGuestId() {
    return guestId;
  }

  public void setGuestId(int guestId) {
    this.guestId = guestId;
  }

  public static int getGuestIds() {
    return guestIds;
  }

  public static void setGuestIds(int guestIds) {
    Guest.guestIds = guestIds;
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
    /**
     * Uses User class's constructor to store a username and password.
     */

    super(userName, password);
    guestId= guestIds;
    guestIds++;
  }

  public Guest(String userName, String password, String firstName, String lastName,
      String dateOfBirth) {
    super(userName, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    guestId= guestIds;
    guestIds++;
  }
}
