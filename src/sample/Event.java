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
  int numberOfGuests;
  Boolean didManagerCreate; //Check if Manager created sample.Event ornot
  Guest guestCreator; //If Guest created it, then the Guest object is stored here
  String Venue; //Maybe Make Enum
  //Add more Varaibles
  Date date;

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


