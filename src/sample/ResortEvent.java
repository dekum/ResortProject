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

    public StringProperty getNameProperty(){
      return nameproperty;
    }

    public StringProperty getDateProperty(){
      return dateproperty;
    }
}
