package sample;

import java.util.Date;

/**
 * Event.java
 * Events class store informations of the Events of the Resort
 * Events can be created by the Owner or the guest
 * Guest created Events have to be Approved by the owner.
 * Eventt is an abstract Class, it's subclasses are what extend from it
 * THey include: Spa, Meeting, Wedding, other.
 *
 */

public  class Event {
  String name;
  int numberOfGuests;
  Boolean didManagerCreate; //Check if Manager created sample.Event ornot
  Guest guestCreator; //If Guest created it, then the Guest object is stored here
  String Venue; //Maybe Make Enum
  //Add more Varaibles
  Date date;

  public Event(String name) {
    this.name = name;
  }

  public Event() {
  }
}

class Spa extends  Event{

  public Spa(String name) {
    this.name = name;
  }
}

class Meeting extends Event{

  public Meeting(String name) {
    this.name = name;
  }
}

class Wedding extends Event{
  public Wedding(String name) {
    this.name = name;
  }
}

class otherEvent extends Event{
  public otherEvent(String name) {
    this.name = name;
  }
}