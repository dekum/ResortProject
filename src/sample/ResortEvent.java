package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResortEvent {
    private String name;
    private String date;
    private StringProperty nameproperty;
    private StringProperty dateproperty;

    public ResortEvent(String name, String date){
      this.name = name;
      this.date = date;
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
