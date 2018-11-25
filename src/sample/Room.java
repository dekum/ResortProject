package sample;
//Great WEbsite https://examples.javacodegeeks.com/desktop-java/javafx-table-example/

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class Room {
  private StringProperty nameproperty;
  String name;
  String pictureUrl;
  int squareFt;
  boolean isAvailable;
  double price;
  Guest occupiedGuest;
  int daysStaying;

  public void removeGuest(){
    occupiedGuest = null;
    daysStaying = 0;
    isAvailable= true;

  }

  public StringProperty nameProperty(){
    return nameproperty;
  }

  public String getName2(){
    return nameproperty.get();
  }

  public int getDaysStaying() {
    return daysStaying;
  }

  public void setDaysStaying(int daysStaying) {
    this.daysStaying = daysStaying;
  }

  public Guest getOccupiedGuest() {
    return occupiedGuest;
  }

  public void setOccupiedGuest(Guest occupiedGuest) {
    this.occupiedGuest = occupiedGuest;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public int getSquareFt() {
    return squareFt;
  }

  public void setSquareFt(int squareFt) {
    this.squareFt = squareFt;
  }

  public boolean getAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Room(String name, boolean isAvailable, double price, String pictureUrl) {
    nameproperty = new SimpleStringProperty();
    this.name = name;
    this.isAvailable = isAvailable;
    this.price = price;
    this.pictureUrl = pictureUrl;
    nameproperty.set(name);
  }

  public Room(String name) {
    this.name = name;
  }

  public static class RoomCellFactory implements Callback<ListView<Room>, ListCell<Room>> {

    @Override
    public ListCell<Room> call(ListView<Room> listview) {
      return new RoomCell();
    }
  }
}

class RoomCell  extends ListCell<Room>
{
  @Override
  public void updateItem(Room item, boolean empty)
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
          item.getName() + ", ";
    }

    this.setText(name);
    setGraphic(null);
  }
}


