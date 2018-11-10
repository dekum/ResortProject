package sample;

import java.util.Date;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

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
  String eventName;
  int numberOfGuests;
  String vendor;
  Boolean didManagerCreate; //Check if Manager created sample.Event ornot
  Guest guestCreator; //If Guest created it, then the Guest object is stored here
  String Venue; //Maybe Make Enum
  //Add more Varaibles
  Date date;
  Boolean needsApproval; //If true then manager needs to approve or deny
  Boolean isApproved;
  Boolean isDenied;

  public void setName(String name) {
    this.name = name;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public int getNumberOfGuests() {
    return numberOfGuests;
  }

  public void setNumberOfGuests(int numberOfGuests) {
    this.numberOfGuests = numberOfGuests;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public Boolean getDidManagerCreate() {
    return didManagerCreate;
  }

  public void setDidManagerCreate(Boolean didManagerCreate) {
    this.didManagerCreate = didManagerCreate;
  }

  public Guest getGuestCreator() {
    return guestCreator;
  }

  public void setGuestCreator(Guest guestCreator) {
    this.guestCreator = guestCreator;
  }

  public String getVenue() {
    return Venue;
  }

  public void setVenue(String venue) {
    Venue = venue;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Boolean getNeedsApproval() {
    return needsApproval;
  }

  public void setNeedsApproval(Boolean needsApproval) {
    this.needsApproval = needsApproval;
  }

  public Boolean getApproved() {
    return isApproved;
  }

  public void setApproved(Boolean approved) {
    isApproved = approved;
  }

  public Boolean getDenied() {
    return isDenied;
  }

  public void setDenied(Boolean denied) {
    isDenied = denied;
  }

  public String getName() {
    return name;
  }

  public static class EventCellFactory implements Callback<ListView<Event>, ListCell<Event>> {

    @Override
    public ListCell<Event> call(ListView<Event> listview) {
      return new EventCell();
    }
  }

  public Event() {

  }

  public Event(String name) {
    this.name = name;
  }


}


class EventCell  extends ListCell<Event>
{
  @Override
  public void updateItem(Event item, boolean empty)
  {
    super.updateItem(item, empty);

    int index = this.getIndex();
    String name = null;

    // Format name
    if (item == null || empty)
    {
    }
    else
    {
      name = (index + 1) + ". " +
          item.getName() + " ";
    }

    this.setText(name);
    setGraphic(null);
  }
}


