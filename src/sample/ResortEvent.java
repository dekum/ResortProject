package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResortEvent {
    private String name;
    private String date;
    private StringProperty nameproperty;
    private StringProperty dateproperty;
    private Double price=0.0;
    private String eventDescription="";

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public ResortEvent(String name, String date){
      this.name = name;
      this.date = date;
      dateproperty = new SimpleStringProperty();
      dateproperty.set(date);
      nameproperty = new SimpleStringProperty();
      nameproperty.set(name);

    }
    public ResortEvent(){
      dateproperty = new SimpleStringProperty();

      nameproperty = new SimpleStringProperty();
    }

  public ResortEvent(String name, String date, Double price, String eventDescription) {
    this.name = name;
    this.date = date;
    this.price = price;
    this.eventDescription = eventDescription;
    dateproperty = new SimpleStringProperty();
    dateproperty.set(date);
    nameproperty = new SimpleStringProperty();
    nameproperty.set(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getNameproperty() {
    return nameproperty.get();
  }

  public StringProperty namepropertyProperty() {
    return nameproperty;
  }

  public void setNameproperty(String nameproperty) {
    this.nameproperty.set(nameproperty);
  }

  public String getDateproperty() {
    return dateproperty.get();
  }

  public StringProperty datepropertyProperty() {
    return dateproperty;
  }

  public void setDateproperty(String dateproperty) {
    this.dateproperty.set(dateproperty);
  }

  public StringProperty getNameProperty(){
      return nameproperty;
    }

    public StringProperty getDateProperty(){
      return dateproperty;
    }
}
